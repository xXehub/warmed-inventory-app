/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package permenunan;

/**
  _____             _ _        _    
 |_   _| _      __(_) |_ _  _| |__ 
   | || ' \ _  (_-< | ' \ || | '_ \
   |_||_||_(_) /__/_|_||_\_,_|_.__/  
   * sihub | 03 - 27 - 2023
 */
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String role;
    
    public User (int ID, String Firstname, String Lastname, String Username, String Password, String Role)
    {
        this.id = ID ; 
        this.firstname = Firstname;
        this.lastname = Lastname;
        this.username = Username;
        this.password = Password;
        this.role = Role;
    }

    public int getId()
    {
        return id;
    }
    public String getFirstname()
    {
        return firstname;
    }
    public String getLastname()
    {
        return lastname;
    }
        public String getUsername()
    {
        return username;
    }
        public String getPassword()
    {
        return password;
    }
    public String getRole()
    {
        return role;
    }
}
