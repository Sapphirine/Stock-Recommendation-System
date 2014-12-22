package stock.datamodel;

public class RecStock {
	private String recStockName;
	private String similarity;
	private String stockName;
	private String recStockBid;
	private String recStockChangeMA;
	private String recStockMavg;
	private String recStockDH;
	private String recStockDL;
	
	public RecStock(String referencedStockName, String similarity, String stockName,String recStockBid,String recStockChangeMA,String recStockMavg,String recStockDH,String recStockDL) {
		this.recStockName =referencedStockName;
		this.similarity=similarity;
		this.stockName=stockName;
		this.recStockBid=recStockBid;
		this.recStockChangeMA=recStockChangeMA;
		this.recStockMavg=recStockMavg;
		this.recStockDH=recStockDH;
		this.recStockDL=recStockDL;
	}
	public String getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = new String(""+similarity);
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
