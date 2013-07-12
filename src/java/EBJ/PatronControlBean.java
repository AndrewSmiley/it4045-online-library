/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;

import Entities.Patron;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
