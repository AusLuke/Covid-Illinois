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



public class CQ4Dao {
	
	

	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
            
            
            String sql = "SELECT A.State AS state,\n" + 
            		"FORMAT(MAX(cases), 0) AS cases,\n" +
            		"FORMAT(MAX(Deaths), 0) AS deaths,\n" +
            		"FORMAT(B.Pop2019Est, 0) AS population,\n" +
            		"CONCAT(FORMAT((MAX(cases) / B.Pop2019Est) * 100, 2), '%') AS infectionRate\n" + 
            		"FROM state_covid AS A\n" + 
            		"INNER JOIN (SELECT *\n" + 
            		"            FROM county_info) AS B ON A.FIPS = B.FIPS AND A.State = B.stateName\n" + 
            		"GROUP BY state\n" +
            		"ORDER BY ROUND((MAX(cases) / B.Pop2019Est) * 100, 2) DESC;";
            PreparedStatement preparestatement = connect.prepareStatement(sql); 
            ResultSet resultSet = preparestatement.executeQuery();
            
            while(resultSet.next()){
                CovidUser user = new CovidUser();
                user.setState(resultSet.getString("state"));
                user.setCasesS(resultSet.getString("cases"));
                user.setDeathsS(resultSet.getString("deaths"));
                user.setPopulationS(resultSet.getString("population"));
                user.setInfectionRate(resultSet.getString("infectionRate"));
                list.add(user);
             }
             
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
        
    }

		
}
