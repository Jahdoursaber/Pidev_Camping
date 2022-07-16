/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENEVO
 */
public class MyDb {
    private String URL = "jdbc:mysql://127.0.0.1:3306/camping";
    private String USERNAME = "root";
    private String PWD = "";
    static private Connection cnx;
    static private MyDb instance;
    //ouvrir une connexion 
    public MyDb (){
        try {
          cnx =  DriverManager.getConnection(URL, USERNAME, PWD);
          System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    static public MyDb getInstance () {
          if (instance == null) 
              instance =  new MyDb();
          
          return instance;
        }
    static public Connection getCnx () {
        return cnx;
    }

    public Connection getConnection() {
return cnx;

    }
}
