package stock.yahoo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import stock.datamodel.RecStock;

public class YahooStockInfo {
	public static RecStock getStockInfo(String stockName) throws IOException{
		File file = new File("/Users/Kevin/Desktop/stock_info_2.txt");
        ArrayList<ArrayList<String>> buf=new ArrayList<ArrayList<String>>();
        HashMap<String,ArrayList<String>> map=new HashMap<String,ArrayList<String>>();
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String tempString = null;
        
        int count=0; 
        while(count<3000){
        	ArrayList<String> list=new ArrayList<String>();
        	tempString=reader.readLine();
        	if(tempString != null) {
        		String[] cur=tempString.split(",");
      	         for(int i=0;i<cur.length;i++){
      	        	 list.add(cur[i]);
      	         }
      	         if(cur.length>7){
      	        	 String comb=list.get(1)+","+list.get(2);
      	        	 list.remove(1);list.remove(1);
      	        	 list.add(1, comb);
      	         }
      	       buf.add(list);
      	       map.put(list.get(0),list);
   	         }
        	count++;
        }
        stockName="\""+stockName+"\"";

        if(map.containsKey(stockName)){
        	String recStockName=map.get(stockName).get(0);
        	String recStockBid=map.get(stockName).get(2);
        	String recStockChangeMA=map.get(stockName).get(3);
        	String recStockMavg=map.get(stockName).get(4);
        	String recStockDH=map.get(stockName).get(5);
        	String recStockDL=map.get(stockName).get(6);
        	RecStock rec=new RecStock(recStockName,null,null,recStockBid,recStockChangeMA,recStockMavg,recStockDH,recStockDL);
        	return rec;
        } 	
        return null;
	}

}
