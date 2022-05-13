package com.example.mysql_androidstudio_jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetData {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;
    public String getdata() {
        ConnectionClass conStr=new ConnectionClass();
        connect =conStr.CONN();        // Connexion to database
        String data = null;
        data = new String();
        try
        {
            if (connect == null)
            {
                ConnectionResult = "Check Your Internet Access!";
            }
            else
            {
                // Change below query according to your own database.
                String query = "SELECT * FROM test ORDER BY IDW DESC LIMIT 1";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    data = rs.getString("task");
                }
                ConnectionResult = " successful";
                isSuccess=true;
                connect.close();
            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }

        return data;
    }


}
