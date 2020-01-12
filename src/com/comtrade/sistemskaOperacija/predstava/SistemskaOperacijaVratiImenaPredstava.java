package com.comtrade.sistemskaOperacija.predstava;

import com.comtrade.broker.Broker;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.Predstava;
import com.comtrade.domen.TransferKlasa;

import java.util.ArrayList;
import java.util.List;

public class SistemskaOperacijaVratiImenaPredstava extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Predstava predstava = (Predstava) transferKlasa.getRequest();
        Broker broker = new Broker();
        transferKlasa.setResponse(vratiPodatke(broker.vratiPodatke(predstava)));
    }


    public List<String> vratiPodatke(List<OpstiDomen> listaOpstiDomen) {
        String imePredstave;
        List<String>listaImenaPredstava = new ArrayList<>();
        List<Predstava> listaPredstava = new ArrayList<>();
        for (OpstiDomen opsd: listaOpstiDomen) {
            listaPredstava.add((Predstava) opsd);
        }
        for (Predstava predstava: listaPredstava             ) {
            imePredstave = predstava.getNazivPredstave();
            listaImenaPredstava.add(imePredstave);
        }
        return listaImenaPredstava;
    }
}
