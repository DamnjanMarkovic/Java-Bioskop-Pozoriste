package com.comtrade.sistemskaOperacija.termini;

import com.comtrade.broker.Broker;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Predstava;
import com.comtrade.domen.Termin;
import com.comtrade.domen.TransferKlasa;

import java.util.*;

public class SistemskaOperacijaVratiPodatkeTermini extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {


        Termin termin = (Termin) transferKlasa.getRequest();
        int idPredstave = transferKlasa.getMessage();
        Broker broker = new Broker();
        transferKlasa.setResponse(vratiPodatkeTerminiMetoda(broker.vratiPodatke(termin), idPredstave));


    }

    public List<Date> vratiPodatkeTerminiMetoda(List<OpstiDomen> listaOpstiDomen, int idPredstave) {
        List<Termin>listaTermina = new ArrayList<>();
        for (OpstiDomen opsd: listaOpstiDomen) {
            listaTermina.add((Termin) opsd);
        }

        List<Date>listaDatuma = new ArrayList<>();
        Map<Integer, Termin> hm = new HashMap<>();
        Date todayDate = new Date();
        for (int i = 0; i < listaTermina.size(); i++) {
            hm.put(listaTermina.get(i).getIdTermina(), listaTermina.get(i));
        }
        for (Map.Entry<Integer, Termin> s: hm.entrySet() ) {
            if (s.getValue().getIdPredstave() == idPredstave && s.getValue().getDatum().compareTo((todayDate)) > 0
                    &&!listaDatuma.contains(s.getValue().getDatum()))

                listaDatuma.add(s.getValue().getDatum());
        }
        return listaDatuma;
    }
}






