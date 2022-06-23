package subs;

public class TelephoneNumber {
	
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) {
            return false;
        }
        return (telNumber.matches("^\\+(\\d[()]?){12}$")
        		//^в начале строки один символ + 
        		//далее выражение только цифры [()]? = одна или ноль скоборк - всего 12 того что в скобках
        		//
        		
        		
        		
        		|| telNumber.matches("^([()]?\\d){10}$"))//если в начинаюится со скобок ииои нет и далее в конце обязательно 10 цифр
                && telNumber.matches("^(\\+)?(\\d+)?(\\(\\d{3}\\))?\\d+$");
        		//может начинаться на +, далее много цифры далее скобка, если есть то в ней максимум 3 цифнры, заканчиваются обязательно на цифры
        
        
    }

    public static void main(String[] args) {
    	
    	
    	/*+380501234567 - true
+38(050)1234567 - true
(050)1234567 - true
0(501)234567 - true
+38)050(1234567 - false
+38(050)123-45-67 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false

https://regexr.com
    	 * */
    	
    	

    }

}
