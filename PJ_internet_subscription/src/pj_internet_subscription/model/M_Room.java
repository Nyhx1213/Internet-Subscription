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
public class M_Room {
    
    private Db_mariadb db;
    int capacity; 
    private String code, comment;
    private LocalDateTime created_at, updated_at; 

    public M_Room(Db_mariadb db, int capacity, String code, String comment, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.capacity = capacity;
        this.code = code;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public M_Room(Db_mariadb db, int capacity, String code, String comment) throws SQLException {
        this.db = db;
        this.code = code;
        this.capacity = capacity;
        this.comment = comment;
        
        String sql;
        sql = "INSERT INTO mcd_rooms (code, capacity, comment) "
                + "Values('"+code+"', "+capacity+",'"+comment+"' );";
        db.sqlExec(sql);
        ResultSet res;
        sql = "SELECT created_at, updated_at FROM mcd_rooms WHERE code='"+code+"'";
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class); 
        res.close();
    }

    public M_Room(Db_mariadb db, String code) throws SQLException {
        this.db = db;
        this.code = code;
        
        String sql;
        sql = "SELECT * FROM mcd_rooms WHERE code='"+code+"'";
        
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.comment = res.getString("comment");
        this.capacity = res.getInt("capacity");
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }

    public Db_mariadb getDb() {
        return db;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCode() {
        return code;
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

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
        String sql = "UPDATE mcd_rooms "
                + "SET capacity = "+capacity+", comment='"+comment+"' WHERE code='"+code+"'";
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_rooms WHERE code='"+code+"'";
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException {
        String sql = "DELETE FROM mcd_rooms WHERE code='"+code+"'";
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<String, M_Room> getRecords(Db_mariadb db) throws SQLException{
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<String, M_Room> getRecords(Db_mariadb db, String where) throws SQLException{
        LinkedHashMap<String, M_Room> roomList; 
        roomList = new LinkedHashMap();
        M_Room room;
        
        int capacity;
        String key, comment; 
        LocalDateTime created_at, updated_at;
        
        String sql = "SELECT * FROM mcd_rooms WHERE "+where;
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            key = res.getString("code");
            capacity = res.getInt("capacity");
            comment = res.getString("comment");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            room = new M_Room(db, capacity, key, comment, created_at, updated_at);
            roomList.put(key, room);
        }
        return roomList;
    }
    
    public String toString(){
        return "M_Room{ code="+code+", capacity="+capacity+", comment="+comment+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <String, M_Room> roomList;
        M_Room room; 
        
        //Constructors
        //room = new M_Room(db, 1, "fu_1", "", LocalDateTime.now(), LocalDateTime.now());
        //room = new M_Room(db, 1, "fu_03", "");
     //   room = new M_Room(db, "fu_02");
        
        //Delete
        //room.delete();
        
        //Update
        //room.setComment("Please Switch my comment!");
        //room.update();
        
        //getRecords 
        //roomList = M_Room.getRecords(db);
        //roomList = M_Room.getRecords(db, "code= 'fu_01'");
        /*for (String key : roomList.keySet()){
            room = roomList.get(key);
            System.out.println(room.toString());
        }
        */
        //System.out.println(room.toString());
    }
}

