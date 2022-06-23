package subs;
/*Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
 * */
public class TaskSubstrings {
	
	
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("АмигДиего лучшие друзья!"));
    }

    public static String getPartOfString(String string) {

    	if(string==null) {
    		throw new TooShortStringException();
    	}
    	
    	String[] parts = string.split(" ");
    	if(parts.length<5) {
    		throw new TooShortStringException();
    	}
    	StringBuilder sb = new StringBuilder();
    	for(int i=1; i<5;i++) {
    		sb.append(parts[i]);
    		sb.append(" ");
    	}
    	return sb.toString().trim();
    	
    	
    		
    	
    }

    public static class TooShortStringException extends RuntimeException {
    	
    }

}
