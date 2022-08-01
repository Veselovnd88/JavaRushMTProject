package restaraunt;

import restaraunt.kitchen.Dish;

import javax.swing.table.AbstractTableModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readMessage() throws IOException {
            return br.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        System.out.println("Введите блюдо, или введите exit для выхода");
        Dish.allDishesToString();
        while (true) {
            String input =readMessage().trim();
            if ("exit".equals(input)) {
                break;
            }
            try {
                Dish dish = Dish.valueOf(input);
                dishes.add(dish);
                writeMessage(input + " has been successfully added to your order");
            } catch (Exception e) {
                writeMessage(input + " hasn't been detected");
            }
        }
        return dishes;
    }

}
