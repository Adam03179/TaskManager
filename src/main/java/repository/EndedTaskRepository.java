package repository;

import model.EndedTask;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EndedTaskRepository {
    EndedTask create(EndedTask task);

    EndedTask get(int id);

    EndedTask update(EndedTask task);

    boolean delete(int id);

    List<EndedTask> getAll();

    void saveFromTasks(Connection connection, int id) throws SQLException;
}
