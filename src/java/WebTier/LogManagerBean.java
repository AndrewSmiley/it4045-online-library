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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pridemai
 */
@ManagedBean
@SessionScoped
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
       private List<LogArchive> logsArray; 
       
       
       /**
        * Method to grab the logs back from the database
        * 
        */
       
       public void retrieveLogs()
       {
           logsArray = new ArrayList<LogArchive>();
           date = new DateUtil();
           //logsArray = new ArrayList();
          logsArray.addAll(logBeanObject.retrieveLogs(date.getTodaysDate()));
          
           
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
     * @return the logsArrayList
     *
    public ArrayList getLogsArrayList() {
        return logsArrayList;
    }

    /**
     * @param logsArrayList the logsArrayList to set
     *
    public void setLogsArrayList(ArrayList logsArrayList) {
        this.logsArrayList = logsArrayList;
    }
       **/
       
        
        
        
        
        
}
