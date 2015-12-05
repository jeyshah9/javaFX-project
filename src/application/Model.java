package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
	public static String nameOfUser;

	/**
	 * Check Login is correct
	 * 
	 * @param level
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean getLogin(String level, String username, String password) {

		try {
			Connection conn = SqlHelperClass.connector();
			if (conn == null)
				System.exit(1);
			String query = "select * from login where level=? and name=? and password=? ";

			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, level);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				Music.playMusic();
				conn.close();
				return true;
			} else {
				resultSet.close();
				preparedStatement.close();
				conn.close();
				return false;
			}

		} catch (SQLException e) {
			System.exit(0);
			return false;
		}

	}

	/**
	 * Checks UserName Exists and is Email
	 * 
	 * @param userName
	 * @return
	 */
	public boolean checkUserName(String userName) {
		try {
			Connection conn = SqlHelperClass.connector();

			if (conn == null)
				System.exit(1);
			String query = "select * from login where name=? ";

			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				conn.close();
				return false;
			} else {
				Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
				Matcher matcher = pattern.matcher(userName);
				if (matcher.matches()) {
					resultSet.close();
					preparedStatement.close();
					conn.close();
					return true;
				} else {
					resultSet.close();
					preparedStatement.close();
					conn.close();
					return false;
				}

			}

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Login user and adds date_created
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean registrationUser(String userName, String password) {
		try {
			Connection conn = SqlHelperClass.connector();
			if (conn == null)
				System.exit(1);
			Date date = new Date();
			String query = "insert into login (level,name,password,date_created) values(?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, "u");
			preparedStatement.setString(2, userName.trim());
			preparedStatement.setString(3, password.trim());
			preparedStatement.setString(4, date.toString());
			int i = preparedStatement.executeUpdate();
			if (i == 1) {

				new ChangeFxml().swap("/design/First.fxml");

				preparedStatement.close();
				conn.close();
				return true;
			} else {
				preparedStatement.close();
				conn.close();
				return false;
			}

		} catch (Exception e) {

			return false;
		}

	}

	/**
	 * Update last login Field
	 * 
	 * @param level
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean updateDateLogin(String level, String userName, String password) {
		try {

			Connection conn = SqlHelperClass.connector();
			if (conn == null)
				System.exit(1);
			Date date = new Date();
			String query = "update  login  set date_accessed=? where level=? and name=? and password =? ";

			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, date.toString());
			preparedStatement.setString(2, level);
			preparedStatement.setString(3, userName.trim());
			preparedStatement.setString(4, password.trim());
			int i = preparedStatement.executeUpdate();
			if (i == 1) {
				nameOfUser = userName;
				preparedStatement.close();
				conn.close();
				return true;
			} else {
				preparedStatement.close();
				conn.close();
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	/**
	 * Gets Email and gives password away
	 * 
	 * @param username
	 * @return
	 */
	public String getPassword(String username) {
		String sValue = "";
		try {

			Connection connection = SqlHelperClass.connector();
			String query = "select password from login where name=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				sValue = resultSet.getString("password");
				preparedStatement.close();
				connection.close();

			} else {

				sValue = "InValid.! Plz Enter Email";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			sValue = e + "";
		}

		return sValue;
	}

	/**
	 * Get Number of count in data table
	 * 
	 * @return
	 */
	public static int getCount() {
		int count = 0;
		try {
			Connection connection = SqlHelperClass.connector();
			String query = "select count(*) as count from data";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				count = resultSet.getInt("count");
				preparedStatement.close();
				resultSet.close();
			} else {
				count = 0;
				preparedStatement.close();
				connection.close();

			}
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}

	/**
	 * Gets Number of total user
	 * @return
	 */
	public static int getUserCount(){
		int count = 0;
		try {
			Connection connection = SqlHelperClass.connector();
			String query = "select count(*) as count from login";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				count = resultSet.getInt("count");
				preparedStatement.close();
				resultSet.close();
			} else {
				count = 0;
				preparedStatement.close();
				connection.close();

			}
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}
	/**
	 * Insert Data into data table
	 * 
	 * @param header
	 * @param containt
	 * @return
	 */
	public static boolean insertData(String header, String containt) {
		try {
			if (Model.CheckHeader(header)) {
				Connection connection = SqlHelperClass.connector();
				String query = "insert into data(header,descriptioin,date_updated) values(?,?,?)";

				PreparedStatement preparedStatement = connection.prepareStatement(query);

				preparedStatement.setString(1, header);
				preparedStatement.setString(2, containt);
				preparedStatement.setString(3, new Date().toString());
				int i = preparedStatement.executeUpdate();
				if (i == 1) {
					preparedStatement.close();
					connection.close();
					return true;
				} else {
					preparedStatement.close();
					connection.close();
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}

	}

	/**
	 * Getting List for choice Box in admin edit panel
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List values() {
		List<String> values = new ArrayList<>();
		try {
			Connection connection = SqlHelperClass.connector();
			String query = "select header from data ";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				values.add(resultSet.getString("header"));
			}
			preparedStatement.close();
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}

		return values;
	}

	/**
	 * To populate TextArea for edition
	 * 
	 * @param header
	 * @return
	 */
	public static String allValues(String header) {
		String value = "";
		try {
			Connection connection = SqlHelperClass.connector();
			String query = "select descriptioin from data where header=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, header);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				value = resultSet.getString("descriptioin");
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			value = "";
		}
		return value;
	}

	/**
	 * processes update
	 * 
	 * @param choiceBox
	 * @param textFeild
	 * @param textArea
	 * @return
	 */
	public static boolean updateValues(String choiceBox, String textFeild, String textArea) {
		try {
			Connection connection = SqlHelperClass.connector();
			String query = "update data set header = ? , descriptioin=? , date_updated=? where header=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, textFeild);
			preparedStatement.setString(2, textArea);
			preparedStatement.setString(3, new Date().toString());
			preparedStatement.setString(4, choiceBox);
			System.out.println(textArea + " TextArea");
			int i = preparedStatement.executeUpdate();
			if (i == 1) {
				preparedStatement.close();
				connection.close();
				return true;
			} else {
				preparedStatement.close();
				connection.close();
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e + ">");
			return false;
		}
	}

	/**
	 * validate duplicate header persist
	 * 
	 * @param header
	 * @return
	 */
	public static boolean CheckHeader(String header) {
		try {
			Connection connection = SqlHelperClass.connector();
			String query = "select * from data where header=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, header);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {

				resultSet.close();
				preparedStatement.close();
				connection.close();
				return true;
			} else {

				resultSet.close();
				preparedStatement.close();
				connection.close();
				return false;
			}

		} catch (Exception exception) {
			return false;
		}

	}

	public static String[] getHeader(int pageIndex) {
		// TODO Auto-generated method stub
		String[] value = {"","",""};
		try {
			Connection connection = SqlHelperClass.connector();
			String query = "select * from data where id=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, pageIndex);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				value[0] = resultSet.getString("Header");
				value[1] = resultSet.getString("descriptioin");
				value[2] = resultSet.getString("date_updated");
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			value[0] = "No Data";
			value[1] ="No Data";
			value[2] ="No Data";
		}
		return value;
	}

	public static String[] getLoginData(int i) {
		// TODO Auto-generated method stub
		String[] value = {"","","",""};
		try {
			Connection connection = SqlHelperClass.connector();
			String query = "select * from login where id=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, i);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				
				value[0] = resultSet.getString("name");
				value[1] = resultSet.getString("password");
				value[2] = resultSet.getString("date_created");
				value[3]= resultSet.getString("date_accessed");
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e+"");
		}
		return value;
	}

}
