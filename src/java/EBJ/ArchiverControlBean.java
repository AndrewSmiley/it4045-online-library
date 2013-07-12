/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;

import Entities.LogArchive;
import Utilities.DateUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Smiley
 */
@Stateless
public class ArchiverControlBean {

    @PersistenceContext
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private EntityManager archiveEntityManager;
    
    /*
     *     private Long id;
    private String content;
    private String entryDate;

     */
    
    /*
     * Method to log a new activity to the Database of logs. 
     * @param content- the content of the log being pushed to the database
     * @param entryDate - The date in which the 
     */
    public void logNewActivity(String content, String entryDate)
    {
            LogArchive archive = new LogArchive();
            archive.setContent(content);
            archive.setEntryDate(entryDate);
            archiveEntityManager.persist(archive);
    }
    
    /**
     * 
     * @param date The date of which we wish to search for logs. Default is today, format is YYY-mm-dd
     * @return A list of the results. 
     */
    
    public ArrayList retrieveLogs(String date)
    {
        DateUtil util = new DateUtil();
        String findLogsQueryStr = "select * from APP.LOGARCHIVE Where ENTRYDATE  = '"+util.getTodaysDate()+"' ORDER BY ID";
         List       logResults = null ;
       // ArrayList logResults = new ArrayList();
  
     logResults.addAll(archiveEntityManager.createQuery(findLogsQueryStr).getResultList());
     //   System.out.println(logResults.);
     for (int i = 0; i < logResults.size(); i++)
     {
         System.out.println(logResults.get(i).getClass());
         
     }
        return (ArrayList) logResults;
      //  return logResults;
        
       // archiveEntityManager.createQuery().
        
        
    }
    
    
}
