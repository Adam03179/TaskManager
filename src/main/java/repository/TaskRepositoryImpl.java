package repository;

import model.Priority;
import model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    private EndedTaskRepository endedTaskRepository;

    @Override
    public Task create(Task task) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement psCreate = connection.prepareStatement
                    ("INSERT INTO tasks.tasks(task,term,date_of_completion, priority)VALUES (?,?,?,?)");
            psCreate.setString(1, task.getTask());
            psCreate.setTimestamp(2, task.getTerm());
            psCreate.setTimestamp(3, task.getDateOfCompletion());
            psCreate.setString(4, task.getPriority().name());
            psCreate.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

    @Override
    public Task get(int id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement psGetTask = connection.prepareStatement
                    ("SELECT * FROM tasks.tasks WHERE id=?");

            psGetTask.setInt(1, id);
            ResultSet result = psGetTask.executeQuery();

            result.next();
            return getTask(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    public Task update(Task task) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement psUpdate = connection.prepareStatement
                    ("UPDATE tasks.tasks SET task=?, term=?,date_of_completion=?, priority=? WHERE id=?");
            psUpdate.setInt(4, task.getId());
            psUpdate.setString(1, task.getTask());
            psUpdate.setTimestamp(2, task.getTerm());
            psUpdate.setTimestamp(3, task.getDateOfCompletion());
            psUpdate.setString(4, task.getPriority().name());
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
                    ("DELETE FROM tasks.tasks WHERE id=?");
            psDelete.setInt(1, id);
            psDelete.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement psGetTasks = connection.prepareStatement("SELECT * FROM tasks.tasks");
            ResultSet resultSet = psGetTasks.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTask(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }


    @Override
    public void replaceTaskToEnded(int id) {


        try (Connection connection = JdbcConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);

                endedTaskRepository = new EndedTaskRepositoryImpl();
                endedTaskRepository.saveFromTasks(connection, id);
                deleteAfterReplacement(connection,id);
                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteAfterReplacement(Connection connection, int id) throws SQLException {

        PreparedStatement psDelAfterReplacement = connection.prepareStatement
                ("DELETE FROM tasks.tasks WHERE id=?");
        psDelAfterReplacement.setInt(1, id);
        psDelAfterReplacement.execute();


    }

    private Task getTask(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String task = resultSet.getString("task");
            Timestamp term = resultSet.getTimestamp("term");
            Timestamp dateOfCompletion = resultSet.getTimestamp("date_of_completion");
            Priority priority = Priority.valueOf(resultSet.getString("priority").toUpperCase());

            return new Task(id, task, term, dateOfCompletion, priority);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
