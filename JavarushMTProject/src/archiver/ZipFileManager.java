package archiver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class ZipFileManager {
    Path zipFile;//полный путь к архиву с которым будем работать

    public  ZipFileManager(Path path){
        this.zipFile = path;
    }


    public void createZip(Path source) throws IOException {//пусть к файлу который мы будем архивировать
        Path dir = zipFile.getParent();
        if(Files.notExists(dir)){
            Files.createDirectory(dir);
        }



        try(ZipOutputStream zos= new ZipOutputStream(Files.newOutputStream(zipFile));//открыли потока для записи в архив
            )//открыли поток для чтения из файла
        {if(Files.isRegularFile(source)){//если сорс - обычный файл, то просто его кладем
            addNewZipEntry(zos,zipFile,source);
        } else if (
                Files.isDirectory(source)
        ) {FileManager fm = new FileManager(source);



        }
            zos.closeEntry();
        }
    }
    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path filename) throws IOException{
        ZipEntry ze = new ZipEntry(filename.toString());
        try(InputStream is = Files.newInputStream(filename.resolve(filePath))){
            zipOutputStream.putNextEntry(ze);
            copyData(is,zipOutputStream);
            zipOutputStream.closeEntry();
        }
    }
    private void copyData(InputStream is, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len=0;
        while ((len=is.read(buffer))>0){
            out.write(buffer,0,len);
        }
    }



}
