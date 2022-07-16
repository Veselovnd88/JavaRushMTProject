package archiver.command;

import archiver.ConsoleHelper;
import archiver.FileProperties;
import archiver.ZipFileManager;

import java.io.IOException;

public class ZipContentCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Просмотр содержимого архива.");
        ZipFileManager zfm = getZipFileManager();
        ConsoleHelper.writeMessage("Содержимое архива:");
        for(FileProperties fp: zfm.getFilesList()){
            ConsoleHelper.writeMessage(fp.toString());
        }
        ConsoleHelper.writeMessage("Содержимое архива прочитано.");

    }
}
