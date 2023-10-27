/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.TableFoodController;
import Models.Data.TableFood;
import Models.TableFoodModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class TableManager extends javax.swing.JFrame {

    /**
     * Creates new form TableManager
     */
//    public static boolean check = false;

    public TableManager() {
        initComponents();
        setTitle("Quản lý bàn ");
        setSize(750, 550);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        refreshTableLayout();
        jCbb_floor.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    if(e.getStateChange() == ItemEvent.SELECTED){
                        refreshTableLayout();
                    }
                }
            }
        );
        
        searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText = searchTextField.getText(); // Lấy tên bàn cần tìm kiếm
            boolean found = false; // Biến để kiểm tra xem có bàn nào được tìm thấy không

            // Lặp qua danh sách các JButton (bàn)
            Component[] components = jPanel1.getComponents(); // jPanel1 là panel chứa các JButton
            for (Component component : components) {
                if (component instanceof JButton) {
                    JButton jButton = (JButton) component;
                    String buttonText = jButton.getText(); // Lấy tên của JButton
//                    tableButtonReference = (JButton) component;
                    // Kiểm tra xem JButton đã gọi đồ (màu nền là xanh)
                    Color buttonColor = jButton.getBackground();
                    if (buttonColor.equals(Color.GREEN)) {
                        // Nếu có ít nhất một bàn đã gọi đồ, đổi màu nền của nút tìm kiếm thành màu xanh
                        searchButton.setBackground(Color.GREEN);
                        found = true;
                    } else if (buttonText.equalsIgnoreCase(searchText)) {
                        jButton.setBackground(Color.YELLOW); // Đổi màu nền thành màu vàng
                        found = true;
                    } else {
                        // Đổi lại màu nền gốc của các JButton không trùng khớp
                        jButton.setBackground(Color.WHITE);
                    }
                }
            }

            // Nếu không tìm thấy bàn nào trùng khớp, hiển thị thông báo lỗi
            if (!found) {
                JOptionPane.showMessageDialog(TableManager.this, "Không tìm thấy " + searchText, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    }
    public static JButton jButtonTransform;
    public static String tableFoodName;
    public void changeColor(JButton button, Color color){
        button.setBackground(color);
    }
    public JButton getButton(JButton button){
        return button;
    }

    private static Map<String, Color> tableColors = new HashMap<>();
    
    public static void updateTableColor(String tableName, Color color) {
        tableColors.put(tableName, color);
    }
    
    
    private void refreshTableLayout() {
        String floor = jCbb_floor.getSelectedItem().toString();
        TableFood  tableFood = new TableFood();
        TableFoodModel tableFoodModel = new TableFoodModel();
        TableFoodController tableFoodController = new TableFoodController(tableFoodModel);

        // Lấy danh sách các bàn từ cơ sở dữ liệu ứng với số tầng được chọn
        List<TableFood> listTable = tableFoodController.GetTableFood(floor);

        // Xoá tất cả các button (bàn) trong panel
        jPanel1.removeAll();

        // Thiết lập GridLayout cho jPanel1
        GridBagLayout gridBagLayout = new GridBagLayout();
        jPanel1.setLayout(gridBagLayout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        int buttonWidth = 110;
        int buttonHeight = 80;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        int numRows = (listTable.size() + 5) / 6; // Số hàng tính theo mỗi 6 nút
        int numColumns = 6; // Số cột

        int buttonCount = 0; // Biến đếm số lượng nút đã thêm vào

        for (TableFood table : listTable) {
            JButton jButton1  = new JButton(table.getTableName());
            jButton1.setBackground(Color.white);
            jButton1.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            gbc.anchor = GridBagConstraints.WEST;

            // Kiểm tra xem tên bàn có trong Map không
            if (tableColors.containsKey(table.getTableName())) {
                jButton1.setBackground(tableColors.get(table.getTableName())); // Cài đặt màu từ Map
            } else {
                jButton1.setBackground(Color.white); // Màu mặc định
            }

            jButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String tableName = table.getTableName();
                    tableFoodName = table.getTableName(); // tạo biến toàn cục chứa tên bàn
                    CustomerRequest request = new CustomerRequest(tableName);
                    request.setButton(jButton1);
                    request.setTableManagerForm(TableManager.this);
                    request.setVisible(true);

                    // Khi bàn được chọn, cập nhật trạng thái màu trong Map
                    tableColors.put(tableName, Color.GREEN); // Ví dụ màu xanh khi bàn đang có khách
                }
            });
            jPanel1.add(jButton1, gbc);

            buttonCount++; // Tăng biến đếm sau khi thêm nút

            // Nếu đã thêm đủ số cột, xuống dòng
            if (buttonCount == numColumns) {
                gbc.gridwidth = GridBagConstraints.REMAINDER; // Xuống dòng mới
                buttonCount = 0; // Reset biến đếm về 0
            } else {
                gbc.gridwidth = 1; // Tiếp tục trên cùng một dòng
            }
        }
        jPanel1.setPreferredSize(new Dimension(680, 270));
        // Cập nhật lại giao diện
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jCbb_floor = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        searchButton.setText("Tìm kiếm");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jCbb_floor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tầng 1", "Tầng 2", "Tầng 3" }));
        jCbb_floor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbb_floorActionPerformed(evt);
            }
        });

        jLabel1.setText("HIển thị theo");

        jButton12.setText("Thêm bàn");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Trang chủ");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Quản lý bàn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(182, 182, 182)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCbb_floor, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCbb_floor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        AddTable addTableForm = new AddTable();
        addTableForm.setVisible(true);
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        HomeStaff home = new HomeStaff();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jCbb_floorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbb_floorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbb_floorActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String nameTableFinding = searchTextField.getText();
        if (tableColors.containsKey(nameTableFinding)) {
                searchButton.setBackground(tableColors.get(nameTableFinding)); // Cài đặt màu từ Map
            } else {
                searchButton.setBackground(Color.white); // Màu mặc định
            }
    }//GEN-LAST:event_searchButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TableManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TableManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TableManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TableManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JComboBox<String> jCbb_floor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
}
