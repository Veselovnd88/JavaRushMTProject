package ClassesExtends.innerAnonymus;

import java.util.LinkedList;
import java.util.List;

/* 
Рефакторинг, анонимные классы
*/

public class Solution {
    public static List<Iterator> iterators = new LinkedList<>();//лист итераторов

    private int countItems;//поле инт количество айтемов

    public Iterator getIterator(final String name) {

        return new Iterator() {//создаем ноыве анонимный класс от интерфейса
        	
        	{
        		countItems++;//статик блок которые служит конструктором
        		//код выполняется при создании экземпляра
        		System.out.println(name+ " item "+ countItems);
        	}
			@Override
			public Iterator next() {
				return getIterator(name);//вызывается тот же самый метод
			}
        	
        };
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        Iterator iterator = solution.getIterator("iterator");
        for (int i = 1; i < 5; i++) {
            iterators.add(iterator.next());
        }
    }
}

