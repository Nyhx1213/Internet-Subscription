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
import pj_internet_subscription.views.V_Subscription;
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
    
    public C_internet_subscription() throws Exception{ 
        connexion();
        userList = M_User.getRecords(baseRR);
        fm_main = new V_Main(this);
        fm_subscription = new V_Subscription(this, fm_main, true);
        fm_subscriptionCrud = new V_SubscriptionDetail(this, fm_main, true);
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
        fm_subscription.display(M_Subscription.getRecords(baseRR, offset));
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
