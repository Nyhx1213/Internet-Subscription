/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_internet_subscription.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import pj_internet_subscription.Cl_Connexion;
import pj_internet_subscription.Db_mariadb;

/**
 *
 * @author matis
 */
public class M_Authorization {
    private Db_mariadb db;
    private int idAuth;
    private String code, description;

    public M_Authorization(Db_mariadb db, int idAuth, String code, String description) {
        this.db = db;
        this.idAuth = idAuth;
        this.code = code;
        this.description = description;
    }

    public M_Authorization(Db_mariadb db, String code, String description) throws SQLException {
        this.db = db;
        this.code = code;
        this.description = description;
        String sql;
        sql = "INSERT INTO mcd_authorization (code, description) "
           +"VALUES ('"+code+"', '"+description+"');" ;
        db.sqlExec(sql);

        ResultSet res;
        res = db.sqlLastId();
        res.first();
        this.idAuth = res.getInt("id");  
        res.close();
    }

    public M_Authorization(Db_mariadb db, int idAuth) throws SQLException {
        this.db = db;
        this.idAuth = idAuth;
        String sql;
        sql = "SELECT * FROM mcd_authorization WHERE id = "+idAuth;
        ResultSet res;
        res = db.sqlSelect(sql);
        res.first();
        this.code = res.getString("code");
        this.description = res.getString("description");;
        res.close();

    }

    public Db_mariadb getDb() {
        return db;
    }

    public int getIdAuth() {
        return idAuth;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDb(Db_mariadb db) {
        this.db = db;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void update() throws SQLException{
        String sql;
        sql = "UPDATE mcd_authorization SET code='"+code+"', description='"+description+"'" +
                " WHERE id="+idAuth+";";
        db.sqlExec(sql);
    }
    
    public void delete () throws SQLException{
        String sql ;
        sql = "DELETE FROM mcd_authorization WHERE id="+idAuth+";";
        db.sqlExec(sql);
    }
    
    public static LinkedHashMap <Integer, M_Authorization> getRecords (Db_mariadb db) throws SQLException {
        return getRecords(db, "1 = 1");
    } 
     
    public static LinkedHashMap <Integer, M_Authorization> getRecords (Db_mariadb db, String clauseWhere) throws SQLException {
        LinkedHashMap <Integer, M_Authorization> authList;
        authList = new LinkedHashMap();
        M_Authorization auth;
        
        int key;
        String code, description;
          
        String sql;
        sql = "SELECT * FROM mcd_authorization WHERE "+clauseWhere +" ORDER BY id";
        ResultSet res;
        res = db.sqlSelect(sql);
        
        while (res.next()){
            key = res.getInt("id");
            code = res.getString("code");
            description = res.getString("description");
        
            auth = new M_Authorization(db, key, code, description);
            authList.put(key, auth);
        }
        res.close();
        return authList;
    } 
    
    @Override
    public String toString() {
        return "M_Authorization{ idAuth=" + idAuth + ", code=" + code + ", description=" + description + "";
    }

    public static void main(String[] args) throws Exception {
        //Test du premier constructeur 
        LinkedHashMap <Integer ,M_Authorization> dicoAutorisations;
        M_Authorization uneAutorisation = null;
        Db_mariadb db;
        dicoAutorisations = new LinkedHashMap();
        db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        
        //uneAutorisation = new M_Authorization (db, 1, "test", "test"); 
        //uneAutorisation = new M_Authorization (db, "test", "test"); 
        //uneAutorisation = new M_Authorization (db, 18); 
        
        //uneAutorisation.setDescription("casd");
        //uneAutorisation.update();
        
        //uneAutorisation.delete();
       //Verification de la methode getRecords avec un paramètre 
       
       dicoAutorisations = M_Authorization.getRecords(db);
       
       
      
       //dicoAutorisations = M_Authorization.getRecords(db, "id = 4");
       
       for (int uneCle : dicoAutorisations.keySet()){
           uneAutorisation = dicoAutorisations.get(uneCle);
           System.out.println(uneAutorisation.toString());
       }
    }
}
