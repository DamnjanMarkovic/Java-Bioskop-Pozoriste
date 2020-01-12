package com.comtrade.controlerPL;

import com.comtrade.domen.TransferKlasa;
import com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija;

import com.comtrade.sistemskaOperacija.rezervacija.SistemskaOperacijaRezervacija_Broj_Ulaznica;
import com.comtrade.sistemskaOperacija.rezervacija.SistemskaOperacijaRezervacija_PregledRezervacija;
import com.comtrade.sistemskaOperacija.rezervacija.SistemskaOperacijaRezervacija_Unesi;
import com.comtrade.sistemskaOperacija.termini.*;

public class ControlerRezervacija implements CommandBase {
    @Override
    public void execute(TransferKlasa transferKlasa) {

        OpstaSistemskaOperacija opstaSistemskaOperacija=null;

        switch (transferKlasa.getKonstanteKPL()){
            case REZERVACIJA_BROJ_ULAZNICA:
                opstaSistemskaOperacija = new SistemskaOperacijaRezervacija_Broj_Ulaznica();
                break;
            case UNESI_REZERVACIJE:
                opstaSistemskaOperacija = new SistemskaOperacijaRezervacija_Unesi();
                break;
            case REZERVACIJA_PREGLED_REZERVACIJA:
                opstaSistemskaOperacija = new SistemskaOperacijaRezervacija_PregledRezervacija();
                break;


            default:
                break;


        }opstaSistemskaOperacija.IzvrsiSistemskuOperaciju(transferKlasa);



    }
}
