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



public class SQ1Dao {
	
	

	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
            
            
            String sql = "SELECT State, FIPS, MAX(Cases) as cases, MAX(Deaths) as deaths\n" + 
            			 "FROM state_covid\n" + 
            			 "GROUP BY State\n" + 
            			 "ORDER BY FIPS ASC;";
            PreparedStatement preparestatement = connect.prepareStatement(sql); 
            ResultSet resultSet = preparestatement.executeQuery();
            
            while(resultSet.next()){
                CovidUser user = new CovidUser();
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

		
}
