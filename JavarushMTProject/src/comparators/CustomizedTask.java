package comparators;

import java.util.Comparator;

public class CustomizedTask {
	
	
	public static void main(String[] args) {
		
	}
	
	
	public static class CustomizedComparator<T> implements Comparator<T> {
		private Comparator<T>[] comparators;
		
		public  CustomizedComparator(Comparator<T>... comparators) {
			this.comparators = comparators;
		}
		
		
		@Override
		public int compare(T o1, T o2) {
			for(Comparator<T> comp: comparators) {
				if(comp.compare(o1, o2)!=0) {
					return comp.compare(o1, o2);
				}
			}
			return 0;
		}
		
		
	}

}
