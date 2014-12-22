package stock.yahoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class StringList {
	public HashMap<String,HashMap<String,Double>> getScoreList (List <List <String> > open, List <List <String> > volume) {
		HashMap<String,HashMap<String,Double>> ScoreList = new HashMap<String,HashMap<String,Double>>();
		List <List <String> >  openchange = getchange(open);
		List <List <String> >  volumechange = getchange(volume);
		List <List <String> > getList = stringGenerate(openchange, volumechange);
		JaroWinkler jaro = new JaroWinkler();
		for (int i = 0; i < getList.size(); i++){
			HashMap <String,Double> row = new HashMap <String,Double>();			
			for (int j = 0; j < getList.size(); j++){
				double value = jaro.compare(getList.get(i).get(1),getList.get(j).get(1));
				String name = getList.get(j).get(0);					
				//name = name.substring(name.indexOf("\"")+1,name.lastIndexOf("\""));
				row.put(name, value);
				
			}
			//System.out.println(row.size());
			String name1 = getList.get(i).get(0);
			//TreeMap<String, Double> sorted_row = SortByValue(row);
			
			//name1 = name1.substring(name1.indexOf("\"")+1,name1.lastIndexOf("\""));
			ScoreList.put(name1, row);
		}
		//System.out.println(ScoreList.size());

		return ScoreList;
	}
	
	public  List <List <String> > getList (List <List <String> > open, List <List <String> > volume) {
		List <List <String> >  openchange = getchange(open);
		List <List <String> >  volumechange = getchange(volume);
		List <List <String> > getList = stringGenerate(openchange, volumechange);
		return getList;
	}
	
	private static  List <List <String> > getchange(List <List <String> > array){
		List <List <String> > result = new ArrayList<>();
		for (int i = 0; i < array.size(); i++){
			List <String> row = new ArrayList<>(array.get(i).size());
			row.add(array.get(i).get(0));
			for (int j = 2; j < array.get(i).size(); j++){				
				String a = array.get(i).get(j);
				String b = array.get(i).get(j-1);
				double c = Double.parseDouble(a);
			    double d = Double.parseDouble(b);
			    String r = "0";
			    if ( c > d && ( c*d != 0)){
			    	r = "1";
			    }
			    else if ( c< d && ( c*d != 0)){
			    	r = "-1";
			    }
			    row.add(r);
			}
			//System.out.println(row);
			result.add(row);
		}
		//System.out.println(result);
		return result;
	}
	
	private static List <List <String>>  stringGenerate(List <List <String> > openchange, List <List <String> > volumechange){
		List <List <String> > result = new ArrayList<>();
		for (int i = 0; i < openchange.size(); i++){
			List <String> row = new ArrayList<>(openchange.get(i).size());
			row.add(openchange.get(i).get(0));
			StringBuffer list = new StringBuffer(openchange.get(i).size());
			if (openchange.get(i).size() == 1) { list.append("e");}
			else {
			for (int j = 1; j < openchange.get(i).size(); j++){				
				String a = openchange.get(i).get(j);
				String b = volumechange.get(i).get(j);
				int c = Integer.parseInt(a);
			    int d = Integer.parseInt(b);
			    if ( c*d > 0 ){
			    	list.append("a");
			    }
			    else if ( c*d <0){
			    	list.append("b");
			    }
			    else if ( c*d == 0){
			    	list.append("c");
			    }
			    else {
			    	list.append("d");
			    }
			}
			}
			row.add(list.toString());
			//System.out.println(row);
			result.add(row);
		}		
		
		//System.out.println(result);
		return result;
	}
	
	public  List <String>  arraychange(List <List <String> > array){
		List <String>  result = new ArrayList<>();
		for (int i = 0; i < array.size(); i++){
			StringBuffer row = new StringBuffer(array.get(i).size());
			for (int j = 2; j < array.get(i).size(); j++){				
				String a = array.get(i).get(j);
				String b = array.get(i).get(j-1);
				double c = Double.parseDouble(a);
			    double d = Double.parseDouble(b);
			    String r = "c";
			    if ( c > d && ( c*d != 0)){
			    	r = "a";
			    }
			    else if ( c< d && ( c*d != 0)){
			    	r = "b";
			    }
			    row.append(r);
			}
			//System.out.println(row);
			result.add(row.toString());
			
		}
		//System.out.println(result);
		return result;
	}
	

	
}
