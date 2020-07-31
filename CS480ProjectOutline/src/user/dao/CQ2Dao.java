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



public class CQ2Dao {
	
	

	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
            
            
            String sql = "SELECT state, county, MAX(cases) as cases, MAX(deaths) as deaths, CONCAT(FORMAT((MAX(deaths) / MAX(cases)) * 100, 2), '%') AS deathRate\n" + 
            			 "FROM county_covid A\n" + 
            			 "INNER JOIN\n" + 
            			 "    (SELECT FIPS, MAX(Cases) AS myCases\n" + 
            			 "    FROM county_covid\n" + 
            			 "    GROUP BY FIPS) AS B ON A.FIPS = B.FIPS AND cases = B.myCases\n" + 
            			 "GROUP BY State\n" + 
            			 "ORDER BY ((MAX(deaths) / MAX(cases)) * 100) DESC;";
            PreparedStatement preparestatement = connect.prepareStatement(sql); 
            ResultSet resultSet = preparestatement.executeQuery();
            
            while(resultSet.next()){
                CovidUser user = new CovidUser();
                user.setState(resultSet.getString("state"));
                user.setCounty(resultSet.getString("county"));
                user.setCases(resultSet.getInt("cases"));
                user.setDeaths(resultSet.getInt("deaths"));
                user.setDeathRate(resultSet.getString("deathRate"));
                list.add(user);
             }
             
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
        
    }

		
}
