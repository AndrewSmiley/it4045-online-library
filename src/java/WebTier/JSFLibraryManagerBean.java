/*
 * This bean manages the JSF Server forms and pages
 */

package WebTier;

import EBJ.OnlineLibraryControlBean;
import Entities.LibraryItem;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Andrew
 */
@ManagedBean
@SessionScoped
public class JSFLibraryManagerBean {
    
    //create attributes
    private String title;
    private String author;
    private String publicationYear;
    private String format;
    private String searchTitle;
    private LibraryItem resultItem;
    
   
    @EJB
    OnlineLibraryControlBean control;

    
    
    /**
     * Creates a new instance of JSFLibraryManagerBean
     */
    public JSFLibraryManagerBean() {
        
}
    
        /*
         *Method to create a library item 
         */
        public void createLibraryItem()
        {
            control.createLibraryItem(title, author, format, author, author, title);
        }
        
        
        /*
         * Method to find item
         */
        public void findItem()
        {
            setResultItem(control.findItem(title));
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
}
