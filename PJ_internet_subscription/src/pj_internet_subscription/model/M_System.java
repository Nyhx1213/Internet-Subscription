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
 * @author adjedjm
 */
public class M_System {

    private Db_mariadb db;
    private int id;
    private String name, comment, version;
    private LocalDateTime created_at, updated_at;

    public M_System(Db_mariadb db, int id, String name, String comment, String version, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.version = version;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public M_System(Db_mariadb db, String name, String comment, String version) throws SQLException {
        this.db = db;
        this.name = name;
        this.comment = comment;
        this.version = version;
        String sql;
        sql = "INSERT INTO mcd_systems (name, comment, version)"
                + "VALUES('"+name+"', '"+comment+"', '"+version+"' );";
        db.sqlExec(sql);
        
        ResultSet res;
        res=db.sqlLastId();
        res.first();
        this.id = res.getInt("id");
        res.close();
        sql = "SELECT created_at, updated_at FROM mcd_systems WHERE id="+id;
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }

    public M_System(Db_mariadb db, int id) throws SQLException {
        this.db = db;
        this.id = id;
        String sql;
        sql = "SELECT * FROM mcd_systems WHERE id = "+id; 
        ResultSet res;
        res = db.sqlSelect(sql);
        res.first();
        this.name = res.getString("name");
        this.version = res.getString("version");
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

    public String getVersion() {
        return version;
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

    public void setVersion(String version) {
        this.version = version;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public void update() throws SQLException{
        String sql;
        
        sql = "UPDATE mcd_systems SET name = '"+name+"', comment = '"+comment+"'"
                + ", version = '"+version+"'WHERE id = "+id+" ;";
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_systems WHERE id="+id;
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException{
        String sql;
        
        sql = "DELETE FROM mcd_systems WHERE id = "+id+"";
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<Integer, M_System> getRecords(Db_mariadb db) throws SQLException {
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<Integer, M_System> getRecords(Db_mariadb db, String where) throws SQLException {
        LinkedHashMap<Integer, M_System> systemList; 
        systemList = new LinkedHashMap();
        M_System system; 
        
        int key;
        String name, comment, version; 
        LocalDateTime created_at, updated_at;
        
        String sql; 
        sql = "SELECT * FROM mcd_systems WHERE "+where+" ORDER BY name";
        ResultSet res = db.sqlSelect(sql);
        
        while(res.next()){
            key = res.getInt("id");
            name = res.getString("name");
            version = res.getString("version");
            comment = res.getString("comment");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            
            system = new M_System(db, key, name, comment, version, created_at, updated_at);
            systemList.put(key, system);
        }
        return systemList;
    }
    
    public String toString() {
        return "M_System{ id = "+id+", name="+name+", version = "+version+", comment="+comment+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <Integer, M_System> systemList;
        M_System system; 
        
        //Constructors
        //system = new M_System(db, 1, "Windows", "", "5", LocalDateTime.now(), LocalDateTime.now());
        //system = new M_System(db, "Windows", "Un super System", "01");
        //system = new M_System(db, 1);
        
        //Delete
        //system.delete();
        
        //Update
       // system.setComment("Please Switch my comment!");
        //system.update();
        
        //getRecords 
        //systemList = M_System.getRecords(db);
       /* systemList = M_System.getRecords(db, "id=3");
        for (int key : systemList.keySet()){
            system = systemList.get(key);
            System.out.println(system.toString());
        }
        */
        //System.out.println(system.toString());
    }
}
