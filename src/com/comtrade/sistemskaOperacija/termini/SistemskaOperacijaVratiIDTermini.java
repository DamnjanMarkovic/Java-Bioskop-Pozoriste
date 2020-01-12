package com.comtrade.sistemskaOperacija.termini;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.Termin;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaVratiIDTermini extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Termin termin = (Termin) transferKlasa.getRequest();
        Broker broker = new Broker();
        transferKlasa.setResponse(broker.vratiID(termin));



    }
}
