package com.ecommerce.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing // createdAt and updatedAt will work
public class MongoConfig {}
