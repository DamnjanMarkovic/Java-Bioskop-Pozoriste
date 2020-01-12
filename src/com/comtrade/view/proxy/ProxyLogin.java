package com.comtrade.view.proxy;

import com.comtrade.domen.KonstanteFK;
import com.comtrade.domen.KonstanteKPL;
import com.comtrade.domen.Korisnik;
import com.comtrade.domen.TransferKlasa;
import com.comtrade.view.AdminForma;
import com.comtrade.view.RezervacijaForma;

import javax.swing.*;

public class ProxyLogin implements Proxy{





    @Override
    public void login(Korisnik korisnik) {

        int status = (int) TransferKlasa.transferKlasa(korisnik, 0, KonstanteFK.KORISNIK, KonstanteKPL.LOGOVANJE).getResponse();

        if (status==1){
            AdminForma adminForma = new AdminForma();
            adminForma.login(korisnik);
        }else if (status ==2){
            RezervacijaForma rezervacijaForma = new RezervacijaForma(korisnik);
            rezervacijaForma.setVisible(true);
        }else {
            JOptionPane.showMessageDialog(null, "Niste registrovani korisnik.");
        }
    }
}
