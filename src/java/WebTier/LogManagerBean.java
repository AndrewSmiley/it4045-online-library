/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WebTier;

import EBJ.ArchiverControlBean;
import Entities.LogArchive;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Utilities.DateUtil;

import java.util.List;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author pridemai
 */
@ManagedBean
@ViewScoped
/**
 * Managed session bean to handle log retrieval 
 */
public class LogManagerBean {

    /**
     * Creates a new instance of LogManagerBean
     */
    public LogManagerBean() {
    
        
    }
        
        @EJB
       private ArchiverControlBean logBeanObject;
       private DateUtil date;
       private List<LogArchive> logs;
       
       
       /**
        * Method to grab the logs back from the database
        * 
        */
       
       public List<LogArchive> retrieveLogs()
       {
           
         date = new DateUtil();  
         
        logs = logBeanObject.retrieveLogs();
           return logs;
       }

    /**
     * @return the logBeanObject
     */
    public ArchiverControlBean getLogBeanObject() {
        return logBeanObject;
    }

    /**
     * @param logBeanObject the logBeanObject to set
     */
    public void setLogBeanObject(ArchiverControlBean logBeanObject) {
        this.logBeanObject = logBeanObject;
    }

    /**
     * @return the date
     */
    public DateUtil getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(DateUtil date) {
        this.date = date;
    }

    /**
     * @return the logs
     */
    public List<LogArchive> getLogs() {
      if(logs == null)
      {
          
          logs = logBeanObject.retrieveLogs();
      }
        return logs;
    }

    /**
     * @param logs the logs to set
     */
    public void setLogs(List<LogArchive> logs) {
        this.logs = logs;
    }



    
       
        
        
        
        
        
}
