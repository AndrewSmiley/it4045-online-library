/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WebTier;

import EBJ.PatronControlBean;
import Entities.Patron;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Smiley
 */
@ManagedBean
@SessionScoped
public class PatronManagerBean {

    private String fName;
    private String lName;
    private String address;
    private String city;
    private String stateName;
    private String zip;
    private List<Patron> patronList;
    
    @EJB
    private PatronControlBean patronControl;
           
            /**
     * Creates a new instance of PatronManagerBean
     */
    public PatronManagerBean() {
        
        
    }

    /**
     * Method to connect the create a new patron and add them to the database from the jsf page
     */
        public void createNewPatron()
        {
            getPatronControl().addNewPatron(getfName(), getlName(), getCity(), getAddress(), getStateName(), getZip());
            
        }
    
       /**
        * Method to search for a patron by their first name
        */
        public void patronFirstNameSearch()
        {
         setPatronList(getPatronControl().searchByFirstName(this.fName));
        }
        
        
        /**
     * @return the fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * @param fName the fName to set
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @return the lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * @param lName the lName to set
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the patronControl
     */
    public PatronControlBean getPatronControl() {
        return patronControl;
    }

    /**
     * @param patronControl the patronControl to set
     */
    public void setPatronControl(PatronControlBean patronControl) {
        this.patronControl = patronControl;
    }

    /**
     * @return the patronList
     */
    public List<Patron> getPatronList() {
        return patronList;
    }

    /**
     * @param patronList the patronList to set
     */
    public void setPatronList(List<Patron> patronList) {
        this.patronList = patronList;
    }
}
