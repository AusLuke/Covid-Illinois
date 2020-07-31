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



public class CQ1Dao {
	
	

	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
            
            
            String sql = "SELECT state, county, county_covid.fips, MAX(cases) as cases, MAX(deaths) as deaths\n" + 
            			 "FROM county_covid\n" + 
            			 "INNER JOIN ( SELECT fips, MAX(cases) as myCases\n" +
            			 "FROM county_covid\n" +
            			 "GROUP BY fips) as b\n" +
            			 "ON county_covid.fips = b.fips AND cases = b.myCases\n" +
            			 "GROUP BY state\n" + 
            			 "ORDER BY cases DESC;";
            PreparedStatement preparestatement = connect.prepareStatement(sql); 
            ResultSet resultSet = preparestatement.executeQuery();
            
            while(resultSet.next()){
                CovidUser user = new CovidUser();
                user.setState(resultSet.getString("state"));
                user.setCounty(resultSet.getString("county"));
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
