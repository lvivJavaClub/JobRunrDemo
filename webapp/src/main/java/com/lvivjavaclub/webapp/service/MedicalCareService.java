package com.lvivjavaclub.webapp.service;

import java.time.LocalTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class MedicalCareService {

  private final VaccineDepartment vaccineDepartment;

  public MedicalCareService(VaccineDepartment vaccineDepartment) {
    this.vaccineDepartment = vaccineDepartment;
  }

  public String vaccinatePersonNow(String userName) {
    vaccineDepartment.immediateVaccination(userName);
    return "Patient entry ID: %s".formatted(UUID.randomUUID().toString());
  }

  public String vaccinatePreExaminedPerson(String userName) {
    vaccineDepartment.doVaccinationAfterExamination(userName);
    return "After examination. Patient entry ID: %s".formatted(UUID.randomUUID().toString());
  }

  public String schedulePersonVaccination(String userName, LocalTime localTime) {
    return "Scheduled today at %s. Patient entry ID: %s".formatted(localTime, UUID.randomUUID().toString());
  }
}
