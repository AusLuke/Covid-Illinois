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
	public void create(String date, String county, String state, String fips, String countyNum, String cases, String deaths)
    {
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
            
            String sqlstmt = "INSERT INTO county_covid VALUES \r\n" + 
                    "('" + date + "', '" + county + "', '" + state + "', " + f + ", " + cn + ", " + c + ", " + d + ");";
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
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
            
            
            String sql = "select date, county, state, fips, countyNum, FORMAT(cases, 0) as cases, FORMAT(deaths, 0) as deaths from county_covid";
            PreparedStatement preparestatement = connect.prepareStatement(sql); 
            ResultSet resultSet = preparestatement.executeQuery();
            
            while(resultSet.next()){
                CovidUser user = new CovidUser();
                user.setDate(resultSet.getString("date"));
                user.setCounty(resultSet.getString("county"));
                user.setState(resultSet.getString("state"));
                user.setFips(resultSet.getInt("fips"));
                user.setCountyNum(resultSet.getInt("countyNum"));
                user.setCasesS(resultSet.getString("cases"));
                user.setDeathsS(resultSet.getString("deaths"));
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
            
            String sqlstmt = "DELETE FROM county_covid \r\n" + 
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
