package service;

import model.EndedTask;
import utils.exception.NotFoundException;

import java.util.List;

public interface EndedTaskService {

    EndedTask addEndedTask(EndedTask task);

    boolean delete(int id) throws NotFoundException;

    EndedTask get(int id) throws NotFoundException;

    List<EndedTask> getAll();

    void update(EndedTask task) throws NotFoundException;
}
