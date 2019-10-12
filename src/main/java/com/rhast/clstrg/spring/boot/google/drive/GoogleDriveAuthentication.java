package com.rhast.clstrg.spring.boot.google.drive;

import java.util.stream.Collectors;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;

/**
 * @author lermontov-w
 */
public class GoogleDriveAuthentication {

    private final HttpTransport httpTransport;
    private final JsonFactory jsonFactory;

    public GoogleDriveAuthentication(HttpTransport httpTransport, JsonFactory jsonFactory) {
        this.httpTransport = httpTransport;
        this.jsonFactory = jsonFactory;
    }

    public Drive authenticate(Credential credential) {
        return new Drive.Builder(httpTransport, jsonFactory, credential).build();
    }


}
