package com.comtrade.sistemskaOperacija.pozoriste;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Korisnik;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaVratiNazivPozorista extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {


        Pozoriste pozoriste = (Pozoriste) transferKlasa.getRequest();
        int idKorisnika = transferKlasa.getMessage();
        Broker broker = new Broker();
        transferKlasa.setResponse(broker.vratiIme(pozoriste, idKorisnika));


    }
}
