package archiver.command;

import archiver.ConsoleHelper;
import archiver.ZipFileManager;

import java.io.IOException;
import java.nio.file.Path;

public  abstract  class ZipCommand implements Command {
    public abstract void execute() throws Exception;
    public ZipFileManager getZipFileManager() throws IOException {
        ConsoleHelper.writeMessage("Введите полный путь архива");
        Path path = Path.of(ConsoleHelper.readString());
        return new ZipFileManager(path);
    }
}
