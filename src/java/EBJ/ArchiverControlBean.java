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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;


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
     * Method to get the logs from the DB
     * @param date The date of which we wish to search for logs. Default is today, format is YYY-mm-dd
     * @return A list of the results. 
     */
    
    public List retrieveLogs()
    {
       DateUtil util = new DateUtil();

        
        
        CriteriaBuilder builder = archiveEntityManager.getCriteriaBuilder();
        CriteriaQuery<LogArchive> query = builder.createQuery(LogArchive.class);
        Root<LogArchive> root = query.from(LogArchive.class);
        query.select(root);
        query.where(builder.equal(root.get("entryDate"), util.getTodaysDate()));
        return archiveEntityManager.createQuery(query).getResultList();
        
                
        
        
        
    }
    
    
}
