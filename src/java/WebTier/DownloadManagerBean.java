/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WebTier;

import Utilities.DateUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Smiley
 */
@ManagedBean
@RequestScoped


public class DownloadManagerBean {
    private String filePath;
       
       
       /**
     * Creates a new instance of DownloadManagerBean
     */
    public DownloadManagerBean() {
        
    }
        private static final int DEFAULT_BUFFER_SIZE = 10240;  
      
    
       
      
      /*
       * Method to download the daily log to your local machine
       */
       public void downLoad() throws IOException {  
           
           DateUtil dateUtil = new DateUtil();
           
           /**********************
            * Update this portion to 
            * allow the server to know where the dailylog is located
            * point it to the absolute location of the file
            * ********************
            */
           this.setFilePath("/Users/Smiley/dailylog_"+dateUtil.getTodaysDate()+".txt");
           
            FacesContext context = FacesContext.getCurrentInstance();  
            HttpServletResponse response = (HttpServletResponse) context  
                      .getExternalContext().getResponse();  
            File file = new File(getFilePath());  
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

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
 }  
    
