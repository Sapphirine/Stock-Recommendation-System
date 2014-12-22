package stock.yahoo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

import stock.datamodel.CustomerStock;

public class YahooWebServiceGet {	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
   }
	
	public static List<CustomerStock> yahooGet(String input) throws IOException{
		File file = new File("/Users/Garfield/Desktop/bigdata/table.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        ArrayList<ArrayList<String>> buf=new ArrayList<ArrayList<String>>();
         
        BufferedWriter writer=new BufferedWriter(new FileWriter("/Users/Garfield/Desktop/bigdata/stock_info_2.txt"));
        String tempString = null;
        
        //0 to 200
        int count=0; 
        int index=1;

        while(index<=15){
        	ArrayList<String> list=new ArrayList<String>();
            count=0;
           while (count<index*200) {
        	    if(count>=(index-1)*200){
        	       tempString = reader.readLine();
        	       if(tempString != null) {
        	    	 String[] cur=tempString.split("\\|");
        	         String cell=cur[0];
        	         list.add(cell);
        	         }
        	    }	
          count++;
        }
        buf.add(list);
        index++;
      } 
		
        for(int i=0;i<buf.size();i++){
		String request = "http://finance.yahoo.com/d/quotes.csv?s=";
		ArrayList<String> list=buf.get(i);
		   for(int j=0;j<list.size()-1;j++){
			   request+=list.get(j)+"+";
		    }
		   if(list.size()>1) request+=list.get(list.size()-1);
		   request+="&f=snbm7m3gh";
		
           HttpClient client = new HttpClient();
            GetMethod method = new GetMethod(request);
        
        // Send GET request
        int statusCode = client.executeMethod(method);
        
        if (statusCode != HttpStatus.SC_OK) {
        	System.err.println("Method failed: " + method.getStatusLine());
        }
        InputStream rstream = null;
        
        // Get the response body
        rstream = method.getResponseBodyAsStream();
        
        // Process the response from Yahoo! Web Services
        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            writer.write(line+"\n");
        }
        br.close();
    }    
        writer.close();
        yahooFinanceSimilarity yahoo=new yahooFinanceSimilarity();
        return yahoo.findDistance(input);
        
	}

}