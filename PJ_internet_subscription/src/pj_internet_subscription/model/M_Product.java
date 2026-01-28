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
public class M_Product {
    
    private Db_mariadb db;
    int id, id_sub;
    float price, quantity;
    private String code, comment, designation;
    private LocalDateTime created_at, updated_at; 

    public M_Product(Db_mariadb db, int id, float price, String code, String comment, String designation, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id = id;
        this.price = price;
        this.code = code;
        this.comment = comment;
        this.designation = designation;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    
    public M_Product(Db_mariadb db, int id, float price, float quantity, String code, String label, int id_sub, LocalDateTime 
                    created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.code = code;
        this.designation = label;
        this.id_sub = id_sub; 
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
        
    public M_Product(Db_mariadb db, float price, String code, String designation, String comment) throws SQLException {
        this.db = db;        
        this.code = code;
        this.price = price;
        this.designation = designation;
        this.comment = comment;
        
        String sql;
        sql = "INSERT INTO mcd_products (code, price, designation, comment) "
                + "Values('"+code+"', "+price+", '"+designation+"','"+comment+"' );";
        db.sqlExec(sql);
        ResultSet res;
        res=db.sqlLastId();
        res.first();
        this.id = res.getInt("id");
        res.close();
        sql = "SELECT created_at, updated_at FROM mcd_products WHERE id="+id+"";
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class); 
        res.close();
    }

    public M_Product(Db_mariadb db, int id) throws SQLException {
        this.db = db;
        this.id = id;
        
        String sql;
        sql = "SELECT * FROM mcd_products WHERE id='"+id+"'";
        
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.comment = res.getString("comment");
        this.price = res.getFloat("price");
        this.code = res.getString("code");
        this.designation = res.getString("designation");
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

    public float getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public String getComment() {
        return comment;
    }

    public String getDesignation() {
        return designation;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public int getId_sub() {
        return id_sub;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setId_sub(int id_sub) {
        this.id_sub = id_sub;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    

    public void setDb(Db_mariadb db) {
        this.db = db;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public void update() throws SQLException {
        String sql = "UPDATE mcd_products "
                + "SET code = '"+code+"', designation='"+designation+"', comment='"+comment+"', price='"+price+"' WHERE id="+id+"";
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_products WHERE id='"+id+"'";
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException {
        String sql = "DELETE FROM mcd_products WHERE id="+id+"";
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<Integer, M_Product> getRecords(Db_mariadb db) throws SQLException{
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<Integer, M_Product> getRecords(Db_mariadb db, String where) throws SQLException{
        LinkedHashMap<Integer, M_Product> productList; 
        productList = new LinkedHashMap();
        M_Product product;
        
        int key;
        float price;
        String designation, code, comment; 
        LocalDateTime created_at, updated_at;
        
        String sql = "SELECT * FROM mcd_products WHERE "+where;
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            key = res.getInt("id");
            price = res.getFloat("price");
            comment = res.getString("comment");
            designation = res.getString("designation");
            code = res.getString("code");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            product = new M_Product(db, key, price, code, comment, designation, created_at, updated_at);
            productList.put(key, product);
        }
        return productList;
    }
    
    public String toString(){
        return "M_Room{ id = "+id+", price="+price+", code="+code+", designation="+designation+", comment="+comment+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <Integer, M_Product> productList;
        M_Product product; 
        
        //Constructors
        //product = new M_Product(db, 6, 15, "gr", "sat", "test", LocalDateTime.MAX, LocalDateTime.MAX);
       // product = new M_Product(db, 5, "as", "Brosse", "Une borsse quoi");
         // product = new M_Product(db, 5);
        
        //Delete
          //product.delete();
        
        //Update
       // product.setComment("Please Switch my comment!");
       // product.update();
        
        //getRecords 
        //productList = M_Product.getRecords(db);
        //productList = M_Product.getRecords(db, "id = 5");
        /*for (int key : productList.keySet()){
            product = productList.get(key);
            System.out.println(product.toString());
        }
        */
       // System.out.println(product.toString());
    }
}