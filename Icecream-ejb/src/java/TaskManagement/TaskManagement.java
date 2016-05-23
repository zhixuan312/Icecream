package TaskManagement;

import entity.SwapTaskPermission;
import entity.Task;
import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@Local(TaskManagementLocal.class)
@Remote(TaskManagementRemote.class)
public class TaskManagement implements TaskManagementRemote, TaskManagementLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Long createTask(Task task) {
        try {
            em.persist(task);
            em.flush();
            return task.getTaskId();
        } catch (Exception e) {
            e.printStackTrace();
            return new Long(-1);
        }
    }
    
    @Override
    public Task retrieveTaskByTaskId(Long taskId) {
        try {
            String jpql = "SELECT t FROM Task e WHERE t.taskId = " + "'" + taskId + "'";
            Query query = em.createQuery(jpql);
            return (Task) query.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return new Task();
        }
    }
    
    @Override
    public Boolean updateTask(Task task) {
        try {
            em.merge(task);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<Task> retrieveAllTasks() {
        List<Task> tasks = null;
        try {
            String jpql = "SELECT t FROM Task t";
            Query query = em.createQuery(jpql);
            tasks = query.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return tasks;
    }
    
    @Override
    public List<Task> retrieveTasksByEmployeeId(Long employeeId) {
        List<Task> tasks = null;
        try {
            String jpql = "SELECT t FROM Task t.employee.employeeId = " + employeeId;
            Query query = em.createQuery(jpql);
            tasks = query.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return tasks;
    }
    
    @Override
    public Boolean deleteTask(Long taskId) {
        try {
            Task task = em.find(Task.class, taskId);
            em.remove(task);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Long createSwapTaskPermission(SwapTaskPermission swapTaskPermission) {
        try {
            em.persist(swapTaskPermission);
            em.flush();
            return swapTaskPermission.getSwapTaskPermissionId();
        } catch (Exception e) {
            e.printStackTrace();
            return new Long(-1);
        }
    }
    
    @Override
    public SwapTaskPermission retrieveSwapTaskPermissionBySwapTaskPermissionId(Long swapTaskPermissionId) {
        try {
            String jpql = "SELECT stp FROM SwapTaskPermission stp WHERE stp.swapTaskPermissionId = " + "'" + swapTaskPermissionId + "'";
            Query query = em.createQuery(jpql);
            return (SwapTaskPermission) query.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return new SwapTaskPermission();
        }
    }
    
    @Override
    public Boolean updateSwapTaskPermission(SwapTaskPermission swapTaskPermission) {
        try {
            em.merge(swapTaskPermission);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<SwapTaskPermission> retrieveAllSwapTaskPermissions() {
        List<SwapTaskPermission> swapTaskPermissions = null;
        try {
            String jpql = "SELECT stp FROM SwapTaskPermission stp";
            Query query = em.createQuery(jpql);
            swapTaskPermissions = query.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return swapTaskPermissions;
    }
    
    @Override
    public List<SwapTaskPermission> retrieveSwapTaskPermissionsByOwnerId(Long ownerId) {
        List<SwapTaskPermission> swapTaskPermissions = null;
        try {
            String jpql = "SELECT stp FROM SwapTaskPermission stp.owner.employeeId = " + ownerId;
            Query query = em.createQuery(jpql);
            swapTaskPermissions = query.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return swapTaskPermissions;
    }
    
    @Override
    public Boolean deleteSwapTaskPermission(Long swapTaskPermissionId) {
        try {
            SwapTaskPermission swapTaskPermission = em.find(SwapTaskPermission.class, swapTaskPermissionId);
            em.remove(swapTaskPermission);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
