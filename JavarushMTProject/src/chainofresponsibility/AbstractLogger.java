package chainofresponsibility;

public abstract class AbstractLogger implements Logger {

    public int level;
    public Logger next;


    @Override
    public void setNext(Logger next) {
        this.next = next;
    }
    @Override
    public void inform(String message, int level) {
        if (this.level <= level) {
            info(message);
        }
        if (next != null) {
            next.inform(message, level);
        }
    }

}
