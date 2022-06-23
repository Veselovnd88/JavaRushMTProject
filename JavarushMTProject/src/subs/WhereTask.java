package subs;

import java.util.Map;
import java.util.LinkedHashMap;
public class WhereTask {
	
	
	public static void main(String[] args) {
		
		Map<String, String> mp = new LinkedHashMap<String, String>();
		mp.put("name","Ivanov");
		mp.put("county", "Vasya");
		mp.put("city", "Petya");
		mp.put("age", null);
		
		System.out.println(getQuery(mp));
	}
	
	
    public static String getQuery(Map<String, String> params) {
    	StringBuilder sb = new StringBuilder();
    	int i=0;
    	for(Map.Entry<String,String> entry: params.entrySet()){
    		
    		if(entry.getValue()!=null) {
    			if (i!=0) {
    				sb.append(" and ");
    			}
    			
    			sb.append(entry.getKey()+" = "+ "\'"+entry.getValue()+"\'");
    			i++;
    			
    		}
    	}
    	
    	
        return sb.toString();
    }

}
