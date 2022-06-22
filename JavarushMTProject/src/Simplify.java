
public class Simplify {
    public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
        return (a && b && c && !d) || //чтобы было тру с-тру, д фолс
        		(!a && c) ||//чтобы было тру - с-тру - а фолс
        		(!b && c) ||//чтобы было тру с-тру б фолс
        		(c && d);//чтобы было тру - с тру д тру
        //								 
        // return c;
        
    }

    public static void main(String[] args) {

    }

}
