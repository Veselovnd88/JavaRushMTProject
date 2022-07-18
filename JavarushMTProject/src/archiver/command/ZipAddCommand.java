package archiver.command;

import archiver.ConsoleHelper;
import archiver.ZipFileManager;

import java.io.IOException;
import java.nio.file.Paths;

public class ZipAddCommand  extends  ZipCommand{
    @Override
    public void execute() throws Exception {
        System.out.println("Добавление файла");
        ZipFileManager zipFileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Введите полное имя файла или директорию для удаления");
        zipFileManager.addFile(Paths.get(archiver.ConsoleHelper.readString()));
    }
}
