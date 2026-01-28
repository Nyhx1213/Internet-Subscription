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
public class M_Subscription {

/**
 *
 * @author matis
 */
    private Db_mariadb db;
    int id, id_user;
    private String login, password, comment, code_outlet, first_name, last_name;
    private LocalDateTime created_at, updated_at; 
    private LocalDate date_begin,date_end;
    
    public M_Subscription(Db_mariadb db, int id, int id_user, String login, String first_name, String last_name, String password, String comment, String code_outlet, LocalDate date_begin, LocalDate date_end, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id = id;
        this.id_user = id_user;
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.comment = comment; 
        this.code_outlet = code_outlet;
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.created_at = created_at;
        this.updated_at = updated_at;
        
    }

    public M_Subscription(Db_mariadb db, int id_user, String login, String password, String comment, String code_outlet, LocalDate date_begin, LocalDate date_end) throws SQLException {
        this.db = db;
        this.id_user = id_user;
        this.login = login;
        this.password = password;
        this.comment = comment; 
        this.code_outlet = code_outlet;
        this.date_begin = date_begin;
        this.date_end = date_end;

        String sql;
        sql = "INSERT INTO mcd_subscriptions (id_user, login, password, comment, code_outlet, date_begin, date_end) "
                + "Values("+id_user+", '"+login+"', '"+password+"', '"+comment+"', '"+code_outlet+"', '"+date_begin+"', '"+date_end+"'  );";
        db.sqlExec(sql);
        ResultSet res;
        res=db.sqlLastId();
        res.first();
        this.id = res.getInt("id");
        res.close();
        sql = "SELECT created_at, updated_at FROM mcd_subscriptions WHERE id="+id+"";
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class); 
        res.close();
    }

    public M_Subscription(Db_mariadb db, int id) throws SQLException {
        this.db = db;
        this.id = id;
        
        String sql;
        sql = "SELECT * FROM mcd_subscriptions WHERE id="+id+" ";
        
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.id_user = res.getInt("id_user");
        this.login = res.getString("login");
        this.password = res.getString("password");
        this.comment = res.getString("comment"); 
        this.code_outlet = res.getString("code_outlet");
        this.date_begin = res.getObject("date_begin", LocalDate.class);
        this.date_end = res.getObject("date_end", LocalDate.class);
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

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public int getId_user() {
        return id_user;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getComment() {
        return comment;
    }

    public String getCode_outlet() {
        return code_outlet;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public LocalDate getDate_begin() {
        return date_begin;
    }

    public LocalDate getDate_end() {
        return date_end;
    }

    public void setDb(Db_mariadb db) {
        this.db = db;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCode_outlet(String code_outlet) {
        this.code_outlet = code_outlet;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public void setDate_begin(LocalDate date_begin) {
        this.date_begin = date_begin;
    }

    public void setDate_end(LocalDate date_end) {
        this.date_end = date_end;
    }

    public boolean isActive(){
        return LocalDate.now().isBefore(date_end);
    }

    public void update() throws SQLException {
        String sql = "UPDATE mcd_subscriptions "
                    + "SET date_begin='"+date_begin+"', date_end='"+date_end+"', login='"+login+"', password='"+password+"', comment='"+comment+"', code_outlet='"+code_outlet+"', id_user="+id_user+" WHERE id="+id+"";
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_subscriptions WHERE id='"+id+"'";
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException {
        String sql = "DELETE FROM mcd_subscriptions WHERE id="+id+"";
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<Integer, M_Subscription> getRecords(Db_mariadb db) throws SQLException{
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<Integer, M_Subscription> getRecords(Db_mariadb db, String where) throws SQLException{
        LinkedHashMap<Integer, M_Subscription> subscriptionList; 
        subscriptionList = new LinkedHashMap();
        M_Subscription subscription;
        
        int key, id_user;
        String login, password, comment, code_outlet, first_name, last_name;
        LocalDateTime created_at, updated_at; 
        LocalDate date_begin, date_end; 
        
        String sql = "SELECT SU.id, SU.date_begin, SU.date_end, SU.login, SU.password, SU.comment, SU.created_at, SU.updated_at, SU.code_outlet, SU.id_user"
                + ", US.first_name, US.last_name FROM mcd_subscriptions SU "
                + "INNER JOIN mcd_users US ON SU.id_user = US.id WHERE "+where+" ";
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            key = res.getInt("id");
            id_user = res.getInt("id_user");
            comment = res.getString("comment");
            login = res.getString("login");
            code_outlet = res.getString("code_outlet");
            password = res.getString("password");
            date_begin = res.getObject("date_begin", LocalDate.class);
            date_end = res.getObject("date_end", LocalDate.class);
            first_name = res.getString("first_name");
            last_name = res.getString("last_name");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            subscription = new M_Subscription(db, key, id_user, login, first_name, last_name, password, comment, code_outlet, date_begin, date_end, created_at, updated_at);
            subscriptionList.put(key, subscription);            
        }
        return subscriptionList;
    }
    
    public static LinkedHashMap<Integer, M_Subscription> getRecords(Db_mariadb db, int offset) throws SQLException{
        LinkedHashMap<Integer, M_Subscription> subscriptionList; 
        subscriptionList = new LinkedHashMap();
        M_Subscription subscription;
        
        int key, id_user;
        String login, password, comment, code_outlet, first_name, last_name;
        LocalDateTime created_at, updated_at; 
        LocalDate date_begin, date_end; 
        
        String sql = "SELECT SU.id, SU.date_begin, SU.date_end, SU.login, SU.password, SU.comment, SU.created_at, SU.updated_at, SU.code_outlet, SU.id_user"
                + ", US.first_name, US.last_name FROM mcd_subscriptions SU "
                + "INNER JOIN mcd_users US ON SU.id_user = US.id LIMIT 50 OFFSET "+offset+" ";
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            key = res.getInt("id");
            id_user = res.getInt("id_user");
            comment = res.getString("comment");
            login = res.getString("login");
            code_outlet = res.getString("code_outlet");
            password = res.getString("password");
            date_begin = res.getObject("date_begin", LocalDate.class);
            date_end = res.getObject("date_end", LocalDate.class);
            first_name = res.getString("first_name");
            last_name = res.getString("last_name");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            subscription = new M_Subscription(db, key, id_user, login, first_name, last_name, password, comment, code_outlet, date_begin, date_end, created_at, updated_at);
            subscriptionList.put(key, subscription);   
            
            System.out.println(id_user);
        }
        return subscriptionList;
    }
    public String toString(){
        return "M_Subscription{ id = "+id+", id_user="+id_user+", comment="+comment+", login="+login+", password="+password+", code_outlet="+code_outlet+", date_begin="+date_begin+", date_end="+date_end+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <Integer, M_Subscription> subscriptionList;
        M_Subscription subscription = null; 
        
    //public M_Subscription(Db_mariadb db, int id, int id_user, String login, String password, String comment, String code_outlet, LocalDate date_begin, LocalDate date_end, LocalDateTime created_at, LocalDateTime updated_at) {


        //Constructors
        //subscription = new M_Subscription(db, 1, 1, "salut", "mdp", "", "ac", LocalDate.MAX, LocalDate.MAX, LocalDateTime.MAX, LocalDateTime.MAX);
        //LocalDate dateBeg = LocalDate.now();
        //LocalDate dateEnd = LocalDate.of(2026, 10, 13);
         //subscription = new M_Subscription(db, 1,"salut", "mdp", "test" ,"C1", dateBeg, dateEnd);
         //subscription = new M_Subscription(db, 40);
        
        //Delete
         //subscription.delete();
        
        //Update
         //subscription.setComment("Please Switch my comment!");
         //subscription.update();        
        //getRecords 
        //subscriptionList = M_Subscription.getRecords(db, i);
        //subscriptionList = M_Subscription.getRecords(db, "id = 21");
        /*for (int key : subscriptionList.keySet()){
            subscription = subscriptionList.get(key);
            System.out.println(subscription.toString());
        }
        */
        //System.out.println(subscription.toString());
    }
}
