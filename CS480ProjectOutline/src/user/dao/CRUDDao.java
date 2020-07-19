package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import user.domain.User;



/**
 * DDL functions performed in database
 * @author changxin bai
 *
 */
public class CRUDDao {
	
	
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
		
		// create Entity1 county_covid table and populate with 3 entries
		statement.executeUpdate("DROP TABLE IF EXISTS county_covid");
					
		String sqlstmt = "CREATE TABLE county_covid (\r\n" + 
							"Date date NOT NULL,\r\n" + 
							"County VARCHAR(100),\r\n" + 
							"State VARCHAR(50),\r\n" + 
							"FIPS INT NOT NULL,\r\n" + 
							"Cases INT,\r\n" + 
							"Deaths INT,\r\n" + 
							"PRIMARY KEY(Date, FIPS)\r\n" + 
							");";
					statement.executeUpdate(sqlstmt);
					
					sqlstmt = "INSERT INTO county_covid VALUES\r\n" + 
							"('2020-1-1', 'Autauga County', 'Alabama',  1, 10, 1),\r\n" + 
							"('2020-1-1', 'Aleutians East Borough', 'Alaska',  2, 20, 2),\r\n" + 
							"('2020-1-1', 'Apache County', 'Arizona',  4, 30, 3);";
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
	
	/*public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<Object> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
				              + "user=root&password=1234");
			
			
			String sql = "select * from county_covid";
			PreparedStatement preparestatement = connect.prepareStatement(sql); 
			ResultSet resultSet = preparestatement.executeQuery();
			
			while(resultSet.next()){
				User user = new User();
				user.setDate(resultSet.getString("date"));
	    		user.setCounty(resultSet.getString("county"));
	    		user.setState(resultSet.getString("state"));
				user.setFIPS(resultSet.getInt("fips"));
				user.setCases(resultSet.getInt("cases"));
				user.setDeaths(resultSet.getInt("deaths"));
	    		list.add(user);
			 }
			 
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
		
	}*/
	
	public void update(String date)
	{
		Statement statement;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
				              + "user=root&password=1234");
		
			statement = connect.createStatement();
			
			String sqlstmt = "INSERT INTO county_covid VALUES\r\n" + 
					"('" + date + "', 'Test', 'Test',  1, 10, 1)";
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
