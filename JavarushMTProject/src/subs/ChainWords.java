package subs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ChainWords {
	
	
    	
        public static void main(String[] args) {
            List<String> list = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())))) {
                while (fileReader.ready()) {
                    list.add(fileReader.readLine());
                }
            } catch (IOException ignored) {
            }//добавление в список всех строк из файла

            List<String> resultList = new ArrayList<>();
            for (String line : list) {
                StringTokenizer tokenizer = new StringTokenizer(line);// добавленгие всех слов токенайзер, и потом из токенайзера в список всех слов
                while (tokenizer.hasMoreTokens()) {
                    resultList.add(tokenizer.nextToken());
                }
            }

            StringBuilder result = getLine(getWords(resultList));
            System.out.println(result.toString());
        }

        private static String[] getWords(List<String> list) {// достали из листа все слова в массив
            String[] array = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }
            return array;
        }

        private static boolean isTheSameChars(String firstWord, String secondWord) {
            if (firstWord.endsWith(" ")) {
                firstWord = firstWord.substring(0, firstWord.length() - 1);// проверка на окончание пробелом
            }
            return firstWord.isEmpty() || (secondWord != null &&
                    Character.toUpperCase(firstWord.charAt(firstWord.length() - 1)) == Character.toUpperCase(secondWord.charAt(0)));//сравнение последнего символа
            // первого слова и первого символа второго слова
        }

        private static <T> T getLastElement(List<? extends T> list) {
            return list.get(list.size() - 1);
        }


        public static StringBuilder getLine(String... words) {
            StringBuilder builder = new StringBuilder();
            List<Integer> list = new ArrayList<>();// список индексов
            for (int i = 0; i < words.length; i++) {
                list.add(i);//добавили в новый список индекс
                if (findSolutions(list, words)) {
                    for (Integer integer : list) {
                        builder.append(words[integer]);
                        builder.append(" ");
                    }
                    return builder;
                }
                list.remove(Integer.valueOf(i));
            }

            return builder;
        }

        private static boolean findSolutions(List<Integer> list, String... words) {
            if (list.size() == words.length) {
                return true;
            }// если результирующий список равен списку всех слов то тру - решение найден
            for (int i = 0; i < words.length; i++) {
                if (isValid(list, words[i], words)) {// проверка слов если предпоследнее слово списка валидно с переданным словом - то тру
                	//добавляем это слово в список - проверяем дальше
                    list.add(i);
                    if (findSolutions(list, words)) {
                        return true;
                    }
                    list.remove(Integer.valueOf(i));//если не решение до слово удаляется / и так рекурсивно по всему массиву
                }
            }
            return false;
        }

        private static boolean isValid(List<Integer> list, String word, String... words) {
            for (Integer integer : list) {
                if (words[integer].equals(word)) {
                    return false;
                }//если одинаковые слова то фолс
            }
            return isTheSameChars(words[getLastElement(list)], word);//последнее слово из списка с переданным слово
        }

}
