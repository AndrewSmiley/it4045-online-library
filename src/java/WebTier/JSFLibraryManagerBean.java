/*
 * This bean manages the JSF Server forms and pages
 */
package WebTier;

import EBJ.ArchiverControlBean;
import EBJ.OnlineLibraryControlBean;
import Entities.LibraryItem;
import Utilities.DateUtil;
import Utilities.FileWriterUtil;
import Utilities.LogFormatter;
import java.io.IOException;
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
    private LogFormatter logFormatter = new LogFormatter();
    private DateUtil dateUtil = new DateUtil();
    private Long patronID;
    private Boolean displaySearch;
    //Default error value here
    private String errorMessage = "";
    private FileWriterUtil fileWriter = new FileWriterUtil(); 
    @EJB
    private OnlineLibraryControlBean control;
    @EJB
    private ArchiverControlBean archiveControl;

    /**
     * Creates a new instance of JSFLibraryManagerBean
     */
    public JSFLibraryManagerBean() {
        this.displaySearch = false;
    }

    /*
     *Method to create a library item 
     *    private Long id;
     private String title;
     private String author;
     private String format;
     private String publisher;
     private String yearPublished;
     private String status;
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
        try {
         
            setResultItem(getControl().findItem(searchTitle));
            setDisplaySearch(true);
            this.errorMessage = "";
        } catch (Exception ex) {
            setDisplaySearch(false);
            this.setErrorMessage("Error: Item Not Found, Please Check Your Search and Try Again");


        }
    }

   
    /**
     * Method to update the status of an item from "checked-out" to "available;
     */
    public void checkIn() {

        getControl().ejbCheckIn(this.resultItem.getId());
       
        getArchiveControl().logNewActivity(logFormatter.logItemCheckedIn(this.resultItem.getTitle()), dateUtil.getTodaysDate(), getPatronID());

    }

    
  
    /**
     * Method to check-out an item
     */
    public void checkOut() {
        getControl().ejbCheckOut(this.resultItem.getId(), getPatronID());
        getArchiveControl().logNewActivity(logFormatter.logItemCheckedOut(this.resultItem.getTitle()), dateUtil.getTodaysDate(), getPatronID());
    }

    /*
     * Method to pull any trailing whitespace from the 
     * title which has been sumitted 
     */
    public void removeWhiteSpace() {
        this.searchTitle = this.searchTitle.trim();
    }

    
    /*
     * Method to display error message if exception is thrown in the search portion of the page. 
     */
    public boolean displaySearchError() {
        if (this.getErrorMessage().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    
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
}


