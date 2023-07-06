/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpluginan;

/**
  _____             _ _        _    
 |_   _| _      __(_) |_ _  _| |__ 
   | || ' \ _  (_-< | ' \ || | '_ \
   |_||_||_(_) /__/_|_||_\_,_|_.__/  
   * sihub | 03 - 27 - 2023
 */
public class akses {
    /* 
    firstname, lastname, username, password, role
    */
    private static String firstname;
    private static String lastname;
    private static String username;
    private static String password;
    private static String role;

    public static String getFirstname() {
        return firstname;
    }

    public static void setFirstname(String firstname) {
        akses.firstname = firstname;
    }

    public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String lastname) {
        akses.lastname = lastname;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        akses.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        akses.password = password;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        akses.role = role;
    }
    public static void hapusAkses(){
        firstname = "";
        lastname = ""; 
        username = "";
        password = ""; 
        role = "";
    }
    
}
