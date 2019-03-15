package in.education.student.common.authentication;

import in.education.student.common.GeneralQueries;
import in.education.student.common.configuration.DBUtils;
import in.education.student.common.exception.AuthenticationException;
import in.education.student.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthenticationRepository {

	private DBUtils dbUtils;

	@Autowired
	AuthenticationRepository(DBUtils dbUtils) {
		this.dbUtils = dbUtils;
	}

	User authenticateUser(String userName, String password) throws AuthenticationException {

		try {
			Statement statement = dbUtils.getDBStatement();

			// If User Exists
			if(dbUtils.recordExists(GeneralQueries.isUserExists(userName))) {

				// If User and Password Matches
				if(dbUtils.recordExists(GeneralQueries.isValidUser(userName,password))) {

					User user = getUserData(GeneralQueries.getUserData(userName
							, password));

					statement.execute(GeneralQueries.updateUserLogin(user.getId()));	// Update User Last Login
					statement.executeUpdate(GeneralQueries.resetUserFailureAttempt(user.getId()));	// Update User Last Login

					return user;
				} else {
					// Update user failure login attempt
					statement.executeUpdate(GeneralQueries.updateUserFailureAttempt(userName));
					throw new AuthenticationException("Username and Password does not match");
				}
			} else {
				throw new AuthenticationException("Username does not exists");
			}

		} catch (SQLException e) {

			throw new AuthenticationException("Something went wrong !!!");
		}
	}

	private User getUserData(String sql) throws AuthenticationException {
		ResultSet rs = null;
		User user = null;
		try
		{
			rs = dbUtils.getDBStatement().executeQuery(sql);

			if(rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("user_name"),
						rs.getString("email"), rs.getLong("role_id"),
						rs.getString("user_desc"));

			}
		} catch (SQLException e) {
			throw new AuthenticationException("Something went Wrong");

		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	ArrayList getServices(String userName, String password)
	{
		ResultSet rs;
		ArrayList<HashMap<String, String>> services = new ArrayList<>();
		HashMap<String, String> serviceMap;

		try
		{
			String sql = "select s.service_id, s.service_url, s.service_name,s" +
					".parent_id, s.dispay_order" +
					" from users u" +
					" join role_services rs on(u.role_id = rs.role_id )" +
					" join services s on (s.service_id = rs.service_id)" +
					" where user_name = '"+userName+ "' " +
					" and password = '"+password+"'" +
					" order by parent_id, service_id";

			rs = dbUtils.getDBStatement().executeQuery(sql);
			while (rs.next())
			{
				serviceMap = new HashMap<>();

				serviceMap.put("service_id", rs.getString("service_id"));
				serviceMap.put("service_url", rs.getString("service_url"));
				serviceMap.put("service_name", rs.getString("service_name"));
				serviceMap.put("parent_id", rs.getString("parent_id"));
				serviceMap.put("dispay_order", rs.getString("dispay_order"));

				services.add(serviceMap);
			}
		}
		catch(SQLException sqle)
		{
			return null;
		}

		return services;
	}
}
