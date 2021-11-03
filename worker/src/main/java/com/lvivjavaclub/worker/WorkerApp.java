package com.lvivjavaclub.worker;

import com.lvivjavaclub.core.StorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(StorageConfig.class)
public class WorkerApp {

  public static void main(String[] args) {
    SpringApplication.run(WorkerApp.class, args);
  }
}
