package subs;
/*Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
 * */
public class TaskSubstrings {
	
	
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
    }

    public static String getPartOfString(String string) {
    	try {
    		
    		int index1 = string.indexOf(" ");
    		int curr = index1;
    		int first = index1;
    		for ( int i = 0; i< 3; i++) {
    			curr+= string.substring(first+1).indexOf(" ");
    			first = curr;
    		}
    		int index5 = string.substring(curr+1).indexOf(" ");
    		if(index5==-1) {
    			return string.substring(index1+1);
    		}
    		
    		return string.substring(index1+1,index5+curr+1 );
    	} catch (RuntimeException e) {
    		throw new TooShortStringException();
    	}
    		
    	
    }

    public static class TooShortStringException extends RuntimeException {
    	
    }

}
