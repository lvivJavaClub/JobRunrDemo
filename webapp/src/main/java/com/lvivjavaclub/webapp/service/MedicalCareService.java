package com.lvivjavaclub.webapp.service;

import com.lvivjavaclub.webapp.repository.PatientsRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalCareService {

  private final VaccineDepartment vaccineDepartment;
  private final JobScheduler jobScheduler;
  private final PatientsRepository patientsRepository;
  private final EmailService emailService;

  public String vaccinatePersonNow(String userName) {
    JobId id = jobScheduler.enqueue(() -> vaccineDepartment.immediateVaccination(userName));
    return "Patient entry ID: %s".formatted(id.toString());
  }

  public String vaccinatePreExaminedPerson(String userName) {
    JobId id = jobScheduler.enqueue(() -> vaccineDepartment.doVaccinationAfterExamination(userName, JobContext.Null));

    return "After examination. Patient entry ID: %s".formatted(id);
  }

  public String schedulePersonVaccination(String userName, LocalTime localTime) {
    JobId id = jobScheduler.schedule(localTime.atDate(LocalDate.now()), () -> vaccineDepartment.immediateVaccination(userName));
    return "Scheduled today at %s. Patient entry ID: %s".formatted(localTime, id.toString());
  }

  public void notifyPatients() {
    jobScheduler.scheduleRecurrently("Every_minute", Cron.minutely(), () -> System.out.println("TAKE CARE!"));
    jobScheduler.scheduleRecurrently("Every_day", Cron.daily(), () -> System.out.println("TAKE CARE!"));
    jobScheduler.enqueue(patientsRepository.allPatients(), emailService::sendAnnouncement);
  }

  public void removeScheduledVaccination(String id) {
    jobScheduler.delete(id);
  }
}
