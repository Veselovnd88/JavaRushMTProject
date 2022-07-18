package archiver.command;

import archiver.ConsoleHelper;
import archiver.ZipFileManager;


import java.io.IOException;
import java.nio.file.Paths;

public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        System.out.println("Удаление файла");
        ZipFileManager zipFileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Введите полное имя файла или директорию для удаления");
        zipFileManager.removeFile(Paths.get(archiver.ConsoleHelper.readString()));



    }
}
