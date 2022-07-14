public class ThreadsTraining {

    public static void main(String[] args) {
        new ThreadsTraining().task3();
    }

    public void task3(){
        StringBuilder sb = new StringBuilder();
        sb.append('a');
        new TaskThread(sb).start();
        new TaskThread(sb).start();
        new TaskThread(sb).start();

    }


    public static void task1(){
        new NewThread().start();

    }
    public static void task2(){
        new Thread(new NewRunnable()).start();
        new Thread(new NewRunnable()).start();
        new Thread(new NewRunnable()).start();
    }

    public static class NewThread extends  Thread{
        @Override
        public void run(){
            for (int i = 0; i <100 ; i++) {{
                System.out.println((char) i);
            }
            }
        }
    }

    public static class NewRunnable implements Runnable{

        @Override
        public void run() {
            for (int i=0; i<100; i++){
                if(i%10==0){

                    System.out.println(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    public class TaskThread extends Thread{
        private StringBuilder sb;
        public TaskThread(StringBuilder sb){
            this.sb = sb;
        }
        @Override
        public void run(){

            synchronized (this.sb){//синхонизация по этому объекту
                System.out.println("Зашел поток "+this.getName());
                //поток заходит - проделывает работу, выходит, потом пускает следующий.
                for (int i = 0; i < 100; i++) {
                    System.out.print(sb.toString());
                }

                sb.setCharAt(0,(char) (sb.toString().charAt(0)+1));
                System.out.println();
            }
        }
    }
}
