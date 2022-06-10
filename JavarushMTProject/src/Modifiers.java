import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
/*Реализовать логику метода isModifierSet, который проверяет, 
 * содержит ли переданный параметр allModifiers значение конкретного
 *  модификатора specificModifier.

P.S. Перед выполнением задания ознакомься с классом Modifier и 
реализацией методов isPublic, isStatic и т.п.
 * */
public class Modifiers {
	public static void main(String[] args) {
        int classModifiers = Modifiers.class.getModifiers();
        System.out.println(isModifierSet(classModifiers, Modifier.PUBLIC));   //true
        System.out.println(isModifierSet(classModifiers, Modifier.STATIC));   //false

        int methodModifiers = getMainMethod().getModifiers();
        System.out.println(isModifierSet(methodModifiers, Modifier.STATIC));      //true
    }

    public static boolean isModifierSet(int allModifiers, int specificModifier) {
    	//метод гетМодифаерс возвращает битовое значение модификаторов
    	//а каждый из модификаторов обозначается также в двоичной системе
    	//соответственно для того чтобы понять есть ли этот модификатор или нет применяем
    	//побитовое AND-> если полученное значение > 0 значит такой модификатор есть
    	//например паблик 0000 0001, Статик 0000 1000.
        return (allModifiers&specificModifier)>0;
    }

    private static Method getMainMethod() {
        Method[] methods = Modifiers.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase("main")) return method;
        }

        return null;
    }
}
