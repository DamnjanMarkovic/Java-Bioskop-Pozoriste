package com.comtrade.sistemskaOperacija.termini;

import com.comtrade.broker.Broker;
import com.comtrade.domen.*;

import java.time.LocalDate;
import java.util.*;

public class SistemskaOperacijaVratiPodatkeUlaznaStrana extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Termin termin = (Termin) transferKlasa.getRequest();
        Broker broker = new Broker();
        int idPredstave = transferKlasa.getMessage();
        transferKlasa.setResponse(broker.vratiPodatkeUlaznaStrana(termin));
        transferKlasa.setResponse(konkretnaPozorista(broker.vratiPodatkeUlaznaStrana(termin), idPredstave));

    }
    public Map<Termin, Integer>  konkretnaPozorista(Map<Termin, Integer> hmList, int idPredstave){
        Map<Termin, Integer> finalHM = new HashMap<>();
        for (Map.Entry<Termin, Integer> hash: hmList.entrySet()) {
            if (hash.getKey().getIdPredstave()==idPredstave ){
                finalHM.put(hash.getKey(), hash.getValue());
            }
        }return finalHM;
    }
}
