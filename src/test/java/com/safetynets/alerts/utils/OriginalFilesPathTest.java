package com.safetynets.alerts.utils;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.utils.OriginalInputFile;


public class OriginalFilesPathTest {
  
 
 FilesPath filesPath;
  
  @Test
  public void get_Original_File_Path_Test() {
    assertNotNull(OriginalInputFile.getOriginalInputFile());
    assertEquals(OriginalInputFile.getOriginalInputFile(), FilesPath.ORIGINAL_INPUT_FILE);
    
   
  }
  
  

}
