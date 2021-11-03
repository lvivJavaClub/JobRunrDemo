package com.lvivjavaclub.webapp.controller;

import com.lvivjavaclub.webapp.service.MedicalCareService;
import java.time.Instant;
import java.time.LocalTime;
import org.jobrunr.scheduling.BackgroundJob;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccineController {

  private final MedicalCareService medicalCareService;

  public VaccineController(MedicalCareService medicalCareService) {
    this.medicalCareService = medicalCareService;
  }

  @GetMapping("/vaccinate-person-now")
  public String vaccinatePersonNow(@RequestParam String userName) {
    BackgroundJob.<MedicalCareService>schedule(Instant.now(), s -> s.vaccinatePersonNow(userName));
    return medicalCareService.vaccinatePersonNow(userName);
  }

  @GetMapping("/vaccinate-person")
  public String scheduleVaccination(@RequestParam String userName) {
    return medicalCareService.vaccinatePreExaminedPerson(userName);
  }

  @GetMapping("/schedule-vaccination")
  public String schedulePersonVaccination(@RequestParam String userName, @RequestParam LocalTime time) {

    return medicalCareService.schedulePersonVaccination(userName, time);
  }
}
