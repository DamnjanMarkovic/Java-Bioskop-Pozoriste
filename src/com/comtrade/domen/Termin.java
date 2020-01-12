package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Termin implements OpstiDomen{

    private int idTermina;
    private String vreme;
    private Date datum;
    private int brojUlaznica;
    private int idPredstave;

    public Termin(String vreme, Date datum, int brojUlaznica, int idPredstave) {
        this.datum = datum;
        this.vreme = vreme;

        this.brojUlaznica = brojUlaznica;
        this.idPredstave = idPredstave;
    }

    public Termin(int idTermina, String vreme, Date datum, int brojUlaznica, int idPredstave) {
        this.idTermina = idTermina;
        this.datum = datum;
        this.vreme = vreme;

        this.brojUlaznica = brojUlaznica;
        this.idPredstave = idPredstave;
    }

    public Termin(){

    }


    public int getIdTermina() {
        return idTermina;
    }

    public void setIdTermina(int idTermina) {
        this.idTermina = idTermina;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getbrojUlaznica() {
        return brojUlaznica;
    }

    public void setbrojUlaznica(int brojUlaznica) {
        this.brojUlaznica = brojUlaznica;
    }

    public int getIdPredstave() {
        return idPredstave;
    }

    public void setIdPredstave(int idPredstave) {
        this.idPredstave = idPredstave;
    }

    @Override
    public String vratiNazivTabele() {
        return "termin";
    }

    @Override
    public OpstiDomen procitajObjekte(ResultSet resultSet) {
        Termin termin = null;
        try {
            termin = new Termin(resultSet.getInt("idTermina"), resultSet.getString("vreme"),
                    resultSet.getDate("datum"), resultSet.getInt("brojUlaznica"),
                    resultSet.getInt("idPredstave"));


        } catch (SQLException e) {
            e.printStackTrace();
        } return termin;


    }

    @Override
    public String vratiKoloneSave() {
        return " (vreme, datum, brojUlaznica, idPredstave) ";
    }

    @Override
    public String vratiVrednosti() {
        return " (?,?,?,?) ";
    }

    @Override
    public PreparedStatement vratiPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getVreme());
            preparedStatement.setDate(2, (java.sql.Date) getDatum());
            preparedStatement.setInt(3, getbrojUlaznica());
            preparedStatement.setInt(4, getIdPredstave());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String vratiKolonuBrisanje() {
        return " idTermina ";
    }

    @Override
    public String vratiVrednostUpitnik() {
        return " ? ";
    }

    @Override
    public String vratiKoloneIzmena() {
        return " vreme = ?, datum = ?, brojUlaznica = ? ";
    }

    @Override
    public String izmenePoVrednosti() {
        return " idTermina = ? ";
    }

    @Override
    public PreparedStatement vratiPreparedStatementUpdate(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getVreme());
            preparedStatement.setDate(2, (java.sql.Date) getDatum());
            preparedStatement.setInt(3, getbrojUlaznica());
            preparedStatement.setInt(4, idTermina);


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
        return "idTermina";
    }

    @Override
    public String nazivNazivKolone() {
        return " (datum, vreme, idPredstave) ";
    }

    @Override
    public PreparedStatement vratiIdPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setDate(1, (java.sql.Date) getDatum());
            preparedStatement.setString(2, getVreme());
            preparedStatement.setInt(3, getIdPredstave());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }





    @Override
    public String vratiVrednostUpitnikID() {
        return " (?,?,?) ";
    }

    @Override
    public String vratiImeTrazeneKolone() {
        return null;
    }


}
