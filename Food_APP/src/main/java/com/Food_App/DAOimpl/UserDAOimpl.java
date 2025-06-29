package com.Food_App.DAOimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.Food_App.DAO.UserDAO;
import com.Food_App.DBConnection.DBConnection;
import com.Food_App.Models.User;

public class UserDAOimpl implements UserDAO{
	
	private final String INSERT = "insert into user ( name, user_name, password, email, phonenumber, address, role, created_date, last_login_date)  values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String UPDATE = "update user set name = ?, user_name = ?, password = ?, email = ?, phonenumber = ?, address = ?, role = ? ";
	private final String GET_USER_BY_ID = "select * from user where user_id = ? ";
	private final String GET_ALL_USERS="select * from user";
	private final String DELETE_USER_BY_ID = "DELETE FROM user WHERE user_id = ?";  
	

	
	@Override
	public List<User> getAllUsers() {
		List<User> users =new ArrayList<User>();
	
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);) {
			
			
			ResultSet resultset = preparedStatement.executeQuery();
			
			while(resultset.next()) {
				int id=resultset.getInt("user_id");
				String name =resultset.getString("name");
				String user_name =resultset.getString("user_name");
				String password =resultset.getString("password");	
				String email =resultset.getString("email");		
				String phonenumber =resultset.getString("phonenumber");
				String address =resultset.getString("address");
				String role =resultset.getString("role");
				Timestamp created_date =resultset.getTimestamp("created_date");
				Timestamp last_login_date =resultset.getTimestamp("last_login_date");
			
				User user = new User(id, name, user_name, password, email, phonenumber, address, role, created_date, last_login_date );
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserById(int userId) {
		User user = null;
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);) {
			
			preparedStatement.setInt(1, userId);
			
			ResultSet resultset = preparedStatement.executeQuery();
			
			while(resultset.next()) {
				int id=resultset.getInt("user_id");
				String name =resultset.getString("name");
				String user_name =resultset.getString("user_name");
				String password =resultset.getString("password");	
				String email =resultset.getString("email");		
				String phonenumber =resultset.getString("phonenumber");
				String address =resultset.getString("address");
				String role =resultset.getString("role");
				Timestamp created_date =resultset.getTimestamp("created_date");
				Timestamp last_login_date =resultset.getTimestamp("last_login_date");
			
				user = new User(userId ,name, user_name, password, email, phonenumber, address, role, created_date, last_login_date );
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void addUser(User u) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT);) {

			preparedStatement.setString(1, u.getName());
			preparedStatement.setString(2, u.getUser_name());
			preparedStatement.setString(3, u.getPassword());
			preparedStatement.setString(4, u.getEmail());
			preparedStatement.setString(5, u.getPhonenumber());
			preparedStatement.setString(6, u.getAddress());
			preparedStatement.setString(7, u.getRole());
			preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(9,new Timestamp(System.currentTimeMillis()));
			
			int i=preparedStatement.executeUpdate();
			System.out.println("Successfuly done "+i);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(User u) {	
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
			
			preparedStatement.setString(1, u.getName());
			preparedStatement.setString(2, u.getUser_name());
			preparedStatement.setString(3, u.getPassword());
			preparedStatement.setString(4, u.getEmail());
			preparedStatement.setString(5, u.getPhonenumber());
			preparedStatement.setString(6, u.getAddress());
			preparedStatement.setString(7, u.getRole());
			
			
			int i = preparedStatement.executeUpdate();
			System.out.println("Successfuly done "+i);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);) {
		
			preparedStatement.setInt(1, userId);
			int i = preparedStatement.executeUpdate();
			System.out.println("Succassfully done "+i);
			if(i>0) {
				System.out.println("User with ID " + userId + " deleted successfully.");
			}else {
				System.out.println("No user found with ID " + userId);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public User getUserByUsername(String username) {
	    String sql = "SELECT * FROM user WHERE user_name = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, username);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            User user = new User();
	            user.setUserId(rs.getInt("user_id"));
	            user.setName(rs.getString("name"));
	            user.setUsername(rs.getString("user_name"));
	            user.setPassword(rs.getString("password"));
	            user.setEmail(rs.getString("email"));
	            user.setPhonenumber(rs.getString("phonenumber"));
	            user.setAddress(rs.getString("address"));
	            user.setRole(rs.getString("role"));
	            user.setCreated_Date(rs.getTimestamp("created_date"));
	            user.setLast_Login_Date(rs.getTimestamp("last_login_date"));
	            return user;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
