/*
 * This bean manages the JSF Server forms and pages
 */
package WebTier;

import EBJ.ArchiverControlBean;
import EBJ.OnlineLibraryControlBean;
import EBJ.PatronControlBean;
import EBJ.PeriodControlBean;
import Entities.LibraryItem;
import Utilities.DateUtil;
import Utilities.FileWriterUtil;
import Utilities.LogFormatter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andrew
 */
@ManagedBean
@ViewScoped
public class JSFLibraryManagerBean {

    //create attributes
    private String title;
    private String author;
    private String publisher;
    private String publicationYear;
    private String format;
    private String status;
    private String searchTitle;
    private LibraryItem resultItem;
    private List<LibraryItem> resultItems;
    private LogFormatter logFormatter = new LogFormatter();
    private DateUtil dateUtil = new DateUtil();
    private Long patronID;
    private Boolean displaySearch;
    //Default error value here
    private String errorMessage = "";
    private Boolean renewable;
    private Boolean multipleItems;
    private FileWriterUtil fileWriter = new FileWriterUtil(); 
    @EJB
    private OnlineLibraryControlBean control;
    @EJB
    private ArchiverControlBean archiveControl;
    @EJB
    private PatronControlBean patronControlBean;
    @EJB
    private PeriodControlBean periodControlBean;
    
    /**
     * Creates a new instance of JSFLibraryManagerBean
     */
    public JSFLibraryManagerBean() {
        this.displaySearch = false;
    }

    /**
     *Method to create a library item 
     *   
     */
    public void createLibraryItem() {
        getControl().createLibraryItem(getTitle(), getAuthor(), getPublisher(), getPublicationYear(), getFormat(), getStatus());
        
        getArchiveControl().logNewActivity(logFormatter.logItemCreated(getTitle(), getFormat(),getStatus()), dateUtil.getTodaysDate(), getPatronID());
        
    }

    /**
     * Method to find item
     */
    public void findItem() {
        //Trim the whitespace off of the String prior to executing the search
        this.removeWhiteSpace();
       
         
            setResultItems(getControl().findItem(searchTitle));
            setDisplaySearch(true);
           // setRenewable(this.resultItem.getFormat());
            this.errorMessage = "";        
            if(resultItems.isEmpty())
            {
            setDisplaySearch(false);
            this.setErrorMessage("Error: Item Not Found, Please Check Your Search and Try Again");
            }

        
    }

   
    /**
     * Method to update the status of an item from "checked-out" to "available;
     */
    public void checkIn(Long id,GregorianCalendar duedate, String format) {

            
       getControl().ejbCheckIn(id,duedate, getPatronID(),  format);
       
        getArchiveControl().logNewActivity(logFormatter.logItemCheckedIn(this.resultItem.getTitle()), dateUtil.getTodaysDate(), getPatronID());
        
    }

    
  
    /**
     * Method to check-out an item
     */
    public void checkOut(Long id, String title, String format) {
        getControl().ejbCheckOut(id, getPatronID(), format);
        getArchiveControl().logNewActivity(logFormatter.logItemCheckedOut(title), dateUtil.getTodaysDate(), getPatronID());
    }

    /**
     * Method to pull any trailing whitespace from the 
     * title which has been submitted 
     */
    public void removeWhiteSpace() {
        this.searchTitle = this.searchTitle.trim();
    }

    
    /**
     * Method to display error message if exception is thrown in the search portion of the page. 
     */
    public boolean displaySearchError() {
        if (this.getErrorMessage().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Method to toggle the search error message
     * @return true if an error has occurred, false otherwise
     */
    public boolean toggleErrorMessage()
    {
        if(this.getErrorMessage().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
            
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the publicationYear
     */
    public String getPublicationYear() {
        return publicationYear;
    }

    /**
     * @param publicationYear the publicationYear to set
     */
    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return the searchTitle
     */
    public String getSearchTitle() {
        return searchTitle;
    }

    /**
     * @param searchTitle the searchTitle to set
     */
    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    /**
     * @return the resultItem
     */
    public LibraryItem getResultItem() {
        return resultItem;
    }

    /**
     * @param resultItem the resultItem to set
     */
    public void setResultItem(LibraryItem resultItem) {
        this.resultItem = resultItem;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the control
     */
    public OnlineLibraryControlBean getControl() {
        return control;
    }

    /**
     * @param control the control to set
     */
    public void setControl(OnlineLibraryControlBean control) {
        this.control = control;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the archiveControl
     */
    public ArchiverControlBean getArchiveControl() {
        return archiveControl;
    }

    /**
     * @param archiveControl the archiveControl to set
     */
    public void setArchiveControl(ArchiverControlBean archiveControl) {
        this.archiveControl = archiveControl;
    }

    /**
     * @return the patronID
     */
    public Long getPatronID() {
        return patronID;
    }

    /**
     * @param patronID the patronID to set
     */
    public void setPatronID(Long patronID) {
        this.patronID = patronID;
    }

    /**
     * @return the displaySearch
     */
    public Boolean getDisplaySearch() {
        return displaySearch;
    }

    /**
     * @param displaySearch the displaySearch to set
     */
    public void setDisplaySearch(Boolean displaySearch) {
        this.displaySearch = displaySearch;
    }

    /**
     * @return the renewable
     */
    public Boolean getRenewable() {
        return renewable;
    }

    /**
     * @param renewable the renewable to set
     */
    public void setRenewable(String type) {
        this.renewable = periodControlBean.isRenewable(type);
    }

    /**
     * @return the resultItems
     */
    public List<LibraryItem> getResultItems() {
        return resultItems;
    }

    /**
     * @param resultItems the resultItems to set
     */
    public void setResultItems(List<LibraryItem> resultItems) {
        this.resultItems = resultItems;
    }

    /**
     * @return the multipleItems
     */
    public Boolean getMultipleItems() {
        return multipleItems;
    }

    /**
     * @param multipleItems the multipleItems to set
     */
    public void setMultipleItems(Boolean multipleItems) {
        this.multipleItems = multipleItems;
    }
}


