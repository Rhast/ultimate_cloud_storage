package com.rhast.clstrg.spring.boot.google.auth;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lermontov-w
 */
@Configuration
public class GoogleAuthContextConfiguration {

    @Bean
    public GoogleClientSecrets googleClientSecrets(JsonFactory jsonFactory) throws IOException {
        return GoogleClientSecrets.load(
                jsonFactory,
                new InputStreamReader(ClassLoader.getSystemResourceAsStream("client_secrets.json"))
        );
    }

    @Bean
    public DataStoreFactory dataStoreFactory() throws IOException {
        return new FileDataStoreFactory(new File("/home/lermontov-w/.google/credentials"));
    }

    @Bean
    public AuthorizationCodeFlow authorizationCodeFlow(
            HttpTransport httpTransport,
            JsonFactory jsonFactory,
            GoogleClientSecrets clientSecrets,
            DataStoreFactory dataStoreFactory
    ) throws IOException {
        List<String> scopes = List.of(DriveScopes.DRIVE);
        return new GoogleAuthorizationCodeFlow.Builder(
                httpTransport,
                jsonFactory,
                clientSecrets,
                scopes
        )
                .setDataStoreFactory(dataStoreFactory)
                .build();
    }
}
