package com.lvivjavaclub.core;

import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.nosql.mongo.MongoDBStorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = StorageConfig.class)
public class StorageConfig {

  @Bean
  public StorageProvider dataSource(JobMapper jobMapper) {
    var storageProvider = new MongoDBStorageProvider("localhost", 27017);
    storageProvider.setJobMapper(jobMapper);
    return storageProvider;
  }
}
