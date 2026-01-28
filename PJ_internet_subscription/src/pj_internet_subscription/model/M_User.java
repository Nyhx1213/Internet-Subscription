/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_internet_subscription.model;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
public class M_User {
    private Db_mariadb db;
    int id, id_role; 
    private String first_name, last_name,name, email, password, comment;
    private LocalDateTime created_at, updated_at; 

    public M_User(Db_mariadb db, int id, int id_role, String first_name, String last_name, String name, String email, String password, String comment, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id = id;
        this.id_role = id_role;
        this.first_name = first_name;
        this.last_name = last_name;
        this.name = name;
        this.email = email;
        this.password = password;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }



    public M_User(Db_mariadb db, int id_role, String first_name, String last_name, String name, String email, String password, String comment) throws SQLException {
        this.db = db;
        this.id_role = id_role;
        this.first_name = first_name;
        this.last_name = last_name;
        this.name = name;
        this.email = email;
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        this.comment = comment;
        
        String sql;
        sql = "INSERT INTO mcd_users (id ,first_name, last_name, name, email, password, comment, id_role) "
                + "Values("+id+", '"+first_name+"', '"+last_name+"', '"+name+"','"+email+"', '"+this.password+"', '"+comment+"', "+id_role+" );";
        db.sqlExec(sql);
        ResultSet res;
        sql = "SELECT created_at, updated_at FROM mcd_users WHERE id="+id+"";
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class); 
        res.close();
    }

    public M_User(Db_mariadb db, int id) throws SQLException {
        this.db = db;
        this.id = id;
        
        String sql;
        sql = "SELECT * FROM mcd_users WHERE id="+id+"";
        
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.first_name = res.getString("first_name");
        this.last_name = res.getString("last_name");
        this.name = res.getString("name");
        this.email = res.getString("email");
        this.password = res.getString("password");
        this.comment = res.getString("comment");
        this.id_role = res.getInt("id_role");
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

    public int getId_role() {
        return id_role;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getComment() {
        return comment;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPassword(){
        return password;
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

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password){
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public void update() throws SQLException {
        String sql = "UPDATE mcd_users "
                + "SET id = "+id+",  first_name='"+first_name+"', last_name='"+last_name+"', name='"+name+"',  email='"+email+"',password='"+password+"', comment='"+comment+"', id_role="+id_role+" WHERE id="+id+"";
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_users WHERE id="+id+"";
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException {
        String sql = "DELETE FROM mcd_users WHERE id="+id+"";
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<Integer, M_User> getRecords(Db_mariadb db) throws SQLException{
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<Integer, M_User> getRecords(Db_mariadb db, String where) throws SQLException{
        LinkedHashMap<Integer, M_User> userList; 
        userList = new LinkedHashMap();
        M_User user;
        
        int key, id_role;
        String first_name, last_name, name, email, password, comment; 
        LocalDateTime created_at, updated_at;
        
        String sql = "SELECT * FROM mcd_users WHERE "+where;
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            key = res.getInt("id");
            first_name = res.getString("first_name");
            last_name = res.getString("last_name");
            name = res.getString("name");
            email = res.getString("email");
            password = res.getString("password");
            comment = res.getString("comment");
            id_role = res.getInt("id_role");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            user = new M_User(db, key, id_role, first_name, last_name, name, email, password, comment, created_at, updated_at);
            userList.put(key, user);
        }
        return userList;
    }

    public static M_User connexion_log(Db_mariadb db, String loginIns, String passwordIns) throws SQLException {
        String sql, passwordDB;      
        M_User user;
        
        sql = "Select * FROM mcd_users WHERE email = '"+loginIns+"'";
        ResultSet res;
        res = db.sqlSelect(sql);
        
        if (res.first() == false) {
            user = null;
            System.out.println("Your email was not found.");
        }
        else {
            passwordDB = res.getString("password");
            Boolean passwordVerif = BCrypt.verifyer().verify(passwordIns.toCharArray(), passwordDB).verified; // Verification si le hash == hash du mdp dans la base.
            if (passwordVerif) {
                user = new M_User(
                        db,
                        res.getInt("id"),
                        res.getInt("id_role"),
                        res.getString("first_name"),
                        res.getString("last_name"),
                        res.getString("name"),
                        res.getString("email"),
                        res.getString("password"),
                        res.getString("comment"),
                        res.getObject("created_at", LocalDateTime.class),
                        res.getObject("updated_at", LocalDateTime.class)
                                
                );
                
                        System.out.println("Connexion reussi");                
            }
            else {
                user = null;
                System.out.println("Wrong password");
            }
        
        }
        return user; 
    }
    @Override
    public String toString(){
        return "M_User{ id="+id+", name="+name+", email="+email+", password="+password+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <Integer, M_User> userList;
        M_User user; 
        
        //Constructors
        //user = new M_User(db, 1, "truc asd", "machin@alsd.d", LocalDateTime.now(), LocalDateTime.now());
        //user = new M_User(db, 7, "test", "tester", "tes.tester", "tes.tester@example.com", "mdp", "commentaire");
        //  user = new M_User(db,297);
        
        //Delete
        //user.delete();
        
        //Update
        //user.setComment("Please Switch my comment!");
        //user.update();
        
        //getRecords 
        //userList = M_User.getRecords(db);
        //userList = M_User.getRecords(db, "id LIKE '%2'");
        /*
        for (int key : userList.keySet()){
            user = userList.get(key);
            System.out.println(user.toString());
        }
        */
        //System.out.println(user.toString());
    }
    
}
