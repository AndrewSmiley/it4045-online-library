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
    

public void updatePeriod(String type, int days, double lateFee)
{
       Periods period = new Periods();
       period.setType(type);
       period.setNumberOfDays(days);
       period.setLateFee(lateFee);
       
       entityManager.persist(period);
    
}

public Periods getBookPeriods()
{
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Periods> query = builder.createQuery(Periods.class);
        Root<Periods> root = query.from(Periods.class);
        query.select(root);
        query.where(builder.equal(root.get("type"), "Book"));
        return entityManager.createQuery(query).getSingleResult();
}

}
