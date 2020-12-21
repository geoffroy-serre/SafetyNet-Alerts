package com.safetynet.alerts.constants;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(FilesPath.class)
class FilesPathTest {
  @Test
  void constantsNotNulls() {
    FilesPath filesPath = new FilesPath();
    assertNotNull(filesPath.toString());


  }

}