package com.safetynet.alerts.utils;



import com.safetynet.alerts.constants.FilesPath;


public class WorkingFileOuput {
  
  private static String workingInputFile = FilesPath.WORKING_INPUT_FILE;
  
  public static String getOriginalInputFile() {
    return workingInputFile;
  }

}
