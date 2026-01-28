/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_internet_subscription.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import pj_internet_subscription.Cl_Connexion;
import pj_internet_subscription.Db_mariadb;

/**
 *
 * @author matis
 */
public class M_Method {
/**
 *
 * @author matis
 */
    private Db_mariadb db;
    int id;
    private String code, name, comment;
    private LocalDateTime created_at, updated_at; 

    public M_Method(Db_mariadb db, int id, String code, String name, String comment, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id = id;
        this.code = code;
        this.name = name;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public M_Method(Db_mariadb db, String code, String name, String comment) throws SQLException {
        this.db = db;
        this.code = code;
        this.name = name;
        this.comment = comment;
        String sql;
        sql = "INSERT INTO mcd_methods (code, name, comment) "
                + "Values('"+code+"', '"+name+"', '"+comment+"' );";
        db.sqlExec(sql);
        ResultSet res;
        res=db.sqlLastId();
        res.first();
        this.id = res.getInt("id");
        res.close();
        sql = "SELECT created_at, updated_at FROM mcd_methods WHERE id="+id+"";
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class); 
        res.close();
    }

    public M_Method(Db_mariadb db, int id) throws SQLException {
        this.db = db;
        this.id = id;
        
        String sql;
        sql = "SELECT * FROM mcd_methods WHERE id='"+id+"'";
        
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.comment = res.getString("comment");
        this.name = res.getString("name");
        this.code = res.getString("code");
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

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
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

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
    
    

    public void update() throws SQLException {
        String sql = "UPDATE mcd_methods "
                + "SET name = '"+name+"', code='"+code+"', comment='"+comment+"' WHERE id="+id+"";
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_methods WHERE id='"+id+"'";
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException {
        String sql = "DELETE FROM mcd_methods WHERE id="+id+"";
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<Integer, M_Method> getRecords(Db_mariadb db) throws SQLException{
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<Integer, M_Method> getRecords(Db_mariadb db, String where) throws SQLException{
        LinkedHashMap<Integer, M_Method> methodList; 
        methodList = new LinkedHashMap();
        M_Method method;
        
        int key;
        String code, name, comment;
        LocalDateTime created_at, updated_at; 
        
        String sql = "SELECT * FROM mcd_methods WHERE "+where;
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            key = res.getInt("id");
            comment = res.getString("comment");
            name = res.getString("name");
            code = res.getString("code");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            method = new M_Method(db, key, code, name, comment, created_at, updated_at);
            methodList.put(key, method);            
        }
        return methodList;
    }
    
    public String toString(){
        return "M_String{ id = "+id+", comment="+comment+", name="+name+", code="+code+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <Integer, M_Method> methodList;
        M_Method method = null; 
        
        
        //Constructors
        //method = new M_Method(db, 5,"asc", "veryNamey", "comment", LocalDateTime.MAX, LocalDateTime.MAX);
         //method = new M_Method(db, "asc", "test", "a comment");
         //method = new M_Method(db, 4);
        
        //Delete
          //method.delete();
        
        //Update
         //method.setComment("Please Switch my comment!");
         //method.update();
        
        //getRecords 
        //methodList = M_Method.getRecords(db);
        //methodList = M_Method.getRecords(db, "id = 3");
        /*for (int key : methodList.keySet()){
            method = methodList.get(key);
            System.out.println(method.toString());
        }
        */
        //System.out.println(method.toString());
    }
}

