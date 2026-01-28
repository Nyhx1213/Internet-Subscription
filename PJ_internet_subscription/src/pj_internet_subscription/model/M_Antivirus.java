/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_internet_subscription.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import pj_internet_subscription.Cl_Connexion;
import static pj_internet_subscription.Cl_Connexion.login;
import pj_internet_subscription.Db_mariadb;

/**
 *
 * @author adjedjm
 */
public class M_Antivirus {
    private Db_mariadb db;
    private int id;
    private String name, comment;
    private LocalDateTime created_at, updated_at;

    public M_Antivirus(Db_mariadb db, int id, String name, String comment, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public M_Antivirus(Db_mariadb db, String name, String comment) throws SQLException {
        this.db = db;
        this.name = name;
        this.comment = comment;
        String sql;
        sql = "INSERT INTO mcd_antiviruses (name, comment)"
                + "VALUES('"+name+"', '"+comment+"' );";
        db.sqlExec(sql);
        
        ResultSet res;
        res=db.sqlLastId();
        res.first();
        this.id = res.getInt("id");
        res.close();
        sql = "SELECT created_at, updated_at FROM mcd_antiviruses WHERE id="+id;
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }

    public M_Antivirus(Db_mariadb db, int id) throws SQLException {
        this.db = db;
        this.id = id;
        String sql;
        sql = "SELECT * FROM mcd_antiviruses WHERE id = "+id; 
        ResultSet res;
        res = db.sqlSelect(sql);
        res.first();
        this.name = res.getString("name");
        this.comment = res.getString("comment");
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
    
    public void update() throws SQLException{
        String sql;
        
        sql = "UPDATE mcd_antiviruses SET name = '"+name+"', comment = '"+comment+"'"
                + "WHERE id = "+id+" ;";
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_antiviruses WHERE id="+id;
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException{
        String sql;
        
        sql = "DELETE FROM mcd_antiviruses WHERE id = "+id+"";
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<Integer, M_Antivirus> getRecords(Db_mariadb db) throws SQLException {
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<Integer, M_Antivirus> getRecords(Db_mariadb db, String where) throws SQLException {
        LinkedHashMap<Integer, M_Antivirus> antiViruses; 
        antiViruses = new LinkedHashMap();
        M_Antivirus antiVirus; 
        
        int key;
        String name, comment; 
        LocalDateTime created_at, updated_at;
        
        String sql; 
        sql = "SELECT * FROM mcd_antiviruses WHERE "+where+" ORDER BY name";
        ResultSet res = db.sqlSelect(sql);
        
        while(res.next()){
            key = res.getInt("id");
            name = res.getString("name");
            comment = res.getString("comment");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            
            antiVirus = new M_Antivirus(db, key, name, comment, created_at, updated_at);
            antiViruses.put(key, antiVirus);
        }
        return antiViruses;
    }
    
    public String toString() {
        return "M_Antivirus{ id = "+id+", name="+name+", comment="+comment+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <Integer, M_Antivirus> antiVirusList;
        M_Antivirus antiVirus; 
        
        //Constructors
        //antiVirus = new M_Antivirus(db, 1, "Apache", "",LocalDateTime.now(), LocalDateTime.now());
        //antiVirus = new M_Antivirus(db, "Test", "Un super antivirus !");
        //antiVirus = new M_Antivirus(db, 4);
        
        //Delete
        //antiVirus.delete();
        
        //Update
        //antiVirus.setComment("Please Switch my comment!");
        //antiVirus.update();
        
        //getRecords 
        //antiVirusList = M_Antivirus.getRecords(db);
        /*antiVirusList = M_Antivirus.getRecords(db, "id=1");
        for (int key : antiVirusList.keySet()){
            antiVirus = antiVirusList.get(key);
            System.out.println(antiVirus.toString());
        }
        */
       // System.out.println(antiVirus.toString());
    }
}
