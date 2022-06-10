
public class Simplify {
    public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
        return (a && b && c && !d) || (!a && c) ||   (!b && c) || (c && d);
        // 1 - если а,б и с -тру, а д фолс и наоборот   
        //
        
    }

    public static void main(String[] args) {

    }

}
