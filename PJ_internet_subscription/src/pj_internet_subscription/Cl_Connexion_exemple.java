package pj_internet_subscription;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author adjedjm
 */
public class Cl_Connexion_exemple {
    
    /* Pour effectuer des testes sans que l'application soit en JAR  
   
    public static String url = "//myserver:port/db_name";
    public static String login = "username";
    public static String mdp = "password";
   
    */
    /* Quand l'application est mises en JAR
    
    public static String url;
    public static String login;
    public static String mdp;
    static {
        Properties properties = new Properties();
        String configPath = System.getProperty("user.dir") + "/config.properties";

        try (FileInputStream input = new FileInputStream(configPath)) {
            properties.load(input);

            url = properties.getProperty("db.url");
            login = properties.getProperty("db.login");
            mdp = properties.getProperty("db.mdp");

            System.out.println("Config found at : " + configPath);

        } catch (IOException e) {
            throw new RuntimeException("config.properties not found : " + configPath, e);
        }
    }
    */
}
