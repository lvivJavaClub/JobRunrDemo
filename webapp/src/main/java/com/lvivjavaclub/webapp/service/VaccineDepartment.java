package com.lvivjavaclub.webapp.service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import org.jobrunr.jobs.filters.JobFilter;
import org.springframework.stereotype.Service;

@Service
public class VaccineDepartment implements JobFilter {

  private static final AtomicInteger vaccineLimit = new AtomicInteger(15);

  public void immediateVaccination(String userName) {
    checkAvailability();
    try {
      Thread.sleep(1000);
      System.out.println("%s is vaccinated at %s".formatted(userName, LocalDateTime.now()));
      vaccineLimit.getAndDecrement();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void checkAvailability() {
    int counter = vaccineLimit.get();
    if (counter <= 0){
      throw new IllegalStateException("Sorry, no vaccines now...");
    }
    System.out.println("Still have %s vaccines".formatted(counter));
  }

  public void doVaccinationAfterExamination(String userName) {
    checkAvailability();

    final int numberOfVisits = 15;
    try {
      for (int visitIndex = 1; visitIndex <= numberOfVisits; visitIndex++) {
        Thread.sleep(1500);
        System.out.println("The doctor #%s allowed %s to do vaccine ".formatted(visitIndex, userName));
      }
      System.out.println("%s is vaccinated at %s".formatted(userName, LocalDateTime.now()));
      vaccineLimit.getAndDecrement();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
