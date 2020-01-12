package com.comtrade.konekcija;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Konekcija {

    private Connection connection;
    private static Konekcija konekcija;

    public static Konekcija getKonekcija() {
        if (konekcija==null){
            konekcija= new Konekcija();
        }
        return konekcija;
    }

    private Konekcija(){

    }

    public void pokreniTransakciju(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teatar", "root","");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void potvrdiTransakciju(){

        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void ponistiTransakciju(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void zatvoriKonekciju() throws SQLException {
        try {
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
