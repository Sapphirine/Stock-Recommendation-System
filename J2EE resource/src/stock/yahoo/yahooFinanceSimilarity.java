package stock.yahoo;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import stock.datamodel.*;

public class yahooFinanceSimilarity {
	

	public static void main(String[] args) throws IOException {
		String clientSelection="LLEX+RELV";
		findDistance(clientSelection);
	}  
         
	public static List<CustomerStock> findDistance(String clientSelection) throws IOException{
		List<CustomerStock> recStockList=new ArrayList<CustomerStock>();
		//*************************************translate the yahoo api data to String[] array************************************************
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
        
        for(int i=0;i<buf.size();i++){
        	ArrayList<String> list=buf.get(i);
        	//check
        	/*for(int j=0;j<list.size();j++){
        		System.out.print(list.get(j)+" ");
        	}
        	System.out.println();*/
        	//if(list.size()!=7) System.out.print(list.get(0));
        }
        //*****************************************Deal with client seletion****************************************************
        //String clientSelection="LTRE NAVG LSTR SBUX";
        ArrayList<ArrayList<String>> customerStocks=new ArrayList<ArrayList<String>>();
         String[] selectionArr=clientSelection.split("\\+");
         
         for(int i=0;i<selectionArr.length;i++){
        	 String key = "\""+selectionArr[i]+"\"";
        	 if(map.containsKey(key)){
        		 customerStocks.add(map.get(key));
        	 }
         }
         //check
         /*for(ArrayList<String> arr:customerStocks){
        	 for(String str:arr) System.out.print(str+" ");
        	 System.out.println();
         }*/
         //------------------------------------------------bid---------------------------------------------------------------
         
         HashMap<String,ArrayList<HashMap<String,Double>>> customerMap=new HashMap<String,ArrayList<HashMap<String,Double>>>();
         for(ArrayList<String> customerStock:customerStocks){
        	 String customerName=customerStock.get(0);
        	 HashMap<String,Double> bidMap=new HashMap<String,Double>();
        	 
        	 for(ArrayList<String> otherStocks: buf){
             	String othersName=otherStocks.get(0);
             	Double othersBid;
             	if(otherStocks.get(2).equals("N/A")) othersBid=Double.MAX_VALUE-1;
             	else othersBid=Double.parseDouble(otherStocks.get(2));  
             	
             	Double bidDistance=Double.MAX_VALUE-1;
             		if(!customerStock.get(0).equals(othersName)){
             			Double customerBid;
             			if(customerStock.get(2).equals("N/A")) customerBid=Double.MAX_VALUE-1;
                     	else customerBid=Double.parseDouble(customerStock.get(2)); 
             			bidDistance=Math.abs(othersBid-customerBid);
             			bidMap.put(othersName, bidDistance);
             		}
              }
        	
        	  
        	 if(!customerMap.containsKey(customerName)){
        		  customerMap.put(customerName, new ArrayList<HashMap<String,Double>>());
        		  customerMap.get(customerName).add(bidMap);
        	  }
        	  else customerMap.get(customerName).add(bidMap);
         }
       
         
       //----------------------------------Change From 50 Day Moving Average-------------------------------------------
         
         for(ArrayList<String> customerStock:customerStocks){
        	 String customerName=customerStock.get(0);
        	 HashMap<String,Double> changeMAMap=new HashMap<String,Double>();
        	 
        	 for(ArrayList<String> otherStocks: buf){
             	String othersName=otherStocks.get(0);
             	Double othersChangeMA;
             	if(otherStocks.get(3).equals("N/A")) othersChangeMA=Double.MAX_VALUE-1;
             	else othersChangeMA=Double.parseDouble(otherStocks.get(3));  
             	
             	Double changeMADistance=Double.MAX_VALUE-1;
             		if(!customerStock.get(0).equals(othersName)){
             			Double customerChangeMA;
             			if(customerStock.get(3).equals("N/A")) customerChangeMA=Double.MAX_VALUE-1;
                     	else customerChangeMA=Double.parseDouble(customerStock.get(3)); 
             			changeMADistance=Math.abs(othersChangeMA-customerChangeMA);
             			changeMAMap.put(othersName, changeMADistance);
             		}
              }
        	
        	  
        	  
        	 if(!customerMap.containsKey(customerName)){
        		  customerMap.put(customerName, new ArrayList<HashMap<String,Double>>());
        		  customerMap.get(customerName).add(changeMAMap);
        	  }
        	  else customerMap.get(customerName).add(changeMAMap);
         }
        
         //---------------------------------50 Day Moving Average---------------------------------------------
        
         for(ArrayList<String> customerStock:customerStocks){
        	 String customerName=customerStock.get(0);
        	 HashMap<String,Double> mAvgMap=new HashMap<String,Double>();
        	 
        	 for(ArrayList<String> otherStocks: buf){
             	String othersName=otherStocks.get(0);
             	Double othersmAvg;
             	if(otherStocks.get(4).equals("N/A")) othersmAvg=Double.MAX_VALUE-1;
             	else othersmAvg=Double.parseDouble(otherStocks.get(4));  
             	
             	Double mAvgDistance=Double.MAX_VALUE-1;
             		if(!customerStock.get(0).equals(othersName)){
             			Double customerMAvg;
             			if(customerStock.get(4).equals("N/A")) customerMAvg=Double.MAX_VALUE-1;
                     	else customerMAvg=Double.parseDouble(customerStock.get(4)); 
             			mAvgDistance=Math.abs(othersmAvg-customerMAvg);
             			mAvgMap.put(othersName, mAvgDistance);
             		}
              }
        	 
        	  
        	  
        	 if(!customerMap.containsKey(customerName)){
        		  customerMap.put(customerName, new ArrayList<HashMap<String,Double>>());
        		  customerMap.get(customerName).add(mAvgMap);
        	  }
        	  else customerMap.get(customerName).add(mAvgMap);
         }
         
         
         //-----------------------------------------day high and day low-----------------------------------------------------

        
         for(ArrayList<String> customerStock:customerStocks){
        	 String customerName=customerStock.get(0);
        	 HashMap<String,Double> hlMap=new HashMap<String,Double>();
        	 
        	 for(ArrayList<String> otherStocks: buf){
             	String othersName=otherStocks.get(0);
             	Double othersH;
             	Double othersL;
             	if(otherStocks.get(5).equals("N/A")) othersH=Double.MAX_VALUE-1;
             	else othersH=Double.parseDouble(otherStocks.get(5));  
             	
             	if(otherStocks.get(6).equals("N/A")) othersL=Double.MAX_VALUE-1;
             	else othersL=Double.parseDouble(otherStocks.get(6)); 
             	
             	Double othersHL=othersH-othersL;
             	
             	Double hlDistance=Double.MAX_VALUE-1;
             		if(!customerStock.get(0).equals(othersName)){
             			Double customerH;
             			Double customerL;
             			
             			if(customerStock.get(4).equals("N/A")) customerH=Double.MAX_VALUE-1;
                     	else customerH=Double.parseDouble(customerStock.get(4)); 
             			
             			if(customerStock.get(4).equals("N/A")) customerL=Double.MAX_VALUE-1;
                     	else customerL=Double.parseDouble(customerStock.get(4)); 
             			
             			Double customerHL=customerH-customerL;
             			
             			hlDistance=Math.abs(othersHL-customerHL);
             			hlMap.put(othersName, hlDistance);
             		}
              }
        	
        	  
        	 if(!customerMap.containsKey(customerName)){
        		  customerMap.put(customerName, new ArrayList<HashMap<String,Double>>());
        		  customerMap.get(customerName).add(hlMap);
        	  }
        	  else customerMap.get(customerName).add(hlMap);
         }
        
         
         //-------------------------------------check--------------------------------------------------
         /*String key = "\""+"LTRE"+"\"";
         System.out.println(customerMap.get(key).size());*/
         
         //----------------------------------calculate the distance-------------------------------------
         HashMap<String,HashMap<String,Double>>distanceMap=new  HashMap<String,HashMap<String,Double>>();
         HashMap<String,HashMap<String,Double>> similarityMap=new  HashMap<String,HashMap<String,Double>>();
         for(ArrayList<String> customerStock:customerStocks){
        	 String customerName=customerStock.get(0);
        	 CustomerStock cusStock=new CustomerStock();
        	 cusStock.setStockName(customerName);
        	 if(customerMap.containsKey(customerName)){
        		 
        		 ArrayList<HashMap<String, Double>> customerMatrix=customerMap.get(customerName);
        		 
        		 //System.out.println(customerMatrix.size());
        		 //for (Entry<String, Double> entry : customerMatrix.get(3).entrySet()) {
       		    	//System.out.println("customerName: "+customerName+" stockName: "+entry.getKey()+" "+"stock data: "+entry.getValue()+"\n");
       		    // }
        		// double a=customerMatrix.get(0).get("\"LSTR\"");
        		 //System.out.println(a);
        		 
        		 HashMap<String,Double> vectorMap=new HashMap<String,Double>();
        		 
        		 for(ArrayList<String> otherStocks: buf){
        			 String othersName=otherStocks.get(0);
        			 Double bidDistance;
        			 Double changeMADistance;
        			 Double mAvgDistance;
        			 Double hlDistance;
        			 
        			 if(customerMatrix.get(0).containsKey(othersName)){
        				  bidDistance=customerMatrix.get(0).get(othersName);
        			 }
        			 else bidDistance=Double.MAX_VALUE-1;
        			 
        			 if(customerMatrix.get(1).containsKey(othersName)){
       				  changeMADistance=customerMatrix.get(1).get(othersName);
       			     }
       			     else changeMADistance=Double.MAX_VALUE-1;
        			 
        			if(customerMatrix.get(2).containsKey(othersName)){
        				 mAvgDistance=customerMatrix.get(2).get(othersName);
       			     }
       			     else mAvgDistance=Double.MAX_VALUE-1;
        			 
        			 if(customerMatrix.get(3).containsKey(othersName)){
        				 hlDistance=customerMatrix.get(3).get(othersName);
       			     }
       			     else hlDistance=Double.MAX_VALUE-1;
        			 //System.out.println(bidDistance+" "+changeMADistance+" "+mAvgDistance+" "+hlDistance);
        			 double distanceVec=Math.sqrt(Math.pow(bidDistance, 2)+Math.pow(changeMADistance, 2)+Math.pow(mAvgDistance, 2)+Math.pow(hlDistance, 2));
        			 vectorMap.put(othersName, distanceVec);		 
        		 }
        		 
        		 Set<Entry<String,Double>> thismap=vectorMap.entrySet();
        		 TreeMap<String, Double> sorted_vectorMap = SortByValue(vectorMap);
        		
        		 
        		 if(!distanceMap.containsKey(customerName)){
        			 distanceMap.put(customerName,vectorMap);
        			 similarityMap.put(customerName,new HashMap<String,Double>());
        			 count=0;
        			 //Euclidean Distance Similarity
        			 for (Entry<String, Double> entry : sorted_vectorMap.entrySet()) {
        				 Double similarity=1/(1+entry.getValue());//calculate similarity
        				 similarityMap.get(customerName).put(entry.getKey(),similarity);
           		    	if(count<5){
           		    		
                            java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.0000");  
           		    		
           		    		String recStockName=entry.getKey();
           		    		String similar=df.format(similarity);
           		    		
           		    		String recStockBid=map.get(recStockName).get(2);
           		    		String recStockChangeMA=map.get(recStockName).get(3);
           		    		String recStockMavg=map.get(recStockName).get(4);
           		    		String recStockDH=map.get(recStockName).get(5);
           		    		String recStockDL=map.get(recStockName).get(6);
           		    		//System.out.print("customerName: "+customerName+" stockName: "+recStockName+" "+"stock similarity: "+similarity+"\n");
           		    		RecStock recStock=new RecStock(recStockName,similar,customerName,recStockBid,recStockChangeMA,recStockMavg,recStockDH,recStockDL);
           		    		
           		    		List<RecStock> tmp= cusStock.getSimilarityBlock();
           		    		tmp.add(recStock);
           		    		count++;
           		    	}
           		     }
        			 System.out.println();
        			 recStockList.add(cusStock);
        		 }
        		 
        	 }
         }
        // System.out.println(recStockList.get(0).getStockName());
        // System.out.println(recStockList.get(1).getStockName());
         return recStockList;
	}
	public static TreeMap<String, Double> SortByValue (HashMap<String,Double> map) {
		distanceComparator vc =  new distanceComparator(map);
	    TreeMap<String, Double> sortedMap = new TreeMap<String, Double>(vc);
	    sortedMap.putAll(map);
	    return sortedMap;
	    }
}



class distanceComparator implements Comparator<String> { 
    Map<String, Double> map; 
    public distanceComparator(Map<String, Double> base) {
        this.map = base;
    }
 
    public int compare(String a, String b) {
        if (map.get(a) >= map.get(b)) {
            return 1;
        } else {
            return -1;
        } 
    }


}
