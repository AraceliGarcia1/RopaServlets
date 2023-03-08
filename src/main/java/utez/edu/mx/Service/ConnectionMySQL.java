package utez.edu.mx.Service;

import java.sql.*;

public class ConnectionMySQL {
    public static Connection getConnection() throws SQLException {
        String host = "localhost";
        String port = "3307";
        String database = "ropa";
        String useSSL = "false";
        String timezone = "UTC";
        String user = "root";
        String password = "root";
        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=%s&serverTimezone=%s", host, port, database, useSSL, timezone);
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        return DriverManager.getConnection(url, user, password);
    }

    public static void closeConnections(Connection con,PreparedStatement pstm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
        }

    }

    public static void closeConnections(Connection con,PreparedStatement pstm) {
        try {

            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
        }

    }

    public static void main(String[] args) {
        try {
            Connection con = ConnectionMySQL.getConnection();
            System.out.println("Ok");
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
