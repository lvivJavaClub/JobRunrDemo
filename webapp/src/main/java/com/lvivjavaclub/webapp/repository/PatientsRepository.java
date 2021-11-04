package com.lvivjavaclub.webapp.repository;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class PatientsRepository {

  public Stream<String> allPatients() {
    return IntStream.range(0, 20).mapToObj("user%d@gmail.com"::formatted);
  }
}
