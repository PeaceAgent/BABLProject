package net.joelaustin.bablproject;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Joel on 2/6/2017.
 */

//WIP

public class BABLLoginVerify {

    ResultSet rs;
    PreparedStatement pstmt;
    private String ip = "databaseforbabl.cpngtl6yxjrl.us-west-2.rds.amazonaws.com:1433";
    private String Dbclass = "net.sourceforge.jtds.jdbc.Driver";
    private String db = "DbBABL";
    private String un = "gregmckibbin";
    private String password = "password";
    private String test = "Test";

    public BABLLoginVerify() {

    }

    public Boolean VerifyLogin(String strUsername, String strPassword){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;


        //Checks if USERNAMES are Equal, if they are, stops process;
        try {
            Class.forName(Dbclass).newInstance();
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);

            String query = "SELECT * FROM Users";

            pstmt = conn.prepareStatement(query);

            rs = pstmt.executeQuery();
            while (rs.next()){
                String strUsernameVerify = rs.getString("Username");


            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
            return false;
        }
        catch (Exception e) {
            Log.e("ERRO", e.getMessage());
            return false;
        }

        return false;
    }




}