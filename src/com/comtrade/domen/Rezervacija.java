package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rezervacija implements OpstiDomen {

    private int idRezervacije;
    private int brojUlaznica;
    private int idKorisnika;
    private int idTermina;

    public Rezervacija(int brojUlaznica, int idKorisnika, int idTermina) {
        this.brojUlaznica = brojUlaznica;
        this.idKorisnika = idKorisnika;
        this.idTermina = idTermina;
    }

    public Rezervacija(int idRezervacije, int brojUlaznica, int idKorisnika, int idTermina) {
        this.idRezervacije = idRezervacije;
        this.brojUlaznica = brojUlaznica;
        this.idKorisnika = idKorisnika;
        this.idTermina = idTermina;
    }

    public Rezervacija(){

    }
    public int getIdRezervacije() {
        return idRezervacije;
    }

    public void setIdRezervacije(int idRezervacije) {
        this.idRezervacije = idRezervacije;
    }

    public int getBrojUlaznica() {
        return brojUlaznica;
    }

    public void setBrojUlaznica(int brojUlaznica) {
        this.brojUlaznica = brojUlaznica;
    }

    public int getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public int getIdTermina() {
        return idTermina;
    }

    public void setIdTermina(int idTermina) {
        this.idTermina = idTermina;
    }

    @Override
    public String vratiNazivTabele() {
        return " rezervacija ";
    }

    @Override
    public OpstiDomen procitajObjekte(ResultSet resultSet) {
        return null;
    }

    @Override
    public String vratiKoloneSave() {
        return " (brojUlaznica, idKorisnika, idTermina) ";
    }

    @Override
    public String vratiVrednosti() {
        return " (?,?,?) ";
    }

    @Override
    public PreparedStatement vratiPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setInt(1, getBrojUlaznica());
            preparedStatement.setInt(2, getIdKorisnika());
            preparedStatement.setInt(3, getIdTermina());

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
    public String nazivKolone() {
        return null;
    }

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
