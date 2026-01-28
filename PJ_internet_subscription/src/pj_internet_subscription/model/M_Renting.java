/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_internet_subscription.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedHashMap;
import pj_internet_subscription.Cl_Connexion;
import pj_internet_subscription.Db_mariadb;

/**
 *
 * @author adjedjm
 */
public class M_Renting {
     
    private Db_mariadb db;
    private int id, id_user; 
    private String code_room, comment;
    private LocalDate date_begin, date_end;
    private LocalDateTime created_at, updated_at; 

    public M_Renting(Db_mariadb db, int id, int id_user, String code_room, String comment, LocalDate date_begin, LocalDate date_end, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id = id;
        this.id_user = id_user;
        this.code_room = code_room;
        this.comment = comment;
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public M_Renting(Db_mariadb db, int id_user, String code_room, String comment, LocalDate date_begin, LocalDate date_end) throws SQLException {
        this.db = db;
        this.id_user = id_user;
        this.code_room = code_room;
        this.comment = comment;
        this.date_begin = date_begin;
        this.date_end = date_end;
        
        String sql;
        sql = "INSERT INTO mcd_rentings (date_begin, date_end, comment, id_user, code_room) "
                + "Values('"+date_begin+"', '"+date_end+"', '"+comment+"', "+id_user+", '"+code_room+"');";
        db.sqlExec(sql);
        ResultSet res;
        res = db.sqlLastId();
        res.first();
        this.id = res.getInt("id");
        res.close();
        sql = "SELECT created_at, updated_at FROM mcd_rentings WHERE id="+id;
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class); 
        res.close();
    }

    public M_Renting(Db_mariadb db, int id) throws SQLException {
        this.db = db;
        this.id = id;
        
        String sql;
        sql = "SELECT * FROM mcd_rentings WHERE id="+id;
        
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.date_begin = res.getObject("date_begin", LocalDate.class);
        this.date_end = res.getObject("date_end", LocalDate.class);
        this.comment = res.getString("comment");
        this.id_user = res.getInt("id_user");
        this.code_room = res.getString("code_room");
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

    public int getId_user() {
        return id_user;
    }

    public String getCode_room() {
        return code_room;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getDate_begin() {
        return date_begin;
    }

    public LocalDate getDate_end() {
        return date_end;
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

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setCode_room(String code_room) {
        this.code_room = code_room;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate_begin(LocalDate date_begin) {
        this.date_begin = date_begin;
    }

    public void setDate_end(LocalDate date_end) {
        this.date_end = date_end;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
    
    public void update() throws SQLException {
        String sql = "UPDATE mcd_rentings "
                + "SET date_begin = '"+date_begin+"', date_end='"+date_end+"', comment='"+comment+"', id_user="+id_user+", code_room='"+code_room+"' WHERE id="+id;
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_rentings WHERE id="+id;
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException {
        String sql = "DELETE FROM mcd_rentings WHERE id="+id;
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<Integer, M_Renting> getRecords(Db_mariadb db) throws SQLException{
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<Integer, M_Renting> getRecords(Db_mariadb db, String where) throws SQLException{
        LinkedHashMap<Integer, M_Renting> rentingList; 
        rentingList = new LinkedHashMap();
        M_Renting renting;
        
        int key, id_user;
        String code_room, comment;
        LocalDate date_begin, date_end;
        LocalDateTime created_at, updated_at;
        
        String sql = "SELECT * FROM mcd_rentings WHERE "+where+" ";
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            key = res.getInt("id");
            code_room = res.getString("code_room");
            id_user = res.getInt("id_user");
            date_begin = res.getObject("date_begin", LocalDate.class);
            date_end = res.getObject("date_end", LocalDate.class);
            comment = res.getString("comment");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            renting = new M_Renting(db, key, id_user, code_room, comment, date_begin, date_end, created_at, updated_at);
            rentingList.put(key, renting);
        }
        return rentingList;
    }
    
    @Override
    public String toString(){
        return "M_Renting{ id="+id+", id_user="+id_user+", date_begin="+date_begin+", date_end="+date_end+", comment="+comment+", code_room="+code_room+" created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <Integer, M_Renting> rentingList;
        M_Renting renting; 
        
        //Constructors
        //renting = new M_Renting(db, 1, 1, "fu_01", "test", LocalDate.now(), LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        //renting = new M_Renting(db, 1, "fu_02", "test", LocalDate.now(), LocalDate.of(2026, Month.MARCH, 14));
        //renting = new M_Renting(db, 121);
        
        //Delete
        //renting.delete();
        
        //Update
        //renting.setComment("Please Switch my comment!");
        //renting.update();
        
        //getRecords 
        //rentingList = M_Renting.getRecords(db);
        //rentingList = M_Renting.getRecords(db, "id=101");
        /*for (int key : rentingList.keySet()){
            renting = rentingList.get(key);
            System.out.println(renting.toString());
        }
        */
        //System.out.println(renting.toString());
    }
}
