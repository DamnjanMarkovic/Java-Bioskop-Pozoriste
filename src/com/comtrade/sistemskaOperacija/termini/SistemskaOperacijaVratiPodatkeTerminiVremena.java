package com.comtrade.sistemskaOperacija.termini;

import com.comtrade.broker.Broker;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Termin;
import com.comtrade.domen.TransferKlasa;

import java.util.*;

public class SistemskaOperacijaVratiPodatkeTerminiVremena extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {
        Termin termin = (Termin) transferKlasa.getRequest();
        int idPredstave = transferKlasa.getMessage();
        Broker broker = new Broker();
        transferKlasa.setResponse(vratiPodatkeTerminiVremenaMetoda(broker.vratiPodatke(termin), termin.getDatum(), idPredstave));
    }


    public List<String> vratiPodatkeTerminiVremenaMetoda(List<OpstiDomen> listaOpstiDomen, Date datum, int idPredstave) {
        List<Termin>listaTermina = new ArrayList<>();
        for (OpstiDomen opsd: listaOpstiDomen) {
            listaTermina.add((Termin) opsd);
        }
        List<String>listaVremena = new ArrayList<>();
        Map<Integer, Termin> hm = new HashMap<>();
        for (int i=0; i<listaTermina.size(); i++) {
            hm.put(listaTermina.get(i).getIdTermina(), listaTermina.get(i));
        }
        for (Map.Entry<Integer, Termin> s: hm.entrySet() ) {
            if (s.getValue().getIdPredstave() == idPredstave
                    && s.getValue().getDatum().toString().equalsIgnoreCase(datum.toString())
                    && !listaVremena.contains(s.getValue().getVreme()))

                listaVremena.add(s.getValue().getVreme());
        }
        return listaVremena;
    }





}
