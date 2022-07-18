package archiver;

import archiver.command.ExitCommand;
import archiver.exception.WrongZipFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println("Введите полный путь к архиву");


        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                e.printStackTrace();
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }

        } while (operation != Operation.EXIT);
    }

    public static Operation askOperation() throws IOException{
        System.out.printf("Выберите операцию:\n" +
                "%s - упаковать файлы в архив\n" +
                "%s - добавить файл в архив\n" +
                "%s - удалить файл из архива\n" +
                "%s - распаковать архив\n" +
                "%s - просмотреть содержимое архива\n" +
                "%s - выход\n",Operation.CREATE.ordinal(),Operation.ADD.ordinal(),Operation.REMOVE.ordinal(),Operation.EXTRACT.ordinal(),
                Operation.CONTENT.ordinal(),Operation.EXIT.ordinal());
        int op = ConsoleHelper.readInt();
        return Operation.values()[op];

    }



}
