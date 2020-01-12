package com.comtrade.sistemskaOperacija.glumac;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Glumac;
import com.comtrade.domen.Korisnik;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaVratiGlumceUPredstavi extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Glumac glumac = (Glumac) transferKlasa.getRequest();
        int idPredstave = transferKlasa.getMessage();
        Broker broker = new Broker();
        transferKlasa.setResponse(broker.vratiIDGlumacaUPredstavi(idPredstave));


    }
}
