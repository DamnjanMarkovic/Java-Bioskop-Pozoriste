package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Glumac_predstava implements OpstiDomen {

    private int id_glumac_predstava;
    private int idPredstave;
    private int idGlumca;

    public Glumac_predstava(int idPredstava, int idGlumca) {
        this.idPredstave = idPredstava;
        this.idGlumca = idGlumca;
    }

    public int getId_glumac_predstava() {
        return id_glumac_predstava;
    }

    public void setId_glumac_predstava(int id_glumac_predstava) {
        this.id_glumac_predstava = id_glumac_predstava;
    }

    public int getIdPredstave() {
        return idPredstave;
    }

    public void setIdPredstave(int idPredstava) {
        this.idPredstave = idPredstava;
    }

    public int getIdGlumca() {
        return idGlumca;
    }

    public void setIdGlumca(int idGlumca) {
        this.idGlumca = idGlumca;
    }

    @Override
    public String vratiNazivTabele() {
        return "glumac_predstava";
    }

    @Override
    public OpstiDomen procitajObjekte(ResultSet resultSet) {
        return null;
    }

    @Override
    public String vratiKoloneSave() {
        return "(idPredstave, idGlumca)";
    }

    @Override
    public String vratiVrednosti() {
        return "(?,?)";
    }

    @Override
    public PreparedStatement vratiPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setInt(1, getIdPredstave());
            preparedStatement.setInt(2, getIdGlumca());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String vratiKolonuBrisanje() {
        return null;
    }

    @Override
    public String vratiVrednostUpitnik() {
        return null;
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
    public String nazivKolone() {        return null;    }


    @Override
    public String nazivIDKolone() {
        return null;
    }

    @Override
    public String nazivNazivKolone() {
        return null;
    }

    @Override
    public PreparedStatement vratiIdPreparedStatement(PreparedStatement preparedStatement) {
        return null;
    }

    @Override
    public String vratiVrednostUpitnikID() {
        return null;
    }

    @Override
    public String vratiImeTrazeneKolone() {
        return null;
    }
}
