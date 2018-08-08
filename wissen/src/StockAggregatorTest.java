import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface StatisticsAggregator {
    // Price feed will call this method when new price is received. This is input to your class.
    public void putNewPrice(String symbol, double price);
    // Report the average price of the stock symbol.
    public double getAveragePrice(String symbol);
    // Report the total number of prices received for the symbol.
    public int getTickCount(String symbol);
}

class StockData {

    private List<Double> prices;
    private Double sum = new Double(0);

    StockData(){
        prices = new ArrayList<>();
    }

    public void addPrice(Double price){
        prices.add(price);
        sum+= price;
    }

    public Double getAverage(){
        if(prices.isEmpty()){
            return Double.valueOf(0);
        }
        return sum/Double.valueOf(prices.size());
    }

    public int getCount(){
        return prices.size();
    }
}

class StatisticsAggregatorImpl implements StatisticsAggregator {

    private ConcurrentMap<String,StockData> stockDataMap = null;

    public StatisticsAggregatorImpl(){
        stockDataMap = new ConcurrentHashMap<>();
    }


    @Override
    public void putNewPrice(String symbol, double price) {
        if(symbol == null){
            return;
        }
        StockData data = new StockData();
        data.addPrice(price);
        if(null != stockDataMap.putIfAbsent(symbol,data)){
            stockDataMap.get(symbol).addPrice(price);
        }
    }

    @Override
    public double getAveragePrice(String symbol) {
        return stockDataMap.containsKey(symbol) ? stockDataMap.get(symbol).getAverage() : Double.valueOf(0);
    }

    @Override
    public int getTickCount(String symbol) {
        return stockDataMap.containsKey(symbol) ? stockDataMap.get(symbol).getCount() : 0;
    }
}

public class StockAggregatorTest {

    public static void main(String[] args) {
        StatisticsAggregator statisticsAggregator = new StatisticsAggregatorImpl();
        statisticsAggregator.putNewPrice("A", 0);
        statisticsAggregator.putNewPrice("A", 0);
        statisticsAggregator.putNewPrice("A", 0);
        System.out.println(statisticsAggregator.getAveragePrice("A"));
        System.out.println(statisticsAggregator.getTickCount("A"));
        System.out.println(statisticsAggregator.getTickCount("B"));
        System.out.println(statisticsAggregator.getAveragePrice("B"));


    }
}