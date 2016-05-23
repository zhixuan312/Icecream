package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SwapTaskPermission implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long swapTaskPermissionId;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Task task;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Employee owner;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Employee taker;
    private String bossStatus;
    private String takerStatus;
    
    public SwapTaskPermission() {
        task = new Task();
        owner = new Employee();
        taker = new Employee();
        bossStatus = "";
        takerStatus = "";
    }

    public Long getSwapTaskPermissionId() {
        return swapTaskPermissionId;
    }

    public void setSwapTaskPermissionId(Long swapTaskPermissionId) {
        this.swapTaskPermissionId = swapTaskPermissionId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public Employee getTaker() {
        return taker;
    }

    public void setTaker(Employee taker) {
        this.taker = taker;
    }

    public String getBossStatus() {
        return bossStatus;
    }

    public void setBossStatus(String bossStatus) {
        this.bossStatus = bossStatus;
    }

    public String getTakerStatus() {
        return takerStatus;
    }

    public void setTakerStatus(String takerStatus) {
        this.takerStatus = takerStatus;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (swapTaskPermissionId != null ? swapTaskPermissionId.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the swapTaskPermissionId fields are not set
        if (!(object instanceof SwapTaskPermission)) {
            return false;
        }
        SwapTaskPermission other = (SwapTaskPermission) object;
        if ((this.swapTaskPermissionId == null && other.swapTaskPermissionId != null) || (this.swapTaskPermissionId != null && !this.swapTaskPermissionId.equals(other.swapTaskPermissionId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "entity.SharedBoxPermission[ id=" + swapTaskPermissionId + " ]";
    }
    
}
