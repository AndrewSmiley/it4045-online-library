/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Smiley
 */
public class FileWriterUtil {
    
    
    
    
    
    public void logItemAdded(String title, String format, String status)
    {
        
        
        
		try {
                        DateUtil dateUtil = new DateUtil();
			String content = dateUtil.getCurrentTime()+" Item \""+title+"\" added; Format "+format+"; Status set to "+status;
                        
                        
                        /*
                         * Update this to  the absolute location
                         * on your own local machine
                         */
			File file = new File("/Users/Smiley/dailylog_"+dateUtil.getTodaysDate()+".txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
                        bw.newLine();
			bw.close();
 
			
 
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    



public void logStatusChanged(String title, String format, String status)
{
try {
                        DateUtil dateUtil = new DateUtil();
			String content = dateUtil.getCurrentTime()+" Item \""+title+"\"; Format "+format+"; Status updated to "+status;
                        
                        
                        /*
                         * Update this to  the absolute location
                         * on your own local machine
                         */
			File file = new File("/Users/Smiley/dailylog_"+dateUtil.getTodaysDate()+".txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
                        bw.newLine();
			bw.close();
 
			
 
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
}
