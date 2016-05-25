/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Task implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
    private Employee employee;
    private String duration;
    private String day;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task")
    private List<SwapTaskPermission> swapTaskPermissions;
    
    public Task(){
        employee = new Employee();
        duration = "";
        startDateTime = new Date();
        endDateTime = new Date();
        swapTaskPermissions = new ArrayList<>();
        day = "";
    }
    
    public Long getTaskId() {
        return taskId;
    }
    
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public Date getStartDateTime() {
        return startDateTime;
    }
    
    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }
    
    public Date getEndDateTime() {
        return endDateTime;
    }
    
    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public List<SwapTaskPermission> getSwapTaskPermissions() {
        return swapTaskPermissions;
    }

    public void setSwapTaskPermissions(List<SwapTaskPermission> swapTaskPermissions) {
        this.swapTaskPermissions = swapTaskPermissions;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the taskId fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "entity.Task[ id=" + taskId + " ]";
    }
    
}
