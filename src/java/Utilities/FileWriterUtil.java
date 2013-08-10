/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *@deprecated 
 * POJO utility class to create a local log file of activities
 * @author Smiley
 */
public class FileWriterUtil {
    
    private static final int DEFAULT_BUFFER_SIZE = 10240;  
    
    
    /**
     * Method to log an item being added to the daily log
     * @param title the title of the item added
     * @param format the format of the item
     * @param status the status of the item being added
     */
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
    


/**
     * Method to log the the changes status of item(s)
     * @param title the title of the item 
     * @param format the format of the item
     * @param status the status of the item being
     */
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
/**
 * Method to allow a user to download a log file
 * @throws IOException 
 */
public void createNewFileDownload() throws IOException
{
    DateUtil dateUtil = new DateUtil();
           
           /**********************
            * Update this portion to 
            * allow the server to know where the dailylog is located
            * point it to the absolute location of the file
            * ********************
            */
           File file = new File(System.getProperty("user.dir")+"log.txt");  
           
            FacesContext context = FacesContext.getCurrentInstance();  
            HttpServletResponse response = (HttpServletResponse) context  
                      .getExternalContext().getResponse();  
            
            if (!file.exists()) {  
                 response.sendError(HttpServletResponse.SC_NOT_FOUND);  
                 return;  
            }  
            response.reset();  
            response.setBufferSize(DEFAULT_BUFFER_SIZE);  
            response.setContentType("application/octet-stream");  
            response.setHeader("Content-Length", String.valueOf(file.length()));  
            response.setHeader("Content-Disposition", "attachment;filename=\""  
                      + file.getName() + "\"");  
            BufferedInputStream input = null;  
            BufferedOutputStream output = null;  
            try {  
                 input = new BufferedInputStream(new FileInputStream(file),  
                           DEFAULT_BUFFER_SIZE);  
                 output = new BufferedOutputStream(response.getOutputStream(),  
                           DEFAULT_BUFFER_SIZE);  
                 byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];  
                 int length;  
                 while ((length = input.read(buffer)) > 0) {  
                      output.write(buffer, 0, length);  
                 }  
            } finally {  
                 input.close();  
                 output.close();  
            }  
            context.responseComplete();  
      }  



}
