package com.comtrade.sistemskaOperacija.termini;

import com.comtrade.broker.Broker;

import com.comtrade.domen.Termin;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaTerminiVratiDatume extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Termin termin = (Termin) transferKlasa.getRequest();
        int messageInt = transferKlasa.getMessage();
        String message = String.valueOf(messageInt);
        Broker broker = new Broker();
        transferKlasa.setResponse(broker.vratiDatume(termin, message));


    }
}
