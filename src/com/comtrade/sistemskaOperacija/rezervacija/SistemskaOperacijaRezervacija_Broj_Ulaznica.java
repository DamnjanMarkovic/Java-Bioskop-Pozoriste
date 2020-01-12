package com.comtrade.sistemskaOperacija.rezervacija;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Termin;
import com.comtrade.domen.TransferKlasa;

public class SistemskaOperacijaRezervacija_Broj_Ulaznica extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Termin termin= (Termin) transferKlasa.getRequest();
        Broker broker = new Broker();
        transferKlasa.setResponse(broker.vratiBrojUlaznica(termin));


    }
}
