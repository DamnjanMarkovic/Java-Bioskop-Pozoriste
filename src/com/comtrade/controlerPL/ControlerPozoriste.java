package com.comtrade.controlerPL;

import com.comtrade.domen.TransferKlasa;
import com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija;
import com.comtrade.sistemskaOperacija.pozoriste.*;

public class ControlerPozoriste implements com.comtrade.controlerPL.CommandBase {
    @Override
    public void execute(TransferKlasa transferKlasa) {

        OpstaSistemskaOperacija opstaSistemskaOperacija=null;

        switch (transferKlasa.getKonstanteKPL()){

            case VRATI_PODATKE:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiPodatke();
                break;
            case UNOS_PODATAKA:
                opstaSistemskaOperacija = new SistemskaOperacijaUnosPodataka();
                break;
            case OBRISI_POZORISTE:
                opstaSistemskaOperacija = new SistemskaOperacijaBrisanjePozorista();
                break;
            case IZMENA_PODATAKA_O_POZORISTU:
                opstaSistemskaOperacija = new SistemskaOperacijaIzmenaPodataka();
                break;
            case IMENA_POZORISTA:
                opstaSistemskaOperacija = new SistemskaOperacijaImenaPozorista();
                break;
            case VRATI_ID_POZORISTA:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiIdPozorista();
                break;

            case VRATI_NAZIV_POZORISTA:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiNazivPozorista();
                break;
            default:
                break;




        }opstaSistemskaOperacija.IzvrsiSistemskuOperaciju(transferKlasa);



    }
}
