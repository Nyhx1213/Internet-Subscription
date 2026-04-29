/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pj_internet_subscription;

/**
 *
 * @author adjedjm
 */
import pj_internet_subscription.views.V_Main;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import pj_internet_subscription.model.M_Antivirus;
import pj_internet_subscription.model.M_Authorized;
import pj_internet_subscription.model.M_Buy;
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
import pj_internet_subscription.views.V_Room;
import pj_internet_subscription.views.V_Subscription;
import pj_internet_subscription.views.V_SubscriptionAdditions;
import pj_internet_subscription.views.V_SubscriptionDetail;


public class C_internet_subscription {

    /**
     * @param args the command line arguments
     */
    
    private V_Main fm_main;
    private V_Subscription fm_subscription;
    private Db_mariadb baseRR;
    private LinkedHashMap <Integer, M_User> userList;
    private M_User userSession;
    private V_SubscriptionDetail fm_subscriptionCrud;
    private V_Room fm_room;
    private V_SubscriptionAdditions fm_subscriptionAdditions;
    
    public C_internet_subscription() throws Exception{ 
        connexion();
        userList = M_User.getRecords(baseRR);
        fm_main = new V_Main(this);
        fm_subscription = new V_Subscription(this, fm_main, true);
        fm_subscriptionCrud = new V_SubscriptionDetail(this, fm_main, true);
        fm_room = new V_Room(this, fm_main, true);
        fm_subscriptionAdditions = new V_SubscriptionAdditions(this, fm_main, true);
        fm_main.display(userSession, null, null);
    }
    
    public void connectToSession(String login, String password) throws SQLException {
        int idRole = -1;
        String errorMessage = null;
        userSession = M_User.connexion_log(baseRR, login, password);
        if (userSession == null){
            idRole = -1;
        }
        else if (userSession.getId_role() != 1 && userSession.getId_role() != 3){
            userSession = null;
            errorMessage = "You do not have the right authorization to access this application.";
        }
        else{
            idRole = userSession.getId_role();
        }
        fm_main.display(userSession, M_Authorized.getLesAutorisations(baseRR, idRole), errorMessage);
    }
    
    public void subscriptionPage(int offset) throws SQLException {         
        fm_subscription.display(M_Subscription.getRecords(baseRR), false, null);
    }
    
    public void subscriptionCrud(String action, M_Subscription subscription) throws SQLException{
        M_User user = new M_User(baseRR, subscription.getId_user());
        LinkedHashMap<String, M_Outlet> outletList = M_Outlet.getRecords(baseRR);
        LinkedHashMap<String, M_Room> roomList = M_Room.getRecords(baseRR);
        LinkedHashMap<Integer, M_Role> roleList = M_Role.getRecords(baseRR);
        LinkedHashMap<Integer, M_Computer> computerList = M_Computer.getRecords(baseRR, "id_subscription = "+subscription.getId());
        LinkedHashMap<Integer, M_System> systemList = M_System.getRecords(baseRR);
        LinkedHashMap<Integer, M_Antivirus> antivirusList = M_Antivirus.getRecords(baseRR);
        LinkedHashMap<Integer, M_Method> paymentMethodList = M_Method.getRecords(baseRR);
        LinkedHashMap<Integer, M_Product> productList = M_Buy.getRecords(baseRR, "bu.id_subscription = "+subscription.getId());
        LinkedHashMap<Integer, M_Payment> paymentList = M_Payment.getRecords(baseRR, " id_subscription = "+subscription.getId());
        fm_subscriptionCrud.display(action, subscription, user, roleList, computerList, systemList, antivirusList, outletList, roomList, paymentMethodList, productList, paymentList);
    }
    
    public void updateSubscription(int subscriptionId, String firstName, String lastName, String email, LocalDate dateBegin, LocalDate dateEnd,
            String boxLogin, String comment, String roomCode, String outletCode) throws SQLException
    {
        M_Subscription subscription = new M_Subscription(baseRR, subscriptionId);
        M_User user = new M_User(baseRR, subscription.getId_user());
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setEmail(email);
        subscription.setDate_begin(dateBegin);
        subscription.setDate_end(dateEnd);
        subscription.setLogin(boxLogin);
        subscription.setComment(comment);
        subscription.update();
        user.update();
        fm_subscription.display(M_Subscription.getRecords(baseRR), true, "Modifications executed.");
    }
    
    public void subscriptionAdditionsPage(String action, M_Subscription subscription) throws SQLException{
        switch (action) {
            case "products" : 
                LinkedHashMap<Integer, M_Product> productList = M_Product.getRecords(baseRR);
                LinkedHashMap<Integer, M_Product> currentProductList = M_Buy.getRecords(baseRR, "id_subscription = "+subscription.getId());
                fm_subscriptionAdditions.displayProducts(action, subscription, productList, currentProductList);
                break;
            case "payments" : 
                LinkedHashMap<Integer, M_Method> paymentMethodList = M_Method.getRecords(baseRR);
                LinkedHashMap<Integer, M_Payment> currentPaymentsList = M_Payment.getRecords(baseRR, "id_subscription = "+subscription.getId());
                fm_subscriptionAdditions.displayPayments(action, subscription, paymentMethodList, currentPaymentsList);
                break;
            case "computers" :
                LinkedHashMap<Integer, M_Computer> currentComputerList = M_Computer.getRecords(baseRR, "id_subscription = "+subscription.getId());
                fm_subscriptionAdditions.displayComputers(action, subscription, currentComputerList);
                break;
            }     
    }
    
    public void updateOutletListByRoom (String roomCode) throws SQLException {
        fm_subscriptionCrud.updateOutlet(M_Outlet.getRecords(baseRR, "code_room = '" + roomCode + "'"));
    }
    
    public void roomPage() throws SQLException {
        fm_room.display(M_Room.getRecords(baseRR));
    }
    
    public void roomCrud(String action, M_Room room) throws SQLException{
        
    }
    
    public void disconnect() throws SQLException {
        baseRR.closeBase();
    }
    
    private void connexion() throws Exception{
        baseRR = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
    }
    public static void main(String[] args) throws Exception {
        C_internet_subscription leControl = new C_internet_subscription();
    }
    
}
