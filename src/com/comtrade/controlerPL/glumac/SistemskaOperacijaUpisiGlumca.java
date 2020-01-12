package com.comtrade.controlerPL.glumac;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Glumac;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaUpisiGlumca extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {
        Glumac glumac = (Glumac) transferKlasa.getRequest();
        Broker broker = new Broker();
        broker.save(glumac);


    }
}
