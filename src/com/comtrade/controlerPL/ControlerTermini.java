package com.comtrade.controlerPL;

import com.comtrade.domen.TransferKlasa;
import com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija;
import com.comtrade.sistemskaOperacija.termini.*;

public class ControlerTermini implements CommandBase {
    @Override
    public void execute(TransferKlasa transferKlasa) {

        OpstaSistemskaOperacija opstaSistemskaOperacija=null;

        switch (transferKlasa.getKonstanteKPL()){

            case VRATI_PODATKE_TERMINI:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiPodatkeTermini();
                break;
            case UNOS_PODATAKA_TERMINI:
                opstaSistemskaOperacija = new SistemskaOperacijaUnosPodatakaTermini();
                break;
            case VRATI_PODATKE_TERMINI_VREMENA:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiPodatkeTerminiVremena();
                break;
            case IZBRISI_TERMIN:
                opstaSistemskaOperacija = new SistemskaOperacijaIzbrisiTermin();
                break;
            case IZMENA_TERMINA:
                opstaSistemskaOperacija = new SistemskaOperacijaIzmenaPodatakaTermini();
                break;
            case VRATI_ID_TERMINA:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiIDTermini();
                break;
            case VRATI_PODATKE_ULAZNA_STRANA:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiPodatkeUlaznaStrana();
                break;
            case VRATI_PODATKE_TERMINI_TERMINI:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiPodatkeTerminiTermini();
                break;



            default:
                break;


        }opstaSistemskaOperacija.IzvrsiSistemskuOperaciju(transferKlasa);



    }
}
