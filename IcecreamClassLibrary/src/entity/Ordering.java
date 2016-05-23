package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author XUAN
 */
@Entity
public class Ordering implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderingId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ordering")
    private List<Product> products;
    
    public Ordering() {
        createDateTime = new Date();
        products = new ArrayList<>();
    }

    public Long getOrderingId() {
        return orderingId;
    }

    public void setOrderingId(Long orderingId) {
        this.orderingId = orderingId;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
    
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderingId != null ? orderingId.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the orderingId fields are not set
        if (!(object instanceof Ordering)) {
            return false;
        }
        Ordering other = (Ordering) object;
        if ((this.orderingId == null && other.orderingId != null) || (this.orderingId != null && !this.orderingId.equals(other.orderingId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "entity.ProductCategory[ productCategoryId=" + orderingId + " ]";
    }
    
}
