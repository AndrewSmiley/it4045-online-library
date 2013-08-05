/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;

import Entities.LogArchive;
import Entities.Patron;
import Entities.Periods;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.resource.NotSupportedException;

/**
 *
 * @author pridemai
 */
@Stateless
public class PatronControlBean {
@PersistenceContext
private EntityManager manager;

@EJB
private PeriodControlBean periodControlBean;


/**
 *@param fname The first name of the patron
 * @param lname The last name of the patron
 * @param address The address of the patron
 * @param city  The city the patron resides in
 * @param stateName The state the patron resides in
 * @param zip The zip code of the patron's address
 * 
 */
public void addNewPatron(String fName, String lName, String address, String city, String stateName, String zip)
{
    Patron patron =  new Patron();
    patron.setfName(fName);
    patron.setlName(lName);
    patron.setAddress(address);
    patron.setCity(city);
    patron.setStateName(stateName);
    patron.setZip(zip);
    
    manager.persist(patron);
}


/**
 * Method to search for patron by first name
 * @param fname The first name of patron we wish to search for
 * @return List List The list of matching results 
 */
public List searchByFirstName(String fname)
{
    
    
    CriteriaBuilder criteriaBuilder =  manager.getCriteriaBuilder();
    CriteriaQuery<Patron> pQuery =  criteriaBuilder.createQuery(Patron.class);
    Root<Patron> patron = pQuery.from(Patron.class);
    pQuery.select(patron);
    //create a path object? 
    Path path = patron.get("fName");
    pQuery.where(criteriaBuilder.like(path, fname));
    return manager.createQuery(pQuery).getResultList();
    
    
}

/**
 * Method to search by Last name for a patron
 * @return List A list of patrons found
 */
public List searchByLastName(String lName)
{
    CriteriaBuilder criteriaBuilder =  manager.getCriteriaBuilder();
    CriteriaQuery<Patron> pQuery =  criteriaBuilder.createQuery(Patron.class);
    Root<Patron> patron = pQuery.from(Patron.class);
    pQuery.select(patron);
    //create a path object? 
    Path path = patron.get("lName");
    pQuery.where(criteriaBuilder.like(path, lName));
    return manager.createQuery(pQuery).getResultList();
    
   
}

/**
 * Method to get the activities of a patron
 * @param id The patron ID we wish to search for
 * @return A list of activities
 */
public List getPatronActivities(Long id)
{
    CriteriaBuilder criteriaBuilder =  manager.getCriteriaBuilder();
    CriteriaQuery<LogArchive> pQuery =  criteriaBuilder.createQuery(LogArchive.class);
    Root<LogArchive> archive = pQuery.from(LogArchive.class);
    pQuery.select(archive);
    //create a path object? 
    Path path = archive.get("id");
    pQuery.where(criteriaBuilder.equal(path, id));
    return manager.createQuery(pQuery).getResultList();
    
}


/**
 * Method to Search for patron by their unique patron ID
 * @param id the ID to search for patrons by
 */

public List searchByID(Long id)
{
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Patron> query = builder.createQuery(Patron.class);
    Root<Patron> pRoot = query.from(Patron.class);
    query.select(pRoot);
    Path path = pRoot.get("id");
    query.where(builder.equal(path, id));
    return manager.createQuery(query).getResultList();
}

/**
 * Method to get the activity report of the Patron 
 * @param id The patron id for which we want to get the activity report of
 * @return 
 */
public List getPatronActivityReport(Long id)
{
  
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<LogArchive> query = builder.createQuery(LogArchive.class);
    Root<LogArchive> lRoot = query.from(LogArchive.class);
    query.select(lRoot);
    Path path = lRoot.get("patronID");
    query.where(builder.equal(path, id));
    return manager.createQuery(query).getResultList();
}

/**
 * Method to add late fees to a a patron's account
 * @param fees 
 */
public void addLateFees(Long patronID, String type)
{
    /**
     * Query the DB and get the patron
     * so now we can add late fees to the patron's account. I.e. if 
     * they already have fees, we add the new fees on
     */
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Patron> query = builder.createQuery(Patron.class);
    Root<Patron> pRoot = query.from(Patron.class);
    query.select(pRoot);
    Path path = pRoot.get("id");
    query.where(builder.equal(path, patronID));
    Patron patron =  new Patron();
          patron =  manager.createQuery(query).getSingleResult();
    
    
    Periods period = null;
     if(type.equalsIgnoreCase("Book"))
    {
   period = periodControlBean.getBookPeriods();
    }else if(type.equalsIgnoreCase("Audio"))
    {
       period = periodControlBean.getAudioPeriods();
    }else if(type.equalsIgnoreCase("Video"))
    {
       period = periodControlBean.getVideoPeriods();
    }
     else
    {
        //we should never get to this point. Just create a blank Periods object
    period = new Periods();
    }
    
    
     
    double fees  = period.getLateFee();
   double totalFees = patron.getFees() + fees;
   
   manager.createNamedQuery("addFees").setParameter("f", totalFees).setParameter("id", patronID).executeUpdate();
    
    
    
    
    
}
}
