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

/*
    ###
    ### I've stopped updating this model after the "update" function and it needs to be reworked too. 
    ### The whole model is not correct and will need to be adapted.
    ###
*/
/**
 *
 * @author matis
 */
public class M_Buy {
    private Db_mariadb db;
    private int id_subscription, id_product, price;
    private float quantity;
    private String comment;
    private LocalDateTime created_at, updated_at; 

    public M_Buy(Db_mariadb db, int id_subscription, int id_product, int price, float quantity, String comment, LocalDateTime created_at, LocalDateTime updated_at) {
        this.db = db;
        this.id_subscription = id_subscription;
        this.id_product = id_product;
        this.price = price;
        this.quantity = quantity;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public M_Buy(Db_mariadb db, int id_subscription, int id_product, int price, float quantity, String comment) throws SQLException {
        this.db = db;
        this.id_subscription = id_subscription;
        this.id_product = id_product;
        this.price = price;
        this.quantity = quantity;
        this.comment = comment;
        
        String sql;
        sql = "INSERT INTO mcd_buy (id_subscription, id_product, price, quantity, comment) "
                + "Values("+id_subscription+", "+id_product+", "+price+","+quantity+", '"+comment+"' );";
        db.sqlExec(sql);
        ResultSet res;
        sql = "SELECT created_at, updated_at FROM mcd_buy WHERE id_subscription="+id_subscription+" AND id_product="+id_product;
        res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class); 
        res.close();
    }

    public M_Buy(Db_mariadb db, int id_subscription, int id_product) throws SQLException {
        this.db = db;
        this.id_subscription = id_subscription;
        this.id_product = id_product;
        
        String sql;
        sql = "SELECT * FROM mcd_buy WHERE id_subscription="+id_subscription+" AND id_product="+id_product+"";

        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.comment = res.getString("comment");
        this.price = res.getInt("price");
        this.quantity = res.getFloat("quantity");
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }

    public Db_mariadb getDb() {
        return db;
    }

    public int getId_subscription() {
        return id_subscription;
    }

    public int getId_product() {
        return id_product;
    }

    public int getPrice() {
        return price;
    }

    public float getQuantity() {
        return quantity;
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

    public void setId_subscription(int id_subscription) {
        this.id_subscription = id_subscription;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
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
        String sql = "UPDATE mcd_buy "
                + "SET comment = '"+comment+"', id_product="+id_product+", price="+price+", quantity="+quantity+", id_subscription="+id_subscription+" WHERE id_subscription="+id_subscription+" AND id_product="+id_product;
        db.sqlExec(sql);
        sql = "SELECT created_at, updated_at FROM mcd_buy WHERE id_subscription="+id_subscription+" AND id_product="+id_product+"";
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.created_at = res.getObject("created_at", LocalDateTime.class);
        this.updated_at = res.getObject("updated_at", LocalDateTime.class);
        res.close();
    }
    
    public void delete() throws SQLException {
        String sql = "DELETE FROM mcd_buy WHERE id_product="+id_product+" AND id_subscription="+id_subscription+"";
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap<Integer, M_Product> getRecords(Db_mariadb db) throws SQLException{
        return getRecords(db, "1=1");
    }
    
    public static LinkedHashMap<Integer, M_Product> getRecords(Db_mariadb db, String where) throws SQLException{
        LinkedHashMap<Integer, M_Product> productList; 
        productList = new LinkedHashMap();
        M_Product product;
        
        int key=0, id_product, id_subscription, price;
        float quantity;
        String comment, code, designation;
        LocalDateTime created_at, updated_at; 
        
        String sql = "SELECT pr.id, pr.code, pr.designation, bu.id_subscription, bu.price, bu.quantity, bu.comment, bu.created_at, bu.updated_at "
                + " FROM mcd_buy bu"
                + " INNER JOIN mcd_products pr ON bu.id_product = pr.id "
                + " WHERE "+where;
        ResultSet res = db.sqlSelect(sql);
        
        while (res.next()){
            id_subscription = res.getInt("id_subscription");
            id_product = res.getInt("id");
            comment = res.getString("comment");
            price = res.getInt("price");
            quantity = res.getFloat("quantity");
            code = res.getString("code");
            designation = res.getString("designation");
            created_at = res.getObject("created_at", LocalDateTime.class);
            updated_at = res.getObject("updated_at", LocalDateTime.class);
            product = new M_Product(db, id_product, price, quantity, code, designation, id_subscription, 
                    created_at, updated_at);
            productList.put(key, product);
            key++;
        }
        return productList;
    }
    
    @Override
    public String toString(){
        return "M_Buy{ id_subscription = "+id_subscription+", id_product="+id_product+", comment="+comment+", price="+price+", quantity="+quantity+", created_at="+created_at+", updated_at="+updated_at+" }";
    }
    
    public static void main(String[] args) throws Exception {
        Db_mariadb db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        LinkedHashMap <Integer, M_Buy> buyList;
        M_Buy buy; 
        
        //Constructors
        //buy = new M_Buy(db, 21, 5, 100, 1, "test",  LocalDateTime.MAX, LocalDateTime.MAX);
         //buy = new M_Buy(db, 21, 6, 100, 1 ,"test");
         //buy = new M_Buy(db, 21, 5);
        
        //Delete
          //buy.delete();
        
        //Update
         //buy.setComment("Please Switch my comment!");
         //buy.update();
        
        //getRecords 
        //buyList = M_Buy.getRecords(db);
        //buyList = M_Buy.getRecords(db, "id_product = 5");
        /*for (int key : buyList.keySet()){
            buy = buyList.get(key);
            System.out.println(buy.toString());
        }
        */
        //System.out.println(buy.toString());
    }
}


