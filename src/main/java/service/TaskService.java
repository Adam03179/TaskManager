package service;

import model.Task;
import utils.exception.NotFoundException;

import java.util.Map;

public interface TaskService {

    Task addTask(Task task);

    boolean delete(int id) throws NotFoundException;

    Task get(int id) throws NotFoundException;

    Map<Task,String> getAll();

    void update(Task task) throws NotFoundException;

    void replaceToEndedTasks(int id);
}
