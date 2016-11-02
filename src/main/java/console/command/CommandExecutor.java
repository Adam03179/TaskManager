package console.command;

import console.Operation;
import utils.exception.WrongInputException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {

    private static final Map<Operation, Command> allKnownCommandsMap
            = new HashMap<>();

    static {
        allKnownCommandsMap.put(Operation.ADD_TASK, new AddTaskCommand());
        allKnownCommandsMap.put(Operation.LIST_TASKS, new ListTasksCommand());
        allKnownCommandsMap.put(Operation.ENDED_TASKS, new EndedTasksCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor() {
    }

    public static void execute(Operation operation) throws WrongInputException {
        allKnownCommandsMap.get(operation).execute();
    }
}
