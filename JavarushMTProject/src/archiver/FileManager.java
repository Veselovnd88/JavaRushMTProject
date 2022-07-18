package archiver;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private Path rootPath;//путь директории список файлов которой мы хотим получить
    private List<Path> fileList;

    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
        this.fileList = new ArrayList<>();
        collectFileList(rootPath);
    }
    private void collectFileList(Path path) throws IOException{
        if(Files.isRegularFile(path)){
            fileList.add(rootPath.relativize(path));//если файл - то получаем относительный путь от рут пас
        }
        else if (Files.isDirectory(path)){
            DirectoryStream<Path> ds = Files.newDirectoryStream(path);
            for(Path p: ds){
                collectFileList(p);
            }
            ds.close();
        }
    }


    public List<Path> getFileList() {
        return fileList;
    }
}
