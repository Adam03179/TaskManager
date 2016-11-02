package repository;

import model.EndedTask;
import model.Priority;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EndedTaskRepositoryImpl implements EndedTaskRepository {
    @Override
    public EndedTask create(EndedTask task) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement psCreate = connection.prepareStatement
                    ("INSERT INTO tasks.ended_tasks(id,task,term,date_of_completion,priority)VALUES (?,?,?,?,?)");
            psCreate.setInt(1,task.getId());
            psCreate.setString(2, task.getTask());
            psCreate.setTimestamp(3, task.getTerm());
            psCreate.setTimestamp(4, task.getDateOfCompletion());
            psCreate.setString(5, task.getPriority().name());
            psCreate.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

    @Override
    public EndedTask get(int id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement psGetTask = connection.prepareStatement
                    ("SELECT * FROM tasks.ended_tasks WHERE id=?");

            psGetTask.setInt(1, id);
            ResultSet result = psGetTask.executeQuery();

            result.next();
            return getEndedTask(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EndedTask update(EndedTask task) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement psUpdate = connection.prepareStatement
                    ("UPDATE tasks.ended_tasks SET task=?, term=?, date_of_completion=? WHERE id=?");
            psUpdate.setInt(4, task.getId());
            psUpdate.setString(1, task.getTask());
            psUpdate.setTimestamp(2, task.getTerm());
            psUpdate.setTimestamp(3, task.getDateOfCompletion());
            psUpdate.executeUpdate();
            return task;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement psDelete = connection.prepareStatement
                    ("DELETE FROM tasks.ended_tasks WHERE id=?");
            psDelete.setInt(1, id);
            psDelete.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<EndedTask> getAll() {
        List<EndedTask> tasks = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement psGetTasks = connection.prepareStatement("SELECT * FROM tasks.ended_tasks");
            ResultSet resultSet = psGetTasks.executeQuery();
            while (resultSet.next()) {

                tasks.add(getEndedTask(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public void saveFromTasks(Connection connection, int id) throws SQLException {

        PreparedStatement psSaveFromTask = connection.prepareStatement
                ("INSERT INTO tasks.ended_tasks SELECT * FROM tasks.tasks WHERE id=?");
        psSaveFromTask.setInt(1,id);
        psSaveFromTask.execute();
    }

    private EndedTask getEndedTask(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String task = resultSet.getString("task");
            Timestamp term = resultSet.getTimestamp("term");
            Timestamp dateOfCompletion = resultSet.getTimestamp("date_of_completion");
            Priority priority = Priority.valueOf(resultSet.getString("priority").toUpperCase());


            return new EndedTask(id, task, term, dateOfCompletion,priority);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
