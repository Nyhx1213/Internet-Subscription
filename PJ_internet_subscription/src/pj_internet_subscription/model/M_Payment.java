/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_internet_subscription.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import pj_internet_subscription.Cl_Connexion;
import pj_internet_subscription.Db_mariadb;

/**
 *
 * @author adjedjm
 */
public class M_Payment {
         
    private Db_mariadb db;
    private int id, id_method, id_subscription, amount; 
    private String comment;
    private LocalDate date_payment;
    private LocalDateTime created_at, updated_at; 

    public M_Payment(Db_mariadb db, int id, int id_method, int id_subscription, int amount, String comment, LocalDate date_payment, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id = id;
        this.id_method = id_method;
        this.id_subscription = id_subscription;
        this.amount = amount;
        this.comment = comment;
        this.date_payment = date_payment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public M_Payment(Db_mariadb db, int id_method, int id_subscription, int amount, String comment, LocalDate date_payment) throws SQLException {
        this.db = db;
        this.id_method = id_method;
        this.id_subscription = id_subscription;
        this.amount = amount;
        this.comment = comment;
        this.date_payment = date_payment;
        
        String sql;
        sql = "INSERT INTO mcd_payment (date_payment, amount, comment, id_method, id_subscription) "
                + "Values('"+date_payment+"', "+amount+", '"+comment+"', "+id_method+", "+id_subscription+");";
        db.sqlExec(sql);
        ResultSet res;
        res = db.sqlLastId();
        res.first();
        this.id = res.getInt("id");
        res.close();
        sql = "SELECT created_at, updated_at FROM mcd_payment WHERE id="+id;
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class); 
        res.close();
    }

    public M_Payment(Db_mariadb db, int id) throws SQLException {
        this.db = db;
        this.id = id;
        
        String sql;
        sql = "SELECT * FROM mcd_payment WHERE id="+id;
        
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.id_method = res.getInt("id_method");
        this.date_payment = res.getObject("date_payment", LocalDate.class);
        this.amount = res.getInt("amount");
        this.comment = res.getString("comment");
        this.id_subscription = res.getInt("id_subscription");
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }

    public Db_mariadb getDb() {
        return db;
    }

    public int getId() {
        return id;
    }

    public int getId_method() {
        return id_method;
    }

    public int getId_subscription() {
        return id_subscription;
    }

    public int getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getDate_payment() {
        return date_payment;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setDb(Db_mariadb db) {
        this.db = db;
    }

    public void setId_method(int id_method) {
        this.id_method = id_method;
    }

    public void setId_subscription(int id_subscription) {
        this.id_subscription = id_subscription;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate_payment(LocalDate date_payment) {
        this.date_payment = date_payment;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public void update() throws SQLException {
        String sql = "UPDATE mcd_payment "
                + "SET date_payment = '"+date_payment+"', amount="+amount+", comment='"+comment+"', id_method="+id_method+", id_subscription="+id_subscription+" WHERE id="+id;
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_payment WHERE id="+id;
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException {
        String sql = "DELETE FROM mcd_payment WHERE id="+id;
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<Integer, M_Payment> getRecords(Db_mariadb db) throws SQLException{
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<Integer, M_Payment> getRecords(Db_mariadb db, String where) throws SQLException{
        LinkedHashMap<Integer, M_Payment> paymentList; 
        paymentList = new LinkedHashMap();
        M_Payment payment;
        
        int key, id_method, id_subscription, amount;
        String comment;
        LocalDate date_payment;
        LocalDateTime created_at, updated_at;
        
        String sql = "SELECT * FROM mcd_payment WHERE "+where+" ";
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            key = res.getInt("id");
            id_method = res.getInt("id_method");
            id_subscription = res.getInt("id_subscription");
            amount = res.getInt("amount");
            comment = res.getString("comment");
            date_payment = res.getObject("date_payment", LocalDate.class);
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            payment = new M_Payment(db, key, id_method, id_subscription, amount, comment, date_payment, created_at, updated_at);
            paymentList.put(key, payment);
        }
        return paymentList;
    }
    
    @Override
    public String toString(){
        return "M_Payment{ id="+id+", id_subscription="+id_subscription+", id_method="+id_method+", amount="+amount+", date_payment="+date_payment+", comment="+comment+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <Integer, M_Payment> paymentList;
        M_Payment payment; 
        
        //Constructors
        //payment = new M_Payment(db, 1, 1, 21, 40, "test", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        //payment = new M_Payment(db, 1, 40, 27, "test", LocalDate.now());
        //payment = new M_Payment(db, 41);
        
        //Delete
       //payment.delete();
        
        //Update
        //payment.setComment("Please Switch my comment!");
        //payment.update();
        
        //getRecords 
        //paymentList = M_Payment.getRecords(db);
        //paymentList = M_Payment.getRecords(db, "id=40");
        /*for (int key : paymentList.keySet()){
            payment = paymentList.get(key);
            System.out.println(payment.toString());
        }
        */
        //System.out.println(payment.toString());
    }
}
