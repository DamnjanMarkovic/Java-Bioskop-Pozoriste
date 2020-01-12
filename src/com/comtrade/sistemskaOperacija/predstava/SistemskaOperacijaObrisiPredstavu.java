package com.comtrade.sistemskaOperacija.predstava;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Predstava;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaObrisiPredstavu extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Predstava predstava = (Predstava) transferKlasa.getRequest();
        Broker broker = new Broker();
        broker.delete(predstava, predstava.getIdPredstave());


    }
}
