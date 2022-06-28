package ClassesExtends.blackbox;

/* 
Black box
*/

public class Solution implements Action {
    public static int countActionObjects;
    private int param;

    private Action solutionAction = new Action() {
    	
    	private FirstClass fc;
    	private SecondClass sc;
    	
		@Override
		public void someAction() {
			if (param >0) {
				if(fc==null)
					fc = new FirstClass() {			
						@Override
						public void someAction() {
							super.someAction();//вызываем метод родительского класса - (это печатает строку class
							Solution.this.someAction();//вызываем метод экземпляра
						}
						@Override
						public Action getDependantAction() {
							System.out.println(param);
							param--;
							return param > 0 ? Solution.this : this;
							
							// если параметр больше нуля, то возвращается экземпляр Solution.Action, если меньше - то метод Fc
							//этот метод возвращает Действие - дальше сразу вызывается его метод СамЭкшн
						}
				
				};
				fc.getDependantAction().someAction();
			}
			else { 
				if(sc==null) {
					sc = new SecondClass() {
						@Override
						public  void someAction() {
							sb.append(SecondClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM+param);
							super.someAction();
							
						}
					};
					sc.someAction();
				}
				
			}

			
		}
    	
    };


    public Solution(int param) {
        this.param = param;
    }

    @Override
    public void someAction() {
        solutionAction.someAction();
    }

    /**
     * 5
     * 4
     * 3
     * 2
     * 1
     * class FirstClass, method someAction
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = 0
     * Count of created Action objects is 2
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = -1
     * Count of created Action objects is 3
     */
    public static void main(String[] args) {
        Solution solution = new Solution(5);//создан экземпляр солюшн, парам =5
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);

      solution = new Solution(-1);
      solution.someAction();
      System.out.println("Count of created Action objects is " + countActionObjects);
    }
}

