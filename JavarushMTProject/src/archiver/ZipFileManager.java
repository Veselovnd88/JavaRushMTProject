package archiver;

import java.io.IOException;
import java.io.InputStream;
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


        try(ZipOutputStream zos= new ZipOutputStream(Files.newOutputStream(zipFile));//открыли потока для записи в архив
            )//открыли поток для чтения из файла
        {  ZipEntry ze = new ZipEntry(source.getFileName().toString());//создали ентри для ввода в архив
            zos.putNextEntry(ze);// посадили ентри в наш архив
            try(InputStream is = Files.newInputStream(source);){

                    byte[] buffer = new byte[1024];//массив байтов
                    int len = 0; //сколько прочитали
                    while((len = is.read(buffer))>0){//пока то что вычитал больше нуля
                    zos.write(buffer,0,len);//пишем весь буффер
                }
                    zos.closeEntry();
            }
        }
    }



}
