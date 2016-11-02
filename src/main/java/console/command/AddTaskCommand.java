package console.command;

import model.Priority;
import model.Task;
import repository.TaskRepositoryImpl;
import service.TaskService;
import service.TaskServiceImpl;
import utils.ConsoleHelper;
import utils.exception.WrongInputException;

import java.sql.Timestamp;

public class AddTaskCommand implements Command {


    @Override
    public void execute() throws WrongInputException {


        ConsoleHelper.writeMessage("Please, type a new task: ");
        String task = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Enter term task in format yyyy.MM.dd");
        Timestamp term = ConsoleHelper.readTime();
        ConsoleHelper.writeMessage("Please, choose priority of the task");
        ConsoleHelper.writeMessage("0 - High priority\n1 - Low priority\n ");
        Priority priority = Priority.values()[ConsoleHelper.readInt()];

        TaskService service = new TaskServiceImpl(new TaskRepositoryImpl());

        service.addTask(new Task(task, term, priority));


    }
}
