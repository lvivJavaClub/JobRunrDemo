package com.lvivjavaclub.webapp.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class VaccineDepartment {

  private static int vaccineLimit = 10;
  // TODO logger

  public void immediateVaccination(String userName) {
    try {
      Thread.sleep(1000);
      System.out.println("%s is vaccinated at %s".formatted(userName, LocalDateTime.now()));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public void doVaccinationAfterExamination(String userName) {

    try {
      final int numberOfVisits = 5;
      for (int visitIndex = 0; visitIndex < numberOfVisits; visitIndex++) {
        Thread.sleep(1000);
        System.out.println("The doctor #%s allowed %s to do vaccine ".formatted(visitIndex, userName));
      }
      System.out.println("%s is vaccinated at %s".formatted(userName, LocalDateTime.now()));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
