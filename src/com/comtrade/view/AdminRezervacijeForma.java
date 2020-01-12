package com.comtrade.view;

import com.comtrade.domen.*;
import com.comtrade.view.proxy.Proxy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AdminRezervacijeForma extends JFrame implements Proxy {
    private JPanel jPanel;
    private JComboBox cbPozoriste;
    private JComboBox cbPredstava;
    private JComboBox cbDatum;
    private JTable table1;
    private JComboBox cbVreme;
    private JScrollPane jScrollPane;
    private DefaultTableModel dtm = new DefaultTableModel();
    private int idPozorista;
    private int idPredstave;


    public AdminRezervacijeForma(){


        add(jPanel);
        setBounds(200, 200, 700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ADMIN REZERVACIJE FORMA");
        unosPozoristaUCB();
        unosKolonaUTabelu();
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);


        cbPozoriste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbPredstava.removeAllItems();
                unosPredstavaUCB();
            }
        });
        cbPredstava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbDatum.removeAllItems();
                unosTerminaDatumUCB();
            }
        });
        cbDatum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbVreme.removeAllItems();
                    if (cbDatum.getSelectedItem() !=null){
                        unosTerminaVremeUCB  ((Date) cbDatum.getSelectedItem());
                    }
            }
        });
        cbVreme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                postaviPodatkeUTabelu();
            }
        });
    }
    private void unosPozoristaUCB() {
        Pozoriste pozoriste = new Pozoriste();
        List<String> imenaPozorista = (List<String>) TransferKlasa.transferKlasa(pozoriste, 0, KonstanteFK.POZORISTE, KonstanteKPL.IMENA_POZORISTA).getResponse();
        for (String str: imenaPozorista             ) {
            cbPozoriste.addItem(str);
        }
    }
    private void unosPredstavaUCB() {
        String nazivPozorista = cbPozoriste.getSelectedItem().toString();
        Pozoriste pozoriste = new Pozoriste();
        pozoriste.setNazivPozorista(nazivPozorista);
        idPozorista = (int) TransferKlasa.transferKlasa(pozoriste, 0, KonstanteFK.POZORISTE, KonstanteKPL.VRATI_ID_POZORISTA).getResponse();
        Predstava predstava = new Predstava();
        List<Predstava> listaPredstava = (List<Predstava>) TransferKlasa.transferKlasa(predstava, idPozorista, KonstanteFK.PREDSTAVE, KonstanteKPL.VRATI_PODATKE_PREDSTAVA).getResponse();
        for (Predstava predstava1: listaPredstava             ) {
            cbPredstava.addItem(predstava1.getNazivPredstave());
        }
    }
    private void unosTerminaDatumUCB() {
        if (cbPredstava.getSelectedItem() != null) {
            String nazivPredstave = cbPredstava.getSelectedItem().toString();
            Predstava predstava = new Predstava();
            predstava.setNazivPredstave(nazivPredstave);
            idPredstave = (int) TransferKlasa.transferKlasa(predstava, 0, KonstanteFK.PREDSTAVE, KonstanteKPL.VRATI_ID_PREDSTAVE).getResponse();
            Termin termin = new Termin();
            List<Date>listaDatuma = (List<Date>) TransferKlasa.transferKlasa(termin, idPredstave, KonstanteFK.TERMINI, KonstanteKPL.VRATI_PODATKE_TERMINI).getResponse();
            for (Date datumi:listaDatuma ) {
                cbDatum.addItem(datumi);
            }
        }
    }
    private void unosTerminaVremeUCB(Date cbTerminDatum) {
        Termin termin = new Termin();
        termin.setDatum((java.sql.Date) cbTerminDatum);
        List<String> listaVremena = (List<String>) TransferKlasa.transferKlasa(termin, idPredstave, KonstanteFK.TERMINI, KonstanteKPL.VRATI_PODATKE_TERMINI_VREMENA).getResponse();
        for (String vremena:listaVremena             ) {
            cbVreme.addItem(vremena);
        }
    }

    private void postaviPodatkeUTabelu() {
        dtm.setRowCount(0);
        Rezervacija rezervacija = new Rezervacija();
        List<Rezervacija> listaRezervacija = (List<Rezervacija>) TransferKlasa.transferKlasa(rezervacija, vratiidTermina(), KonstanteFK.REZERVACIJA, KonstanteKPL.REZERVACIJA_PREGLED_REZERVACIJA).getResponse();

        for (Rezervacija rez: listaRezervacija             ) {
            Korisnik korisnik = new Korisnik();
            String imeKorisnika = (String) TransferKlasa.transferKlasa(korisnik, rez.getIdKorisnika(), KonstanteFK.KORISNIK, KonstanteKPL.VRATI_IME_KORISNIKA).getResponse();
            Object [] red = {imeKorisnika, rez.getBrojUlaznica()};
            dtm.addRow(red);
        }
    }

    private void unosKolonaUTabelu() {

        Object[] kolone ={"ime korisnika", "broj rezervisanih ulaznica"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);

    }
    private int vratiidTermina() {
        int idTermina=0;
        if (cbVreme.getSelectedItem()!=null && cbDatum.getSelectedItem() !=null &&
                cbPredstava.getSelectedItem()!=null){
            Date datum = (Date) cbDatum.getSelectedItem();
            String vreme = cbVreme.getSelectedItem().toString();
            Termin termin = new Termin();
            termin.setDatum((java.sql.Date) datum);
            termin.setVreme(vreme);
            termin.setIdPredstave(idPredstave);
            idTermina = (int) TransferKlasa.transferKlasa(termin, 0, KonstanteFK.TERMINI, KonstanteKPL.VRATI_ID_TERMINA).getResponse();
        }return idTermina;
    }

    @Override
    public void login(Korisnik korisnik) {

    }
}
