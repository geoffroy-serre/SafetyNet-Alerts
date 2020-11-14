package com.safetynets.alerts.utils;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.utils.OriginalInputFile;

@WebMvcTest(value=OriginalInputFile.class)
public class OriginalFilesPath {
  
  @Autowired
  FilesPath filesPath;
  
  @Test
  public void get_Original_File_Path_Test() {
    assertNotNull(OriginalInputFile.getOriginalInputFile());
    assertEquals(OriginalInputFile.getOriginalInputFile(), filesPath.ORIGINAL_INPUT_FILE);
    
   
  }
  
  

}
