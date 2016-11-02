package console.command;

import model.EndedTask;
import model.Task;
import repository.EndedTaskRepositoryImpl;
import repository.TaskRepositoryImpl;
import service.EndedTaskImpl;
import service.EndedTaskService;
import service.TaskService;
import service.TaskServiceImpl;
import utils.ConsoleHelper;
import utils.exception.WrongInputException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

 class ListTasksCommand implements Command {

    private TaskService taskService = new TaskServiceImpl(new TaskRepositoryImpl());
    private EndedTaskService endedTaskService = new EndedTaskImpl(new EndedTaskRepositoryImpl());

    @Override
    public void execute() throws WrongInputException {
        printTasks();

        ConsoleHelper.writeMessage("\n\nPlease, make a choice:");
        ConsoleHelper.writeMessage("\t 1. Select task, which is made");
        ConsoleHelper.writeMessage("\t 2. Show all ended tasks");
        ConsoleHelper.writeMessage("\t any other number. Return to main menu");

        int choice = ConsoleHelper.readInt();

        if (choice == 1) {
            ConsoleHelper.writeMessage("Please select number of task, which is done");
            int taskId = ConsoleHelper.readInt();
            taskService.replaceToEndedTasks(taskId);

            ConsoleHelper.writeMessage("It is done!");

        } else if (choice == 2) {
            new EndedTasksCommand().execute();
        }


    }

    private void printTasks() {

        Map<Task, String> tasks = taskService.getAll();

        for (Map.Entry<Task, String> pair : tasks.entrySet()) {

            ConsoleHelper.writeMessage(pair.getKey().getId() + " " + pair.getKey() + " " + pair.getValue());

        }


    }


}
