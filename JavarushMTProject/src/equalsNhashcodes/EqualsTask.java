package equalsNhashcodes;

import java.util.HashSet;
import java.util.Set;

public class EqualsTask {
    private final String first, last;

    public EqualsTask(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(EqualsTask n) {
        if(n==null) {
        	return false;
        }
        if(n.getClass()!=this.getClass()) {
        	return false;
        }
        if(n.hashCode()!=this.hashCode()) {
        	return false;
        }
        if(n==this) {
        	return true;
        }
        else {
        	EqualsTask myOb = (EqualsTask) n;
        	if(myOb.first!=null &&myOb.last!=null) {
        		return this.first.equals(myOb.first)&&this.last.equals(myOb.last);
        	}
        	
        	
        }
		return false;
    	
    }

    public int hashCode() {
    	if(first==null&&last!=null) {
    		return last.hashCode();
    	}
    	if(last==null&&first!=null) {
    		return 31*first.hashCode();
    	}
    	if(last==null&&first==null) {
    		return 0;
    	}
    	
        return 31 * first.hashCode() + last.hashCode();
    }

    public static void main(String[] args) {
    	EqualsTask ob1 = new EqualsTask("first","last");
    	EqualsTask obj2 = new EqualsTask("first","last");
    	System.out.println(ob1.first.equals(obj2.first));
    	System.out.println(ob1.last.equals(obj2.last));
    	System.out.println(ob1.equals(obj2));
    	System.out.println(ob1.hashCode()==obj2.hashCode());
    	System.out.println(ob1.hashCode());
    	System.out.println(obj2.hashCode());
        Set<EqualsTask> s = new HashSet<>();
        s.add(new EqualsTask("Donald", "Duck"));
        System.out.println(s.contains(new EqualsTask("Donald", "Duck")));
    }
	
	
	

}
