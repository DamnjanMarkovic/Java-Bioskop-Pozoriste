package com.comtrade.sistemskaOperacija.pozoriste;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaVratiIdPozorista extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Pozoriste pozoriste= (Pozoriste) transferKlasa.getRequest();
        Broker broker = new Broker();
        transferKlasa.setResponse(broker.vratiID(pozoriste));




    }
}
