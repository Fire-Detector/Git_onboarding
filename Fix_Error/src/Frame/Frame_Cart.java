import java.awt.event.*;
import java.sql.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Frame_Cart extends javax.swing.JFrame {
    private String userId;
    public static Frame_Cart instance;

    public Frame_Cart(String userId) {
        this.userId = userId;
        instance = this;
        initComponents();
        loadCartDataFromDatabase();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveCartDataToDatabase();
            }
        });
    }

    public void addRowToCart(String name, String spec, String price, String desc) {
        DefaultTableModel model = (DefaultTableModel) Tbl_Cart.getModel();
        model.insertRow(0, new Object[]{name, spec, price, desc});
    }

    public void loadCpuListToCart(List<CPU_DB> cpulList) {
        DefaultTableModel model = (DefaultTableModel) Tbl_Cart.getModel();
        model.setRowCount(0);
        for (CPU_DB cpu : cpulList) {
            model.addRow(new Object[]{
                cpu.getcpuid(), cpu.getcpudata(), cpu.getcpuprice(), cpu.getcpuprodctid()
            });
        }
    }

    public void loadCartDataFromDatabase() {
        DefaultTableModel model = (DefaultTableModel) Tbl_Cart.getModel();
        model.setRowCount(0);

        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "member", "12345")) {

            String sql = "SELECT name, spec, price, description FROM cart WHERE user_Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("name"),
                    rs.getString("spec"),
                    rs.getString("price"),
                    rs.getString("description")
                });
            }

            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCartDataToDatabase() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "member", "12345")) {

            String deleteSql = "DELETE FROM cart WHERE user_Id = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
            deleteStmt.setString(1, userId);
            deleteStmt.executeUpdate();
            deleteStmt.close();

            String insertSql = "INSERT INTO cart (name, spec, price, description, user_Id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            DefaultTableModel model = (DefaultTableModel) Tbl_Cart.getModel();

            for (int i = 0; i < model.getRowCount(); i++) {
                pstmt.setString(1, model.getValueAt(i, 0).toString());
                pstmt.setString(2, model.getValueAt(i, 1).toString());
                pstmt.setString(3, model.getValueAt(i, 2).toString());
                pstmt.setString(4, model.getValueAt(i, 3).toString());
                pstmt.setString(5, userId);
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl_Cart = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(450, 480));

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 0, 24));
        jLabel1.setText("장바구니");

        jButton1.setText("구매하기");
        jButton1.addActionListener(evt -> {
            // 구매 로직은 여기 작성
        });

        Tbl_Cart.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[]{"제품 명", "성능", "가격", "설명"}
        ));
        jScrollPane1.setViewportView(Tbl_Cart);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(59, 59, 59))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
        );

        pack();
    }

    private javax.swing.JTable Tbl_Cart;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
}
