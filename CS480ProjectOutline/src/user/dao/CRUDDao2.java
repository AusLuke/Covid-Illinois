package user.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import user.domain.CovidUser;
import user.domain.User;



/**
 * DDL functions performed in database
 * @author changxin bai
 *
 */
public class CRUDDao2 {
	
	
	/**
	 * get the search result with username 
	 * @param username
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void create() throws IOException{
		Statement statement;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
				              + "user=root&password=1234");

		statement = connect.createStatement();
		
		 // create Entity2 county_info table and populate with 3 entries
        statement.executeUpdate("DROP TABLE IF EXISTS county_info");
                    
        String sqlstmt = "CREATE TABLE county_info (\r\n" + 
                            "FIPS INT NOT NULL,\r\n" + 
                            "CountyNum INT NOT NULL,\r\n" + 
                            "StateName VARCHAR(50),\r\n" + 
                            "CountyName VARCHAR(100),\r\n" + 
                            "Pop2019Est INT,\r\n" + 
                            "PRIMARY KEY(FIPS, CountyNum)\r\n" + 
                            ");";
                    statement.executeUpdate(sqlstmt);
                    
					// import data from CSV file
					String sql = "INSERT INTO county_info (FIPS, CountyNum, StateName, CountyName, Pop2019Est) VALUES (?, ?, ?, ?, ?)";
					PreparedStatement statementInsert = connect.prepareStatement(sql);
					
					BufferedReader lineReader = new BufferedReader(new FileReader("C:\\Users\\Andrew\\git\\cs480-course-project-covid_illinois\\CS480ProjectOutline\\censusPopByCounty2019Est.csv"));
					String lineText = null;
					
					int count = 0;
					int batchSize = 100;
					
					lineReader.readLine(); // skip header line
					
					while((lineText = lineReader.readLine()) != null) {
						String[] data = lineText.split(",");
						String FIPS = data[0];
						String CountyNum = data[1];
						String StateName = data[2];
						String CountyName = data[3];
						String Pop2019Est = data[4];
						
						Integer iFIPS = Integer.parseInt(FIPS);
						Integer iCountyNum = Integer.parseInt(CountyNum);
						statementInsert.setInt(1, iFIPS);
						statementInsert.setInt(2,  iCountyNum);
						
						statementInsert.setString(3, StateName);
						statementInsert.setString(4, CountyName);
						
						Integer iPop2019Est = Integer.parseInt(Pop2019Est);
						statementInsert.setInt(5,  iPop2019Est);
						
						statementInsert.addBatch();
						count++;
						if (count % batchSize == 0) {
							statement.executeBatch();
							count = 0;
						}
						
					}
					
					lineReader.close();
					
					// execute the leftover queries
					statementInsert.executeBatch();

					connect.close();
                    
                    
                    /*
                    sqlstmt = "INSERT INTO county_info VALUES\r\n" + 
                            "(1, 1, 'Alabama', 'Autauga County',55869),\r\n" + 
                            "(2, 13, 'Alaska', 'Aleutians East Borough', 3337),\r\n" + 
                            "(4, 1, 'Arizona', 'Apache County', 71887);";
                    statement.executeUpdate(sqlstmt);
                    */


		} catch(SQLException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
            
            
            String sql = "select * from county_info";
            PreparedStatement preparestatement = connect.prepareStatement(sql); 
            ResultSet resultSet = preparestatement.executeQuery();
            
            while(resultSet.next()){
                CovidUser user = new CovidUser();
                user.setFips(resultSet.getInt("fips"));
                user.setCountyNum(resultSet.getInt("countyNum"));
                user.setState(resultSet.getString("StateName"));
                user.setCounty(resultSet.getString("CountyName"));
                user.setPopEst(resultSet.getInt("Pop2019Est"));
                list.add(user);
             }
             
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
        
    }
	
	public void update(String fips, String countynum, String state,  String county, String pop)
    {
        System.out.println(fips);
        Statement statement;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
        
            statement = connect.createStatement();
            
            String sqlstmt = "UPDATE county_info\r\n" + 
                    "SET CountyName = '" + county + "', StateName = '" + state + "', Pop2019Est = " + pop + "\r\n" +
                    " WHERE FIPS = '" + fips + "' AND CountyNum = " + countynum + ";";
            System.out.println(sqlstmt);
            statement.executeUpdate(sqlstmt);
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        
    }
        
}
	
	 public void delete(String fips, String countynum)
	    {
	        Statement statement;
	        String NULL = "NULL";

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	            Connection connect = DriverManager
	                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
	                              + "user=root&password=1234");

	            statement = connect.createStatement();
	            
	            String sqlstmt = "UPDATE county_info \r\n" + 
	                    "SET StateName = " + NULL + ", CountyName = " + NULL + ", Pop2019Est = " + NULL +
	                    " WHERE FIPS = '" + fips + "' AND CountyNum = " + countynum + ";";
	            statement.executeUpdate(sqlstmt);
	            
	        } catch(SQLException e) {
	            throw new RuntimeException(e);
	        } catch (InstantiationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	    }
}
