package stock.datamodel;

public class CalStock {
	private String recStockName;
	private String similarity;
	private String stockName;
	private String calStockBid;
	private String calStockChangeMA;
	private String calStockMavg;
	private String calStockDchange;
	private String distance;

	public CalStock(String recStockName, String similarity, String customerName, String calStockBid,
			String calStockChangeMA, String calStockMavg, String calStockDchange, String calDistance) {
		this.recStockName =recStockName;
		this.similarity=similarity;
		this.stockName=customerName;
		this.calStockBid=calStockBid;
		this.calStockChangeMA=calStockChangeMA;
		this.calStockMavg=calStockMavg;
		this.calStockDchange=calStockDchange;
		this.distance=calDistance;
	}

	public String getReferencedStockName() {
		return recStockName;
	}
	public void setReferencedStockName(String referencedStockName) {
		this.recStockName = referencedStockName;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

}
