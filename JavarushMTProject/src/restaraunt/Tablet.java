package restaraunt;

import restaraunt.kitchen.Order;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {//планшет со своим индивидуальным номером
    private final int number;
    public static Logger logger = Logger.getLogger(Tablet.class.getName());
    //логгер на классе таблет

    public Tablet(int number) {
        this.number = number;
    }


    public void createOrder(){
        //будет создавать заказ
        try {
            Order order = new Order(this);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable");
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
