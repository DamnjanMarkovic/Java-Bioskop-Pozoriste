package com.comtrade.sistemskaOperacija.korisnik;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Korisnik;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.TransferKlasa;
import com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija;

import java.util.ArrayList;
import java.util.List;

public class SistemskaOperacijaKorisnikLogin extends OpstaSistemskaOperacija {
    @Override
    public void IzvrsiKonkretnuOperaciju(TransferKlasa transferKlasa) {

        Korisnik korisnik = (Korisnik) transferKlasa.getRequest();
        Broker broker = new Broker();
        transferKlasa.setResponse(vratiPodatke(broker.vratiPodatke(korisnik), korisnik.getUsername(), korisnik.getPassword()));
    }
    public int vratiPodatke(List<OpstiDomen> listaOpstiDomen, String username, String password) {
        int status = 0;
        List<Korisnik>listaKorisnika = new ArrayList<>();
        for (OpstiDomen opsd: listaOpstiDomen) {
            listaKorisnika.add((Korisnik) opsd);
        }
        for (Korisnik korisnik: listaKorisnika             ) {
            if (korisnik.getUsername().equalsIgnoreCase(username)&& korisnik.getPassword().equalsIgnoreCase(password))
            status = korisnik.getStatus();
        }
        return status;
    }
}
