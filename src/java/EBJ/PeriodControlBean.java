/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EBJ;

import Entities.LogArchive;
import Entities.Periods;
import javax.ejb.Stateless;
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
 * Method to update the amount of time allowed for check-out and the late fee for a particular item
 * @param type The type of item to update the information of
 * @param days  The number of days an item format is able to be checked-out for
 * @param lateFee The lateFee for a particular item format
 */
public void updatePeriod(String type, int days, double lateFee)
{
       Periods period = new Periods();
       period.setType(type);
       period.setNumberOfDays(days);
       period.setLateFee(lateFee);
       
       entityManager.persist(period);
    
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
}
