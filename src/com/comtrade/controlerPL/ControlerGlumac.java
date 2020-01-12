package com.comtrade.controlerPL;

import com.comtrade.controlerPL.glumac.SistemskaOperacijaUpisiGlumca;
import com.comtrade.domen.TransferKlasa;
import com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija;
import com.comtrade.sistemskaOperacija.glumac.*;
import com.comtrade.sistemskaOperacija.korisnik.*;

public class ControlerGlumac implements com.comtrade.controlerPL.CommandBase {
    @Override
    public void execute(TransferKlasa transferKlasa) {

        OpstaSistemskaOperacija opstaSistemskaOperacija=null;

        switch (transferKlasa.getKonstanteKPL()){

            case VRATI_PODATKE_GLUMAC:
                opstaSistemskaOperacija = new SistemskaOperacijaGlumacVratiPodatke();
                break;
            case VRATI_GLUMCE_U_PREDSTAVI:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiGlumceUPredstavi();
                break;
            case VRATI_ID_GLUMCA:
                opstaSistemskaOperacija = new SistemskaOperacijaUVratIDGlumca();
                break;

            case UPISI_GLUMCA:
                opstaSistemskaOperacija = new SistemskaOperacijaUpisiGlumca();
                break;
            case UPIS_GLUMAC_PREDSTAVA:
                opstaSistemskaOperacija = new SistemskaOperacijaUpisGlumacPredstava();
                break;
            case OBRISI_GLUMCA:
                opstaSistemskaOperacija = new SistemskaOperacijaObrisiGlumca();
                break;

            default:
                break;

        }opstaSistemskaOperacija.IzvrsiSistemskuOperaciju(transferKlasa);

    }
}
