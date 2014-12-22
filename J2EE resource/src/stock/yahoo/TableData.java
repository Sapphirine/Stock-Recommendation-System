package stock.yahoo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableData {
	public List <String> open (ResultSet resultset) throws SQLException {

	    List <String>  open = new ArrayList<>();
	    
	    while (resultset.next()) {
	        open.add(resultset.getString("Open"));
	    }
		return open;
	}
	
	public List <String> volume (ResultSet resultset) throws SQLException {

	    List <String>  volume = new ArrayList<>();
	    
	    while (resultset.next()) {
	        volume.add(resultset.getString("Volume"));
	    }
		return volume;
	}
	
	public List <String> high (ResultSet resultset) throws SQLException {

	    List <String>  high = new ArrayList<>();
	    
	    while (resultset.next()) {
	        high.add(resultset.getString("High"));
	    }
		return high;
	}
	
	public List <String> low (ResultSet resultset) throws SQLException {

	    List <String>  low = new ArrayList<>();
	    
	    while (resultset.next()) {
	        low.add(resultset.getString("Low"));
	    }
		return low;
	}
}
