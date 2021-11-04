package com.lvivjavaclub.webapp.jobreq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobrunr.jobs.lambdas.JobRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacJobRequest implements JobRequest {

  private String name;
  private String type;

  @Override
  public Class<VaccRequestHandler> getJobRequestHandler() {
    return VaccRequestHandler.class;
  }
}