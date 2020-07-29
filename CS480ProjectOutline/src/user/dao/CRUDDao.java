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
public class CRUDDao {
	
	
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
        
        // create Entity3 state_covid table and populate with 3 entries
        statement.executeUpdate("DROP TABLE IF EXISTS county_covid");
                    
        String sqlstmt = "CREATE TABLE county_covid (\r\n" + 
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
					
					BufferedReader lineReader = new BufferedReader(new FileReader("C:\\Users\\Andrew\\git\\cs480-course-project-covid_illinois\\CS480ProjectOutline\\usCounties.csv"));
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
							count = 0;
						}
						
					}
					
					lineReader.close();
					
					// execute the leftover queries
					statementInsert.executeBatch();
					
					connect.close();
					
					
					/*
					sqlstmt = "INSERT INTO county_covid VALUES\r\n" + 
							"('2020-1-1', 'Autauga County', 'Alabama',  1, 1, 10, 1),\r\n" + 
							"('2020-1-1', 'Aleutians East Borough', 'Alaska',  2, 2, 20, 2),\r\n" + 
							"('2020-1-1', 'Apache County', 'Arizona',  4, 3, 30, 3);";
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
            
            
            String sql = "select * from county_covid";
            PreparedStatement preparestatement = connect.prepareStatement(sql); 
            ResultSet resultSet = preparestatement.executeQuery();
            
            while(resultSet.next()){
                CovidUser user = new CovidUser();
                user.setDate(resultSet.getString("date"));
                user.setCounty(resultSet.getString("county"));
                user.setState(resultSet.getString("state"));
                user.setFips(resultSet.getInt("fips"));
                user.setCountyNum(resultSet.getInt("countyNum"));
                user.setCases(resultSet.getInt("cases"));
                user.setDeaths(resultSet.getInt("deaths"));
                list.add(user);
             }
             
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
        
    }
	
	public void update(String date, String county, String state, String fips, String countyNum, String cases, String deaths)
    {
		System.out.println(fips);
        Statement statement;
        int f = Integer.parseInt(fips);
        int cn  = Integer.parseInt(countyNum);
        int c= Integer.parseInt(cases);
        int d = Integer.parseInt(deaths);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
        
            statement = connect.createStatement();
            
            String sqlstmt = "UPDATE county_covid\r\n" + 
                    "SET COUNTY = '" + county + "', State = '" + state + "', Cases = " + c + ", Deaths = " + d +// "\r\n" +
                    " WHERE Date = '" + date + "' AND FIPS = " + f + " AND CountyNum = " + cn + ";";
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
	
	public void delete(String date, String fips, String countyNum)
    {
        Statement statement;
        String NULL = "NULL";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");

            statement = connect.createStatement();
            
            String sqlstmt = "UPDATE county_covid \r\n" + 
                    "SET COUNTY = " + NULL + ", State = " + NULL + ", Cases = " + NULL + ", Deaths = " + NULL +
                    " WHERE Date = '" + date + "' AND FIPS = " + fips + " AND CountyNum = " + countyNum + ";";
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
