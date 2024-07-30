package Employee_Management_System.Code;

import java.sql.*;

public class Connect {

    Connection c;
    Statement s;
   public Connect(){
        String url = "jdbc:mysql://localhost:3306/EMS";  // last mein jis database se connect karna uska naam
        String username = "root";
        String password = "1506249738";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(url, username, password);
            s = c.createStatement();
        } catch (Exception e) {
            System.out.print("Not connected");
        }
    }
}
