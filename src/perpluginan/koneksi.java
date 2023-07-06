/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpluginan;
import java.sql.Connection;
import java.sql.DriverManager;

/**
  _____             _ _        _    
 |_   _| _      __(_) |_ _  _| |__ 
   | || ' \ _  (_-< | ' \ || | '_ \
   |_||_||_(_) /__/_|_||_\_,_|_.__/  
   * sihub | 03 - 27 - 2023
 */
public class koneksi {
    static Connection con;
    
    public static Connection getConnection() { 
        try { 
            Class.forName(("com.mysql.cj.jdbc.Driver"));
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warmed-sakkarepmu","root", "");
            System.out.println("[FINE] rispek koneksi ke db aman paman");
        } catch(Exception ex) { 
            System.out.println("[ERROR] ndek koneksi db "+ex);
        }
        return con;
    }
}