/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;


import Entities.LibraryItem;
import java.util.GregorianCalendar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andrew
 */
@Stateless

public class OnlineLibraryControlBean {
    
    //get access to the persistence context
    @PersistenceContext
    
    /*          a reference to the collection of entities that the container maintains
     * give us a link to the context that the entity manages. Through this we can run different queries, run searchs & finds
     */

    private EntityManager entityManager;
    
    /*
     * @param title the title of item
     * @param author the author of the item
     * @param format the format of the item
     * @param publisher the publisher of the item
     * @param yearPublished the year the item was published
     * @param status the status of the item 
     *
     * Method to add item the database using entity object and the persistence 
     * framwork
     */
    public void createLibraryItem(String title, String author,  String publisher, 
            String yearPublished,String Format , String status)
    {
     //create library item entity
        LibraryItem item = new LibraryItem();
        
        //set attributes of the entity object
        item.setTitle(title);
        item.setAuthor(author);
        item.setFormat(Format);
        item.setPublisher(publisher);
        item.setYearPublished(yearPublished);
        item.setStatus(status);
                
        
       
        
        //Persist the Entity object to the DB
        entityManager.persist(item);
        
      
        
    }
    
 
        /*
         * Method to view the status of the item
         * @param title the title of the book to search for
         * @return LibraryItem
         */
        public LibraryItem findItem(String title)
        {
            LibraryItem temp = (LibraryItem) entityManager.createNamedQuery("getItemByTitle").setParameter("t", title).getSingleResult();
            return temp;
        }
    
        
        
        /**
     * EJB Method to check an item out
     * @param id the id of the item to check-in
     * @param patronID the id of the patron we wish to check items in to
     */
    public void ejbCheckIn(Long id)        {
            int checkedIn = entityManager.createNamedQuery("checkIn").setParameter("id", id).setParameter("p", null).setParameter("d", null).executeUpdate();
                
        }
        
        
        /*
         *@param id the id of the search result item we are looking for  
         * @param patronID the ID of the patron who we wish to check items out for
         *Method to called the named query to update
         * status to 'checked-out'
         */
        public void ejbCheckOut(Long id, Long patronID)
        {
            GregorianCalendar calendar = new GregorianCalendar();
           
          
            int checkedOut = entityManager.createNamedQuery("checkOut").setParameter("id", id).setParameter("p", patronID).setParameter("d", null).executeUpdate();
        }
        

}
