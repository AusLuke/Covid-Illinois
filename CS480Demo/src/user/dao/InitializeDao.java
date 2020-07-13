package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




import java.util.ArrayList;
import java.util.List;

import user.domain.User;



public class InitializeDao {

	
	
	public void initDB() {
		Statement statement;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
				              + "user=root&password=1234");
			
			statement = connect.createStatement();
			statement.executeUpdate("DROP TABLE IF EXISTS tb_user");
			
			String sqlstmt = "CREATE TABLE IF NOT EXISTS tb_user (" +
							"username VARCHAR(50) NOT NULL PRIMARY KEY," + 
							"password VARCHAR(50)," + 
							"email VARCHAR(50)" + 
							")";

			statement.executeUpdate(sqlstmt);
			
			sqlstmt = "INSERT INTO tb_user VALUES " +
					"('Luke1', 'Luke1', 'Luke1')," +
					"('Luke2', 'Luke2', 'Luke2')," +
					"('Luke3', 'Luke3', 'Luke3');";
			
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
	
	
	
	
	/**
	 * insert User
	 * @param user
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void add(User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
				              + "user=root&password=1234");
			
			
			String sql = "insert into tb_user values(?,?,?)";
			PreparedStatement preparestatement = connect.prepareStatement(sql); 
		    preparestatement.setString(1,user.getUsername());
		    preparestatement.setString(2,user.getPassword());
		    preparestatement.setString(3,user.getEmail());
		    preparestatement.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<Object> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/covid_illinois?"
				              + "user=root&password=1234");
			
			
			String sql = "select * from tb_user";
			PreparedStatement preparestatement = connect.prepareStatement(sql); 
			ResultSet resultSet = preparestatement.executeQuery();
			
			while(resultSet.next()){
				User user = new User();
				user.setUsername(resultSet.getString("username"));
	    		user.setPassword(resultSet.getString("password"));
	    		user.setEmail(resultSet.getString("email"));
	    		list.add(user);
			 }
			 
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
		
	}
		
}
