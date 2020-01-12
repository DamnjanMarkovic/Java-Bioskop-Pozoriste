package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Glumac implements OpstiDomen {

    private int idGlumca;
    private String ime;
    private String prezime;

    public Glumac(int idGlumca, String ime, String prezime) {
        this.idGlumca = idGlumca;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Glumac(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public Glumac(){

    }


    public int getIdGlumca() {
        return idGlumca;
    }

    public void setIdGlumca(int idGlumca) {
        this.idGlumca = idGlumca;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "glumac";
    }

    @Override
    public OpstiDomen procitajObjekte(ResultSet resultSet) {
        Glumac glumac = null;
        try {
            glumac = new Glumac(resultSet.getInt("idGlumca"), resultSet.getString("ime"),
                    resultSet.getString("prezime"));
        } catch (SQLException e) {
            e.printStackTrace();
        } return glumac;
    }

    @Override
    public String vratiKoloneSave() {
        return " (ime, prezime) ";
    }

    @Override
    public String vratiVrednosti() {
        return " (?,?) ";
    }

    @Override
    public PreparedStatement vratiPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getIme());
            preparedStatement.setString(2, getPrezime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String vratiKolonuBrisanje() {
        return "idGlumca";
    }

    @Override
    public String vratiVrednostUpitnik() {
        return "?";
    }

    @Override
    public String vratiKoloneIzmena() {
        return null;
    }

    @Override
    public String izmenePoVrednosti() {
        return null;
    }

    @Override
    public PreparedStatement vratiPreparedStatementUpdate(PreparedStatement preparedStatement) {
        return null;
    }

    @Override
    public String nazivKolone() {
        return "idGlumca";
    }

    @Override
    public String nazivIDKolone() {
        return "idGlumca";
    }

    @Override
    public String nazivNazivKolone() {
        return "(ime, prezime)";
    }

    @Override
    public PreparedStatement vratiIdPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getIme());
            preparedStatement.setString(2, getPrezime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String vratiVrednostUpitnikID() {
        return "(?, ?)";
    }

    @Override
    public String vratiImeTrazeneKolone() {
        return "null";
    }
}
