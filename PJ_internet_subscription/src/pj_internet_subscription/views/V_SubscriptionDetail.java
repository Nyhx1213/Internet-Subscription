/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package pj_internet_subscription.views;

import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    private C_internet_subscription controller;
    private int indexRoom;
    private int currentOutletIndex;
    private DefaultTableModel dm_tb_products, dm_tb_payments, dm_tb_computers;
    private LinkedHashMap<Integer, M_Product> productList;
    private LinkedHashMap<Integer, M_Payment> paymentList;
    private LinkedHashMap<Integer, M_Method> paymentMethodList;
    private int subscriptionId;
    private M_Subscription subscription;

    public void updateOutlet(LinkedHashMap<String, M_Outlet> outletList) {
        cb_outlet.removeAllItems();
        cb_addOutlet.removeAllItems();
        for (String code : outletList.keySet()) {
            M_Outlet outletverification = outletList.get(code);
            cb_outlet.addItem(outletverification.getCode());
            cb_addOutlet.addItem(outletverification.getCode());
        }
    }
    
    public void updateUsers(LinkedHashMap<Integer, M_User> userList) {
        cb_addUser.removeAllItems();
        for (int key : userList.keySet()) {
            M_User userVerification = userList.get(key);
            cb_addUser.addItem(userVerification.getEmail());
        }
    }

    public void empty() {
        cb_outlet.removeAllItems();
        cb_room.removeAllItems();
        cb_addUser.removeAllItems();
    }

    public void productTable() {
        M_Product product = null;
        dm_tb_products.setRowCount(0);
        for (int key : productList.keySet()) {
            product = productList.get(key);
            System.out.println(product.getDesignation());
            dm_tb_products.addRow(new Object[]{product.getId(), product.getDesignation(), product.getPrice()});
        }
    }

    public void paymentTable() {
        M_Payment payment = null;
        dm_tb_payments.setRowCount(0);
        for (int key : paymentList.keySet()) {
            payment = paymentList.get(key);
            dm_tb_payments.addRow(new Object[]{payment.getId(), payment.getAmount(), payment.getDate_payment(), paymentMethodList.get(payment.getId_method()).getName()});
        }
    }

    public void computerTable(LinkedHashMap<Integer, M_Computer> computerList) {
        M_Computer computer = null;
        dm_tb_computers.setRowCount(0);
        for (int key : computerList.keySet()) {
            computer = computerList.get(key);
            System.out.println("computers exist");
            dm_tb_computers.addRow(new Object[]{computer.getName(), computer.getComment()});
        }
    }

    public void editable(String action) {
        cb_active.setEnabled(false);
        cb_room.setEditable(false);
        switch (action) {
            case "detail":
                tf_userFirstName.setEditable(false);
                tf_userLastName.setEditable(false);
                dt_beginDate.setEnabled(false);
                dt_endDate.setEnabled(false);
                tf_boxLogin.setEditable(false);
                ta_comment.setEditable(false);
                cb_room.setEnabled(false);
                cb_outlet.setEnabled(false);
                tf_userEmail.setEditable(false);
                bt_products.setText("Details");
                cb_active.setEnabled(false);
                bt_products.setVisible(false);
                bt_computers.setVisible(false);
                bt_payments.setVisible(false);
                bt_validerCrud.setVisible(false);
                break;
            case "modify":
                tf_userFirstName.setEditable(true);
                tf_userLastName.setEditable(true);
                dt_beginDate.setEnabled(true);
                dt_endDate.setEnabled(true);
                tf_boxLogin.setEditable(true);
                ta_comment.setEditable(true);
                cb_room.setEnabled(true);
                cb_outlet.setEnabled(true);
                tf_userEmail.setEditable(true);
                bt_products.setText("Modify");
                cb_active.setEnabled(true);
                bt_products.setVisible(true);
                bt_computers.setVisible(true);
                bt_payments.setVisible(true);
                bt_validerCrud.setVisible(true);
                break;
        }
    }

    public void actionType(String action) {
        if (action.equals("add")) {
            pn_detail.setVisible(false);
            this.setSize(1100, 600);
            pn_add.setVisible(true);
        } else if (action.equals("detail")) {
            pn_detail.setVisible(true);
            pn_add.setVisible(false);
            this.setSize(1100, 800);
        }
    }

  /*  public void displayAdditionFrame(LinkedHashMap<String, M_Room> roomList, LinkedHashMap<String, M_Outlet> outletList,
            LinkedHashMap<Integer, M_User> userList) {
        actionType("add");
        int compteur = 0;

        for (String code : roomList.keySet()) {
            cb_room.addItem(roomList.get(code).getCode());
            if (roomList.get(code).getCode().equals(outlet.getCode_room())) {
                indexRoom = compteur;
            }
            compteur++;
        }

    }
*/
    public void displayAddSub(String action, LinkedHashMap<Integer, M_User> userList, LinkedHashMap<String, M_Room> roomList, LinkedHashMap<String, M_Outlet> outletList){
        empty();
        actionType("add");
        updateUsers(userList);
        for (String code : roomList.keySet()) {
            cb_addRoom.addItem(roomList.get(code).getCode());
        }
        this.setVisible(true);
    }
    
    public void display(String action, M_Subscription subscription, M_User user, LinkedHashMap<Integer, M_Role> roleList,
            LinkedHashMap<Integer, M_Computer> computerList, LinkedHashMap<Integer, M_System> systemList,
            LinkedHashMap<Integer, M_Antivirus> antivirusList, LinkedHashMap<String, M_Outlet> outletList,
            LinkedHashMap<String, M_Room> roomList, LinkedHashMap<Integer, M_Method> paymentMethodList,
            LinkedHashMap<Integer, M_Product> productList, LinkedHashMap<Integer, M_Payment> paymentList) {
        empty();    
        actionType("detail");
        this.subscriptionId = subscription.getId();
        this.subscription = subscription;
        this.productList = productList;
        this.paymentList = paymentList;
        this.paymentMethodList = paymentMethodList;
        dm_tb_products = (DefaultTableModel) tb_products.getModel();
        dm_tb_payments = (DefaultTableModel) tb_payments.getModel();
        dm_tb_computers = (DefaultTableModel) tb_computers.getModel();
        computerTable(computerList);
        productTable();
        paymentTable();
        lb_crud.setText("Subscription of " + user.getFirst_name() + " " + user.getLast_name());
        editable(action);
        int compteur = 0;
        M_Outlet outlet = outletList.get(subscription.getCode_outlet());
        tf_userFirstName.setText(user.getFirst_name());
        tf_userLastName.setText(user.getLast_name());
        Date dateBegin = M_Subscription.convertToDateViaInstant(subscription.getDate_begin());
        Date dateEnd = M_Subscription.convertToDateViaInstant(subscription.getDate_end());
        dt_beginDate.setDate(dateBegin);
        dt_endDate.setDate(dateEnd);
        ta_comment.setText(subscription.getComment());
        tf_boxLogin.setText(subscription.getLogin());
        tf_userEmail.setText(user.getEmail());
        cb_active.setSelected(subscription.isActive());
        for (String code : roomList.keySet()) {
            cb_room.addItem(roomList.get(code).getCode());
            if (roomList.get(code).getCode().equals(outlet.getCode_room())) {
                indexRoom = compteur;
            }
            compteur++;
        }
        cb_room.setSelectedIndex(indexRoom);

        //String selectedRoomCode = cb_room.getItemAt(indexRoom);

        /*
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
         */
        this.setVisible(true);
    }

    public V_SubscriptionDetail(C_internet_subscription controller, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.controller = controller;
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

        op_error = new javax.swing.JOptionPane();
        pn_add = new javax.swing.JPanel();
        lb_addSub = new javax.swing.JLabel();
        lb_email = new javax.swing.JLabel();
        lb_addBeginDate = new javax.swing.JLabel();
        lb_addEndDate = new javax.swing.JLabel();
        lb_addBoxLogin = new javax.swing.JLabel();
        lb_addBoxPassword = new javax.swing.JLabel();
        lb_addRoom = new javax.swing.JLabel();
        lb_addOutlet = new javax.swing.JLabel();
        lb_addComment = new javax.swing.JLabel();
        bt_addSub = new javax.swing.JButton();
        bt_addCancelSub = new javax.swing.JButton();
        cb_addUser = new javax.swing.JComboBox<>();
        dc_addBeginDate = new com.toedter.calendar.JDateChooser();
        dc_addEndDate = new com.toedter.calendar.JDateChooser();
        tf_addBoxLogin = new javax.swing.JTextField();
        tf_addBoxPassword = new javax.swing.JTextField();
        cb_addRoom = new javax.swing.JComboBox<>();
        cb_addOutlet = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        ta_addComment = new javax.swing.JTextArea();
        lb_filterEmail = new javax.swing.JLabel();
        tf_filterEmail = new javax.swing.JTextField();
        pn_detail = new javax.swing.JPanel();
        lb_payments = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_payments = new javax.swing.JTable();
        bt_payments = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_computers = new javax.swing.JTable();
        bt_computers = new javax.swing.JButton();
        lb_crud = new javax.swing.JLabel();
        cb_room = new javax.swing.JComboBox<>();
        cb_outlet = new javax.swing.JComboBox<>();
        lb_outlet = new javax.swing.JLabel();
        lb_room = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_products = new javax.swing.JTable();
        lb_products = new javax.swing.JLabel();
        bt_products = new javax.swing.JButton();
        bt_validerCrud = new javax.swing.JButton();
        bt_cancel = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ta_comment = new javax.swing.JTextArea();
        lb_comment = new javax.swing.JLabel();
        lb_boxLogin = new javax.swing.JLabel();
        lb_endDate = new javax.swing.JLabel();
        lb_userEmail = new javax.swing.JLabel();
        tf_userEmail = new javax.swing.JTextField();
        tf_userFirstName = new javax.swing.JTextField();
        lb_userName = new javax.swing.JLabel();
        cb_active = new javax.swing.JCheckBox();
        dt_beginDate = new com.toedter.calendar.JDateChooser();
        dt_endDate = new com.toedter.calendar.JDateChooser();
        tf_userLastName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_boxLogin = new javax.swing.JTextField();
        lb_beginDate = new javax.swing.JLabel();
        mb_menu = new javax.swing.JMenuBar();
        mn_file = new javax.swing.JMenu();
        mi_close = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        lb_addSub.setText("Add a subscription");

        lb_email.setText("Email Address :");

        lb_addBeginDate.setText("Begin Date :");

        lb_addEndDate.setText("End Date :");

        lb_addBoxLogin.setText("Box Login :");

        lb_addBoxPassword.setText("Box Password :");

        lb_addRoom.setText("Room : ");

        lb_addOutlet.setText("Outlet :");

        lb_addComment.setText("Comment : ");

        bt_addSub.setText("Validate");
        bt_addSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addSubActionPerformed(evt);
            }
        });

        bt_addCancelSub.setText("Cancel");
        bt_addCancelSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addCancelSubActionPerformed(evt);
            }
        });

        cb_addRoom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_addRoomItemStateChanged(evt);
            }
        });

        ta_addComment.setColumns(20);
        ta_addComment.setRows(5);
        jScrollPane5.setViewportView(ta_addComment);

        lb_filterEmail.setText("Email Filter :");

        tf_filterEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_filterEmailKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pn_addLayout = new javax.swing.GroupLayout(pn_add);
        pn_add.setLayout(pn_addLayout);
        pn_addLayout.setHorizontalGroup(
            pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_addLayout.createSequentialGroup()
                .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_addLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_email, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_addBeginDate, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_addEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_addBoxLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_addBoxPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_addOutlet, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_addRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_filterEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_addUser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dc_addBeginDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dc_addEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(tf_addBoxLogin)
                            .addComponent(tf_addBoxPassword)
                            .addComponent(cb_addRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_addOutlet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_filterEmail))
                        .addGap(91, 91, 91)
                        .addComponent(lb_addComment, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_addLayout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(bt_addSub)
                        .addGap(85, 85, 85)
                        .addComponent(bt_addCancelSub))
                    .addGroup(pn_addLayout.createSequentialGroup()
                        .addGap(389, 389, 389)
                        .addComponent(lb_addSub, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_addLayout.setVerticalGroup(
            pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_addLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lb_addSub)
                .addGap(9, 9, 9)
                .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_filterEmail)
                    .addComponent(tf_filterEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_addLayout.createSequentialGroup()
                        .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pn_addLayout.createSequentialGroup()
                                .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_email)
                                    .addComponent(cb_addUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_addComment))
                                .addGap(28, 28, 28)
                                .addComponent(dc_addBeginDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lb_addBeginDate))
                        .addGap(27, 27, 27)
                        .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dc_addEndDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_addEndDate, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(27, 27, 27)
                        .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_addBoxLogin)
                            .addComponent(tf_addBoxLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_addBoxPassword)
                            .addComponent(tf_addBoxPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_addRoom)
                    .addComponent(cb_addRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_addOutlet)
                    .addComponent(cb_addOutlet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_addSub)
                    .addComponent(bt_addCancelSub))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        getContentPane().add(pn_add);
        pn_add.setBounds(0, 0, 900, 510);

        pn_detail.setLayout(null);

        lb_payments.setText("Payments");
        pn_detail.add(lb_payments);
        lb_payments.setBounds(845, 82, 90, 16);

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
        tb_payments.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tb_payments);
        if (tb_payments.getColumnModel().getColumnCount() > 0) {
            tb_payments.getColumnModel().getColumn(0).setResizable(false);
            tb_payments.getColumnModel().getColumn(1).setResizable(false);
            tb_payments.getColumnModel().getColumn(2).setResizable(false);
            tb_payments.getColumnModel().getColumn(3).setResizable(false);
        }

        pn_detail.add(jScrollPane2);
        jScrollPane2.setBounds(701, 110, 341, 101);

        bt_payments.setText("Modify");
        bt_payments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_paymentsActionPerformed(evt);
            }
        });
        pn_detail.add(bt_payments);
        bt_payments.setBounds(838, 229, 72, 23);

        jLabel1.setText("Computers");
        pn_detail.add(jLabel1);
        jLabel1.setBounds(837, 344, 120, 16);

        tb_computers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Comment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_computers.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tb_computers);
        if (tb_computers.getColumnModel().getColumnCount() > 0) {
            tb_computers.getColumnModel().getColumn(0).setResizable(false);
            tb_computers.getColumnModel().getColumn(1).setResizable(false);
        }

        pn_detail.add(jScrollPane4);
        jScrollPane4.setBounds(705, 374, 337, 108);

        bt_computers.setText("Modify");
        bt_computers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_computersActionPerformed(evt);
            }
        });
        pn_detail.add(bt_computers);
        bt_computers.setBounds(842, 500, 72, 23);

        lb_crud.setText("jLabel1");
        pn_detail.add(lb_crud);
        lb_crud.setBounds(513, 50, 170, 16);

        cb_room.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_roomItemStateChanged(evt);
            }
        });
        pn_detail.add(cb_room);
        cb_room.setBounds(444, 119, 178, 22);

        cb_outlet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_outletActionPerformed(evt);
            }
        });
        pn_detail.add(cb_outlet);
        cb_outlet.setBounds(444, 159, 178, 22);

        lb_outlet.setText("Outlet :");
        pn_detail.add(lb_outlet);
        lb_outlet.setBounds(385, 162, 50, 16);

        lb_room.setText("Room : ");
        pn_detail.add(lb_room);
        lb_room.setBounds(385, 122, 50, 16);

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
        tb_products.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_products);
        if (tb_products.getColumnModel().getColumnCount() > 0) {
            tb_products.getColumnModel().getColumn(0).setResizable(false);
            tb_products.getColumnModel().getColumn(1).setResizable(false);
            tb_products.getColumnModel().getColumn(2).setResizable(false);
        }

        pn_detail.add(jScrollPane1);
        jScrollPane1.setBounds(385, 270, 250, 98);

        lb_products.setText("Products");
        pn_detail.add(lb_products);
        lb_products.setBounds(486, 232, 100, 16);

        bt_products.setText("Modify");
        bt_products.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_productsActionPerformed(evt);
            }
        });
        pn_detail.add(bt_products);
        bt_products.setBounds(477, 386, 72, 23);

        bt_validerCrud.setText("Validate");
        bt_validerCrud.setToolTipText("");
        bt_validerCrud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_validerCrudActionPerformed(evt);
            }
        });
        pn_detail.add(bt_validerCrud);
        bt_validerCrud.setBounds(385, 565, 90, 23);

        bt_cancel.setText("Cancel");
        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });
        pn_detail.add(bt_cancel);
        bt_cancel.setBounds(545, 565, 90, 23);

        ta_comment.setColumns(20);
        ta_comment.setRows(5);
        jScrollPane3.setViewportView(ta_comment);

        pn_detail.add(jScrollPane3);
        jScrollPane3.setBounds(140, 466, 155, 73);

        lb_comment.setText("Comment : ");
        pn_detail.add(lb_comment);
        lb_comment.setBounds(61, 479, 70, 16);

        lb_boxLogin.setText("Box login :");
        pn_detail.add(lb_boxLogin);
        lb_boxLogin.setBounds(74, 317, 70, 16);

        lb_endDate.setText("End date : ");
        pn_detail.add(lb_endDate);
        lb_endDate.setBounds(74, 280, 60, 16);

        lb_userEmail.setText("Email :");
        pn_detail.add(lb_userEmail);
        lb_userEmail.setBounds(93, 195, 50, 16);
        pn_detail.add(tf_userEmail);
        tf_userEmail.setBounds(146, 192, 123, 22);
        pn_detail.add(tf_userFirstName);
        tf_userFirstName.setBounds(146, 115, 123, 22);

        lb_userName.setText("First name :");
        pn_detail.add(lb_userName);
        lb_userName.setBounds(68, 118, 70, 16);

        cb_active.setText("Active");
        pn_detail.add(cb_active);
        cb_active.setBounds(229, 77, 80, 20);
        pn_detail.add(dt_beginDate);
        dt_beginDate.setBounds(148, 231, 121, 22);
        pn_detail.add(dt_endDate);
        dt_endDate.setBounds(148, 274, 121, 22);
        pn_detail.add(tf_userLastName);
        tf_userLastName.setBounds(146, 155, 123, 22);

        jLabel3.setText("Last name :");
        pn_detail.add(jLabel3);
        jLabel3.setBounds(69, 158, 70, 16);

        tf_boxLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_boxLoginActionPerformed(evt);
            }
        });
        pn_detail.add(tf_boxLogin);
        tf_boxLogin.setBounds(146, 314, 123, 22);

        lb_beginDate.setText("Beginning date : ");
        pn_detail.add(lb_beginDate);
        lb_beginDate.setBounds(39, 237, 100, 16);

        getContentPane().add(pn_detail);
        pn_detail.setBounds(0, 0, 1110, 820);

        mb_menu.setName("mb_menu"); // NOI18N

        mn_file.setText("File");
        mn_file.setName("mn_file"); // NOI18N

        mi_close.setText("Close");
        mi_close.setName("mi_close"); // NOI18N
        mi_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_closeActionPerformed(evt);
            }
        });
        mn_file.add(mi_close);

        mb_menu.add(mn_file);

        setJMenuBar(mb_menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mi_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_closeActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_mi_closeActionPerformed

    private void bt_validerCrudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_validerCrudActionPerformed
        if (dt_beginDate.getDate() == null || dt_endDate.getDate() == null) {
            op_error.showMessageDialog(this, "Please enter a valid date.");
        } else {
            
            LocalDate dateBegin = M_Subscription.convertToLocalDateViaInstant(dt_beginDate.getDate());
            LocalDate dateEnd = M_Subscription.convertToLocalDateViaInstant(dt_endDate.getDate());
            try {
                controller.updateSubscription(subscriptionId, tf_userFirstName.getText(), tf_userLastName.getText(), tf_userEmail.getText(),
                        dateBegin, dateEnd, tf_boxLogin.getText(), ta_comment.getText(), cb_outlet.getSelectedItem().toString()
                       );
            } catch (SQLException ex) {
                System.getLogger(V_SubscriptionDetail.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            this.setVisible(false);
        }
    }//GEN-LAST:event_bt_validerCrudActionPerformed

    private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
        try {
            controller.subscriptionPage();
        } catch (SQLException ex) {
            System.getLogger(V_SubscriptionDetail.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_bt_cancelActionPerformed

    private void cb_roomItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_roomItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selected = cb_room.getSelectedItem();
            if (selected != null) {  // <-- Add this null check
                try {
                    controller.updateOutletListByRoom(selected.toString());
                } catch (SQLException ex) {
                    System.getLogger(V_SubscriptionDetail.class.getName()).log(System.Logger.Level.ERROR, "Failed to update outlet list", ex);
                }
            }
        }
    }//GEN-LAST:event_cb_roomItemStateChanged

    private void bt_paymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_paymentsActionPerformed
        try {
            controller.subscriptionAdditionsPage("payments", subscription);
        } catch (SQLException ex) {
            System.getLogger(V_SubscriptionDetail.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_bt_paymentsActionPerformed

    private void bt_productsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_productsActionPerformed
        try {
            controller.subscriptionAdditionsPage("products", subscription);
        } catch (SQLException ex) {
            System.getLogger(V_SubscriptionDetail.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_bt_productsActionPerformed

    private void bt_computersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_computersActionPerformed
        try {
            controller.subscriptionAdditionsPage("computers", subscription);
        } catch (SQLException ex) {
            System.getLogger(V_SubscriptionDetail.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_bt_computersActionPerformed

    private void tf_boxLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_boxLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_boxLoginActionPerformed

    private void cb_outletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_outletActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_outletActionPerformed

    private void cb_addRoomItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_addRoomItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selected = cb_addRoom.getSelectedItem();
            if (selected != null) {  // <-- Add this null check
                try {
                    controller.updateOutletListByRoom(selected.toString());
                } catch (SQLException ex) {
                    System.getLogger(V_SubscriptionDetail.class.getName()).log(System.Logger.Level.ERROR, "Failed to update outlet list", ex);
                }
            }
        }    }//GEN-LAST:event_cb_addRoomItemStateChanged

    private void tf_filterEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_filterEmailKeyReleased
        String selected = tf_filterEmail.getText();
            try {
                controller.updateEmailByInput(selected);
            } catch (SQLException ex) {
                System.getLogger(V_SubscriptionDetail.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
    }//GEN-LAST:event_tf_filterEmailKeyReleased

    private void bt_addCancelSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addCancelSubActionPerformed
        try {
            controller.subscriptionPage();
            this.setVisible(false);
        } catch (SQLException ex) {
            System.getLogger(V_SubscriptionDetail.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_bt_addCancelSubActionPerformed

    private void bt_addSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addSubActionPerformed
        String email = String.valueOf(cb_addUser.getSelectedItem());
        Date beginDate = dc_addBeginDate.getDate();
        Date endDate = dc_addEndDate.getDate();
        String boxLogin = tf_addBoxLogin.getText();
        String passwordLogin = tf_addBoxPassword.getText();
        String comment = ta_addComment.getText();
        String room = String.valueOf(cb_addRoom.getSelectedItem());
        String outlet = String.valueOf(cb_addOutlet.getSelectedItem());
        
        if (email.equals("") || beginDate == null || endDate == null || boxLogin.equals("") || passwordLogin.equals("") || 
                outlet.equals("")){
            op_error.showMessageDialog(this, "Please fill in all of the information.");
        } else {
            try {
                controller.addSubscription(email, beginDate, endDate, boxLogin, passwordLogin, comment, outlet);
            } catch (SQLException ex) {
                System.getLogger(V_SubscriptionDetail.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }//GEN-LAST:event_bt_addSubActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            controller.subscriptionPage();
        } catch (SQLException ex) {
            System.getLogger(V_SubscriptionDetail.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

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
    private javax.swing.JButton bt_addCancelSub;
    private javax.swing.JButton bt_addSub;
    private javax.swing.JButton bt_cancel;
    private javax.swing.JButton bt_computers;
    private javax.swing.JButton bt_payments;
    private javax.swing.JButton bt_products;
    private javax.swing.JButton bt_validerCrud;
    private javax.swing.JCheckBox cb_active;
    private javax.swing.JComboBox<String> cb_addOutlet;
    private javax.swing.JComboBox<String> cb_addRoom;
    private javax.swing.JComboBox<String> cb_addUser;
    private javax.swing.JComboBox<String> cb_outlet;
    private javax.swing.JComboBox<String> cb_room;
    private com.toedter.calendar.JDateChooser dc_addBeginDate;
    private com.toedter.calendar.JDateChooser dc_addEndDate;
    private com.toedter.calendar.JDateChooser dt_beginDate;
    private com.toedter.calendar.JDateChooser dt_endDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lb_addBeginDate;
    private javax.swing.JLabel lb_addBoxLogin;
    private javax.swing.JLabel lb_addBoxPassword;
    private javax.swing.JLabel lb_addComment;
    private javax.swing.JLabel lb_addEndDate;
    private javax.swing.JLabel lb_addOutlet;
    private javax.swing.JLabel lb_addRoom;
    private javax.swing.JLabel lb_addSub;
    private javax.swing.JLabel lb_beginDate;
    private javax.swing.JLabel lb_boxLogin;
    private javax.swing.JLabel lb_comment;
    private javax.swing.JLabel lb_crud;
    private javax.swing.JLabel lb_email;
    private javax.swing.JLabel lb_endDate;
    private javax.swing.JLabel lb_filterEmail;
    private javax.swing.JLabel lb_outlet;
    private javax.swing.JLabel lb_payments;
    private javax.swing.JLabel lb_products;
    private javax.swing.JLabel lb_room;
    private javax.swing.JLabel lb_userEmail;
    private javax.swing.JLabel lb_userName;
    private javax.swing.JMenuBar mb_menu;
    private javax.swing.JMenuItem mi_close;
    private javax.swing.JMenu mn_file;
    private javax.swing.JOptionPane op_error;
    private javax.swing.JPanel pn_add;
    private javax.swing.JPanel pn_detail;
    private javax.swing.JTextArea ta_addComment;
    private javax.swing.JTextArea ta_comment;
    private javax.swing.JTable tb_computers;
    private javax.swing.JTable tb_payments;
    private javax.swing.JTable tb_products;
    private javax.swing.JTextField tf_addBoxLogin;
    private javax.swing.JTextField tf_addBoxPassword;
    private javax.swing.JTextField tf_boxLogin;
    private javax.swing.JTextField tf_filterEmail;
    private javax.swing.JTextField tf_userEmail;
    private javax.swing.JTextField tf_userFirstName;
    private javax.swing.JTextField tf_userLastName;
    // End of variables declaration//GEN-END:variables
}
