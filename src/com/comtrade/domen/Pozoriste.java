package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pozoriste implements OpstiDomen{

    private int idPozorista;
    private String nazivPozorista;
    private String ulica;
    private int broj;
    private String grad;

    public Pozoriste(int idPozorista, String nazivPozorista, String ulica, int broj, String grad) {
        this.idPozorista = idPozorista;
        this.nazivPozorista = nazivPozorista;
        this.ulica = ulica;
        this.broj = broj;
        this.grad = grad;
    }

    public Pozoriste(String nazivPozorista, String ulica, int broj, String grad) {
        this.nazivPozorista = nazivPozorista;
        this.ulica = ulica;
        this.broj = broj;
        this.grad = grad;
    }
    public Pozoriste(){

    }

    public int getIdPozorista() {
        return idPozorista;
    }

    public void setIdPozorista(int idPozorista) {
        this.idPozorista = idPozorista;
    }

    public String getNazivPozorista() {
        return nazivPozorista;
    }

    public void setNazivPozorista(String nazivPozorista) {
        this.nazivPozorista = nazivPozorista;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    @Override
    public String vratiNazivTabele() {
        return "pozoriste";
    }

    @Override
    public OpstiDomen procitajObjekte(ResultSet resultSet) {
        Pozoriste pozoriste =null;
        try {
            pozoriste = new Pozoriste(resultSet.getInt("idPozorista"),
                    resultSet.getString("nazivPozorista"), resultSet.getString("ulica"),
                    resultSet.getInt("broj"), resultSet.getString("grad"));
        } catch (SQLException e) {
            e.printStackTrace();
        } return pozoriste;
    }

    @Override
    public String vratiKoloneSave() {
        return " (nazivPozorista, ulica, broj, grad) ";
    }

    @Override
    public String vratiVrednosti() {
        return " (?,?,?,?) ";
    }

    @Override
    public PreparedStatement vratiPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getNazivPozorista());
            preparedStatement.setString(2, getUlica());
            preparedStatement.setInt(3, getBroj());
            preparedStatement.setString(4, getGrad());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String vratiKolonuBrisanje() {
        return " idPozorista ";
    }

    @Override
    public String vratiVrednostUpitnik() {
        return " ? ";
    }

    @Override
    public String vratiKoloneIzmena() {
        return " nazivPozorista = ?, ulica = ?, broj = ?, grad = ? ";
    }




    @Override
    public String izmenePoVrednosti() {
        return " idPozorista = ? ";
    }

    @Override
    public PreparedStatement vratiPreparedStatementUpdate(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getNazivPozorista());
            preparedStatement.setString(2, getUlica());
            preparedStatement.setInt(3, getBroj());
            preparedStatement.setString(4, getGrad());
            preparedStatement.setInt(5, getIdPozorista());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String nazivKolone() {


        return "nazivPozorista";
    }

    @Override
    public String nazivIDKolone() {
        return "idPozorista";
    }

    @Override
    public String nazivNazivKolone() {
        return "nazivPozorista";
    }

    @Override
    public PreparedStatement vratiIdPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getNazivPozorista());
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
        return "nazivPozorista";
    }


}
