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
public class M_Authorized {
      
    private Db_mariadb db;
    private int idAutorisation, idRole;

    public M_Authorized(Db_mariadb db, int idAutorisation, int idRole, boolean autoriser) throws SQLException {
        this.db = db;
        this.idAutorisation = idAutorisation;
        this.idRole = idRole;
        
        if (autoriser == true){
            String sql;
            sql = "INSERT INTO mcd_authorized (id_auth, id_role) "
            +"VALUES ("+idAutorisation+", "+idRole+");" ;
            db.sqlExec(sql);
        }
        
        
    }

    public static LinkedHashMap <Integer, M_Authorization> getLesAutorisations (Db_mariadb db, int idRole) throws SQLException {
        LinkedHashMap <Integer, M_Authorization> authList;
        authList = new LinkedHashMap();
        M_Authorization auth;
        if (idRole == -1){
            return null;
        }
        int key;
        String code, description;
                  
        String sql;
        sql = "SELECT AT.id, AT.code, AT.description FROM mcd_authorized "
                + "INNER JOIN mcd_authorization AS AT ON mcd_authorized.id_auth = AT.id "
                + "WHERE mcd_authorized.id_role = "+idRole+" ;";
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

    public static void main(String[] args) throws Exception {
        //Test du premier constructeur 
        LinkedHashMap <Integer ,M_Authorized> dicoAutorisations;
        M_Authorized uneAutorisation = null;
        Db_mariadb db;
        dicoAutorisations = new LinkedHashMap();
        db = new Db_mariadb(Cl_Connexion.url, Cl_Connexion.login, Cl_Connexion.mdp);
        
        //uneAutorisation = new M_Authorized (db, 1, "test", "test"); 
        //uneAutorisation = new M_Authorized (db, "test", "test"); 
        //uneAutorisation = new M_Authorized (db, 18); 
        
        //uneAutorisation.setDescription("casd");
        //uneAutorisation.update();
        
        //uneAutorisation.delete();
       //Verification de la methode getRecords avec un paramètre 
       
       //dicoAutorisations = M_Authorized.getRecords(db);
       
       
      
       //dicoAutorisations = M_Authorized.getRecords(db, "id = 4");
       
       for (int uneCle : dicoAutorisations.keySet()){
           uneAutorisation = dicoAutorisations.get(uneCle);
           System.out.println(uneAutorisation.toString());
       }
       
    }
}
