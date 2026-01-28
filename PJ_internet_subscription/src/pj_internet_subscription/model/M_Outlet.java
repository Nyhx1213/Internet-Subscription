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
public class M_Outlet {

    private Db_mariadb db;
    private String code, comment, code_room;
    private LocalDateTime created_at, updated_at; 

    public M_Outlet(Db_mariadb db,String code, String comment, String code_room, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.code = code;
        this.code_room = code_room;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public M_Outlet(Db_mariadb db,String code, String comment, String code_room) throws SQLException {
        this.db = db;
        this.code = code;
        this.code_room = code_room;
        this.comment = comment;
        
        String sql;
        sql = "INSERT INTO mcd_outlets (code, code_room, comment) "
                + "Values('"+code+"', '"+code_room+"', '"+comment+"' );";
        db.sqlExec(sql);
        ResultSet res;
        sql = "SELECT created_at, updated_at FROM mcd_outlets WHERE code="+code+"";
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class); 
        res.close();
    }

    public M_Outlet(Db_mariadb db, String code) throws SQLException {
        this.db = db;
        this.code = code;
        
        String sql;
        sql = "SELECT * FROM mcd_outlets WHERE code='"+code+"' ";
        
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.comment = res.getString("comment");
        this.code = res.getString("code");
        this.code_room = res.getString("code_room");
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }

    public void setDb(Db_mariadb db) {
        this.db = db;
    }

    public Db_mariadb getDb() {
        return db;
    }

    public String getCode() {
        return code;
    }

    public String getComment() {
        return comment;
    }

    public String getCode_room() {
        return code_room;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCode_room(String code_room) {
        this.code_room = code_room;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }


    public void update() throws SQLException {
        String sql = "UPDATE mcd_outlets "
                + "SET code_room = '"+code_room+"', comment='"+comment+"' WHERE code="+code+"";
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_outlets WHERE code='"+code+"'";
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException {
        String sql = "DELETE FROM mcd_outlets WHERE code="+code+"";
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<String, M_Outlet> getRecords(Db_mariadb db) throws SQLException{
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<String, M_Outlet> getRecords(Db_mariadb db, String where) throws SQLException{
        LinkedHashMap<String, M_Outlet> outletList; 
        outletList = new LinkedHashMap();
        M_Outlet outlet;
        
        String code, code_room, comment;
        LocalDateTime created_at, updated_at; 
        
        String sql = "SELECT * FROM mcd_outlets WHERE "+where;
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            code = res.getString("code");
            code_room = res.getString("code_room");
            comment = res.getString("comment");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            outlet = new M_Outlet(db, code, comment, code_room, created_at, updated_at);
            outletList.put(code, outlet);            
        }
        return outletList;
    }
    
    public String toString(){
        return "M_Outlet{ code = "+code+", code_room="+code_room+", comment="+comment+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <String, M_Outlet> outletList;
        M_Outlet outlet = null; 
        
    //public M_Outlet(Db_mariadb db,String code, String comment, String code_room, LocalDateTime created_at, LocalDateTime updated_at) {


        //Constructors
         //outlet = new M_Outlet(db, "D21", "", "fu_01", LocalDateTime.MAX, LocalDateTime.MAX);
         outlet = new M_Outlet(db, "C21", "", "fu_01");
         //computer = new M_Outlet(db, 40);
        
        //Delete
          //computer.delete();
        
        //Update
         //computer.setComment("Please Switch my comment!");
         //computer.update();
        
        //getRecords 
        //computerList = M_Outlet.getRecords(db);
        //computerList = M_Outlet.getRecords(db, "id = 1");
        /*for (int key : computerList.keySet()){
            computer = computerList.get(key);
            System.out.println(computer.toString());
        }
        */
        System.out.println(outlet.toString());
    }
}

