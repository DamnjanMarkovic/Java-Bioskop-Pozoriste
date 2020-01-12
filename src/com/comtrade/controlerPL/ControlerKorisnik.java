package com.comtrade.controlerPL;

import com.comtrade.domen.TransferKlasa;
import com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija;
import com.comtrade.sistemskaOperacija.korisnik.*;

public class ControlerKorisnik implements CommandBase {
    @Override
    public void execute(TransferKlasa transferKlasa) {

        OpstaSistemskaOperacija opstaSistemskaOperacija=null;

        switch (transferKlasa.getKonstanteKPL()){
            case LOGOVANJE:
                opstaSistemskaOperacija = new SistemskaOperacijaKorisnikLogin();
                break;
            case VRATI_PODATKE_KORISNIK:
                opstaSistemskaOperacija = new SistemskaOperacijaKorisnikVratiPodatke();
                break;
            case UNOS_PODATAKA_KORISNIK:
                opstaSistemskaOperacija = new SistemskaOperacijaKorisnikUnosPodataka();
                break;
            case IZMENA_IMENA_KORISNIKA:
                opstaSistemskaOperacija = new SistemskaOperacijaKorisnikIzmenaPodataka();
                break;
            case OBRISI_KORISNIKA:
                opstaSistemskaOperacija = new SistemskaOperacijaKorisnikObrisi();
                break;

            case VRATI_IME_KORISNIKA:
                opstaSistemskaOperacija = new SistemskaOperacijaKorisnikVratiIme();
                break;
            case VRATI_ID_KORISNIK:
                opstaSistemskaOperacija = new SistemskaOperacijaKorisnikVratiID();
                break;


            default:
                break;




        }opstaSistemskaOperacija.IzvrsiSistemskuOperaciju(transferKlasa);



    }
}
