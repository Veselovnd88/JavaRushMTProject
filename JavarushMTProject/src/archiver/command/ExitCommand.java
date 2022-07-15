package archiver.command;

import archiver.ConsoleHelper;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("До свидания");
    }
}
