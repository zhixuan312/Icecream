/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ManagedBean;

import TaskManagement.TaskManagementRemote;
import entity.Employee;
import entity.SwapTaskPermission;
import entity.Task;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author XUAN
 */
@Named(value = "requestManagedBean")
@ViewScoped
public class RequestManagedBean implements Serializable{
    
    @Inject
    private SignUpAndLoginManagedBean signUpAndLoginManagedBean;
    @EJB
    private TaskManagementRemote taskManagementRemote;
    
    private Employee employee;
    private SwapTaskPermission selectedswapTaskPermission;
    private List<SwapTaskPermission> swapTaskPermissions;
    
    public RequestManagedBean() {
        employee = new Employee();
        selectedswapTaskPermission = new SwapTaskPermission();
        swapTaskPermissions = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        
        try {
            if (ec.getSessionMap().get("login") == null) {
                ec.redirect("index.xhtml?faces-redirect=true");
            } else if (ec.getSessionMap().get("login").equals(false)) {
                ec.redirect("index.xhtml?faces-redirect=true");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        employee = signUpAndLoginManagedBean.getAccountManagementRemote().getEmployee();
        if (!employee.getIsAdmin()) {
            try {
                ec.redirect("index.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        swapTaskPermissions = taskManagementRemote.retrieveAllSwapTaskPermissions();
    }
    
    public void updateSwapTaskPermission(){
        SwapTaskPermission permission = taskManagementRemote.retrieveSwapTaskPermissionBySwapTaskPermissionId(selectedswapTaskPermission.getSwapTaskPermissionId());
        permission.setBossStatus(selectedswapTaskPermission.getBossStatus());
        taskManagementRemote.updateSwapTaskPermission(permission);
        if(permission.getBossStatus().equals("Approve")){
            if (permission.getTakerStatus().equals("Approve")){
                Task task = taskManagementRemote.retrieveTaskByTaskId(permission.getTask().getTaskId());
                task.setEmployee(permission.getTaker());
                taskManagementRemote.updateTask(task);
                taskManagementRemote.deleteSwapTaskPermission(permission.getSwapTaskPermissionId());
            }
        }
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public SignUpAndLoginManagedBean getSignUpAndLoginManagedBean() {
        return signUpAndLoginManagedBean;
    }
    
    public void setSignUpAndLoginManagedBean(SignUpAndLoginManagedBean signUpAndLoginManagedBean) {
        this.signUpAndLoginManagedBean = signUpAndLoginManagedBean;
    }
    
    public TaskManagementRemote getTaskManagementRemote() {
        return taskManagementRemote;
    }
    
    public void setTaskManagementRemote(TaskManagementRemote taskManagementRemote) {
        this.taskManagementRemote = taskManagementRemote;
    }

    public SwapTaskPermission getSelectedswapTaskPermission() {
        return selectedswapTaskPermission;
    }

    public void setSelectedswapTaskPermission(SwapTaskPermission selectedswapTaskPermission) {
        this.selectedswapTaskPermission = selectedswapTaskPermission;
    }

    public List<SwapTaskPermission> getSwapTaskPermissions() {
        return swapTaskPermissions;
    }

    public void setSwapTaskPermissions(List<SwapTaskPermission> swapTaskPermissions) {
        this.swapTaskPermissions = swapTaskPermissions;
    }
}
