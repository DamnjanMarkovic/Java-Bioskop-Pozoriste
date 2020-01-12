package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Korisnik implements OpstiDomen {

    private int idKorisnika;
    private String username;
    private String password;
    private String telefon;
    private String mail;
    private int status;
    public Korisnik(){

    }

    public Korisnik(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Korisnik(int idKorisnika, String username, String password, String telefon, String mail, int status) {
        this.idKorisnika = idKorisnika;
        this.username = username;
        this.password = password;
        this.telefon = telefon;
        this.mail = mail;
        this.status = status;
    }

    public Korisnik(String username, String password, String telefon, String mail, int status) {
        this.username = username;
        this.password = password;
        this.telefon = telefon;
        this.mail = mail;
        this.status = status;
    }

    public int getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String vratiNazivTabele() {
        return " korisnik ";
    }

    @Override
    public OpstiDomen procitajObjekte(ResultSet resultSet) {
        Korisnik korisnik = null;
        try {
            korisnik = new Korisnik(resultSet.getInt("idKorisnika"), resultSet.getString("username"),
                    resultSet.getString("password"), resultSet.getString("telefon"),
                    resultSet.getString("mail"), resultSet.getInt("status"));


        } catch (SQLException e) {
            e.printStackTrace();
        } return korisnik;


    }

    @Override
    public String vratiKoloneSave() {
        return " (username, password, telefon, mail, status) ";
    }

    @Override
    public String vratiVrednosti() {
        return " (?,?,?,?,?) ";
    }

    @Override
    public PreparedStatement vratiPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getUsername());
            preparedStatement.setString(2, getPassword());
            preparedStatement.setString(3, getTelefon());
            preparedStatement.setString(4, getMail());
            preparedStatement.setInt(5, getStatus());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String vratiKolonuBrisanje() {
        return " idKorisnika ";
    }

    @Override
    public String vratiVrednostUpitnik() {
        return " ? ";
    }

    @Override
    public String vratiKoloneIzmena() {
        return " username = ?, password = ?, telefon = ?, mail = ? ";
    }

    @Override
    public String izmenePoVrednosti() {
        return " idKorisnika = ? ";
    }

    @Override
    public PreparedStatement vratiPreparedStatementUpdate(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getUsername());
            preparedStatement.setString(2, getPassword());
            preparedStatement.setString(3, getTelefon());
            preparedStatement.setString(4, getMail());
            preparedStatement.setInt(5, getIdKorisnika());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String nazivKolone() {
        return null;
    }


    @Override
    public String nazivIDKolone() {
        return "idKorisnika";
    }

    @Override
    public String nazivNazivKolone() {
        return "username = ? and password";
    }

    @Override
    public PreparedStatement vratiIdPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getUsername());
            preparedStatement.setString(2, getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String vratiVrednostUpitnikID() {
        return " ? ";
    }

    @Override
    public String vratiImeTrazeneKolone() {
        return "username";
    }
}
