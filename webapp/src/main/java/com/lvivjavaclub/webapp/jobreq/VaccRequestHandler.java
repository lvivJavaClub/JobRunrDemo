package com.lvivjavaclub.webapp.jobreq;

import com.lvivjavaclub.webapp.service.VaccineDepartment;
import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VaccRequestHandler implements JobRequestHandler<VacJobRequest> {

  private final VaccineDepartment vaccineDepartment;

  @Override
  public void run(VacJobRequest jobRequest) {
    switch (jobRequest.getType()) {
      case "now" -> vaccineDepartment.immediateVaccination(jobRequest.getName());
      case "with-exam" -> vaccineDepartment.doVaccinationAfterExamination(jobRequest.getName(), jobContext());
      default -> System.out.println("Nothing to do with patient: " + jobRequest.getName());
    }
  }
}
