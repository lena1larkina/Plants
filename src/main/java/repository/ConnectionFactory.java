package repository;
import exceptions.DBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory{
    static{
        try{
            Class.forName ("org.postgresql.Driver");
        }catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC Driver not found", e);
        }}
    public static Connection getConnection(){
        String dbUrl = "jdbc:postgresql://db:5432/plants";
        String dbUser = "postgres";
        String dbPassword = "Lena";
        try{
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }}}
