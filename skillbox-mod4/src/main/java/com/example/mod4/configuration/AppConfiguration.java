package com.example.mod4.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Value("${app.currentUserId}")
    public long currentUserId;

}
