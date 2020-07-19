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



public class InitializeDao {
	
	

	public void initDB() {
		Statement statement;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
				              + "user=root&password=Cr0ss.country");

		statement = connect.createStatement();
		statement.executeLargeUpdate("DROP TABLE IF EXISTS tb_user");
		
		String sqlstmt = "CREATE TABLE IF NOT EXISTS tb_user(" +
				          "username VARCHAR(50) NOT NULL PRIMARY KEY," +
				          "password VARCHAR(50)," +
				          "email VARCHAR(50)" +
						  ")";
		
		statement.executeLargeUpdate(sqlstmt);
		
		sqlstmt = "INSERT INTO tb_user VALUES " +
		          "('Andrew1', 'Andrew1', 'Andrew1')," +
		          "('Andrew2', 'Andrew2', 'Andrew2')," +
		          "('Andrew3', 'Andrew3', 'Andrew3');";
		statement.executeUpdate(sqlstmt);
		
		// create county_covid table and populate with 3 entries
					statement.executeUpdate("DROP TABLE IF EXISTS county_covid");
					
					sqlstmt = "CREATE TABLE county_covid (\r\n" + 
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
					
					
					// create state_covid table and populate with 3 entries
					statement.executeUpdate("DROP TABLE IF EXISTS state_covid");
					
					sqlstmt = "CREATE TABLE state_covid (\r\n" + 
							"Date date NOT NULL,\r\n" + 
							"State VARCHAR(50),\r\n" + 
							"FIPS INT NOT NULL,\r\n" + 
							"Cases INT,\r\n" + 
							"Deaths INT,\r\n" + 
							"PRIMARY KEY(Date, FIPS)\r\n" + 
							");";
					statement.executeUpdate(sqlstmt);
					
					sqlstmt = "INSERT INTO state_covid VALUES\r\n" + 
							"('2020-1-1', 'Alabama',  1, 10, 1),\r\n" + 
							"('2020-1-1', 'Alaska',  2, 20, 2),\r\n" + 
							"('2020-1-1', 'Arizona',  4, 30, 3);";
					statement.executeUpdate(sqlstmt);
					
					
					// create county_info table and populate with 3 entries
					statement.executeUpdate("DROP TABLE IF EXISTS county_info");
					
					sqlstmt = "CREATE TABLE county_info (\r\n" + 
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

		
}
