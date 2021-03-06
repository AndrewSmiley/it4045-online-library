/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WebTier;

import EBJ.PatronControlBean;
import Entities.LogArchive;
import Entities.Patron;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Managed bean to handle login related calls
 * @author Smiley
 */
@ManagedBean
@ViewScoped
public class PatronManagerBean {

    private Long   id;
    private String fName;
    private String lName;
    private String address;
    private String city;
    private String stateName;
    private String zip;
    private List<Patron> patronList;
    private List<LogArchive> patronActivities;
    private Boolean viewReport;
   
    private Boolean searchLastName;
    private Boolean searchFirstName;
    private Boolean searchID; 
    private String  searchAction;
            
    
    @EJB
    private PatronControlBean patronControl;
           
            /**
     * Creates a new instance of PatronManagerBean
     */
    public PatronManagerBean() {
        viewReport = false;
       
        
    }

    /**
     * Method to connect the create a new patron and add them to the database from the jsf page
     */
        public void createNewPatron()
        {
            try{
            getPatronControl().addNewPatron(getfName(), getlName(), getCity(), getAddress(), getStateName(), getZip());
            redirectPatronCreated();
            }catch(Exception ex)
            {
              
            }
        }
    
       /**
        * Method to search for a patron by their first name
        */
        public void patronFirstNameSearch()
        {
         setPatronList(getPatronControl().searchByFirstName(getfName()));
         
         if(getPatronList().isEmpty())
         {
           redirectPatronNotFound();
         }else
         {
             this.viewReport = true;
             
         }
         
        }
        
        /**Method to search for a patron by last name
         * 
         */
        public void patronLastNameSearch()
        {
            setPatronList(getPatronControl().searchByLastName(getlName()));
            if(getPatronList().isEmpty())
         {
            redirectPatronNotFound();
         }else
         {
             this.viewReport = true;
          
         }
        }
 
        /**
         * Method to search for patron using their unique ID
         */
        public void patronIDSearch()
        {
         setPatronList(getPatronControl().searchByID(getId()));
         if(getPatronList().isEmpty())
         {
            redirectPatronNotFound();
         }else
         {
             this.viewReport = true;
             
         }
         
         }
        
        public void getPatronActivityReport(Long id)
        {
            setPatronActivities(getPatronControl().getPatronActivityReport(id));
        }
        
        /**
         * Method to display the patron information. 
         */
        public void displayReport()
        {
            setPatronActivities(getPatronControl().getPatronActivityReport(getId()));
        
           
        }
        
        
        /**
         * Method to determine how the patrons will be searched. 
         */
        public void searchChoice()
        {
            if(this.searchAction.equalsIgnoreCase("LastName"))
                    {
                     setSearchFirstName(false);
                     setSearchLastName(true);
                     setSearchID(false);
                     
                    }else if(this.searchAction.equalsIgnoreCase("FirstName"))
                            {
                     setSearchFirstName(true);
                     setSearchLastName(false);
                     setSearchID(false); 
                            }
                    else if(this.searchAction.equalsIgnoreCase("ID"))
                    {
                     setSearchFirstName(false);
                     setSearchLastName(false);
                     setSearchID(true);
                    }else
                    {
                        
                    }
                            
        }
        
        /**
         * Method to redirect a user back to the create a patron page
         */
        
        public void redirectCreatePatron()
        {
              FacesContext context = FacesContext.getCurrentInstance();
                context.getApplication().getNavigationHandler().handleNavigation(context, null, "/admin/signup.xhtml");
         
        }
        /**
         * Method to redirect a user back to the patron management page
         */
        public void redirectPatronManagement()
        {
              FacesContext context = FacesContext.getCurrentInstance();
            context.getApplication().getNavigationHandler().handleNavigation(context, null, "/patronsearch.xhtml");
         
        }
        
        
        /**
         * Method to redirect user to the patron not found error page. 
         * 
         */
        public static void redirectPatronNotFound()
        {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getApplication().getNavigationHandler().handleNavigation(context, null, "/error/patron_not_found_error.xhtml");
         
        }
        
        
        public static void redirectPatronCreated()
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getApplication().getNavigationHandler().handleNavigation(context, null, "/misc/patron_created.xhtml");
         
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

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the viewReport
     */
    public Boolean getViewReport() {
        return viewReport;
    }

    /**
     * @param viewReport the viewReport to set
     */
    public void setViewReport(Boolean viewReport) {
        this.viewReport = viewReport;
    }

    /**
     * @return the patronActivities
     */
    public List<LogArchive> getPatronActivities() {
        return patronActivities;
    }

    /**
     * @param patronActivities the patronActivities to set
     */
    public void setPatronActivities(List<LogArchive> patronActivities) {
        this.patronActivities = patronActivities;
    }
    
    /**
     * @return the searchLastName
     */
    public Boolean getSearchLastName() {
        return searchLastName;
    }

    /**
     * @param searchLastName the searchLastName to set
     */
    public void setSearchLastName(Boolean searchLastName) {
        this.searchLastName = searchLastName;
    }

    /**
     * @return the searchFirstName
     */
    public Boolean getSearchFirstName() {
        return searchFirstName;
    }

    /**
     * @param searchFirstName the searchFirstName to set
     */
    public void setSearchFirstName(Boolean searchFirstName) {
        this.searchFirstName = searchFirstName;
    }

    /**
     * @return the searchID
     */
    public Boolean getSearchID() {
        return searchID;
    }

    /**
     * @param searchID the searchID to set
     */
    public void setSearchID(Boolean searchID) {
        this.searchID = searchID;
    }

    /**
     * @return the searchAction
     */
    public String getSearchAction() {
        return searchAction;
    }

    /**
     * @param searchAction the searchAction to set
     */
    public void setSearchAction(String searchAction) {
        this.searchAction = searchAction;
    }
}
