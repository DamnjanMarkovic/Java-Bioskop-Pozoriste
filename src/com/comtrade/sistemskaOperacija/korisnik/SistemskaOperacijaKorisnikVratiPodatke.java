package com.comtrade.sistemskaOperacija.korisnik;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Korisnik;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaKorisnikVratiPodatke extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Korisnik korisnik = (Korisnik) transferKlasa.getRequest();
        Broker broker = new Broker();
        transferKlasa.setResponse(broker.vratiPodatke(korisnik));


    }
}
