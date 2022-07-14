package waitNotify;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() {

        while(!isValuePresent){//если нет значение то засыпает в вейт
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Got: " + value);
        isValuePresent=false;
        notifyAll();
        return value;
    }

    public synchronized    void put(int value) {
        while (isValuePresent){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.value = value;
        System.out.println("Put: " + value);
        isValuePresent=true;
        notifyAll();//сообщили всем что положили значение
    }
}
