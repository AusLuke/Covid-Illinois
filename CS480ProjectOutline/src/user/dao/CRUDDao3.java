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
public class CRUDDao3 {
	
	
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
        
        // create Entity3 state_covid table and populate with 3 entries
        statement.executeUpdate("DROP TABLE IF EXISTS state_covid");
                    
        String sqlstmt = "CREATE TABLE state_covid (\r\n" + 
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
            
            
            String sql = "select * from state_covid";
            PreparedStatement preparestatement = connect.prepareStatement(sql); 
            ResultSet resultSet = preparestatement.executeQuery();
            
            while(resultSet.next()){
                CovidUser user = new CovidUser();
                user.setDate(resultSet.getString("date"));
                user.setState(resultSet.getString("state"));
                user.setFips(resultSet.getInt("fips"));
                user.setCases(resultSet.getInt("cases"));
                user.setDeaths(resultSet.getInt("deaths"));
                list.add(user);
             }
             
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
        
    }
	
	public void update(String date, String state, String fips, String cases,  String deaths)
    {
        System.out.println(fips);
        Statement statement;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
        
            statement = connect.createStatement();
            
            String sqlstmt = "UPDATE state_covid\r\n" + 
                    "SET State = '" + state + "', Cases = " + cases + ", Deaths = " + deaths + "\r\n" +
                    " WHERE Date = '" + date + "' AND FIPS = " + fips + ";";
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
	
	public void delete(String date, String fips)
    {
        Statement statement;
        String NULL = "NULL";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");

            statement = connect.createStatement();
            
            String sqlstmt = "UPDATE state_covid \r\n" + 
                    "SET State = " + NULL + ", Cases = " + NULL + ", Deaths = " + NULL +
                    " WHERE Date = '" + date + "' AND FIPS = " + fips + ";";
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
