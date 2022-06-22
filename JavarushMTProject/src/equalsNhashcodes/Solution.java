package equalsNhashcodes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);
            System.out.println(clone.users==solution.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    protected Map<String, User> users = new LinkedHashMap<>();
    @Override
    public Object clone() throws CloneNotSupportedException {
    	Solution sol_cl = new Solution();
		Map<String, User> users_cl = new LinkedHashMap<>();
		users.forEach((x,y)->{
			try {
				users_cl.put(new String(x), (User) y.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		sol_cl.users.putAll(users_cl);
		return sol_cl;
    } 

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
        @Override
        public Object clone() throws CloneNotSupportedException {
        	int age_u = age;
        	String name_u = new String(name);
        	User u  = new User(age_u, name_u);
			return u;
        	
        }
		@Override
		public int hashCode() {
			return Objects.hash(age, name);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			return age == other.age && Objects.equals(name, other.name);
		}
        
        
        
    }
}
