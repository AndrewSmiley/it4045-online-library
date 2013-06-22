/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;

import Entities.LogArchive;
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
    
    
}
