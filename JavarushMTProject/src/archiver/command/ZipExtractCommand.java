package archiver.command;

import archiver.ZipFileManager;
import archiver.exception.PathIsNotFoundException;
import archiver.exception.WrongZipFileException;
import chatTask.ConsoleHelper;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipExtractCommand extends ZipCommand{
    @Override
    public void execute() throws Exception {

        ConsoleHelper.writeMessage("Распакова архива");
        ZipFileManager zf  = getZipFileManager();
        ConsoleHelper.writeMessage("Введите папку для распаковки");
        try {
            zf.extractAll(Paths.get(ConsoleHelper.readString()));
            ConsoleHelper.writeMessage("Архив распакован");
        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }

    }
}
