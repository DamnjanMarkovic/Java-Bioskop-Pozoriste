package com.comtrade.view;

import com.comtrade.domen.*;
import com.comtrade.view.proxy.Proxy;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class RezervacijaForma extends JFrame implements Proxy {
    private JPanel jPanel;
    private JComboBox cbPozoriste;
    private JComboBox cbTerminDatum;
    private JComboBox cbPredstava;
    private JTextField tfBrojKarata;
    private JLabel lblBrojKarata;
    private JComboBox cbTerminVreme;
    private JButton REZERVISIButton;
    private JLabel lblKorisnik;
    private JButton INFOOPREDSTAVIButton;

    private int idPozorista;
    private int idPredstave;
    private String datum;
    private String vreme;



    public RezervacijaForma(Korisnik korisnik) {

        add(jPanel);
        setBounds(200, 200, 700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("REZERVACIJA FORMA");
        unosPozoristaUCB();
        lblKorisnik.setText(korisnik.getUsername());



        cbPozoriste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbPredstava.removeAllItems();
                String nazivPozorista = cbPozoriste.getSelectedItem().toString();
                Pozoriste pozoriste = new Pozoriste();
                pozoriste.setNazivPozorista(nazivPozorista);
                idPozorista = (int) TransferKlasa.transferKlasa(pozoriste, 0, KonstanteFK.POZORISTE, KonstanteKPL.VRATI_ID_POZORISTA).getResponse();
                unosPredstavaUCB();
            }
        });

        cbPredstava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                cbTerminDatum.removeAllItems();
                if (cbPredstava.getSelectedItem() != null) {
                    String nazivPredstave = cbPredstava.getSelectedItem().toString();
                    Predstava predstava = new Predstava();
                    predstava.setNazivPredstave(nazivPredstave);
                    idPredstave = (int) TransferKlasa.transferKlasa(predstava, 0, KonstanteFK.PREDSTAVE, KonstanteKPL.VRATI_ID_PREDSTAVE).getResponse();
                    unosTerminaDatumUCB(idPredstave);
                }
            }
        });


        cbTerminDatum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbTerminVreme.removeAllItems();
                if (cbTerminDatum.getSelectedItem() !=null){
                    unosTerminaVremeUCB();
                }
            }
        });
        cbTerminVreme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                postaviBrojKarata();
            }
        });
        REZERVISIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Integer.parseInt(lblBrojKarata.getText()) - Integer.parseInt(tfBrojKarata.getText())>0 &&
                Integer.parseInt(tfBrojKarata.getText())>0) {
                    try {
                        int idKorisnika = vratiIDKorisnika(korisnik.getUsername(), korisnik.getPassword());
                        int idTermina = vratiidTermina();
                        Rezervacija rezervacija = new Rezervacija(Integer.parseInt(tfBrojKarata.getText()), idKorisnika, idTermina);
                        TransferKlasa.transferKlasa(rezervacija, 0, KonstanteFK.REZERVACIJA, KonstanteKPL.UNESI_REZERVACIJE);
                        izbrisiFildove();
                    }catch(Exception e){
                    }JOptionPane.showMessageDialog(null, "Izvrsena rezervacija");
                }else
                    JOptionPane.showMessageDialog(null, "Ne mozete izvrsiti rezervaciju.");
            }
        });

        INFOOPREDSTAVIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                InfoPredstavaForma infoPredstavaForma = new InfoPredstavaForma(idPozorista, idPredstave);
                infoPredstavaForma.setVisible(true);
            }
        });
    }

    private void izbrisiFildove() {
        tfBrojKarata.setText("");
        cbPredstava.removeAllItems();
        cbTerminDatum.removeAllItems();
        cbTerminVreme.removeAllItems();
    }

    private int vratiIDKorisnika(String username,String password) {

        Korisnik korisnik = new Korisnik(username, password);
        int idKorisnika = (int) TransferKlasa.transferKlasa(korisnik, 0, KonstanteFK.KORISNIK, KonstanteKPL.VRATI_ID_KORISNIK).getResponse();
        return idKorisnika;
    }

    private int vratiidTermina() {
        int idTermina=0;
        if (cbTerminVreme.getSelectedItem()!=null && cbTerminDatum.getSelectedItem() !=null &&
                cbPredstava.getSelectedItem()!=null){
            Date datum = (Date) cbTerminDatum.getSelectedItem();
            String vreme = cbTerminVreme.getSelectedItem().toString();
            Termin termin = new Termin();
            termin.setDatum((java.sql.Date) datum);
            termin.setVreme(vreme);
            termin.setIdPredstave(idPredstave);
            idTermina = (int) TransferKlasa.transferKlasa(termin, 0, KonstanteFK.TERMINI, KonstanteKPL.VRATI_ID_TERMINA).getResponse();
        }return idTermina;
    }


    private void postaviBrojKarata() {

        if (cbTerminVreme.getSelectedItem()!=null && cbTerminDatum.getSelectedItem() !=null &&
                cbPredstava.getSelectedItem()!=null){

            int idTermina = vratiidTermina();
            Termin termin = new Termin();
            termin.setIdTermina(idTermina);
            int brojUlaznica = (int) TransferKlasa.transferKlasa(termin, 0, KonstanteFK.REZERVACIJA, KonstanteKPL.REZERVACIJA_BROJ_ULAZNICA).getResponse();
            lblBrojKarata.setText(String.valueOf(brojUlaznica));
        }
    }

    private void unosTerminaDatumUCB(int idPredstave) {

        Termin termin = new Termin();
        List<Date> listaDatuma = (List<Date>) TransferKlasa.transferKlasa(termin, idPredstave, KonstanteFK.TERMINI, KonstanteKPL.VRATI_PODATKE_TERMINI).getResponse();
        for (Date datumi:listaDatuma             ) {
            cbTerminDatum.addItem(datumi);
        }
    }

    private void unosTerminaVremeUCB() {
        Termin termin = new Termin();
        termin.setDatum(java.sql.Date.valueOf(cbTerminDatum.getSelectedItem().toString()));
        List<String> listaVremena = (List<String>) TransferKlasa.transferKlasa(termin, idPredstave, KonstanteFK.TERMINI, KonstanteKPL.VRATI_PODATKE_TERMINI_VREMENA).getResponse();
        for (String vremena:listaVremena             ) {
            cbTerminVreme.addItem(vremena);
        }
    }

    private void unosPredstavaUCB() {
        Predstava predstava = new Predstava();
        List<Predstava> listaPredstava = (List<Predstava>) TransferKlasa.transferKlasa(predstava, idPozorista, KonstanteFK.PREDSTAVE, KonstanteKPL.VRATI_PODATKE_PREDSTAVA).getResponse();
        for (Predstava predstava1: listaPredstava             ) {
            cbPredstava.addItem(predstava1.getNazivPredstave());
        }
    }
    private void unosPozoristaUCB() {
            Pozoriste pozoriste = new Pozoriste();
            List<String> imenaPozorista = (List<String>) TransferKlasa.transferKlasa(pozoriste, 0, KonstanteFK.POZORISTE, KonstanteKPL.IMENA_POZORISTA).getResponse();
            for (String str: imenaPozorista             ) {
                cbPozoriste.addItem(str);
            }
    }
    @Override
    public void login(Korisnik korisnik) {
    }
}
