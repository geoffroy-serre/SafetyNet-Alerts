package com.safetynet.alerts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@WebMvcTest(SafetyNetAlertsApplication.class)
class SafetyNetAlertsApplicationTest {

  @Test
  void main() {
    assertDoesNotThrow(() -> SafetyNetAlertsApplication.main(new String[]{}));
  }
}