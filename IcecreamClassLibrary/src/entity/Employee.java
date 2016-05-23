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

@Entity
public class Employee implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isAdmin;
    private String accountStatus;
    private String verificationCode;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
    private List<Task> tasks;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<SwapTaskPermission> ownerPermissions;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taker")
    private List<SwapTaskPermission> takerPermissions;
    
    public Employee() {
        email = "";
        password = "";
        firstName = "";
        lastName = "";
        accountStatus = "";
        createDate = new Date();
        tasks = new ArrayList<>();
        verificationCode = "";
        isAdmin = false;
        ownerPermissions = new ArrayList<>();
        takerPermissions = new ArrayList<>();
    }
    
    public Long getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public String getAccountStatus() {
        return accountStatus;
    }
    
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    
    public String getVerificationCode() {
        return verificationCode;
    }
    
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
    
    public Boolean getIsAdmin() {
        return isAdmin;
    }
    
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public List<Task> getTasks() {
        return tasks;
    }
    
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<SwapTaskPermission> getOwnerPermissions() {
        return ownerPermissions;
    }

    public void setOwnerPermissions(List<SwapTaskPermission> ownerPermissions) {
        this.ownerPermissions = ownerPermissions;
    }

    public List<SwapTaskPermission> getTakerPermissions() {
        return takerPermissions;
    }

    public void setTakerPermissions(List<SwapTaskPermission> takerPermissions) {
        this.takerPermissions = takerPermissions;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeId != null ? employeeId.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the employeeId fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeeId == null && other.employeeId != null) || (this.employeeId != null && !this.employeeId.equals(other.employeeId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "entity.Employee[ id=" + employeeId + " ]";
    }
    
}
