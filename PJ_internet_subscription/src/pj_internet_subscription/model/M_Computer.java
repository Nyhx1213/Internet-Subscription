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

/**
 *
 * @author matis
 */
public class M_Computer { 
    private Db_mariadb db;
    int id, id_antivirus, id_subscription, id_system;
    private String name, comment;
    private LocalDateTime created_at, updated_at; 

    public M_Computer(Db_mariadb db, int id, int id_antivirus, int id_subscription, int id_system, String name, String comment, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id = id;
        this.id_antivirus = id_antivirus;
        this.id_subscription = id_subscription;
        this.id_system = id_system;
        this.name = name;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public M_Computer(Db_mariadb db, int id_antivirus, int id_subscription, int id_system, String name, String comment) throws SQLException {
        this.db = db;
        this.id_antivirus = id_antivirus;
        this.id_subscription = id_subscription;
        this.id_system = id_system;
        this.name = name;
        this.comment = comment;

        String sql;
        sql = "INSERT INTO mcd_computers (name, id_antivirus, id_subscription, id_system, comment) "
                + "Values('"+name+"', "+id_antivirus+", "+id_subscription+","+id_system+", '"+comment+"' );";
        db.sqlExec(sql);
        ResultSet res;
        res=db.sqlLastId();
        res.first();
        this.id = res.getInt("id");
        res.close();
        sql = "SELECT created_at, updated_at FROM mcd_computers WHERE id="+id+"";
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class); 
        res.close();
    }

    public M_Computer(Db_mariadb db, int id) throws SQLException {
        this.db = db;
        this.id = id;
        
        String sql;
        sql = "SELECT * FROM mcd_computers WHERE id="+id+" ";
        
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.comment = res.getString("comment");
        this.name = res.getString("name");
        this.id_antivirus = res.getInt("id_antivirus");
        this.id_system = res.getInt("id_system");
        this.id_subscription = res.getInt("id_subscription");
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }

    public void setDb(Db_mariadb db) {
        this.db = db;
    }

    public void setId_antivirus(int id_antivirus) {
        this.id_antivirus = id_antivirus;
    }

    public void setId_subscription(int id_subscription) {
        this.id_subscription = id_subscription;
    }

    public void setId_system(int id_system) {
        this.id_system = id_system;
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

    public Db_mariadb getDb() {
        return db;
    }

    public int getId() {
        return id;
    }

    public int getId_antivirus() {
        return id_antivirus;
    }

    public int getId_subscription() {
        return id_subscription;
    }

    public int getId_system() {
        return id_system;
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

    public void update() throws SQLException {
        String sql = "UPDATE mcd_computers "
                + "SET name = '"+name+"', id_antivirus="+id_antivirus+", comment='"+comment+"', id_system="+id_system+", id_subscription="+id_subscription+" WHERE id="+id+"";
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_computers WHERE id='"+id+"'";
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException {
        String sql = "DELETE FROM mcd_computers WHERE id="+id+"";
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<Integer, M_Computer> getRecords(Db_mariadb db) throws SQLException{
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<Integer, M_Computer> getRecords(Db_mariadb db, String where) throws SQLException{
        LinkedHashMap<Integer, M_Computer> computerList; 
        computerList = new LinkedHashMap();
        M_Computer computer;
        
        int key, id_antivirus, id_subscription, id_system;
        String name, comment;
        LocalDateTime created_at, updated_at; 
        
        String sql = "SELECT * FROM mcd_computers WHERE "+where;
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            key = res.getInt("id");
            id_antivirus = res.getInt("id_antivirus");
            comment = res.getString("comment");
            name = res.getString("name");
            id_subscription = res.getInt("id_subscription");
            id_system = res.getInt("id_system");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            computer = new M_Computer(db, key, id_antivirus, id_subscription, id_system, name, comment, created_at, updated_at);
            computerList.put(key, computer);            
        }
        return computerList;
    }
    
    public String toString(){
        return "M_Computer{ id = "+id+", id_antivirus="+id_antivirus+", comment="+comment+", name="+name+", id_subscription="+id_subscription+", id_system="+id_system+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <Integer, M_Computer> computerList;
        M_Computer computer = null; 
        
  //  public M_Computer(Db_mariadb db, int id_antivirus, int id_subscription, int id_system, String name, String comment) throws SQLException {


        //Constructors
        //computer = new M_Computer(db, 11, 1, 5, 1, "", "test", LocalDateTime.MAX, LocalDateTime.MAX);
         //computer = new M_Computer(db, 1, 21, 1, "test" ,"test");
         //computer = new M_Computer(db, 40);
        
        //Delete
          //computer.delete();
        
        //Update
         //computer.setComment("Please Switch my comment!");
         //computer.update();
        
        //getRecords 
        //computerList = M_Computer.getRecords(db);
        //computerList = M_Computer.getRecords(db, "id = 1");
        /*for (int key : computerList.keySet()){
            computer = computerList.get(key);
            System.out.println(computer.toString());
        }
        */
        //System.out.println(computer.toString());
    }
}
