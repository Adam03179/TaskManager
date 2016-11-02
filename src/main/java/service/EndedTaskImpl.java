package service;

import model.EndedTask;
import repository.EndedTaskRepository;
import utils.exception.NotFoundException;

import java.util.List;

public class EndedTaskImpl implements EndedTaskService {
    private EndedTaskRepository repository;

    public EndedTaskImpl(EndedTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public EndedTask addEndedTask(EndedTask task) {
        return repository.create(task);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        if (!repository.delete(id)) {
            throw new NotFoundException("EndedTask with " + id + " is missing");
        }
        return repository.delete(id);
    }

    @Override
    public EndedTask get(int id) throws NotFoundException {

        if (repository.get(id) == null) {
            throw new NotFoundException("EndedTask with " + id + " is missing");
        } else return repository.get(id);
    }

    @Override
    public List<EndedTask> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(EndedTask task) throws NotFoundException {
        if (repository.update(task) == null) {
            throw new NotFoundException("EndedTask is missing");
        } else repository.update(task);
    }
}
