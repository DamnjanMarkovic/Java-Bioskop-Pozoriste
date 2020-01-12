package com.comtrade.sistemskaOperacija.termini;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Predstava;
import com.comtrade.domen.Termin;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaIzmenaPodatakaTermini extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Termin termin = (Termin) transferKlasa.getRequest();
        Broker broker = new Broker();
        broker.update(termin);


    }
}
