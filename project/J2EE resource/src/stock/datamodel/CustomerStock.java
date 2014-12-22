package stock.datamodel;

import java.util.ArrayList;
import java.util.List;

public class CustomerStock {
	private String stockName;
	private List<RecStock> similarityBlock;
	private List<CalStock> calculationBlock;
	
	public CustomerStock() {
		this.similarityBlock=new ArrayList<RecStock>();
		this.calculationBlock=new ArrayList<CalStock>();
	}
	
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public List<RecStock> getSimilarityBlock() {
		return similarityBlock;
	}
	public List<CalStock> getCalculationBlock() {
		return calculationBlock;
	}
	public void setSimilarityBlock(List<RecStock> similarityBlock) {
		this.similarityBlock = similarityBlock;
	}
	
}
