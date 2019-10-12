package com.rhast.clstrg.spring.boot.controllers.google.auth;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.drive.model.File;
import com.rhast.clstrg.spring.boot.controllers.test.TestController;
import com.rhast.clstrg.spring.boot.google.drive.GoogleDriveAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author lermontov-w
 */
@RestController
@RequestMapping("/google-auth")
public class GoogleAuthController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    private static final String ME = "_first_test_user_";

    @Autowired
    private GoogleDriveAuthentication googleDriveAuthentication;
    @Autowired
    private AuthorizationCodeFlow authorizationCodeFlow;

    @GetMapping()
    public RedirectView googleAuth(
            @RequestParam(defaultValue = ME) String user,
            HttpServletResponse response
    ) throws IOException {
        response.addCookie(new Cookie("user", user));
        Credential credential = authorizationCodeFlow.loadCredential(user);
        if (credential != null) {
            return new RedirectView("google-auth/get-token", true);
        } else {
            AuthorizationCodeRequestUrl authorizationUrl = authorizationCodeFlow.newAuthorizationUrl();
            authorizationUrl.setRedirectUri(new URL("http", "127.0.0.1", 8080, "/google-auth/accept-code").toString());
            return new RedirectView(authorizationUrl.build());
        }
    }

    @GetMapping("/accept-code")
    public RedirectView acceptCode(
            @CookieValue(value = "user") String user,
            String code
    ) throws IOException {
        AuthorizationCodeTokenRequest tokenRequest = authorizationCodeFlow.newTokenRequest(code);
        tokenRequest.setRedirectUri(new URL("http", "127.0.0.1", 8080, "/google-auth/accept-code").toString());
        authorizationCodeFlow.createAndStoreCredential(tokenRequest.execute(), user);
        return new RedirectView("get-token", true);
    }

    @GetMapping("get-token")
    public List<String> getToken(
            @CookieValue(value = "user") String user
    ) throws IOException {
        Credential credential = authorizationCodeFlow.loadCredential(user);
        return googleDriveAuthentication.authenticate(credential).files().list().execute()
                .getItems().stream().map(File::getTitle).collect(Collectors.toList());
    }
}
