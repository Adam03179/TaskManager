package console.command;

import utils.exception.WrongInputException;

public interface Command {
    void execute() throws WrongInputException;
}
