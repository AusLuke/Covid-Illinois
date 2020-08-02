package user.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

import user.domain.User;


public class InitializeDao {
	
	

	public void initDB() throws IOException {
		String filepath1 = "C:\\Users\\Andrew\\git\\cs480-course-project-covid_illinois\\CS480ProjectOutline\\usCounties.csv";
		String filepath2 = "C:\\Users\\Andrew\\git\\cs480-course-project-covid_illinois\\CS480ProjectOutline\\censusPopByCounty2019Est.csv";
		String filepath3 = "C:\\Users\\Andrew\\git\\cs480-course-project-covid_illinois\\CS480ProjectOutline\\usStates.csv";
		
		Statement statement;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
				              + "user=root&password=1234");

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
		
		
		// -----------------------------------------------------------------
		
		
		// create Entity1 county_covid table and populate with csv file
		statement.executeUpdate("DROP TABLE IF EXISTS county_covid");
        
        sqlstmt = "CREATE TABLE county_covid (\r\n" + 
                            "Date date NOT NULL,\r\n" +
                            "County VARCHAR(100),\r\n" +
                            "State VARCHAR(50),\r\n" + 
                            "FIPS INT NOT NULL,\r\n" + 
                            "CountyNum INT NOT NULL,\r\n" + 
                            "Cases INT,\r\n" +
                            "Deaths INT,\r\n" + 
                            "PRIMARY KEY(Date, FIPS, CountyNum)\r\n" + 
                            ");";
        
        statement.executeUpdate(sqlstmt);
        
		// import data from CSV file
		String sql = "INSERT INTO county_covid (Date, County, State, FIPS, CountyNum, Cases, Deaths) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statementInsert = connect.prepareStatement(sql);
		
		BufferedReader lineReader = new BufferedReader(new FileReader(filepath1));
		String lineText = null;
		
		int count = 0;
		int batchSize = 20;
		
		lineReader.readLine(); // skip header line
		
		while((lineText = lineReader.readLine()) != null) {
			String[] data = lineText.split(",");
			String Date = data[0];
			String County = data[1];
			String State = data[2];
			String FIPS = data[3];
			String CountyNum = data[4];
			String Cases = data[5];
			String Deaths = data[6];
			
			statementInsert.setString(1,  Date);
			statementInsert.setString(2, County);
			statementInsert.setString(3, State);
			
			Integer iFIPS = Integer.parseInt(FIPS);
			Integer iCountyNum = Integer.parseInt(CountyNum);
			Integer iCases = Integer.parseInt(Cases);
			Integer iDeaths = Integer.parseInt(Deaths);
			statementInsert.setInt(4,  iFIPS);
			statementInsert.setInt(5,  iCountyNum);
			statementInsert.setInt(6,  iCases);
			statementInsert.setInt(7,  iDeaths);
			
			statementInsert.addBatch();
			count++;
			
			if (count % batchSize == 0) {
				statement.executeBatch();
			}
		}
		
		lineReader.close();
		
		// execute the leftover queries
		statementInsert.executeBatch();
		
		
					
					
		// --------------------------------------------------------------------------------------
					
					
		// create Entity2 county_info table and populate with csv file
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
        
		// import data from CSV file
		sql = "INSERT INTO county_info (FIPS, CountyNum, StateName, CountyName, Pop2019Est) VALUES (?, ?, ?, ?, ?)";
		statementInsert = connect.prepareStatement(sql);
		
		lineReader = new BufferedReader(new FileReader(filepath2));
		lineText = null;
		
		count = 0;
		batchSize = 100;
		
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

		
		
		// --------------------------------------------------------------------
								
								
        // create Entity3 state_covid table and populate from csv
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
        
		// import data from CSV file
		sql = "INSERT INTO state_covid (Date, State, FIPS, Cases, Deaths) VALUES (?, ?, ?, ?, ?)";
		statementInsert = connect.prepareStatement(sql);
		
		lineReader = new BufferedReader(new FileReader(filepath3));
		lineText = null;
		
		count = 0;
		batchSize = 20;
		
		lineReader.readLine(); // skip header line
		
		while((lineText = lineReader.readLine()) != null) {
			String[] data = lineText.split(",");
			String Date = data[0];
			String State = data[1];
			String FIPS = data[2];
			String Cases = data[3];
			String Deaths = data[4];
			
			statementInsert.setString(1,  Date);
			statementInsert.setString(2, State);
			
			Integer iFIPS = Integer.parseInt(FIPS);
			Integer iCases = Integer.parseInt(Cases);
			Integer iDeaths = Integer.parseInt(Deaths);
			statementInsert.setInt(3,  iFIPS);
			statementInsert.setInt(4,  iCases);
			statementInsert.setInt(5,  iDeaths);
			
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
