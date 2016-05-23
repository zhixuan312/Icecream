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
@Named(value = "myTaskManagedBean")
@ViewScoped
public class MyTaskManagedBean implements Serializable{
    
    @Inject
    private SignUpAndLoginManagedBean signUpAndLoginManagedBean;
    @EJB
    private TaskManagementRemote taskManagementRemote;
    
    private Employee employee;
    private Task task;
    private List<Task> tasks;
    private SwapTaskPermission swapTaskPermission;
    private List<SwapTaskPermission> swapTaskPermissions;
    private Long swapTaskTakerId;
    private Long taskId;
    
    public MyTaskManagedBean() {
        employee = new Employee();
        task = new Task();
        tasks = new ArrayList<>();
        swapTaskPermissions = new ArrayList<>();
        swapTaskPermission = new SwapTaskPermission();
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
        System.out.println("###### Employee: " + employee.getEmail());
        if (!employee.getIsAdmin()) {
            try {
                ec.redirect("index.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        tasks = taskManagementRemote.retrieveTasksByEmployeeId(employee.getEmployeeId());
        swapTaskPermissions = taskManagementRemote.retrieveSwapTaskPermissionsByOwnerId(employee.getEmployeeId());
    }
    
    public void createSwapTaskPermission(){
        Employee taker = signUpAndLoginManagedBean.getAccountManagementRemote().retrieveEmployeeByEmployeeId(swapTaskTakerId);
        task.setEmployee(taker);
        swapTaskPermission.setOwner(employee);
        swapTaskPermission.setTaker(taker);
        System.out.println("Task ID= " + taskId);
        Task task = taskManagementRemote.retrieveTaskByTaskId(taskId);
        swapTaskPermission.setTask(task);
        swapTaskPermission.setBossStatus("Pending");
        swapTaskPermission.setTakerStatus("Pending");
        taskManagementRemote.updateSwapTaskPermission(swapTaskPermission);
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
    
    public List<Task> getTasks() {
        return tasks;
    }
    
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public Task getTask() {
        return task;
    }
    
    public void setTask(Task task) {
        this.task = task;
    }
    
    public List<SwapTaskPermission> getSwapTaskPermissions() {
        return swapTaskPermissions;
    }
    
    public void setSwapTaskPermissions(List<SwapTaskPermission> swapTaskPermissions) {
        this.swapTaskPermissions = swapTaskPermissions;
    }
    
    public SwapTaskPermission getSwapTaskPermission() {
        return swapTaskPermission;
    }
    
    public void setSwapTaskPermission(SwapTaskPermission swapTaskPermission) {
        this.swapTaskPermission = swapTaskPermission;
    }
    
    public Long getSwapTaskTakerId() {
        return swapTaskTakerId;
    }
    
    public void setSwapTaskTakerId(Long swapTaskTakerId) {
        this.swapTaskTakerId = swapTaskTakerId;
    }
    
    public Long getTaskId() {
        return taskId;
    }
    
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
