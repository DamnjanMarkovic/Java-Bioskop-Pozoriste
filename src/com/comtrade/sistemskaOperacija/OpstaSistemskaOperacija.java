package com.comtrade.sistemskaOperacija;

import com.comtrade.domen.TransferKlasa;
import com.comtrade.konekcija.Konekcija;

import java.sql.SQLException;


public abstract class OpstaSistemskaOperacija {

    public void IzvrsiSistemskuOperaciju(TransferKlasa transferKlasa){

        try{
            pokreniTransakciju();
            IzvrsiKonkretnuOperaciju(transferKlasa);
            potvrdiTransakciju();

        }catch (Exception e){
            ponistiTransakciju();
        }finally {
            zatvoriKonekciju();
        }

    }

    private void zatvoriKonekciju() {
        try {
            Konekcija.getKonekcija().zatvoriKonekciju();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void ponistiTransakciju() {
        Konekcija.getKonekcija().ponistiTransakciju();

    }

    private void potvrdiTransakciju() {
        Konekcija.getKonekcija().potvrdiTransakciju();
    }

    private void pokreniTransakciju() {
        Konekcija.getKonekcija().pokreniTransakciju();



    }


    public  abstract void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa);
}
