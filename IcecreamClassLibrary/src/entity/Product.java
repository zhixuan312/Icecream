package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer inventory;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private ProductCategory productCategory;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Ordering ordering;
    
    public Product() {
        name = "";
        description = "";
        price = new BigDecimal(0);
        inventory = new Integer(0);
        productCategory = new ProductCategory();
        ordering = new Ordering();
    }
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getInventory() {
        return inventory;
    }
    
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
    
    public ProductCategory getProductCategory() {
        return productCategory;
    }
    
    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Ordering getOrder() {
        return ordering;
    }

    public void setOrder(Ordering order) {
        this.ordering = order;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the productId fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "entity.Product[ id=" + productId + " ]";
    }
    
}
