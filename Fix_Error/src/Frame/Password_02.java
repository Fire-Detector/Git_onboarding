import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Password_02 {
	public static boolean login(String userid, String inputPw) {
		boolean result = false;
		 
		 
		 Connection con = null;
		try {
			con = DriverManager.getConnection(
			 "jdbc:oracle:thin:@localhost:1521:xe", "member", "12345");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Password_01 userDto = null;

		try {
			String sql = "SELECT userid,password FROM  usertbl WHERE userid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				userDto = new Password_01();
				userDto.setUserid(rs.getString("userid"));
				userDto.setPassword(rs.getString("password"));

				if (userDto.getPassword().equals(inputPw)) {
					result = true;
				} 
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
}