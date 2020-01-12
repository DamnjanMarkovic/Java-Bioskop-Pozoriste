package com.comtrade.sistemskaOperacija.korisnik;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Korisnik;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaKorisnikVratiIme extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Korisnik korisnik = (Korisnik) transferKlasa.getRequest();
        int idKorisnika = transferKlasa.getMessage();
        Broker broker = new Broker();
        transferKlasa.setResponse(broker.vratiIme(korisnik, idKorisnika));

    }
}
