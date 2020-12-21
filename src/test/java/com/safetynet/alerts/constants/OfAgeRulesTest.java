package com.safetynet.alerts.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class OfAgeRulesTest{



@Test
  void ofAgeNotEmpty(){
  OfAgeRules ofAgeRules = new OfAgeRules();
  assertNotNull(ofAgeRules.toString());
  assertNotNull(OfAgeRules.OF_AGE_FR);
  assertNotNull(OfAgeRules.OF_AGE_US);
}
}