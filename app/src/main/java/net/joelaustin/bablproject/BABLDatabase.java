package net.joelaustin.bablproject;

/**
 * Created by Joel-PC on 2/3/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BABLDatabase extends AsyncTask<String, Void, String>{


    private Context context;
    private String strUsername;
    private String strPassword;
    private String strFirstName;
    private Integer intCampusSelect;
    private Boolean boolMain;
    private Boolean boolJohnstown;
    private Boolean boolBradford;
    private Boolean boolTitusville;
    private Boolean boolGreensburg;
    private Boolean boolNewUser;

    public BABLDatabase(Context context,Boolean boolNewUser, String strUsername, String strPassword, String strFirstName,Integer intCampusSelect, Boolean boolMain, Boolean boolJohnstown, Boolean boolBradford, Boolean boolTitusville, Boolean boolGreensburg) {
        this.context = context;
        this.strUsername = strUsername;
        this.strPassword = strPassword;
        this.strFirstName = strFirstName;
        this.intCampusSelect = intCampusSelect;
        this.boolMain = boolMain;
        this.boolJohnstown = boolJohnstown;
        this.boolBradford = boolBradford;
        this.boolTitusville = boolTitusville;
        this.boolGreensburg = boolGreensburg;
        this.boolNewUser = boolNewUser;
    }


    private String ip = "databaseforbabl.cpngtl6yxjrl.us-west-2.rds.amazonaws.com:1433";
    private String Dbclass = "net.sourceforge.jtds.jdbc.Driver";
    private String db = "DbBABL";
    private String un = "gregmckibbin";
    private String password = "password";

    ResultSet rs;
    PreparedStatement pstmt;

    protected String doInBackground(String... strArr){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;


        if (boolNewUser == true){
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
                    if (strUsernameVerify.equals(strUsername)){
                        return "Username Already Exists";
                    }

                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return "exception";
            }
            catch (ClassNotFoundException e) {
                Log.e("ERRO", e.getMessage());
                return "exception";
            }
            catch (Exception e) {
                Log.e("ERRO", e.getMessage());
                return "exception";
            }

            //Inputs to the Database
            try {

                Class.forName(Dbclass).newInstance();
                ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                        + "databaseName=" + db + ";user=" + un + ";password="
                        + password + ";";
                conn = DriverManager.getConnection(ConnURL);
                String query = "INSERT INTO Users (Username, Password, FirstName, Lang1, Lang2, Lang3, Lang4, Lang5, Attending, Main, Johnstown, Bradford, Titusville, Greensburg) VALUES " +
                        "(" +
                        "?," + //1
                        "?," + //2
                        "?," + //3
                        "?," + //4
                        "?," + //5
                        "?," + //6
                        "?," + //7
                        "?," + //8
                        "?," + //9
                        "?," + //10
                        "?," + //11
                        "?," + //12
                        "?," + //13
                        "?" + //14
                        ")";


                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, strUsername);
                pstmt.setString(2, strPassword);
                pstmt.setString(3, strFirstName);
                pstmt.setString(4, strArr[0]);
                pstmt.setString(5, strArr[1]);
                pstmt.setString(6, strArr[2]);
                pstmt.setString(7, strArr[3]);
                pstmt.setString(8, strArr[4]);
                pstmt.setInt(9, intCampusSelect);
                pstmt.setBoolean(10, boolMain);
                pstmt.setBoolean(11, boolJohnstown);
                pstmt.setBoolean(12, boolBradford);
                pstmt.setBoolean(13, boolTitusville);
                pstmt.setBoolean(14, boolGreensburg);

                pstmt.executeUpdate();
                return "New User Added Successfully";



            }
            catch (SQLException e) {
                e.printStackTrace();
                return "exception";
            }
            catch (ClassNotFoundException e) {
                Log.e("ERRO", e.getMessage());
                return "exception";
            }
            catch (Exception e) {
                Log.e("ERRO", e.getMessage());
                return "exception";
            }
        }
        else {
            try {
                Class.forName(Dbclass).newInstance();
                ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                        + "databaseName=" + db + ";user=" + un + ";password="
                        + password + ";";
                conn = DriverManager.getConnection(ConnURL);

                String query = "UPDATE Users SET " +
                        "Lang1=?," +
                        "Lang2=?," +
                        "Lang3=?," +
                        "Lang4=?," +
                        "Lang5=?," +
                        "Attending=?," +
                        "Main=?," +
                        "Johnstown=?," +
                        "Bradford=?," +
                        "Titusville=?," +
                        "Greensburg=?" +
                        "WHERE Username=?";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, strArr[0]);
                pstmt.setString(2, strArr[1]);
                pstmt.setString(3, strArr[2]);
                pstmt.setString(4, strArr[3]);
                pstmt.setString(5, strArr[4]);
                pstmt.setInt(6, intCampusSelect);
                pstmt.setBoolean(7, boolMain);
                pstmt.setBoolean(8, boolJohnstown);
                pstmt.setBoolean(9, boolBradford);
                pstmt.setBoolean(10, boolTitusville);
                pstmt.setBoolean(11, boolGreensburg);
                pstmt.setString(12, strUsername);
              


                pstmt.execute();

                return "Account Successfully Updated";
            }
            catch (SQLException e) {
                e.printStackTrace();
                return "exception";
            }
            catch (ClassNotFoundException e) {
                Log.e("ERRO", e.getMessage());
                return "exception";
            }
            catch (Exception e) {
                Log.e("ERRO", e.getMessage());
                return "exception";
            }
        }

    }

    protected void onPostExecute(String result) {

        if (result.equals("New User Added Successfully")) {
            Intent intentRegister = new Intent(context, StartActivity.class);
            intentRegister.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentRegister);
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Account Successfully Updated")) {
            new BABLDataRetrieve(context).execute();
        }
        else {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }


    }

}