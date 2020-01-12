package com.comtrade.sistemskaOperacija.rezervacija;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.Rezervacija;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaRezervacija_Unesi extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Rezervacija rezervacija = (Rezervacija) transferKlasa.getRequest();
        Broker broker = new Broker();
        broker.save(rezervacija);


    }
}
