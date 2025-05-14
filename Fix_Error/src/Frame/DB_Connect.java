
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public String My_Page(String id) throws SQLException{
		String sql = "SELECT user_id, user_phone, user_adress from user_tbl where customer_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);


		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs = pstmt.executeQuery();

		//rs[1];

		return " ";
	}

	    public List<GpuDTO> getAllGpus() { //getAllGpus()메소드: GpuDTO객체 리스트를 반환
        List<GpuDTO> list = new ArrayList<>(); //GpuDTO라는 객체들을 담을 수 있는 비어있는 리스트 생성

        try (
        	//3. SQL 실행 & 결과 처리
            Connection conn = getConnection(); //오라클 DB와 연결된 Connection 객체 생성
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM GPU"); //SQL 실행을 준비하는 PreparedStatement 생성 ("GPU 테이블 전체 조회" 쿼리 준비)
            ResultSet rs = pstmt.executeQuery(); //결과는 rs (ResultSet)에 담김
        ) {
        	//4. 결과를 GpuDTO 객체로 변환
            while (rs.next()) {
                int id = rs.getInt("productID");
                String name = rs.getString("name");
                String perf = rs.getString("performance");
                int price = rs.getInt("price");

                GpuDTO gpu = new GpuDTO(id, name, perf, price);// DTO로 포장
                list.add(gpu);// 리스트에 넣기
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //5. 리스트 반환
        return list;
    }//getAllGpus()

}
