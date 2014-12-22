package stock.yahoo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//Save ResultSet to 2-D ArrayList
public class Table2Array {
	public List <List <String> > result (ResultSet resultset) throws SQLException {
		int numcols = resultset.getMetaData().getColumnCount();
	    List <List <String> > result = new ArrayList<>();
	    
	    while (resultset.next()) {
	        List <String> row = new ArrayList<>(numcols); // new list per row

	        for (int i=1; i<= numcols; i++) {  
	            row.add(resultset.getString(i));
	           // System.out.print(resultset.getString(i) + "\t");
	        }
	        result.add(row); // add it to the result
	       // System.out.print("\n");
	      
	    }	
		return result;
	}
}
