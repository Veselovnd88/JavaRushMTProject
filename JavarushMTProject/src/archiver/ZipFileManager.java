package archiver;

import archiver.exception.PathIsNotFoundException;
import archiver.exception.WrongZipFileException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
public class ZipFileManager {
    Path zipFile;//полный путь к архиву с которым будем работать

    public  ZipFileManager(Path path){
        this.zipFile = path;
    }


    public void createZip(Path source) throws IOException, PathIsNotFoundException {//пусть к файлу который мы будем архивировать
        Path dir = zipFile.getParent();
        if(Files.notExists(dir)){
            Files.createDirectory(dir);
        }
        try(ZipOutputStream zos= new ZipOutputStream(Files.newOutputStream(zipFile));//открыли потока для записи в архив
            )//открыли поток для чтения из файла
        {if(Files.isRegularFile(source)){//если сорс - обычный файл, то просто его кладем
            addNewZipEntry(zos,source.getParent(),source.getFileName());
            //здесь - так как в методе принимается родительская директория и имя файла - нам нужно перевести
            //сорс в такой вид - getParent и файлНейм
        } else if (
                Files.isDirectory(source)
        ) {FileManager fm = new FileManager(source);
            List<Path> fileNames = fm.getFileList();
            for(Path p: fileNames){
                addNewZipEntry(zos,source,p);
            }
        } else{
            throw new PathIsNotFoundException();
        }
            zos.closeEntry();
        }
    }

    public List<FileProperties> getFilesList() throws Exception{//возвращает список свойств файлов в архиве
        if(!Files.isRegularFile(zipFile)){
            throw  new WrongZipFileException();
        }
        List<FileProperties> filePropertiesList = new ArrayList<>();
        try(ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFile))){
            ZipEntry ze = zis.getNextEntry();//получили
            while(ze!=null){

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                copyData(zis,baos);
                filePropertiesList.add(new FileProperties(ze.getName(), baos.size(), ze.getCompressedSize(),ze.getMethod()));
                ze=zis.getNextEntry();
            }
        }return  filePropertiesList;
    }
    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path filename) throws IOException{
        ZipEntry ze = new ZipEntry(filename.toString());// создали зип Ентри - принимает строку на вход
        try(InputStream is = Files.newInputStream(filePath.resolve(filename))){
            //метод резолв - строит абсолютный путь из имени файла и его директории
            zipOutputStream.putNextEntry(ze);// положили новый ентри с именем файла
            copyData(is,zipOutputStream);//копируем данные в стрим
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
