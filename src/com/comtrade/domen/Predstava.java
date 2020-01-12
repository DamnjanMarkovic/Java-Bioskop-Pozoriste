package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Predstava implements OpstiDomen {

    private int idPredstave;
    private String nazivPredstave;
    private int idPozorista;

    public Predstava(){

    }

    public Predstava(int idPredstave, String nazivPredstave, int idPozorista) {
        this.idPredstave = idPredstave;
        this.nazivPredstave = nazivPredstave;
        this.idPozorista = idPozorista;
    }

    public int getIdPredstave() {
        return idPredstave;
    }

    public void setIdPredstave(int idPredstave) {
        this.idPredstave = idPredstave;
    }

    public String getNazivPredstave() {
        return nazivPredstave;
    }

    public void setNazivPredstave(String nazivPredstave) {
        this.nazivPredstave = nazivPredstave;
    }

    public int getIdPozorista() {
        return idPozorista;
    }

    public void setIdPozorista(int idPozorista) {
        this.idPozorista = idPozorista;
    }

    @Override
    public String vratiNazivTabele() {
        return "predstava";
    }

    @Override
    public OpstiDomen procitajObjekte(ResultSet resultSet) {
     Predstava predstava = null;
        try {
            predstava = new Predstava(resultSet.getInt("idPredstave"), resultSet.getString("nazivPredstave"),
                    resultSet.getInt("idPozorista"));
        } catch (SQLException e) {
            e.printStackTrace();
        } return predstava;
    }

    @Override
    public String vratiKoloneSave() {
        return " (nazivPredstave, idPozorista) ";
    }

    @Override
    public String vratiVrednosti() {
        return " (?,?) ";
    }

    @Override
    public PreparedStatement vratiPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getNazivPredstave());
            preparedStatement.setInt(2, getIdPozorista());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String vratiKolonuBrisanje() {
        return " idPredstave ";
    }

    @Override
    public String vratiVrednostUpitnik() {
        return " ? ";
    }

    @Override
    public String vratiKoloneIzmena() {
        return " nazivPredstave = ? ";
    }

    @Override
    public String izmenePoVrednosti() {
        return " idPredstave = ? ";
    }

    @Override
    public PreparedStatement vratiPreparedStatementUpdate(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getNazivPredstave());
            preparedStatement.setInt(2, getIdPredstave());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String nazivKolone() {
        return "nazivPredstave";
    }

    @Override
    public String nazivIDKolone() {
        return "idPredstave";
    }

    @Override
    public String nazivNazivKolone() {
        return "nazivPredstave";
    }

    @Override
    public PreparedStatement vratiIdPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getNazivPredstave());
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
        return "nazivPredstave";
    }
}
