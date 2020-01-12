package com.comtrade.sistemskaOperacija.predstava;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.Predstava;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaVratiNazivPredstava extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {
        Predstava predstava = (Predstava) transferKlasa.getRequest();
        int idKorisnika = transferKlasa.getMessage();
        Broker broker = new Broker();
        transferKlasa.setResponse(broker.vratiIme(predstava, idKorisnika));
    }
}
