/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package pj_internet_subscription.views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pj_internet_subscription.C_internet_subscription;
import pj_internet_subscription.model.M_Computer;
import pj_internet_subscription.model.M_Method;
import pj_internet_subscription.model.M_Payment;
import pj_internet_subscription.model.M_Product;
import pj_internet_subscription.model.M_Subscription;

/**
 *
 * @author adjedjm
 */
public class V_SubscriptionAdditions extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(V_SubscriptionAdditions.class.getName());
    private C_internet_subscription controller;
    private String action;
    private DefaultTableModel dm_tb_products, dm_tb_computers, dm_tb_payments;
    private int nbRow;
    private ArrayList<M_Product> productArray;
    private ArrayList<M_Method> paymentArray;
    private LinkedHashMap<Integer, M_Payment> paymentList;
    private LinkedHashMap<Integer, M_Product> addedProductList, allProducts, deletedProductList;
    private M_Subscription currentSubscription;

    private void windowSize() {
        this.setSize(700, 500);
    }

    private void displayContent(String action) throws SQLException {
        this.action = action;
        switch (action) {
            case "products":
                pn_products.setVisible(true);
                pn_payments.setVisible(false);
                pn_computers.setVisible(false);
                break;
            case "computers":
                pn_products.setVisible(false);
                pn_payments.setVisible(false);
                pn_computers.setVisible(true);
                break;
            case "payments":
                pn_products.setVisible(false);
                pn_payments.setVisible(true);
                pn_computers.setVisible(false);
                break;
            default:
                this.controller.subscriptionCrud("modify", currentSubscription);
                this.setVisible(false);
        }
    }

    private void productTable(LinkedHashMap<Integer, M_Product> currentProducts) {
        M_Product product = null;

        dm_tb_products.setRowCount(0);
        for (int key : currentProducts.keySet()) {
            product = currentProducts.get(key);
            dm_tb_products.addRow(new Object[]{product.getId(), product.getDesignation(), product.getPrice(), product.getQuantity()});
        }
    }

    private void computerTable(LinkedHashMap<Integer, M_Computer> currentComputeres) {
        M_Computer computer = null;
        dm_tb_computers.setRowCount(0);
        for (int key : currentComputeres.keySet()) {
            computer = currentComputeres.get(key);
            dm_tb_computers.addRow(new Object[]{computer.getId(), computer.getName(), computer.getComment()});
        }
    }

    private void paymentTable(LinkedHashMap<Integer, M_Payment> currentPayments, LinkedHashMap<Integer, M_Method> paymentMethods) {
        M_Payment payment = null;
        dm_tb_payments.setRowCount(0);
        for (int key : currentPayments.keySet()) {
            payment = currentPayments.get(key);
            dm_tb_payments.addRow(new Object[]{payment.getId(), payment.getDate_payment(), payment.getAmount(), paymentMethods.get(payment.getId_method()).getName()});
        }
    }

    // Payment Display
    public void displayPayments(String action, M_Subscription subscription, LinkedHashMap<Integer, M_Method> paymentMethodList, LinkedHashMap<Integer, M_Payment> currentPaymentsList) throws SQLException {
        windowSize();
        paymentArray = null;
        paymentArray = new ArrayList();
        this.currentSubscription = subscription;
        this.paymentList = currentPaymentsList;
        dm_tb_payments = (DefaultTableModel) tb_payments.getModel();
        displayContent(action);
        paymentTable(currentPaymentsList, paymentMethodList);
        cb_paymentMethods.removeAllItems();
        for (int id : paymentMethodList.keySet()) {
            cb_paymentMethods.addItem(paymentMethodList.get(id).getName());
            paymentArray.add(paymentMethodList.get(id));
        }
        this.setVisible(true);
    }

    public void displayProducts(String action, M_Subscription subscription, LinkedHashMap<Integer, M_Product> productList, LinkedHashMap<Integer, M_Product> currentProductList) throws SQLException {
        windowSize();
        productArray = null;
        productArray = new ArrayList();
        this.currentSubscription = subscription;
        this.allProducts = productList;
        dm_tb_products = (DefaultTableModel) tb_products.getModel();
        displayContent(action);
        productTable(currentProductList);
        cb_productList.removeAllItems();
        for (int id : productList.keySet()) {
            cb_productList.addItem(productList.get(id).getDesignation());
            productArray.add(productList.get(id));
        }
        this.setVisible(true);
    }

    public void displayComputers(String action, M_Subscription subscription, LinkedHashMap<Integer, M_Computer> currentComputers) throws SQLException {
        windowSize();
        this.currentSubscription = subscription;
        dm_tb_computers = (DefaultTableModel) tb_computers.getModel();
        displayContent(action);
        computerTable(currentComputers);
        this.setVisible(true);
    }

    /**
     * Creates new form V_SubscriptionAdditions
     */
    public V_SubscriptionAdditions(C_internet_subscription controller, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.controller = controller;
        initComponents();
    }

    public boolean errorSelection(JTable tb, String errorMessage) {
        if (tb.getSelectedRow() == -1) {
            op_error.showMessageDialog(this, errorMessage);
        }
        return tb.getSelectedRow() == -1;
    }

    public void exitPage() {
        try {
            controller.subscriptionCrud("modify", currentSubscription);
        } catch (SQLException ex) {
            System.getLogger(V_SubscriptionAdditions.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        this.setVisible(false);
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
        op_delete = new javax.swing.JOptionPane();
        pn_products = new javax.swing.JPanel();
        lb_productsTitle = new javax.swing.JLabel();
        bt_exitProducts = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_products = new javax.swing.JTable();
        bt_deleteProduct = new javax.swing.JButton();
        cb_productList = new javax.swing.JComboBox<>();
        bt_addProduct = new javax.swing.JButton();
        pn_payments = new javax.swing.JPanel();
        lb_payments = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_payments = new javax.swing.JTable();
        bt_deletePayment = new javax.swing.JButton();
        bt_paymentExit = new javax.swing.JButton();
        bt_addPayment = new javax.swing.JButton();
        lb_paymentAmount = new javax.swing.JLabel();
        ftf_paymentAmount = new javax.swing.JFormattedTextField();
        cb_paymentMethods = new javax.swing.JComboBox<>();
        lb_method = new javax.swing.JLabel();
        dc_paymentDate = new com.toedter.calendar.JDateChooser();
        lb_date = new javax.swing.JLabel();
        pn_computers = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_computers = new javax.swing.JTable();
        bt_deleteComputer = new javax.swing.JButton();
        bt_exitComputer = new javax.swing.JButton();
        lb_computerName = new javax.swing.JLabel();
        lb_computerComment = new javax.swing.JLabel();
        tf_computerName = new javax.swing.JTextField();
        tf_computerComment = new javax.swing.JTextField();
        bt_addComputer = new javax.swing.JButton();
        lb_computers = new javax.swing.JLabel();
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

        lb_productsTitle.setText("Product Management");
        lb_productsTitle.setToolTipText("");

        bt_exitProducts.setText("Exit");
        bt_exitProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exitProductsActionPerformed(evt);
            }
        });

        tb_products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Title", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
            tb_products.getColumnModel().getColumn(3).setResizable(false);
        }

        bt_deleteProduct.setText("Delete");
        bt_deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_deleteProductActionPerformed(evt);
            }
        });

        bt_addProduct.setText("Add");
        bt_addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_productsLayout = new javax.swing.GroupLayout(pn_products);
        pn_products.setLayout(pn_productsLayout);
        pn_productsLayout.setHorizontalGroup(
            pn_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_productsLayout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(pn_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_productsLayout.createSequentialGroup()
                        .addComponent(lb_productsTitle)
                        .addGap(266, 266, 266))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bt_addProduct)
                        .addGroup(pn_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_productsLayout.createSequentialGroup()
                                .addComponent(cb_productList, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_deleteProduct)
                                .addGap(141, 141, 141))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_productsLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124))))))
            .addGroup(pn_productsLayout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(bt_exitProducts)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_productsLayout.setVerticalGroup(
            pn_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_productsLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lb_productsTitle)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pn_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_deleteProduct)
                    .addComponent(cb_productList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bt_addProduct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_exitProducts)
                .addGap(87, 87, 87))
        );

        getContentPane().add(pn_products);
        pn_products.setBounds(22, 6, 637, 434);

        lb_payments.setText("Payments");

        tb_payments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Date ", "Amount", "Method"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tb_payments);
        if (tb_payments.getColumnModel().getColumnCount() > 0) {
            tb_payments.getColumnModel().getColumn(0).setResizable(false);
            tb_payments.getColumnModel().getColumn(1).setResizable(false);
            tb_payments.getColumnModel().getColumn(2).setResizable(false);
            tb_payments.getColumnModel().getColumn(3).setResizable(false);
        }

        bt_deletePayment.setText("Delete");
        bt_deletePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_deletePaymentActionPerformed(evt);
            }
        });

        bt_paymentExit.setText("Exit");
        bt_paymentExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_paymentExitActionPerformed(evt);
            }
        });

        bt_addPayment.setText("Add");
        bt_addPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addPaymentActionPerformed(evt);
            }
        });

        lb_paymentAmount.setText("Amount :");

        ftf_paymentAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        lb_method.setText("Method :");

        lb_date.setText("Date :");

        javax.swing.GroupLayout pn_paymentsLayout = new javax.swing.GroupLayout(pn_payments);
        pn_payments.setLayout(pn_paymentsLayout);
        pn_paymentsLayout.setHorizontalGroup(
            pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_paymentsLayout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_paymentsLayout.createSequentialGroup()
                        .addComponent(lb_payments, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(311, 311, 311))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_paymentsLayout.createSequentialGroup()
                        .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_paymentsLayout.createSequentialGroup()
                                .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_paymentsLayout.createSequentialGroup()
                                        .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lb_paymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lb_date, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lb_method, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(26, 26, 26)
                                        .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ftf_paymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cb_paymentMethods, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(dc_paymentDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bt_deletePayment, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_paymentsLayout.createSequentialGroup()
                                        .addComponent(bt_addPayment)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bt_paymentExit)))))
                        .addGap(108, 108, 108))))
        );
        pn_paymentsLayout.setVerticalGroup(
            pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_paymentsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lb_payments)
                .addGap(18, 18, 18)
                .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_paymentsLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_deletePayment)
                            .addComponent(lb_paymentAmount)
                            .addComponent(ftf_paymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_paymentMethods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_method))
                        .addGap(18, 18, 18)
                        .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dc_paymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pn_paymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bt_addPayment)
                                .addComponent(bt_paymentExit))))
                    .addComponent(lb_date))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        getContentPane().add(pn_payments);
        pn_payments.setBounds(10, 20, 680, 390);

        tb_computers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Comment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb_computers);
        if (tb_computers.getColumnModel().getColumnCount() > 0) {
            tb_computers.getColumnModel().getColumn(1).setResizable(false);
            tb_computers.getColumnModel().getColumn(2).setResizable(false);
        }

        bt_deleteComputer.setText("Delete");
        bt_deleteComputer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_deleteComputerActionPerformed(evt);
            }
        });

        bt_exitComputer.setText("Exit");
        bt_exitComputer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exitComputerActionPerformed(evt);
            }
        });

        lb_computerName.setText("Name :");

        lb_computerComment.setText("Comment :");

        bt_addComputer.setText("Add");
        bt_addComputer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addComputerActionPerformed(evt);
            }
        });

        lb_computers.setText("Computers");

        javax.swing.GroupLayout pn_computersLayout = new javax.swing.GroupLayout(pn_computers);
        pn_computers.setLayout(pn_computersLayout);
        pn_computersLayout.setHorizontalGroup(
            pn_computersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_computersLayout.createSequentialGroup()
                .addGroup(pn_computersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_computersLayout.createSequentialGroup()
                        .addGroup(pn_computersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bt_addComputer, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pn_computersLayout.createSequentialGroup()
                                .addGroup(pn_computersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_computersLayout.createSequentialGroup()
                                        .addGap(106, 106, 106)
                                        .addComponent(lb_computerName, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_computersLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lb_computerComment, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)))
                                .addGroup(pn_computersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_computerName)
                                    .addComponent(tf_computerComment, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pn_computersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_deleteComputer, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bt_exitComputer, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(pn_computersLayout.createSequentialGroup()
                        .addContainerGap(89, Short.MAX_VALUE)
                        .addGroup(pn_computersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_computersLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_computersLayout.createSequentialGroup()
                                .addGap(204, 204, 204)
                                .addComponent(lb_computers, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149)))))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        pn_computersLayout.setVerticalGroup(
            pn_computersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_computersLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lb_computers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(pn_computersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_deleteComputer)
                    .addComponent(lb_computerName)
                    .addComponent(tf_computerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_computersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_computerComment)
                    .addComponent(tf_computerComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_computersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_addComputer)
                    .addComponent(bt_exitComputer))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pn_computers);
        pn_computers.setBounds(10, 10, 660, 410);

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

    private void bt_deleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteProductActionPerformed
        if (errorSelection(tb_products, "Please select a product.") == false) {
            int productId = (Integer) dm_tb_products.getValueAt(tb_products.getSelectedRow(), 0);

            try {
                int answer = op_delete.showConfirmDialog(this, "Are you certain about deleting this product ?");
                if (answer == op_delete.YES_OPTION) {
                    controller.deleteProductFromSub(productId, currentSubscription);
                    //deletedProductList.put(productId, allProducts.get(productId));
                    //dm_tb_products.removeRow(tb_products.getSelectedRow());
                }
            } catch (SQLException ex) {
                System.getLogger(V_SubscriptionAdditions.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }//GEN-LAST:event_bt_deleteProductActionPerformed

    private void bt_addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addProductActionPerformed
        int currentIndex = cb_productList.getSelectedIndex();
        M_Product selectedProduct = productArray.get(currentIndex);
        System.out.println(currentIndex);
        try {
            controller.addProductToSub(selectedProduct.getId(), currentSubscription);
        } catch (SQLException ex) {
            System.getLogger(V_SubscriptionAdditions.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_bt_addProductActionPerformed

    private void bt_exitProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exitProductsActionPerformed
        exitPage();
    }//GEN-LAST:event_bt_exitProductsActionPerformed

    private void bt_deleteComputerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteComputerActionPerformed
        if (errorSelection(tb_computers, "Please select a computer.") == false) {
            int computerId = (Integer) dm_tb_computers.getValueAt(tb_computers.getSelectedRow(), 0);
            try {
                int answer = op_delete.showConfirmDialog(this, "Are you certain about deleting this user ?");
                if (answer == op_delete.YES_OPTION) {
                    controller.deleteComputerFromSub(computerId, currentSubscription);
                }
            } catch (SQLException ex) {
                System.getLogger(V_SubscriptionAdditions.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }//GEN-LAST:event_bt_deleteComputerActionPerformed

    private void bt_exitComputerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exitComputerActionPerformed
        exitPage();

    }//GEN-LAST:event_bt_exitComputerActionPerformed

    private void bt_addComputerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addComputerActionPerformed
        String computerName = tf_computerName.getText();
        String computerComment = tf_computerComment.getText();

        if (computerName == "") {
            op_error.showMessageDialog(this, "Please name the computer to procceed.");
        } else {
            try {
                controller.addComputerToSub(computerName, computerComment, currentSubscription);
            } catch (SQLException ex) {
                System.getLogger(V_SubscriptionAdditions.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }//GEN-LAST:event_bt_addComputerActionPerformed

    private void bt_deletePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deletePaymentActionPerformed
        if (errorSelection(tb_payments, "Please select a payment.") == false) {
            int paymentId = (Integer) dm_tb_payments.getValueAt(tb_payments.getSelectedRow(), 0);
            try {
                int answer = op_delete.showConfirmDialog(this, "Are you certain about deleting this user ?");
                if (answer == op_delete.YES_OPTION) {
                    controller.deletePaymentFromSubscription(paymentId, currentSubscription);
                }
            } catch (SQLException ex) {
                System.getLogger(V_SubscriptionAdditions.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }//GEN-LAST:event_bt_deletePaymentActionPerformed

    private void bt_paymentExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_paymentExitActionPerformed
        exitPage();
    }//GEN-LAST:event_bt_paymentExitActionPerformed

    private void bt_addPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addPaymentActionPerformed
        int currentIndex = cb_paymentMethods.getSelectedIndex();
        M_Method selectedMethod = paymentArray.get(currentIndex);
        System.out.println(currentIndex);
        try {
            controller.addPaymentToSubscription(selectedMethod.getId(), Integer.parseInt(ftf_paymentAmount.getText()), dc_paymentDate.getDate(), currentSubscription);
        } catch (SQLException ex) {
            System.getLogger(V_SubscriptionAdditions.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_bt_addPaymentActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            this.controller.subscriptionCrud("modify", currentSubscription);
        } catch (SQLException ex) {
            System.getLogger(V_SubscriptionAdditions.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
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
                V_SubscriptionAdditions dialog = new V_SubscriptionAdditions(null, new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bt_addComputer;
    private javax.swing.JButton bt_addPayment;
    private javax.swing.JButton bt_addProduct;
    private javax.swing.JButton bt_deleteComputer;
    private javax.swing.JButton bt_deletePayment;
    private javax.swing.JButton bt_deleteProduct;
    private javax.swing.JButton bt_exitComputer;
    private javax.swing.JButton bt_exitProducts;
    private javax.swing.JButton bt_paymentExit;
    private javax.swing.JComboBox<String> cb_paymentMethods;
    private javax.swing.JComboBox<String> cb_productList;
    private com.toedter.calendar.JDateChooser dc_paymentDate;
    private javax.swing.JFormattedTextField ftf_paymentAmount;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb_computerComment;
    private javax.swing.JLabel lb_computerName;
    private javax.swing.JLabel lb_computers;
    private javax.swing.JLabel lb_date;
    private javax.swing.JLabel lb_method;
    private javax.swing.JLabel lb_paymentAmount;
    private javax.swing.JLabel lb_payments;
    private javax.swing.JLabel lb_productsTitle;
    private javax.swing.JMenuBar mb_menu;
    private javax.swing.JMenuItem mi_close;
    private javax.swing.JMenu mn_file;
    private javax.swing.JOptionPane op_delete;
    private javax.swing.JOptionPane op_error;
    private javax.swing.JPanel pn_computers;
    private javax.swing.JPanel pn_payments;
    private javax.swing.JPanel pn_products;
    private javax.swing.JTable tb_computers;
    private javax.swing.JTable tb_payments;
    private javax.swing.JTable tb_products;
    private javax.swing.JTextField tf_computerComment;
    private javax.swing.JTextField tf_computerName;
    // End of variables declaration//GEN-END:variables
}
