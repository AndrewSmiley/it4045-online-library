/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;

import Entities.LogArchive;
import Entities.Patron;
import java.util.List;
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
 * Stub method to pull method 
 */
public void  searchByLastName()
{
   
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
}
