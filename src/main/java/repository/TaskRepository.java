package repository;

import model.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface TaskRepository {

    Task create(Task task);

    Task get(int id);

    Task update(Task task);

    boolean delete(int id);

    List<Task> getAll();

    void replaceTaskToEnded(int id);

    void deleteAfterReplacement(Connection connection, int id) throws SQLException;

}
