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
public class M_Exist {
    private Db_mariadb db;
    private int id_antivirus, id_system; 
    private String comment;
    private LocalDateTime created_at, updated_at; 

    public M_Exist(Db_mariadb db, int id_antivirus, int id_system, String comment, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id_antivirus = id_antivirus;
        this.id_system = id_system;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    
    public M_Exist(Db_mariadb db, int id_antivirus, int id_system, String comment ) throws SQLException {
        this.db = db;
        this.id_antivirus = id_antivirus;
        this.id_system = id_system;
        this.comment = comment;
        
        String sql;
        sql = "INSERT INTO mcd_exist (id_antivirus, id_system, comment) "
                + "Values("+id_antivirus+", "+id_system+", '"+comment+"');";
        db.sqlExec(sql);
        ResultSet res;
        sql = "SELECT created_at, updated_at FROM mcd_exist WHERE id_antivirus="+id_antivirus+" AND id_system="+id_system+"";
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class); 
        res.close();
    }

    public M_Exist(Db_mariadb db, int id_antivirus, int id_system) throws SQLException {
        this.db = db;
        this.id_antivirus = id_antivirus;
        this.id_system = id_system;
        
        String sql;
        sql = "SELECT * FROM mcd_exist WHERE id_antivirus="+id_antivirus+" AND id_system="+id_system;
        
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.comment = res.getString("comment");
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }

    public Db_mariadb getDb() {
        return db;
    }

    public int getId_antivirus() {
        return id_antivirus;
    }

    public int getId_system() {
        return id_system;
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

    public void setId_antivirus(int id_antivirus) {
        this.id_antivirus = id_antivirus;
    }

    public void setId_system(int id_system) {
        this.id_system = id_system;
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
        String sql = "UPDATE mcd_exist "
                + "SET id_antivirus = "+id_antivirus+", id_system="+id_system+", comment='"+comment+"' WHERE id_antivirus= "+id_antivirus+" AND  id_system="+id_system;
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_exist WHERE id_antivirus="+id_antivirus+" AND id_system="+id_system+"";
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException {
        String sql = "DELETE FROM mcd_exist WHERE id_antivirus="+id_antivirus+" AND id_system="+id_system;
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<Integer, M_Exist> getRecords(Db_mariadb db) throws SQLException{
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<Integer, M_Exist> getRecords(Db_mariadb db, String where) throws SQLException{
        LinkedHashMap<Integer, M_Exist> existList; 
        existList = new LinkedHashMap();
        M_Exist exist;
        
        int key = 0, id_antivirus, id_system;
        String comment;
        LocalDateTime created_at, updated_at;
        
        String sql = "SELECT * FROM mcd_exist WHERE "+where+" ";
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            id_antivirus = res.getInt("id_antivirus");
            id_system = res.getInt("id_system");
            comment = res.getString("comment");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            exist = new M_Exist(db, id_antivirus, id_system, comment, created_at, updated_at);
            existList.put(key, exist);
            key++;
        }
        return existList;
    }
    
    @Override
    public String toString(){
        return "M_Exist{ id_antivirus="+id_antivirus+", id_system="+id_system+", comment="+comment+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <Integer, M_Exist> existList;
        M_Exist exist; 
        
        //Constructors
        //exist = new M_Exist(db, 1, 3, "test", LocalDateTime.now(), LocalDateTime.now());
        //exist = new M_Exist(db, 1, 1, "test");
        //exist = new M_Exist(db, 1, 3);
        
        //Delete
       //exist.delete();
        
        //Update
        //exist.setComment("Please Switch my comment!");
        //exist.update();
        
        //getRecords 
        //existList = M_Exist.getRecords(db);
        //existList = M_Exist.getRecords(db, "id=40");
        /*for (int key : existList.keySet()){
            exist = existList.get(key);
            System.out.println(exist.toString());
        }
        */
        //System.out.println(exist.toString());
    }
}
