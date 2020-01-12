package com.comtrade.view;

import com.comtrade.domen.*;
import com.comtrade.view.proxy.Proxy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminForma extends JFrame implements Proxy {
    private JPanel jPanel;
    private JComboBox cbPozoriste;
    private JComboBox cbPredstava;
    private JTable table1;
    private JButton NOVIKORISNIKButton;
    private JButton POZORISTAButton;
    private JButton PREDSTAVEButton;
    private JButton TERMINIButton;
    private JButton GLUMCIButton;
    private JScrollPane jScrollPane;
    private JButton REZERVACIJEButton;
    private DefaultTableModel dtm = new DefaultTableModel();
    private JLabel lblKorisnik;
    private int idPozorista;
    private int idPredstave;



    public AdminForma (){

        add(jPanel);
        setBounds(200, 200, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ADMIN FORMA");
        unosPozoristaUCB();
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        postaviKolone();



        NOVIKORISNIKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NoviKorisnikForma noviKorisnikForma = new NoviKorisnikForma();
                noviKorisnikForma.setVisible(true);
            }
        });
        POZORISTAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PozoristeForma pozoristeForma = new PozoristeForma();
                pozoristeForma.setVisible(true);
            }
        });
        PREDSTAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PredstavaForma predstavaForma = new PredstavaForma();
                predstavaForma.setVisible(true);
            }
        });
        TERMINIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TerminiForma terminiForma = new TerminiForma();
                terminiForma.setVisible(true);
            }
        });
        GLUMCIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            GlumciForma glumciForma = new GlumciForma();
            glumciForma.setVisible(true);
            }
        });
        cbPozoriste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbPredstava.removeAllItems();
                String nazivPozorista = cbPozoriste.getSelectedItem().toString();
                Pozoriste pozoriste = new Pozoriste();
                Predstava predstava = new Predstava();
                pozoriste.setNazivPozorista(nazivPozorista);
                idPozorista = (int) TransferKlasa.transferKlasa(pozoriste, 0, KonstanteFK.POZORISTE, KonstanteKPL.VRATI_ID_POZORISTA).getResponse();

                List<Predstava> listaPredstava = (List<Predstava>) TransferKlasa.transferKlasa(predstava, idPozorista, KonstanteFK.PREDSTAVE, KonstanteKPL.VRATI_PODATKE_PREDSTAVA).getResponse();
                for (Predstava predstava1: listaPredstava ) {
                        cbPredstava.addItem(predstava1.getNazivPredstave());
                }
            }
        });
        cbPredstava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (cbPredstava.getSelectedItem() != null) {
                    String nazivPredstave = cbPredstava.getSelectedItem().toString();
                    Predstava predstava = new Predstava();
                    dtm.setRowCount(0);
                    Termin termin = new Termin();
                    predstava.setNazivPredstave(nazivPredstave);
                    idPredstave = (int) TransferKlasa.transferKlasa(predstava, 0, KonstanteFK.PREDSTAVE, KonstanteKPL.VRATI_ID_PREDSTAVE).getResponse();

                    Map<Termin, Integer> hmList = (Map<Termin, Integer>) TransferKlasa.transferKlasa(termin, idPredstave, KonstanteFK.TERMINI, KonstanteKPL.VRATI_PODATKE_ULAZNA_STRANA).getResponse();
                    for (Map.Entry<Termin, Integer> hash: hmList.entrySet()) {
                            Object [] red = {hash.getKey().getDatum(), hash.getKey().getVreme(),
                                    hash.getKey().getbrojUlaznica(), hash.getValue()};
                            dtm.addRow(red);
                    }
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table1.getSelectedRow();
            }
        });
        REZERVACIJEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AdminRezervacijeForma adminRezervacijeForma = new AdminRezervacijeForma();
                adminRezervacijeForma.setVisible(true);
            }
        });
    }

        private void postaviKolone() {
        Object[] kolone = {"Datum", "Vreme", "Ukupno ulaznica", "Rezervisano ulaznica" };
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
        dtm.addColumn(kolone[2]);
        dtm.addColumn(kolone[3]);
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
        setVisible(true);
    }
}
