/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO object model to utilize for Library inventory management with the database
 * @author Andrew
 */
@Entity
@NamedQueries({
    
    
@NamedQuery(name="getItemByTitle", query="select c from LibraryItem "+
        " c where c.title like :t "),

@NamedQuery(name="checkIn", query="update LibraryItem set status = 'Available', patronID=:p, dueDate=:d where id = :id"),
        
@NamedQuery(name="checkOut", query="update LibraryItem set status = 'Checked-Out', patronID=:p, dueDate=:d where id = :id")


})


public class LibraryItem implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    //Create attributes 
    private Long id;
    private String title;
    private String author;
    private String format;
    private String publisher;
    private String yearPublished;
    private String status;
    
    @Temporal(TemporalType.TIMESTAMP)
    private GregorianCalendar dueDate;
    private Long patronID;
    
    
    
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
        if (!(object instanceof LibraryItem)) {
            return false;
        }
        LibraryItem other = (LibraryItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.LibraryItem[ id=" + id + " ]";
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
     * @return the yearPublished
     */
    public String getYearPublished() {
        return yearPublished;
    }

    /**
     * @param yearPublished the yearPublished to set
     */
    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
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
     * @return the dueDate
     */
    public GregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(GregorianCalendar dueDate) {
        this.dueDate = dueDate;
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
    
}
