
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Connect {
	private String DB_Driver;
	private String DB_Url;
	private String DB_Username;
	private String DB_Password;
	private Connection conn;

	public DB_Connect() {
		this.DB_Driver = "oracle.jdbc.OracleDriver";
		this.DB_Url = "jdbc:oracle:thin:@localhost:1521:xe";
		this.DB_Username = "system";
		this.DB_Password = "12345";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(DB_Driver);
			// 3. Connection
			conn = DriverManager.getConnection(DB_Url, DB_Username, DB_Password); // 이 url로 사용자의 아이디와 패스워드를 가지고 연결 한다!
			System.out.println("DB 연결 성공: " + DB_Username);
		} catch (ClassNotFoundException e1) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e1) {
			System.out.println("db 연결 실패");
			e1.printStackTrace();
		}
	}
	public Connection getConnection() {
	    try {
	        Class.forName(DB_Driver);
	        Connection conn = DriverManager.getConnection(DB_Url, DB_Username, DB_Password);
	        return conn;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

//테스트
	public boolean ID_Check() throws SQLException {
		String sql = "SELECT CUSTOMER_ID FROM USER WHERE CUSTOMER_ID = ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs = pstmt.executeQuery();
		return rs.next();

	}

	public boolean Password_Check() throws SQLException {
		String sql = "SELCET CUSTOMER_PW FROM USER WHERE CUSTOMER_PW = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs = pstmt.executeQuery();
		return rs.next();
	}

	public void Show_List() throws SQLException {
		String sql = "SELECT * FROM ITEM";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs = pstmt.executeQuery();
	}
	public void DB_Connect() {
		// TODO Auto-generated method stub
		
	}
}
