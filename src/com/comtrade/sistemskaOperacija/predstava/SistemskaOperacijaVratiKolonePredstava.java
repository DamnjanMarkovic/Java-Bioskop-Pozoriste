package com.comtrade.sistemskaOperacija.predstava;

import com.comtrade.broker.Broker;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.Predstava;
import com.comtrade.domen.TransferKlasa;

import java.util.ArrayList;
import java.util.List;

public class SistemskaOperacijaVratiKolonePredstava extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Predstava predstava = (Predstava) transferKlasa.getRequest();
        int idPozorista = transferKlasa.getMessage();
        Broker broker = new Broker();
        transferKlasa.setResponse(konkretnePredstave(broker.vratiPodatke(predstava), idPozorista));
    }



    public List<Predstava>  konkretnePredstave(List<OpstiDomen> listaPredstava, int idPozorista){
        List<Predstava> listaPredstavaP =new ArrayList<>();
        List<Predstava> listaPredstavaPFinal =new ArrayList<>();
        for (OpstiDomen predstava1: listaPredstava){
            listaPredstavaP.add((Predstava) predstava1);
        }
        for (Predstava predstava: listaPredstavaP             ) {
            if (predstava.getIdPozorista() == idPozorista)
                listaPredstavaPFinal.add(predstava);
        }return listaPredstavaPFinal;
    }
}
