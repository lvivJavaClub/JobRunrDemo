package com.lvivjavaclub.webapp.service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.jobs.context.JobDashboardProgressBar;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.jobrunr.jobs.filters.JobFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VaccineDepartment implements JobFilter {

  private static final AtomicInteger vaccineLimit = new AtomicInteger(10);
  private final Logger logger = new JobRunrDashboardLogger(LoggerFactory.getLogger(VaccineDepartment.class));

  @Job(name = "This job Vaccinate person right now!", retries = 2)
  public void immediateVaccination(String userName) {
    checkAvailability();
    try {
      Thread.sleep(1000);
      logger.info("%s is vaccinated at %s".formatted(userName, LocalDateTime.now()));
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

  @Job(name = "This job Vaccinate person only after pre-exam!")
  public void doVaccinationAfterExamination(String userName, JobContext jobContext) {
    checkAvailability();

    final int numberOfVisits = 50;
    JobDashboardProgressBar progressBar = jobContext.progressBar(numberOfVisits);
    try {
      for (int visitIndex = 1; visitIndex <= numberOfVisits; visitIndex++) {
        Thread.sleep(1500);
        logger.info("The doctor #%s allowed %s to do vaccine ".formatted(visitIndex, userName));
        progressBar.increaseByOne();
      }
      logger.info("%s is vaccinated at %s".formatted(userName, LocalDateTime.now()));
      vaccineLimit.getAndDecrement();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
