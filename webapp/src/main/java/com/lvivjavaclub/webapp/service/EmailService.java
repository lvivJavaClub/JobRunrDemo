package com.lvivjavaclub.webapp.service;

import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

  public void sendAnnouncement(String userEmail) {
    try {
      TimeUnit.SECONDS.sleep(3);
      System.out.println("Mail sent to the user " + userEmail);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }
}
