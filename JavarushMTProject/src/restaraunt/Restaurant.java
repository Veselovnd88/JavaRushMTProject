package restaraunt;

import restaraunt.kitchen.Dish;
import restaraunt.kitchen.Order;

import java.io.IOException;

public class Restaurant {


    public static void main(String[] args) throws IOException {
        Tablet tablet = new Tablet(1);
        for(int i=0; i<4; i++){
            tablet.createOrder();
        }

    }
}
