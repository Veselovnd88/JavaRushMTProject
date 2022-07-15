package archiver;

import archiver.command.ExitCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите полный путь к архиву");

        try {
            String path = br.readLine();
            Path p = Paths.get(path);
            ZipFileManager zfm = new ZipFileManager(p);
            System.out.println("Введите полный путь к файлу который будем архивировать");
            String pathFile = br.readLine();
            Path f = Paths.get(pathFile);
            zfm.createZip(f);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new ExitCommand().execute();

    }


}
