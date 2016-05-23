package TaskManagement;

import entity.SwapTaskPermission;
import entity.Task;
import java.util.List;

public interface TaskManagementRemote {
    
    public Boolean deleteTask(Long taskId);
    
    public List<Task> retrieveAllTasks();
    
    public Boolean updateTask(Task task);
    
    public Task retrieveTaskByTaskId(Long taskId);
    
    public Long createTask(Task task);
    
    public List<Task> retrieveTasksByEmployeeId(Long employeeId);
    
    public Long createSwapTaskPermission(SwapTaskPermission swapTaskPermission);
    
    public SwapTaskPermission retrieveSwapTaskPermissionBySwapTaskPermissionId(Long swapTaskPermissionId);
    
    public Boolean updateSwapTaskPermission(SwapTaskPermission swapTaskPermission);
    
    public List<SwapTaskPermission> retrieveAllSwapTaskPermissions();
    
    public List<SwapTaskPermission> retrieveSwapTaskPermissionsByOwnerId(Long ownerId);
    
    public Boolean deleteSwapTaskPermission(Long swapTaskPermissionId);
}
