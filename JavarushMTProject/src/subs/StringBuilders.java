package subs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class StringBuilders {
    public static List<Pair> result = new LinkedList<>();
	
    public static void main(String[] args) {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String path = "JavarushMTProject/src/subs/text.txt";
    	StringBuilder sb = new StringBuilder();
    	try {
			BufferedReader fr = new BufferedReader(new FileReader(path));
			String line;
			while(!((line=fr.readLine())==null)) {
				String[] parts = line.split(" ");
				for(String s:parts) {
					sb.append(s+" ");
				}
			} fr.close();
		} 
    	catch (IOException e) {

			e.printStackTrace();
		}
    	String[] parts = sb.toString().split(" ");
    	for(int i=0; i<parts.length-1;i++) {
	    		for(int j=i+1;j<parts.length;j++) {
	    			
	    			String one = parts[i];
	    			String two = parts[j];

	    			if(one!=null && two!=null) {
	    				StringBuilder sbldr = new StringBuilder(two);
	    				if(one.equals(sbldr.reverse().toString())) {
	    					Pair p = new Pair();
	    					p.first = parts[i];
	    					p.second = parts[j];
	    					result.add(p);
	    					parts[i]=null;
	    					parts[j]=null;
	    					break;
	    			} }	    		
    			}    		
    	}
    	//System.out.println(sb.toString());
    	result.forEach(x->{
    		System.out.println(x);
    	});
    }

    public static class Pair {
        String first;
        String second;
        public Pair() {};
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }
}
