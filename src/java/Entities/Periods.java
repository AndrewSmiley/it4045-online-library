/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *POJO object model to utilize for Library inventory management with the database
 * @author pridemai
 */
/*@NamedQuery(name="getItemByTitle", query="select c from LibraryItem "+
        " c where c.title like :t "),
*/

//Named query to update the periods data should go here. 
@NamedQuery (name="updatePeriod", query="UPDATE Periods c set c.numberOfDays = :n, c.renewable = :r, c.lateFee=:f WHERE c.type=:t")

@Entity

public class Periods implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String type;
    private int numberOfDays;
    private double lateFee;
    private Boolean renewable;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periods)) {
            return false;
        }
        Periods other = (Periods) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Periods[ id=" + id + " ]";
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the numberOfDays
     */
    public int getNumberOfDays() {
        return numberOfDays;
    }

    /**
     * @param numberOfDays the numberOfDays to set
     */
    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    /**
     * @return the lateFee
     */
    public double getLateFee() {
        return lateFee;
    }

    /**
     * @param lateFee the lateFee to set
     */
    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
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
    public void setRenewable(Boolean renewable) {
        this.renewable = renewable;
    }
    
}
