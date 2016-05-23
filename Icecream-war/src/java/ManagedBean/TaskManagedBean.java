/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import TaskManagement.TaskManagementRemote;
import entity.Employee;
import entity.Task;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Named(value = "taskManagedBean")
@ViewScoped
public class TaskManagedBean implements Serializable{

    @Inject
    private SignUpAndLoginManagedBean signUpAndLoginManagedBean;
    @EJB
    private TaskManagementRemote taskManagementRemote;

    private Employee employee;
    private Task task;
    private List<Task> tasks;
    private List<Task> filteredTasks;
    private Long taskTakerId;
    private Date startDateTime;
    private Date endDateTime;

    public TaskManagedBean() {
        employee = new Employee();
        task = new Task();
        tasks = new ArrayList<>();
        System.out.println("######### New Page");
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
        tasks = taskManagementRemote.retrieveAllTasks();
    }

    public void createTask() {
        System.out.println("######## takerId = " + taskTakerId);
        Employee taker = signUpAndLoginManagedBean.getAccountManagementRemote().retrieveEmployeeByEmployeeId(taskTakerId);
        task.setEmployee(taker);
        task.setStartDateTime(startDateTime);
        task.setEndDateTime(endDateTime);
        taskManagementRemote.updateTask(task);
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

    public Long getTaskTakerId() {
        return taskTakerId;
    }

    public void setTaskTakerId(Long taskTakerId) {
        this.taskTakerId = taskTakerId;
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

    public List<Task> getFilteredTasks() {
        return filteredTasks;
    }

    public void setFilteredTasks(List<Task> filteredTasks) {
        this.filteredTasks = filteredTasks;
    }
}
