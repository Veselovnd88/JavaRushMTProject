package comparators;

import java.util.Arrays;
import java.util.Comparator;


public class MedianTask {
	
    public static void main(String[] args) {
    	Integer[] myArr = new Integer[] {13,8,15,5,16};
    	System.out.println(Arrays.toString(myArr));
    	System.out.println(Arrays.toString(sort(myArr)));
    	
    }

    public static Integer[] sort(Integer[] array) {
    	final double median;//нахождение медианы - для того чтобы потом передать в компаратор - файнал
		        Arrays.sort(array);
		        if(array.length%2!=0) {
		        	median = array[array.length/2];
		        }
		        else {
		        	median = (array[array.length/2]+array[array.length/2+1])/2;
		        }
    	
    	
        Arrays.sort(array,  new Comparator<Integer>(){   	
			@Override
			public int compare(Integer o1, Integer o2) {

				return (int) ((o1.intValue()-median)*(o1.intValue()-median) - (o2.intValue()-median)*(o2.intValue()-median))*100;//рассчет расстояния
			}

        });
        return array;
    }

}
