package stock.yahoo;


import java.sql.*;
import java.util.*;


public class DBTest {

	private Statement sta;

	public static void main(String args[]) {
		try {
			JDBCTool t = new JDBCTool();
			DBTest operator = new DBTest();
			ResultSet rs1,rs2,rs3,rs4; 
			for (int i = 0; i<10; i++){
				
			}
			String sqlCommand; 
			t.setURL("jdbc:mysql://localhost/stock");
			t.setUser("root");
			t.setPassword("");
			Connection con = t.getConnection();
			StringBuffer s = new StringBuffer();
			
			try (Statement sta1 = con.createStatement();
					Statement sta2 = con.createStatement();
					Statement sta3 = con.createStatement();
					Statement sta4 = con.createStatement();
					) { 
/*
 * CREATE TABLE FOR STOCK DATA
 * 
 *
				sqlCommand = "CREATE TABLE openprice ("
							+"Symbol VARCHAR(300),day1 VARCHAR(300),"
							+"day2 VARCHAR(300),day3 VARCHAR(300),day4 VARCHAR(300),PRIMARY KEY(Symbol))";
				sta1.executeUpdate(sqlCommand);	
				
				sqlCommand = "CREATE TABLE stockvolume ("
							+"Symbol VARCHAR(300),day1 VARCHAR(300),"
							+"day2 VARCHAR(300),day3 VARCHAR(300),day4 VARCHAR(300),PRIMARY KEY(Symbol))";
				sta1.executeUpdate(sqlCommand);	
					
				sqlCommand = "CREATE TABLE day1 ("
						+"Symbol VARCHAR(300),Previous_Close VARCHAR(300), "
						+"Open VARCHAR(300), Day_Low VARCHAR(300), Day_High VARCHAR(300),"
						+"Volume VARCHAR(300), Market_Capitalization VARCHAR(300),PRIMARY KEY(Symbol))";
				sta1.executeUpdate(sqlCommand);	
				sqlCommand = "load data local infile "
						+" 'D://stock_12_1_2014.txt' "
						+"into table day1 fields terminated by ';' "
						+"lines terminated by ';\n'";
				sta1.executeUpdate(sqlCommand);
				
				sqlCommand = "CREATE TABLE day2 ("
						+"Symbol VARCHAR(300),Previous_Close VARCHAR(300), "
						+"Open VARCHAR(300), Day_Low VARCHAR(300), Day_High VARCHAR(300),"
						+"Volume VARCHAR(300), Market_Capitalization VARCHAR(300),PRIMARY KEY(Symbol))";
				sta1.executeUpdate(sqlCommand);	
				sqlCommand = "load data local infile "
						+" 'D://stock_12_2_2014.txt' "
						+"into table day2 fields terminated by ';' "
						+"lines terminated by ';\n'";
				sta1.executeUpdate(sqlCommand);
				
				sqlCommand = "CREATE TABLE day3 ("
						+"Symbol VARCHAR(300),Previous_Close VARCHAR(300), "
						+"Open VARCHAR(300), Day_Low VARCHAR(300), Day_High VARCHAR(300),"
						+"Volume VARCHAR(300), Market_Capitalization VARCHAR(300),PRIMARY KEY(Symbol))";
				sta1.executeUpdate(sqlCommand);	
				sqlCommand = "load data local infile "
						+" 'D://stock_12_3_2014.txt' "
						+"into table day3 fields terminated by ';' "
						+"lines terminated by ';\n'";
				sta1.executeUpdate(sqlCommand);
				
				sqlCommand = "CREATE TABLE day4 ("
						+"Symbol VARCHAR(300),Previous_Close VARCHAR(300), "
						+"Open VARCHAR(300), Day_Low VARCHAR(300), Day_High VARCHAR(300),"
						+"Volume VARCHAR(300), Market_Capitalization VARCHAR(300),PRIMARY KEY(Symbol))";
				sta1.executeUpdate(sqlCommand);	
				sqlCommand = "load data local infile "
						+" 'D://stock_12_4_2014.txt' "
						+"into table day4 fields terminated by ';' "
						+"lines terminated by ';\n'";
				sta1.executeUpdate(sqlCommand); 
				
				sqlCommand = "CREATE TABLE openchange ("
						+"Symbol VARCHAR(300), "
						+"ChangeO MEDIUMTEXT, PRIMARY KEY(Symbol))";
				sta1.executeUpdate(sqlCommand);	
				
				sqlCommand = "CREATE TABLE volumechange ("
						+"Symbol VARCHAR(300), "
						+"ChangeV MEDIUMTEXT, PRIMARY KEY(Symbol))";
				sta1.executeUpdate(sqlCommand);	
				
				sqlCommand = "CREATE TABLE highchange ("
						+"Symbol VARCHAR(300), "
						+"ChangeH MEDIUMTEXT, PRIMARY KEY(Symbol))";
				sta1.executeUpdate(sqlCommand);	
				
				sqlCommand = "CREATE TABLE lowchange ("
						+"Symbol VARCHAR(300), "
						+"ChangeL MEDIUMTEXT, PRIMARY KEY(Symbol))";
				sta1.executeUpdate(sqlCommand);	
				
				
	
/*			
 */

/*	 DEAL WITH DATA IN STOCK TABLES	
 *
				sqlCommand = "UPDATE day1 SET Previous_Close = 0 WHERE Previous_close = 'N/A'";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE day1 SET open = Previous_Close WHERE open = 'N/A'";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE day1 SET Volume = 0 WHERE Volume = 'N/A'";
				sta1.executeUpdate(sqlCommand);
				
				sqlCommand = "UPDATE day2 SET Previous_Close = 0 WHERE Previous_Close = 'N/A'";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE day2 SET open = Previous_Close WHERE open = 'N/A'";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE day2 SET Volume = 0 WHERE Volume = 'N/A'";
				sta1.executeUpdate(sqlCommand);
				
				sqlCommand = "UPDATE day3 SET Previous_Close = 0 WHERE Previous_Close = 'N/A'";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE day3 SET open = Previous_Close WHERE open = 'N/A'";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE day3 SET Volume = 0 WHERE Volume = 'N/A'";
				sta1.executeUpdate(sqlCommand);
				
				sqlCommand = "UPDATE day4 SET Previous_Close = 0 WHERE Previous_Close = 'N/A'";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE day4 SET open = Previous_Close WHERE open = 'N/A'";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE day4 SET Volume = 0 WHERE Volume = 'N/A'";
				sta1.executeUpdate(sqlCommand);	
/*
 */

/*  UPDATE DATA IN OPENPRICE AND STOCKVOLUME 
 *
				sqlCommand = "TRUNCATE TABLE openprice;";
				sta1.executeUpdate(sqlCommand);				
				sqlCommand = "insert into openprice (Symbol) select Symbol from day1;";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "TRUNCATE TABLE stockvolume;";
				sta1.executeUpdate(sqlCommand);				
				sqlCommand = "insert into stockvolume (Symbol) select Symbol from day1;";
				sta1.executeUpdate(sqlCommand);
				
				sqlCommand = "SET SQL_SAFE_UPDATES=0;";
				sta1.executeUpdate(sqlCommand);	
				
				sqlCommand = "UPDATE openprice JOIN day1 ON openprice.Symbol = day1.Symbol SET day1 = open;";
				sta1.executeUpdate(sqlCommand);				
				sqlCommand = "UPDATE openprice JOIN day2 ON openprice.Symbol = day2.Symbol SET day2 = open;";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE openprice JOIN day3 ON openprice.Symbol = day3.Symbol SET day3 = open;";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE openprice JOIN day4 ON openprice.Symbol = day4.Symbol SET day4 = open;";
				sta1.executeUpdate(sqlCommand);
				
				sqlCommand = "UPDATE stockvolume JOIN day1 ON stockvolume.Symbol = day1.Symbol SET day1 = volume;";
				sta1.executeUpdate(sqlCommand);				
				sqlCommand = "UPDATE stockvolume JOIN day2 ON stockvolume.Symbol = day2.Symbol SET day2 = volume;";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE stockvolume JOIN day3 ON stockvolume.Symbol = day3.Symbol SET day3 = volume;";
				sta1.executeUpdate(sqlCommand);
				sqlCommand = "UPDATE stockvolume JOIN day4 ON stockvolume.Symbol = day4.Symbol SET day4 = volume;";
				sta1.executeUpdate(sqlCommand);
				
/*
 */
				
				
				sqlCommand = "SELECT * FROM openprice";
				ResultSet resultset1 = sta4.executeQuery(sqlCommand);//from DB      
				Table2Array open = new Table2Array();
				List <List <String> > openarray = open.result(resultset1);
				//System.out.println(openarray);
				
				String stock = "stockvolume";
				sqlCommand = "SELECT * FROM "+stock;
				ResultSet resultset2 = sta4.executeQuery(sqlCommand);//from DB      
				Table2Array volume = new Table2Array();
				List <List <String> > volumearray = volume.result(resultset2);
				//System.out.println(volumearray);
				/*
				List < List <String> > openhistory = new ArrayList<>();
				for (int i = 0; i < volumearray.size()-4; i ++){
					String stockname = volumearray.get(i).get(0);					
					stockname = stockname.substring(stockname.indexOf("\"")+1,stockname.lastIndexOf("\""));	
								sqlCommand = "SELECT * FROM " + stockname+"_NASDAQ";
					ResultSet resultset3 = sta4.executeQuery(sqlCommand);//from DB      
					TableData result3 = new TableData();
					List <String> stockopenhistory = new ArrayList <String>();
					stockopenhistory.add(stockname);
					stockopenhistory.addAll(result3.open(resultset3));
					openhistory.add(stockopenhistory);					
				}
				//System.out.println(openhistory);
				
				List < List <String> > volumehistory = new ArrayList<>();
				for (int i = 0; i < volumearray.size()-4; i ++){
					String stockname = volumearray.get(i).get(0);					
					stockname = stockname.substring(stockname.indexOf("\"")+1,stockname.lastIndexOf("\""));	
								sqlCommand = "SELECT * FROM " + stockname+"_NASDAQ";
					ResultSet resultset4 = sta4.executeQuery(sqlCommand);//from DB      
					TableData result4 = new TableData();
					List <String> stockvolumehistory = new ArrayList <String>();
					stockvolumehistory.add(stockname);
					stockvolumehistory.addAll(result4.volume(resultset4));
					volumehistory.add(stockvolumehistory);					
				}
				//System.out.println(volumehistory);*/
				
				List < List <String> > highhistory = new ArrayList<>();
				for (int i = 0; i < volumearray.size()-4; i ++){
					String stockname = volumearray.get(i).get(0);					
					stockname = stockname.substring(stockname.indexOf("\"")+1,stockname.lastIndexOf("\""));	
								sqlCommand = "SELECT * FROM " + stockname+"_NASDAQ";
					ResultSet resultset4 = sta4.executeQuery(sqlCommand);//from DB      
					TableData result4 = new TableData();
					List <String> stockhighhistory = new ArrayList <String>();
					stockhighhistory.add(stockname);
					stockhighhistory.addAll(result4.high(resultset4));
					highhistory.add(stockhighhistory);					
				}
				
				List < List <String> > lowhistory = new ArrayList<>();
				for (int i = 0; i < volumearray.size()-4; i ++){
					String stockname = volumearray.get(i).get(0);					
					stockname = stockname.substring(stockname.indexOf("\"")+1,stockname.lastIndexOf("\""));	
								sqlCommand = "SELECT * FROM " + stockname+"_NASDAQ";
					ResultSet resultset4 = sta4.executeQuery(sqlCommand);//from DB      
					TableData result4 = new TableData();
					List <String> stocklowhistory = new ArrayList <String>();
					stocklowhistory.add(stockname);
					stocklowhistory.addAll(result4.low(resultset4));
					lowhistory.add(stocklowhistory);					
				}
				
				
				
/*  CREATE TABLE FOR EACH STOCK volumearray.size()
 *
				
				for (int i = 0; i < volumearray.size()-4; i ++){
					System.out.println(volumearray.get(i).get(0));
					String name = volumearray.get(i).get(0);					
					//String newname = name.replaceAll("\"","");
					name = name.substring(name.indexOf("\"")+1,name.lastIndexOf("\""));	
					System.out.println(name);
								sqlCommand = "CREATE TABLE " + name+"_NASDAQ" 
										+" (Date VARCHAR(300),Open VARCHAR(300), "
										+"High VARCHAR(300), Low VARCHAR(300), Close VARCHAR(300),"
										+"Volume VARCHAR(300), Adj_Close VARCHAR(300),PRIMARY KEY(Date))";
								sta1.executeUpdate(sqlCommand);	
				}
/*
 */
				
/*
 *  LOAD DATA INTO STOCK TABLES
 *
				for (int i = 0; i < volumearray.size()-4; i ++){
					System.out.println(volumearray.get(i).get(0));
					String name = volumearray.get(i).get(0);					
					name = name.substring(name.indexOf("\"")+1,name.lastIndexOf("\""));	
					System.out.println(name);
					sqlCommand = "load data local infile "
										+" 'D://StockDataset/"+name+".csv' "
										+"into table " + name + "_NASDAQ" + " fields terminated by ',' "
										+"lines terminated by '\n' " 
										+"IGNORE 1 LINES";
								sta1.executeUpdate(sqlCommand);								
				}				
/*
 */
				
				StringList www = new StringList();
				
				//List <String> openchange = www.arraychange(openhistory);
				
				//List <String> volumechange = www.arraychange(volumehistory);
			
				List <String> highchange = www.arraychange(highhistory);
				
				List <String> lowchange = www.arraychange(lowhistory);
				
				/*
				sqlCommand = "TRUNCATE TABLE openchange;";
				sta1.executeUpdate(sqlCommand);	
				for(int i=0; i< volumearray.size()-4;i++){
					String name = volumearray.get(i).get(0);					
					name = name.substring(name.indexOf("\"")+1,name.lastIndexOf("\""));
					sqlCommand = "INSERT INTO OPENCHANGE(Symbol, ChangeO)"
								+"VALUES('" + name
								+ "','"+openchange.get(i)+"')";
					sta1.executeUpdate(sqlCommand);
				}
				
				sqlCommand = "TRUNCATE TABLE volumechange;";
				sta1.executeUpdate(sqlCommand);	
				for(int i=0; i< volumearray.size()-4;i++){
					String name = volumearray.get(i).get(0);					
					name = name.substring(name.indexOf("\"")+1,name.lastIndexOf("\""));
					sqlCommand = "INSERT INTO VOLUMECHANGE(Symbol, ChangeV)"
							+"VALUES('" + name
							+ "','"+volumechange.get(i)+"')";
				sta1.executeUpdate(sqlCommand);
				}*/
				
				
				sqlCommand = "TRUNCATE TABLE highchange;";
				sta1.executeUpdate(sqlCommand);	
				for(int i=0; i< volumearray.size()-4;i++){
					String name = volumearray.get(i).get(0);					
					name = name.substring(name.indexOf("\"")+1,name.lastIndexOf("\""));
					sqlCommand = "INSERT INTO HIGHCHANGE(Symbol, ChangeH)"
							+"VALUES('" + name
							+ "','"+highchange.get(i)+"')";
				sta1.executeUpdate(sqlCommand);
				}
				
				sqlCommand = "TRUNCATE TABLE lowchange;";
				sta1.executeUpdate(sqlCommand);	
				for(int i=0; i< volumearray.size()-4;i++){
					String name = volumearray.get(i).get(0);					
					name = name.substring(name.indexOf("\"")+1,name.lastIndexOf("\""));
					sqlCommand = "INSERT INTO LOWCHANGE(Symbol, ChangeL)"
							+"VALUES('" + name
							+ "','"+lowchange.get(i)+"')";
				sta1.executeUpdate(sqlCommand);
				}
				
				
				//List <List <String> > openchange = www.getchange(openhistory);
				//List <List <String> > volumechange = www.getchange(volumehistory);
				//List <List <String> > List = www.stringGenerate(openchange, volumechange);
				
/*
 *
				sqlCommand = "CREATE TABLE day4 ("
						+"Symbol VARCHAR(300),Previous_Close VARCHAR(300), "
						+"Open VARCHAR(300), Day_Low VARCHAR(300), Day_High VARCHAR(300),"
						+"Volume VARCHAR(300), Market_Capitalization VARCHAR(300),PRIMARY KEY(Symbol))";
				sta1.executeUpdate(sqlCommand);	
/*
 */

				//HashMap<String,HashMap<String,Double>> map = www.getScoreList(openhistory, volumehistory);
				//for (String key : map.keySet()) {
			    //    System.out.println(key + " " + map.get(key));
			    //}
				
				//for (String key : map.get("LLEX").keySet()) {
			    //    System.out.println(key + " " + map.get("LLEX").get(key));
			    //}
				

			    //System.out.print(score);
			    
				String a = "aabab";
				String b = a.substring(a.length() - Math.min(a.length(),3));
				System.out.println(b);

				
			   
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	

}

