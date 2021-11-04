package com.lvivjavaclub.webapp.controller;

import com.lvivjavaclub.webapp.service.MedicalCareService;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VaccineController {

  private final MedicalCareService medicalCareService;

  @GetMapping("/vaccinate-person-now")
  public String vaccinatePersonNow(@RequestParam String userName) {
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

  @GetMapping("/newsletter")
  public String newsletter() {
    medicalCareService.notifyPatients();
    return "DONE!";
  }
}
