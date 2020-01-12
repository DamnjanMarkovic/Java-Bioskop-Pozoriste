package com.comtrade.broker;

import com.comtrade.domen.*;
import com.comtrade.konekcija.Konekcija;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Broker {

    public List<OpstiDomen> vratiPodatke(OpstiDomen opstiDomen) {
        List<OpstiDomen> listaOpstiDomen = new ArrayList<>();
        String sql = "select * from " + opstiDomen.vratiNazivTabele();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                opstiDomen = (OpstiDomen) opstiDomen.procitajObjekte(resultSet);
                listaOpstiDomen.add(opstiDomen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaOpstiDomen;
    }

    public void save(OpstiDomen opstiDomen) {
        String sql = "insert into " + opstiDomen.vratiNazivTabele() + " " + opstiDomen.vratiKoloneSave() + " values "
                + opstiDomen.vratiVrednosti();
        try {
            PreparedStatement preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);
            preparedStatement = opstiDomen.vratiPreparedStatementSave(preparedStatement);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(OpstiDomen opstiDomen, int id) {
        String sql = "delete from " + opstiDomen.vratiNazivTabele() + " where " + opstiDomen.vratiKolonuBrisanje() +
                " = " + opstiDomen.vratiVrednostUpitnik();
        try {
            PreparedStatement preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ne mozete izbrisati, ima definisanih child-ova");
        }
    }

    public void update(OpstiDomen opstiDomen) {

        String sql = "update " + opstiDomen.vratiNazivTabele() + " set " + opstiDomen.vratiKoloneIzmena() + " where "
                + opstiDomen.izmenePoVrednosti();
        try {
            PreparedStatement preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);
            preparedStatement = opstiDomen.vratiPreparedStatementUpdate(preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int vratiID(OpstiDomen opstiDomen) {
        int idTrazeni = 0;
        String sql = "select " + opstiDomen.nazivIDKolone() + " from " + opstiDomen.vratiNazivTabele() +
                " where " + opstiDomen.nazivNazivKolone() + " = " + opstiDomen.vratiVrednostUpitnikID();
        try {

            PreparedStatement preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);
            preparedStatement = opstiDomen.vratiIdPreparedStatement(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first())
                idTrazeni = resultSet.getInt(opstiDomen.nazivIDKolone());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idTrazeni;
    }


    public List<Object> vratiDatume(OpstiDomen opstiDomen, String message) {
        List<Object> listaObjekata = new ArrayList<>();
        String sql = " select " + message + " from " + opstiDomen.vratiNazivTabele();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) listaObjekata.add(resultSet.getString(message));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaObjekata;
    }

    public int vratiBrojUlaznica(Termin termin) {
        int ukupanBrojKarata = 0;
        int brojRezervisanih = 0;
        int brojRaspolozivihUlaznica = 0;

        String sql = "SELECT brojUlaznica from termin where idTermina = ?";
        try {
            PreparedStatement preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);

            preparedStatement.setInt(1, termin.getIdTermina());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                ukupanBrojKarata = resultSet.getInt("brojUlaznica");
            }
            sql = "select sum(brojUlaznica) as sveUlaznice from rezervacija where idTermina = ?";
            preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, termin.getIdTermina());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) brojRezervisanih = resultSet.getInt("sveUlaznice");
            brojRaspolozivihUlaznica = ukupanBrojKarata - brojRezervisanih;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brojRaspolozivihUlaznica;
    }

    public Map<Termin, Integer> vratiPodatkeUlaznaStrana(OpstiDomen opstiDomen) {
        Map<Termin, Integer>hm = new HashMap<>();


        Termin termin = null;
        String sql = "select termin.idTermina as idTermina, COALESCE((sum(rezervacija.brojUlaznica)),0) as preostaloUlaznica, " +
                "termin.datum as datum, termin.vreme as vreme, termin.brojUlaznica as ukupanBrojUlaznica, " +
                "termin.idPredstave as idPredstave FROM termin left join rezervacija on rezervacija.idTermina = termin.idTermina " +
                "group by termin.idTermina";

        try {
            PreparedStatement preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                termin = new Termin(resultSet.getString("vreme"), resultSet.getDate("datum"),
                        resultSet.getInt("ukupanBrojUlaznica"), resultSet.getInt("idPredstave"));

                hm.put(termin, resultSet.getInt("preostaloUlaznica"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hm;
    }

    public List<Rezervacija> vratiPregledRezervacija(int idTermina) {

        Rezervacija rezervacija = new Rezervacija();
        List<Rezervacija> listaRezervacija = new ArrayList<>();
        String sql = "select rezervacija.idRezervacije, rezervacija.brojUlaznica, rezervacija.idKorisnika FROM rezervacija where rezervacija.idTermina=? group by idRezervacije";
        try {
            PreparedStatement preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idTermina);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rezervacija = new Rezervacija(resultSet.getInt("idRezervacije"),
                        resultSet.getInt("brojUlaznica"), resultSet.getInt("idKorisnika"), idTermina);

                listaRezervacija.add(rezervacija);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRezervacija;
    }
    public String  vratiIme(OpstiDomen opstiDomen, int idKorisnika) {
        String imeKorisnika = null;
        String sql = "select " + opstiDomen.vratiImeTrazeneKolone() + " FROM " + opstiDomen.vratiNazivTabele() + " where " + opstiDomen.nazivIDKolone() + " = ?";
        try {

            PreparedStatement preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idKorisnika);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first())
                imeKorisnika = resultSet.getString(opstiDomen.vratiImeTrazeneKolone());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imeKorisnika;

    }


    public List<Integer> vratiIDGlumacaUPredstavi(int idPredstave) {
        List<Integer> listaObjekata = new ArrayList<>();
        int idGlumca = 0;
        String sql = "select idGlumca from glumac_predstava where idPredstave = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = Konekcija.getKonekcija().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idPredstave);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idGlumca = resultSet.getInt("idGlumca");
                listaObjekata.add(idGlumca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaObjekata;
    }
}