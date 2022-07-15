package archiver;

import archiver.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static Map<Operation, Command> ALL_KNOWN_COMMANDS = new HashMap<>();
    private CommandExecutor() {};
    static {
        ALL_KNOWN_COMMANDS.put(Operation.EXIT, new ExitCommand());
        ALL_KNOWN_COMMANDS.put(Operation.ADD, new ZipAddCommand());
        ALL_KNOWN_COMMANDS.put(Operation.CONTENT, new ZipContentCommand());
        ALL_KNOWN_COMMANDS.put(Operation.CREATE, new ZipCreateCommand());
        ALL_KNOWN_COMMANDS.put(Operation.EXTRACT, new ZipExtractCommand());
        ALL_KNOWN_COMMANDS.put(Operation.REMOVE, new ZipRemoveCommand());
    }

    public static void execute(Operation operation) throws  Exception{
        ALL_KNOWN_COMMANDS.get(operation).execute();
    }

}
