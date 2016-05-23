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
import java.util.Calendar;
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
@Named(value = "myHistoryManagedBean")
@ViewScoped
public class MyHistoryManagedBean implements Serializable{
    
    @Inject
    private SignUpAndLoginManagedBean signUpAndLoginManagedBean;
    @EJB
    private TaskManagementRemote taskManagementRemote;
    
    private Employee employee;
    private List<Task> tasks;
    private Double weekHour;
    private Double monthHour;
    
    public MyHistoryManagedBean() {
        employee = new Employee();
        tasks = new ArrayList<>();
        weekHour = new Double(0);
        monthHour = new Double(0);
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
        tasks = listOfTasks();
        weekHour = weekHourCal();
        monthHour = monthHourCal();
    }
    
    public List<Task> listOfTasks(){
        List<Task> tempTasks = taskManagementRemote.retrieveTasksByEmployeeId(employee.getEmployeeId());
        return tempTasks;
    }
    
    public Double weekHourCal(){
        double tempWeek = 0;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        Date monday =  c.getTime();
        
        c.add(Calendar.DATE, 7);
        Date monday2 = c.getTime();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        
        for (int i = 0; i < tasks.size();i ++){
            if (tasks.get(i).getStartDateTime().after(monday) && tasks.get(i).getEndDateTime().before(monday2)){
                tempWeek += Double.valueOf(tasks.get(i).getDuration());
            }
        }
        return tempWeek;
    }
    
    public Double monthHourCal(){
        double tempMonth = 0;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        Date firstDay =  c.getTime();
        System.out.println("firstDay = " + firstDay);

        c.add(Calendar.MONTH, 1);
        Date nextFirstDay = c.getTime();
        System.out.println("nextFirstDay = " + nextFirstDay);
        for (int i = 0; i < tasks.size();i ++){
            if (tasks.get(i).getStartDateTime().after(firstDay) && tasks.get(i).getEndDateTime().before(nextFirstDay)){
                tempMonth += Double.valueOf(tasks.get(i).getDuration());
            }
        }
        return tempMonth;
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
    
    public Double getWeekHour() {
        return weekHour;
    }
    
    public void setWeekHour(Double weekHour) {
        this.weekHour = weekHour;
    }
    
    public Double getMonthHour() {
        return monthHour;
    }
    
    public void setMonthHour(Double monthHour) {
        this.monthHour = monthHour;
    }
}
