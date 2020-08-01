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



public class CQ3Dao {
	
	

	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
                              + "user=root&password=1234");
            
            
            String sql = "SELECT state, county,\n" + 
            		"FORMAT(MAX(cases), 0) as cases1,\n" + 
            		"FORMAT(MAX(deaths), 0) as deaths1,\n" + 
            		"FORMAT(Pop2019Est, 0) AS popEst1, \n" + 
            		"CONCAT(FORMAT((MAX(Cases) / Pop2019Est) * 100, 2), '%') AS infectionRate, \n" + 
            		"CONCAT(FORMAT((MAX(Deaths) / Pop2019Est) * 100, 2), '%') AS deathRate\n" + 
            		"FROM county_covid A\n" + 
            		"INNER JOIN (SELECT FIPS, MAX(Cases) AS myCases\n" + 
            		"            FROM county_covid\n" + 
            		"            GROUP BY FIPS) AS B ON A.FIPS = B.FIPS AND cases = B.myCases\n" + 
            		"            INNER JOIN (SELECT *\n" + 
            		"                        FROM county_info) AS C ON B.FIPS = C.FIPS\n" + 
            		"GROUP BY State\n" + 
            		"ORDER BY ROUND(((MAX(deaths) / MAX(pop2019Est)) * 100), 2) DESC, ROUND(((MAX(cases) / MAX(pop2019Est)) * 100),2) DESC;";
            PreparedStatement preparestatement = connect.prepareStatement(sql); 
            ResultSet resultSet = preparestatement.executeQuery();
            
            while(resultSet.next()){
                CovidUser user = new CovidUser();
                user.setState(resultSet.getString("state"));
                user.setCounty(resultSet.getString("county"));
                user.setCasesS(resultSet.getString("cases1"));
                user.setDeathsS(resultSet.getString("deaths1"));
                user.setPopulationS(resultSet.getString("popEst1"));
                user.setInfectionRate(resultSet.getString("infectionRate"));
                user.setDeathRate(resultSet.getString("deathRate"));
                list.add(user);
             }
             
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
        
    }

		
}
