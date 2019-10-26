
import static java.lang.Integer.parseInt;
import java.sql.*;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2ndyrGroupC
 */
public class Crud {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/barlas";
    static final String USER = "root";
    static final String PASS = "";
    Registration r = new Registration();
    
    private Connection connect() throws ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER); 
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void insertRegisterInfo(String[] list) throws ClassNotFoundException { 
       
        String sql = "INSERT INTO users VALUES (id,'" + list[0] + "','" + list[1] + "','"+list[2]+"','"+list[3]+"','"+list[4]+"')";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sorry");
            System.out.println(e.getMessage());
        }
    }
    public boolean isPresent(String username,String password) throws ClassNotFoundException{
        boolean ans=false;
         String sql = "SELECT * FROM `users`";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeQuery();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String email=rs.getString("email").trim();
                String pass=rs.getString("password").trim();
                if(email.equals(username)){
                    if(pass.equals(password)){                
                        ans=true;
                        break;
                    }
                }
            }
        } catch (SQLException e) {
          
            System.out.println(e.getMessage());
        }
        System.out.println(ans);
        return ans;
    
    }
    

}
    

