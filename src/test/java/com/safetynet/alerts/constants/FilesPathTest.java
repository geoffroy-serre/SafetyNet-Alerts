package com.safetynet.alerts.constants;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;



import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(value = FilesPath.class)
public class FilesPathTest {
  
  
  @Test
  public void OriginalFilePathConstantNotNullTest() {
    assertNotNull(FilesPath.ORIGINAL_INPUT_FILE);
    
  }

}
