/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;

import Entities.Patron;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

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
    /*
     *  CriteriaBuilder builder = archiveEntityManager.getCriteriaBuilder();
        CriteriaQuery<LogArchive> query = builder.createQuery(LogArchive.class);
        Root<LogArchive> root = query.from(LogArchive.class);
        query.select(root);
        query.where(builder.equal(root.get("entryDate"), util.getTodaysDate()));
        return archiveEntityManager.createQuery(query).getResultList();
        
     */
    
    CriteriaBuilder criteriaBuilder =  manager.getCriteriaBuilder();
    CriteriaQuery<Patron> pQuery =  criteriaBuilder.createQuery(Patron.class);
    Root<Patron> patron = pQuery.from(Patron.class);
    pQuery.select(patron);
    //create a path object? 
    Path path = patron.get("fName");
    pQuery.where(criteriaBuilder.like(path, fname));
    return manager.createQuery(pQuery).getResultList();
    
    
}
}
