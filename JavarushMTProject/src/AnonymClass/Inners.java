package AnonymClass;

public class Inners {
	
    private String name;

    Inners(String name) {
        this.name = name;
    }//при создании базового класса у нас инициализируется поле name

    public String getName() {
        return name;
    }

    private void sout() {
        new Inners("The Darkside Hacker") {
            void printName() {
                System.out.println(getName());
            }
        }.printName();
    }

    public static void main(String[] args) {
    	/*так как класс приватные методы не наследуются, то когжа у нас метод getName приватны
    	 * то новый класс наследник берет его, если мы делаем его публичным - то у нашего экземпляра появляется свой
    	 * метод гетНэйм  и он забирает своё имя
    	 * */
    	
        new Inners("Risha").sout();
    }
}
