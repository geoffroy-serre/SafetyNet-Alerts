package com.safetynet.alerts;

import com.safetynet.alerts.controller.CreateWorkingFileController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(SafetyNetAlertsApplication.class)
class SafetyNetAlertsApplicationTest {

  @Test
  void main() {
    assertDoesNotThrow(()->SafetyNetAlertsApplication.main(new String[] {}));
  }
}