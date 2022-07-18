package archiver;

import archiver.exception.PathIsNotFoundException;
import archiver.exception.WrongZipFileException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
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
    public void removeFiles(List<Path> pathList) throws  Exception{
        //передается список путей на файлы которые требуется удалить
        if(!Files.isRegularFile(zipFile)){
            throw new WrongZipFileException();
        }
        Path temp = Files.createTempFile("temp",".tmp");

        try(ZipOutputStream zosTemp = new ZipOutputStream(Files.newOutputStream(temp))){
            try( ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFile))){
                ZipEntry ze = zis.getNextEntry();
                while (ze != null) {
                    if (pathList.contains(Paths.get(ze.getName()))) {
                        System.out.println("Файл " + ze.getName() + " удален");
                    } else {
                        zosTemp.putNextEntry(new ZipEntry(ze.getName()));//вставили новую ентри
                        copyData(zis,zosTemp);//скопировали данные
                        //данные записываются из одной ентри в другую
                        zis.closeEntry();
                        zosTemp.closeEntry();
                    }
                    ze = zis.getNextEntry();
                }
            }
        }
        Files.move(temp, zipFile, StandardCopyOption.REPLACE_EXISTING);
    }

    public void removeFile(Path path) throws Exception{
        removeFiles(Collections.singletonList(path));
    }

    public void addFiles(List<Path> absolutePathList) throws Exception{
        if(!Files.isRegularFile(zipFile)){
            throw new WrongZipFileException();
        }
        Path temp = Files.createTempFile("prefix",".tmp");
        List<Path> tempList = new ArrayList<>();
        try(ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(temp))){
            try(ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFile))){
                ZipEntry ze = zis.getNextEntry();
                while(ze!=null){
                    zos.putNextEntry(new ZipEntry(ze.getName()));//прошли по всем ентрям и скопировали в новый файл
                    copyData(zis,zos);
                    tempList.add(Paths.get(ze.getName()));
                    //System.out.println(Paths.get(ze.getName()));
                    zis.closeEntry();
                    zos.closeEntry();
                    ze = zis.getNextEntry();
                }

                for(Path p: absolutePathList){
                    if(!Files.isRegularFile(p)){
                        throw new PathIsNotFoundException();
                    }
                    if(tempList.contains(p.getFileName())){
                        System.out.println("Файл уже есть в архиве");
                    }
                    else{
                        addNewZipEntry(zos,p.getParent(),p.getFileName());//добавляем в архив новый ентри если такого нет в списке
                        ConsoleHelper.writeMessage("Файл успешно добавлен в архив");
                    }
                }
            }
        }Files.move(temp, zipFile, StandardCopyOption.REPLACE_EXISTING);//перемещение
    }
    public void addFile(Path p) throws Exception {
        addFiles(Collections.singletonList(p));
    }





    public void extractAll(Path outputFolder) throws Exception{
        System.out.println("зашел");
        if(!Files.isRegularFile(zipFile)){
            throw new WrongZipFileException();
        }
        if(!Files.exists(outputFolder)){
            Files.createDirectories(outputFolder);//создает все директории по заданному пути
        }
        try(ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFile))){



            ZipEntry ze = zis.getNextEntry();
            while(ze!=null){
                Path myPath = outputFolder.resolve(ze.getName());

                Path parent = myPath.getParent();//проверяет, существуют ли родительские папки
                if (Files.notExists(parent))//если нет - то создает все папки
                    Files.createDirectories(parent);
                OutputStream os = Files.newOutputStream(myPath);
                copyData(zis,os);
                ze = zis.getNextEntry();
            }
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
