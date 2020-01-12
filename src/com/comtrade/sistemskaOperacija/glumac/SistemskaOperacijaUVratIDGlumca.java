package com.comtrade.sistemskaOperacija.glumac;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Glumac;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaUVratIDGlumca extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Glumac glumac = (Glumac) transferKlasa.getRequest();
        Broker broker = new Broker();
        transferKlasa.setResponse(broker.vratiID(glumac));


    }
}
