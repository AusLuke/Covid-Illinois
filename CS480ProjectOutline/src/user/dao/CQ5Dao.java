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



public class CQ5Dao {
	
	

	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
            
            
            String sql = "SELECT A.State, B.County,\n" + 
            		"FORMAT(MAX(B.cases), 0) AS cases,\n" + 
            		"FORMAT(MAX(A.cases), 0) AS cases2,\n" + 
            		"CONCAT(FORMAT((MAX(B.cases) / MAX(A.cases) * 100), 2), '%') AS percentage_of_total_state_cases_by_county,\n" + 
            		"FORMAT(MAX(B.Deaths), 0) AS deaths,\n" + 
            		"CONCAT(FORMAT((MAX(B.deaths) / MAX(A.deaths) * 100), 2), '%') AS percentage_of_total_state_deaths_by_county\n" + 
            		"FROM state_covid AS A\n" + 
            		"INNER JOIN (SELECT *\n" + 
            		"            FROM county_covid) AS B ON A.FIPS = B.FIPS\n" + 
            		"GROUP BY A.state, B.county\n" + 
            		"ORDER BY state, ROUND(MAX(B.deaths) / MAX(A.deaths) * 100) DESC, ROUND(MAX(B.cases) / MAX(A.cases) * 100) DESC;";
            PreparedStatement preparestatement = connect.prepareStatement(sql); 
            ResultSet resultSet = preparestatement.executeQuery();
            
            while(resultSet.next()){
                CovidUser user = new CovidUser();
                user.setState(resultSet.getString("state"));
                user.setCounty(resultSet.getString("county"));
                user.setCasesS(resultSet.getString("cases"));
                user.setCasesS2(resultSet.getString("cases2"));
                user.setInfectionRate(resultSet.getString("percentage_of_total_state_cases_by_county"));
                user.setDeathsS(resultSet.getString("deaths"));
                user.setDeathRate(resultSet.getString("percentage_of_total_state_deaths_by_county"));
                list.add(user);
             }
             
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
        
    }

		
}
