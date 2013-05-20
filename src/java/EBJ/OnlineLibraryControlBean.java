/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;


import Entities.LibraryItem;
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
    public void createLibraryItem(String title, String author, String Format , String publisher, 
            String yearPublished, String status)
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
            LibraryItem temp = (LibraryItem) entityManager.createQuery("getItemByTitle").setParameter("t", title).getSingleResult();
            return temp;
        }
    
    


}
