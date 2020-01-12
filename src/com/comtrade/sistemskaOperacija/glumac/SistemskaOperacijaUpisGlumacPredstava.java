package com.comtrade.sistemskaOperacija.glumac;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Glumac_predstava;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaUpisGlumacPredstava extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Glumac_predstava glumac_predstava = (Glumac_predstava) transferKlasa.getRequest();
        Broker broker = new Broker();
        broker.save(glumac_predstava);


    }
}
