package service;

import model.Task;
import repository.TaskRepository;
import utils.exception.NotFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TaskServiceImpl implements TaskService {

    private TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task addTask(Task task) {
        return repository.create(task);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {

        if (!repository.delete(id)) {
            throw new NotFoundException("task with " + id + " is missing");
        }
        return repository.delete(id);
    }

    @Override
    public Task get(int id) throws NotFoundException {

        if (repository.get(id) == null) {
            throw new NotFoundException("task with " + id + " is missing");
        } else return repository.get(id);
    }

    @Override
    public Map<Task, String> getAll() {

        Map<Task, String> taskStringMap = new HashMap<>();

        long time = new Date().getTime();


        for (Task task : repository.getAll()) {

            if (task.getTerm().getTime() < time) {
                taskStringMap.put(task, "Time is up");
            } else taskStringMap.put(task, "In progress");

        }

        return taskStringMap;
    }

    @Override
    public void update(Task task) throws NotFoundException {
        if (repository.update(task) == null) {
            throw new NotFoundException("task is missing");
        } else repository.update(task);

    }

    @Override
    public void replaceToEndedTasks(int id) {
        repository.replaceTaskToEnded(id);
    }
}
