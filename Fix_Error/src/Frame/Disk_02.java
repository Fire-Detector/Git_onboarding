import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Desk_02 {
    
    private String prodctid;
    private String deskid;
    private String diskdata;
    private String price;

    public Desk_02(String prodctid, String deskid, String diskdata, String price) {
        this.prodctid = prodctid;
        this.deskid = deskid;
        this.diskdata = diskdata;
        this.price = price;
    }

    public String getProdctid() { return prodctid; }
    public String getDeskid() { return deskid; }
    public String getDiskdata() { return diskdata; }
    public String getPrice() { return price; }

    public static ArrayList<Desk_02> getDeskList() {
        ArrayList<Desk_02> list = new ArrayList<>();
        try {
        	Connection con = DriverManager.getConnection(
        	"jdbc:oracle:thin:@localhost:1521:xe", "admin", "12345");
            
            String sql = "SELECT * FROM desk";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
 
            while (rs.next()) {
                String prodctid = rs.getString("prodctid");
                String deskid = rs.getString("deskid");
                String diskdata = rs.getString("diskdata");
                String price = rs.getString("price");

                Desk_02 desk = new Desk_02(prodctid, deskid, diskdata, price);
                list.add(desk);
            }

            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

