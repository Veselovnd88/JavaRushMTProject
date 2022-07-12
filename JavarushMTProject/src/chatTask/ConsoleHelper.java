package chatTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	
	
	
	
	public static void writeMessage(String message) {
		System.out.println(message);
	}
	
	public static String readString() {
		while(true) {
			try {
				String str = br.readLine();

				return str;
			}catch (IOException e) {
				System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
			}
		}
	}
	public static int readInt() {
		while(true) {
		try {
			Integer i = Integer.valueOf(readString());
			return i;}
		catch(NumberFormatException e) {
			System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
		}
	}
		}

}
