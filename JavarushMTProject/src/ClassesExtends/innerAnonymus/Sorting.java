package ClassesExtends.innerAnonymus;

//package com.javarush.task.task24.task2412;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Знания - сила!
*/

public class Sorting {
    public static void main(String[] args) {
        List<Stock> stocks = getStocks();//получили список акций
        sort(stocks);//отсротировали
        Date actualDate = new Date();
        printStocks(stocks, actualDate);//напечатали
    }

    public static void printStocks(List<Stock> stocks, Date actualDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        double[] filelimits = {0d, actualDate.getTime()};//тут указываются пределы - если до 0 вкл - то даст первый, если от актуальной даты - то второй
        String[] filepart = {"change {4}", "open {2} and last {3}"};//в зависимрости от выбора этого поля - мы выбираем - поле 4, либо поля 2 и 3

        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);// формат выбора, теперь мы должны установить этот формат к определенному полю
        
        double[] filelimits2 = {0d, 20d, 26d};
        
        String[] filepart2 = {"hello {4}", "second choice {2}","third choice {3}"};
        
       ChoiceFormat fileform2 = new ChoiceFormat(filelimits2,filepart2);
        
        Format[] testFormats = {fileform2,null, dateFormat, fileform};//тут форматы - у нас 4 поля, к первым двум никакие форматы не еприменимы, к другим двум - применимы
        MessageFormat pattform = new MessageFormat("{0}   {1} | {5} {6}");//
        pattform.setFormats(testFormats);

        for (Stock stock : stocks) {
            String name = ((String) stock.get("name"));
            int len = name.length();
            String symbol = (String) stock.get("symbol");
            double open = !stock.containsKey("open") ? 0 : ((double) stock.get("open"));
            double last = !stock.containsKey("last") ? 0 : ((double) stock.get("last"));
            double change = !stock.containsKey("change") ? 0 : ((double) stock.get("change"));
            Date date = (Date) stock.get("date");
            Object[] testArgs = {len, symbol, open, last, change, date, date.getTime()};//тут семь полей, из которых мы юзаем 4 - имя, символ, 5 - дата,  6- дата в мс
            System.out.println(pattform.format(testArgs));
        }
    }

    public static void sort(List<Stock> list) {
        list.sort(new Comparator<Stock>() {
        	int compare;
        	public int compareName(Stock stock1, Stock stock2) {
        		String name1 = (String) stock1.get("name");
                String name2 = (String) stock2.get("name");
                return name1.compareTo(name2);
        	}
        	
        	public int compareDate(Stock stock1, Stock stock2) {
        		long day = 24*60*60*1000;
        		Long d1 = ((Date) stock1.get("date")).getTime()/day;      
        		Long d2 = ((Date) stock2.get("date")).getTime()/day;   
        		return d1.compareTo(d2);
        	}
        	//компаратор на дату, потом компаратор на по прибыли
        	

            private int compareProfit(Stock stock1, Stock stock2) {
                int profit1 = getProfitPercentage(stock1);
                int profit2 = getProfitPercentage(stock2);
                return profit2 - profit1;
            }

            private int getProfitPercentage(Stock stock) {
                double res;
                if (stock.containsKey("change")) {
                    res = !stock.containsKey("change") ? 0 : ((double) stock.get("change"));//если есть ключ разница  то  если нет то 0, если есть то берем разница
                } else if (stock.containsKey("open") && stock.containsKey("last")) {
                    double open = ((double) stock.get("open"));//либо считаем
                    double last = ((double) stock.get("last"));
                    res = last - open;
                } else {
                    throw new RuntimeException("Incorrect data");
                }

                return (int) (res * 1000);
            }
        	
        	
        	
            public int compare(Stock stock1, Stock stock2) {
            	int compName = compareName(stock1, stock2);
            	
            	if(compName==0) {
            		int compDate = compareDate(stock1, stock2);
	            		if(compDate==0) {
	            			return compareProfit(stock1, stock2);
	            		}
            		return compDate;
            		
            	} return compName;
            	
            	
            	
            	
            	
            }
        });
    }

    public static class Stock extends HashMap<String, Object> {
        public Stock(String name, String symbol, double open, double last) {
            put("name", name);
            put("symbol", symbol);
            put("open", open);
            put("last", last);
            put("date", getRandomDate(2020));
        }

        public Stock(String name, String symbol, double change, Date date) {
            put("name", name);
            put("symbol", symbol);
            put("date", date);
            put("change", change);
        }
    }

    public static List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList<>();

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 512.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 21.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 29.72));

        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, getRandomDate()));
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, getRandomDate()));
        stocks.add(new Stock("Fake Starbucks Corporation", "SBUX", .03, getRandomDate()));
        stocks.add(new Stock("Fake Yahoo! Inc.", "YHOO", .32, getRandomDate()));
        stocks.add(new Stock("Fake Applied Materials, Inc.", "AMAT", .26, getRandomDate()));
        stocks.add(new Stock("Fake Comcast Corporation", "CMCSA", .5, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));

        return stocks;
    }

    public static Date getRandomDate() {
        return getRandomDate(1970);
    }

    public static Date getRandomDate(int startYear) {
        int year = startYear + (int) (Math.random() * 30);
        int month = (int) (Math.random() * 12);
        int day = (int) (Math.random() * 28);
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }
}


