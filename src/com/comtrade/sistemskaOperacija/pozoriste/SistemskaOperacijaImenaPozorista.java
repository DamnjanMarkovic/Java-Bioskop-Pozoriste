package com.comtrade.sistemskaOperacija.pozoriste;

import com.comtrade.broker.Broker;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.TransferKlasa;

import java.util.ArrayList;
import java.util.List;

public class SistemskaOperacijaImenaPozorista extends com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Pozoriste pozoriste = (Pozoriste) transferKlasa.getRequest();
        Broker broker = new Broker();
        transferKlasa.setResponse(vratiPodatke(broker.vratiPodatke(pozoriste)));
    }
    public List<String> vratiPodatke(List<OpstiDomen> listaOpstiDomen) {
        String imePozorista;
        List<String>listaImenaPozorista = new ArrayList<>();
        List<Pozoriste> listaPozorista = new ArrayList<>();
        for (OpstiDomen opsd: listaOpstiDomen) {
            listaPozorista.add((Pozoriste) opsd);
        }
        for (Pozoriste pozoriste: listaPozorista             ) {
            imePozorista = pozoriste.getNazivPozorista();
            listaImenaPozorista.add(imePozorista);
        }
        return listaImenaPozorista;
    }
}
