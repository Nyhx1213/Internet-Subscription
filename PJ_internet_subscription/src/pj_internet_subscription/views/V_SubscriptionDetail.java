/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package pj_internet_subscription.views;

import java.util.LinkedHashMap;
import javax.swing.table.DefaultTableModel;
import pj_internet_subscription.C_internet_subscription;
import pj_internet_subscription.model.M_Antivirus;
import pj_internet_subscription.model.M_Computer;
import pj_internet_subscription.model.M_Method;
import pj_internet_subscription.model.M_Outlet;
import pj_internet_subscription.model.M_Payment;
import pj_internet_subscription.model.M_Product;
import pj_internet_subscription.model.M_Role;
import pj_internet_subscription.model.M_Room;
import pj_internet_subscription.model.M_Subscription;
import pj_internet_subscription.model.M_System;
import pj_internet_subscription.model.M_User;

/**
 *
 * @author adjedjm
 */
public class V_SubscriptionDetail extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(V_SubscriptionDetail.class.getName());

    /**
     * Creates new form V_CRUD
     */
    private int indexRoom;
    private int currentOutletIndex;
    private DefaultTableModel dm_tb_products, dm_tb_payments;
    private LinkedHashMap<Integer, M_Product> productList;
    private LinkedHashMap<Integer, M_Payment> paymentList;
    private LinkedHashMap<Integer, M_Method> paymentMethodList;
    
    public void empty(){
        cb_outlet.removeAllItems();
        cb_room.removeAllItems();
    }
    
    public void productTable(){
        M_Product product = null;
        dm_tb_products.setRowCount(0);        
        for (int key : productList.keySet() ) {
            product = productList.get(key);
            System.out.println(product.getDesignation());
            dm_tb_products.addRow(new Object[]{product.getId(), product.getDesignation(), product.getPrice()});
        }
    }
    
    public void paymentTable(){
        M_Payment payment = null;
        dm_tb_payments.setRowCount(0);        
        for (int key : paymentList.keySet() ) {
            payment = paymentList.get(key);
            dm_tb_payments.addRow(new Object[]{payment.getId(), payment.getAmount(), payment.getDate_payment(), paymentMethodList.get(payment.getId_method()).getName() });
        }    
    }
    
    public void editable(String action) {
        cb_active.setEnabled(false);
        cb_room.setEditable(false);
        switch (action){
            case "detail" : 
                tf_userFirstName.setEditable(false);
                tf_userLastName.setEditable(false);
                tf_beginDate.setEditable(false);
                tf_endDate.setEditable(false);
                tf_boxLogin.setEditable(false);
                tf_boxPassword.setEditable(false);
                ta_comment.setEditable(false);
                cb_room.setEnabled(false);
                cb_outlet.setEnabled(false);
                tf_userEmail.setEditable(false);
                bt_products.setText("Details");
                break;
            case "modify" : 
                tf_userFirstName.setEditable(true);
                tf_userLastName.setEditable(true);
                tf_beginDate.setEditable(true);
                tf_endDate.setEditable(true);
                tf_boxLogin.setEditable(true);
                tf_boxPassword.setEditable(true);
                ta_comment.setEditable(true);
                cb_room.setEnabled(true);
                cb_outlet.setEnabled(true); 
                tf_userEmail.setEditable(true);
                bt_products.setText("Modify");
        }
    }
    
    public void display(String action, M_Subscription subscription, M_User user, LinkedHashMap<Integer, M_Role> roleList,
                        LinkedHashMap<Integer, M_Computer> computer, LinkedHashMap<Integer, M_System> systemList, 
                        LinkedHashMap<Integer, M_Antivirus> antivirusList, LinkedHashMap<String,M_Outlet> outletList, 
                        LinkedHashMap<String, M_Room> roomList, LinkedHashMap<Integer, M_Method> paymentMethodList,
                        LinkedHashMap<Integer, M_Product> productList, LinkedHashMap<Integer, M_Payment> paymentList){
        empty();
        this.productList = productList;
        this.paymentList = paymentList;
        this.paymentMethodList = paymentMethodList;
        dm_tb_products = (DefaultTableModel) tb_products.getModel();
        dm_tb_payments = (DefaultTableModel) tb_payments.getModel();
        productTable();
        paymentTable();
        lb_crud.setText("Subscription of "+user.getFirst_name()+" "+user.getLast_name());
        editable(action);
        int compteur = 0;
        M_Outlet outlet = outletList.get(subscription.getCode_outlet());
        tf_userFirstName.setText(user.getFirst_name());
        tf_userLastName.setText(user.getLast_name());
        tf_beginDate.setText(String.valueOf(subscription.getDate_begin()));
        tf_endDate.setText(String.valueOf(subscription.getDate_end()));
        ta_comment.setText(subscription.getComment());
        tf_boxLogin.setText(subscription.getLogin());
        tf_boxPassword.setText(subscription.getPassword());
        tf_userEmail.setText(user.getEmail());
        cb_active.setSelected(subscription.isActive());
        for (String code : roomList.keySet()){
            cb_room.addItem(roomList.get(code).getCode());
            if (roomList.get(code).getCode().equals(outlet.getCode_room())){
                indexRoom = compteur;
            }
            compteur++;
         }
        cb_room.setSelectedIndex(indexRoom);
        String selectedRoomCode = cb_room.getItemAt(indexRoom);
        compteur = 0;
        
        for (String code : outletList.keySet()) {
            M_Outlet outletverification = outletList.get(code);
            if (outletverification.getCode_room().equals(selectedRoomCode)){
                cb_outlet.addItem(outletverification.getCode());
                if (outletverification.getCode().equals(subscription.getCode_outlet())){
                    cb_outlet.setSelectedIndex(compteur);
                    currentOutletIndex = compteur;
                }
                compteur++;
            }
        }
        this.setVisible(true);
    }
    
    public V_SubscriptionDetail(C_internet_subscription controller, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_crud = new javax.swing.JLabel();
        lb_userName = new javax.swing.JLabel();
        lb_userEmail = new javax.swing.JLabel();
        lb_beginDate = new javax.swing.JLabel();
        lb_endDate = new javax.swing.JLabel();
        lb_boxLogin = new javax.swing.JLabel();
        lb_boxPassword = new javax.swing.JLabel();
        tf_userFirstName = new javax.swing.JTextField();
        tf_userEmail = new javax.swing.JTextField();
        tf_beginDate = new javax.swing.JTextField();
        tf_endDate = new javax.swing.JTextField();
        tf_boxLogin = new javax.swing.JTextField();
        tf_boxPassword = new javax.swing.JTextField();
        cb_active = new javax.swing.JCheckBox();
        lb_comment = new javax.swing.JLabel();
        lb_room = new javax.swing.JLabel();
        lb_outlet = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_products = new javax.swing.JTable();
        lb_payments = new javax.swing.JLabel();
        bt_products = new javax.swing.JButton();
        cb_outlet = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_payments = new javax.swing.JTable();
        lb_products = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_userLastName = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        ta_comment = new javax.swing.JTextArea();
        cb_room = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        mb_menu = new javax.swing.JMenuBar();
        mn_file = new javax.swing.JMenu();
        mi_exit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lb_crud.setText("jLabel1");

        lb_userName.setText("First name :");

        lb_userEmail.setText("Email :");

        lb_beginDate.setText("Beginning date : ");

        lb_endDate.setText("End date : ");

        lb_boxLogin.setText("Box login :");

        lb_boxPassword.setText("Box password : ");

        cb_active.setText("Active");

        lb_comment.setText("Comment : ");

        lb_room.setText("Room : ");

        lb_outlet.setText("Outlet :");

        tb_products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Label", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_products);
        if (tb_products.getColumnModel().getColumnCount() > 0) {
            tb_products.getColumnModel().getColumn(0).setResizable(false);
            tb_products.getColumnModel().getColumn(1).setResizable(false);
            tb_products.getColumnModel().getColumn(2).setResizable(false);
        }

        lb_payments.setText("Payments");

        bt_products.setText("jButton1");

        tb_payments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id ", "Amount", "Date", "Method"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb_payments);
        if (tb_payments.getColumnModel().getColumnCount() > 0) {
            tb_payments.getColumnModel().getColumn(0).setResizable(false);
            tb_payments.getColumnModel().getColumn(1).setResizable(false);
            tb_payments.getColumnModel().getColumn(2).setResizable(false);
            tb_payments.getColumnModel().getColumn(3).setResizable(false);
        }

        lb_products.setText("Products");

        jLabel3.setText("Last name :");

        ta_comment.setColumns(20);
        ta_comment.setRows(5);
        jScrollPane3.setViewportView(ta_comment);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jLabel1.setText("Computers");

        mb_menu.setName("mb_menu"); // NOI18N

        mn_file.setText("File");
        mn_file.setName("mn_file"); // NOI18N

        mi_exit.setText("Exit");
        mi_exit.setName("mi_exit"); // NOI18N
        mi_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_exitActionPerformed(evt);
            }
        });
        mn_file.add(mi_exit);

        mb_menu.add(mn_file);

        setJMenuBar(mb_menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lb_userName)
                                        .addComponent(jLabel3)
                                        .addComponent(lb_userEmail))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tf_userFirstName)
                                        .addComponent(tf_userEmail)
                                        .addComponent(tf_userLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lb_boxLogin)
                                            .addComponent(lb_endDate))
                                        .addComponent(lb_beginDate)
                                        .addComponent(lb_boxPassword))
                                    .addGap(16, 16, 16)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tf_boxPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                        .addComponent(tf_boxLogin)
                                        .addComponent(tf_endDate)
                                        .addComponent(tf_beginDate))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lb_comment)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(212, 212, 212)
                                .addComponent(lb_crud, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lb_outlet)
                                                    .addComponent(lb_room))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cb_outlet, 0, 178, Short.MAX_VALUE)
                                                    .addComponent(cb_room, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(lb_products)
                                                    .addGap(103, 103, 103)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(164, 164, 164)
                                        .addComponent(bt_products)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(199, 199, 199)
                                        .addComponent(jLabel1))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(cb_active)
                        .addGap(560, 560, 560)
                        .addComponent(lb_payments)))
                .addGap(69, 69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cb_active)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_userName)
                                    .addComponent(tf_userFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(tf_userLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_userEmail)
                                    .addComponent(tf_userEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_beginDate)
                                    .addComponent(tf_beginDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_endDate)
                                    .addComponent(tf_endDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_boxLogin)
                                    .addComponent(tf_boxLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(132, 132, 132)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_boxPassword)
                                    .addComponent(tf_boxPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(lb_comment))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lb_payments)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(14, 14, 14)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_crud)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_room)
                                    .addComponent(cb_room, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_outlet)
                                    .addComponent(cb_outlet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(lb_products)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(bt_products)))
                .addGap(232, 232, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mi_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mi_exitActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                V_SubscriptionDetail dialog = new V_SubscriptionDetail(null, new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_products;
    private javax.swing.JCheckBox cb_active;
    private javax.swing.JComboBox<String> cb_outlet;
    private javax.swing.JComboBox<String> cb_room;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lb_beginDate;
    private javax.swing.JLabel lb_boxLogin;
    private javax.swing.JLabel lb_boxPassword;
    private javax.swing.JLabel lb_comment;
    private javax.swing.JLabel lb_crud;
    private javax.swing.JLabel lb_endDate;
    private javax.swing.JLabel lb_outlet;
    private javax.swing.JLabel lb_payments;
    private javax.swing.JLabel lb_products;
    private javax.swing.JLabel lb_room;
    private javax.swing.JLabel lb_userEmail;
    private javax.swing.JLabel lb_userName;
    private javax.swing.JMenuBar mb_menu;
    private javax.swing.JMenuItem mi_exit;
    private javax.swing.JMenu mn_file;
    private javax.swing.JTextArea ta_comment;
    private javax.swing.JTable tb_payments;
    private javax.swing.JTable tb_products;
    private javax.swing.JTextField tf_beginDate;
    private javax.swing.JTextField tf_boxLogin;
    private javax.swing.JTextField tf_boxPassword;
    private javax.swing.JTextField tf_endDate;
    private javax.swing.JTextField tf_userEmail;
    private javax.swing.JTextField tf_userFirstName;
    private javax.swing.JTextField tf_userLastName;
    // End of variables declaration//GEN-END:variables
}
