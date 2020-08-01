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



public class SQ3Dao {
	
	

	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
            
            
            String sql = "SELECT FORMAT(MAX(Pop2019Est), 0) AS popEst, StateName AS state, CountyName AS county\n" + 
            			 "FROM county_info\n" + 
            			 "GROUP BY County\n" + 
            			 "ORDER BY StateName ASC, MAX(Pop2019Est) DESC, CountyName ASC;";
            PreparedStatement preparestatement = connect.prepareStatement(sql); 
            ResultSet resultSet = preparestatement.executeQuery();
            
            while(resultSet.next()){
                CovidUser user = new CovidUser();
                user.setCounty(resultSet.getString("county"));
                user.setState(resultSet.getString("state"));
                user.setPopulationS(resultSet.getString("popEst"));
                list.add(user);
             }
             
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
        
    }

		
}
