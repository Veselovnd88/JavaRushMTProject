
public class SwitchesTask {
    public static enum E1 {A, B, C, Y}

    public static enum E2 {D, E, F}

    public static enum E3 {D, E, F}

    public static void main(String[] args) {
        SwitchesTask.switchTest(E1.C);
        SwitchesTask.switchTest(E3.D);
        SwitchesTask.switchTest(E2.D);
        /* output
        it's E1.C
        undefined
        it's E2.D
         */
    }

    public static void switchTest(Enum obj) {
        switch (obj.getClass().getSimpleName()) {
            case ("E1"):
                System.out.println("it's "+obj.getClass().getSimpleName()+"."+obj);
                break;
            case ("E2"):
                System.out.println("it's "+obj);
                break;
            default:
                System.out.println("undefined");
        }
    }
}
