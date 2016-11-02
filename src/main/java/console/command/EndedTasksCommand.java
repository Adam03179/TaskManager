package console.command;

import model.EndedTask;
import repository.EndedTaskRepositoryImpl;
import service.EndedTaskImpl;
import service.EndedTaskService;
import utils.ConsoleHelper;

public class EndedTasksCommand implements Command {
    private EndedTaskService service = new EndedTaskImpl(new EndedTaskRepositoryImpl());




    @Override
    public void execute() {

        for (EndedTask task : service.getAll()){
            ConsoleHelper.writeMessage(task.toString());
         }



    }
}
