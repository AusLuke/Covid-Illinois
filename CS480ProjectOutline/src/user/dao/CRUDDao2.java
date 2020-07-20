package user.dao;

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
	public void create(){
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
                    
                    sqlstmt = "INSERT INTO county_info VALUES\r\n" + 
                            "(1, 1, 'Alabama', 'Autauga County',55869),\r\n" + 
                            "(2, 13, 'Alaska', 'Aleutians East Borough', 3337),\r\n" + 
                            "(4, 1, 'Arizona', 'Apache County', 71887);";
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
