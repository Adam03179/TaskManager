package console;

import console.command.CommandExecutor;
import utils.ConsoleHelper;
import utils.exception.WrongInputException;

public class Main {


    public static void main(String[] args) {

        Operation operation = null;
        do {
            try {

                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongInputException e) {
                ConsoleHelper.writeMessage("Wrong input, try again!");
            }

        } while (operation != console.Operation.EXIT);


    }

    public static Operation askOperation() throws WrongInputException {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Enter number of operation:");
        ConsoleHelper.writeMessage(String.format("\t %d - add new Task",
                Operation.ADD_TASK.ordinal()));
        ConsoleHelper.writeMessage(String.format
                ("\t %d - Show all tasks",
                        Operation.LIST_TASKS.ordinal()));
        ConsoleHelper.writeMessage(String.format
                ("\t %d - exit", Operation.EXIT.ordinal()));

        return Operation.values()[ConsoleHelper.readInt()];
    }
}
