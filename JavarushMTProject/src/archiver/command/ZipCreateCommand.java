package archiver.command;

import archiver.ZipFileManager;
import archiver.exception.PathIsNotFoundException;
import chatTask.ConsoleHelper;

import java.io.IOException;
import java.nio.file.Path;

public class ZipCreateCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Создание архива");
        ZipFileManager zf  = getZipFileManager();
        ConsoleHelper.writeMessage("Введите полное имя файла или директорию архивации");
        try {
            zf.createZip(Path.of(ConsoleHelper.readString()));
            ConsoleHelper.writeMessage("Архив создан");
        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }

    }
}
