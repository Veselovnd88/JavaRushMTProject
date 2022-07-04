package enumsTasks;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public enum Column implements Columnable {
    Customer("Customer"),//0
    BankName("Bank Name"),//1
    AccountNumber("Account Number"),//2
    Amount("Available Amount");//3

    private String columnName;

    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Задает новый порядок отображения колонок, который хранится в массиве realOrder.
     * realOrder[индекс в энуме] = порядок отображения; -1, если колонка не отображается.
     *
     * @param newOrder новая последовательность колонок, в которой они будут отображаться в таблице
     * @throws IllegalArgumentException при дубликате колонки
     */
    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
        	//System.out.println(column.ordinal());
            realOrder[column.ordinal()] = -1;//назначили в ячейку массива -1 (для третьей колонки =-1) 
           // System.out.println(Arrays.toString(realOrder));
            boolean isFound = false;//

            for (int i = 0; i < newOrder.length; i++) {//итерация по массивку с номерами колонок
                if (column == newOrder[i]) {//взяли колону из первого массива - колонца с ординал 3
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;//в ячейку массива присвоили ординал столбца, 
                    isFound = true;
                }
            }
        }
        System.out.println(Arrays.toString(realOrder));
    }

    /**
     * Вычисляет и возвращает список отображаемых колонок в сконфигурированом порядке (см. метод configureColumns)
     * Используется поле realOrder.
     *
     * @return список колонок
     */
    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>();
        int j=0;//позиция по порядку
        while(j<realOrder.length) {//итерируемся по порядку пока не будет макс позиция
        	for(int i=0; i<realOrder.length; i++) {//идем по всем элементам массива

        	if(realOrder[i]!=-1 &&realOrder[i]==j) {//если позиция равна порядковой - добавляем в список
        		result.add(Column.values()[i]);
        		
				
        		}	
        	} j++;// увеличиваем позицию
        	}

        return result;
    }

	@Override
	public String getColumnName() {
		
		return this.columnName;
	}

	@Override
	public boolean isShown() {
		
		return realOrder!=null && realOrder[this.ordinal()]!=-1;
	}

	@Override
	public void hide() {
		int currentPos = realOrder[this.ordinal()];//взяли текущую позицию
		
		realOrder[this.ordinal()]=-1;//спрятали
		for(int i = 0; i<realOrder.length;i++) {
			if(realOrder[i]>=currentPos) {//идем по массиву, позиция столбца больше или равна той которую мы спрятали
				realOrder[i] = realOrder[i]-1;// то вычитаем -1 (смещаем влево)
			}
		}

		
	}
}

