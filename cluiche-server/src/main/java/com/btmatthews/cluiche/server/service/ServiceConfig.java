package com.btmatthews.cluiche.server.service;

import com.mongodb.DB;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 24/08/12
 * Time: 01:30
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class ServiceConfig {

    private static final String DATABASE_HOSTNAME = "com.btmatthews.cluiche.server.database.hostname";

    private static final String DATABASE_PORT = "com.btmatthews.cluiche.server.database.port";

    private static final String DEFAULT_DATABASE_HOSTNAME = "localhost";

    private static final int DEFAULT_DATABASE_PORT = 27017;

    @Autowired
    private Environment environment;

   @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        final String hostname = environment.getProperty(DATABASE_HOSTNAME, String.class, DEFAULT_DATABASE_HOSTNAME);
        final Integer port = environment.getProperty(DATABASE_PORT, Integer.class,
                Integer.valueOf(DEFAULT_DATABASE_PORT));
        return new SimpleMongoDbFactory(new Mongo(hostname, port), "cluiche");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }}
