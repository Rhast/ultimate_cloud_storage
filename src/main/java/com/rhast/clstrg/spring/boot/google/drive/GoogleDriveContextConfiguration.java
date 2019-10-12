package com.rhast.clstrg.spring.boot.google.drive;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lermontov-w
 */
@Configuration
public class GoogleDriveContextConfiguration {

    @Bean
    public GoogleDriveAuthentication googleDriveAuthentication(
            HttpTransport httpTransport,
            JsonFactory jsonFactory
    ) {
        return new GoogleDriveAuthentication(httpTransport, jsonFactory);
    }

}
