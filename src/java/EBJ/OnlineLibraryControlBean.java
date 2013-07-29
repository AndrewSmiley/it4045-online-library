/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;

import Entities.LibraryItem;
import Entities.Periods;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andrew
 */
@Stateless
public class OnlineLibraryControlBean {

    @EJB
    private PeriodControlBean periodControlBean;
    @EJB
    private PatronControlBean patronControlBean;
    //get access to the persistence context
    @PersistenceContext
    /*          a reference to the collection of entities that the container maintains
     * give us a link to the context that the entity manages. Through this we can run different queries, run searchs & finds
     */
    private EntityManager entityManager;

    /**
     * @param title the title of item
     * @param author the author of the item
     * @param format the format of the item
     * @param publisher the publisher of the item
     * @param yearPublished the year the item was published
     * @param status the status of the item
     *
     * Method to add item the database using entity object and the persistence
     * framework, additionally reloads the current page the user is on. 
     */
    public void createLibraryItem(String title, String author, String publisher,
            String yearPublished, String Format, String status) {
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

        //Reload the page
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/admin/AddItems.xhtml");
    }

    /**
     * Method to view the status of the item
     *
     * @param title the title of the book to search for
     * @return LibraryItem
     */
    public LibraryItem findItem(String title) {
        LibraryItem temp = (LibraryItem) entityManager.createNamedQuery("getItemByTitle").setParameter("t", title).getSingleResult();
        return temp;
    }

    /**
     * EJB Method to check an item out and reload the page for the user
     *
     * @param id the id of the item to check-in
     * @param dueDate The due date of the item we wish to check in
     * @param patronID the id of the patron we wish to check items in to
     * @param type The format of the item being checked in
     */
    public void ejbCheckIn(Long id, GregorianCalendar dueDate, Long patronID, String type) {
        GregorianCalendar calendar = new GregorianCalendar();

        //determine if today's date is after the due date

        if (dueDate.before(calendar)) {
            patronControlBean.addLateFees(patronID, type);
        }

        int checkedIn = entityManager.createNamedQuery("checkIn").setParameter("id", id).setParameter("p", null).setParameter("d", null).executeUpdate();
        
        
        //redirect back to the current page
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/search.xhtml");

    }

    /**
     * 
     *Method to called the named query to update the status of an item to checked out 
     * and reload the page for the user
     *
     * @param id the id of the search result item we are looking for
     * @param patronID the ID of the patron who we wish to check items out for
     * @param type the type of item we wish to check-out, used to get the
     * checkout period and overdue fees. status to 'checked-out'
     */
    public void ejbCheckOut(Long id, Long patronID, String type) {
        GregorianCalendar calendar = new GregorianCalendar();
        Periods periods = new Periods();

        //Note: Cannot use a String in a switch statement in Java 6. Only in Java 7 
        if (type.equalsIgnoreCase("Book")) {

            periods = periodControlBean.getBookPeriods();
        } else if (type.equalsIgnoreCase("Audio")) {
            periods = periodControlBean.getAudioPeriods();
        } else if (type.equalsIgnoreCase("Video")) {
            periods = periodControlBean.getVideoPeriods();
        } else {
            periods = new Periods();
        }

        calendar.add(Calendar.DATE, periods.getNumberOfDays());
        int checkedOut = entityManager.createNamedQuery("checkOut").setParameter("id", id).setParameter("p", patronID).setParameter("d", calendar).executeUpdate();
        
        //redirect back to the current page
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/search.xhtml");
    }
}
