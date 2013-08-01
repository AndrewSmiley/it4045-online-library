/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;

import Entities.LogArchive;
import Entities.Periods;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author pridemai
 */
@Stateless
public class PeriodControlBean {
    
@PersistenceContext
private EntityManager entityManager;
    

/**
 * Method to create a new period reference for an item type
 * @param type The type of item to update the information of
 * @param days  The number of days an item format is able to be checked-out for
 * @param lateFee The lateFee for a particular item format
 * @param renewable Whether the item is renewable or not
 */
public void createPeriod(String type, int days, double lateFee, Boolean renewable)
{
       Periods period = new Periods();
       period.setType(type);
       period.setNumberOfDays(days);
       period.setLateFee(lateFee);
       period.setRenewable(renewable);
       entityManager.persist(period);
          //redirect back to the current page
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/admin/periodManagement.xhtml");

    
}
 /**
 * Method to update the period reference for a particular item type
 * @param type The type of item to update the information of
 * @param days  The number of days an item format is able to be checked-out for
 * @param lateFee The lateFee for a particular item format
 * @param renewable Whether the item is renewable or not
 */
public void updatePeriod(String type, int days, double lateFees, Boolean renewable)   
{
    entityManager.createNamedQuery("updatePeriod").setParameter("f", lateFees).setParameter("n", days).setParameter("r", renewable).setParameter("t", type).executeUpdate();
       //redirect back to the current page
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/admin/periodManagement.xhtml");

}


/**
 * Method to get the fees and check-out period for Books
 * @return Periods The format information
 */
public Periods getBookPeriods()
{
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Periods> query = builder.createQuery(Periods.class);
        Root<Periods> root = query.from(Periods.class);
        query.select(root);
        query.where(builder.equal(root.get("type"), "Book"));
        return entityManager.createQuery(query).getSingleResult();
}

/**
 * Method to get the fees and check-out period for Audio CDs
 * @return Periods The format information
 */
public Periods getAudioPeriods()
{
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Periods> query = builder.createQuery(Periods.class);
        Root<Periods> root = query.from(Periods.class);
        query.select(root);
        query.where(builder.equal(root.get("type"), "Audio"));
        return entityManager.createQuery(query).getSingleResult();
    
}

/**
 * Method to get the fees and check-out period for Videos
 * @return Periods The format information
 */
public Periods getVideoPeriods()
{
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Periods> query = builder.createQuery(Periods.class);
        Root<Periods> root = query.from(Periods.class);
        query.select(root);
        query.where(builder.equal(root.get("type"), "Video"));
        return entityManager.createQuery(query).getSingleResult();

}

public Boolean isRenewable(String type)
{
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Periods> query = builder.createQuery(Periods.class);
        Root<Periods> root = query.from(Periods.class);
        query.select(root);
        query.where(builder.equal(root.get("type"), type));
        Periods periodType = entityManager.createQuery(query).getSingleResult();
        return periodType.getRenewable();
}
}
