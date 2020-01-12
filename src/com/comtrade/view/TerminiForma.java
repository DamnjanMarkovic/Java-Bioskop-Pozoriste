package com.comtrade.view;

import com.comtrade.domen.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class TerminiForma extends JFrame{
    private JPanel jPanel;
    private JComboBox cbPredstava;

    private JTextField tfBrojKarata;
    private JTable table1;
    private JScrollPane jScrollPane;
    private JButton UNESITERMINButton;
    private JButton IZBRISITERMINButton;
    private JButton PROMENITERMINButton;

    private JComboBox cbVremeM;
    private JComboBox cbVremeSati;
    private JComboBox cbGodina;
    private JComboBox cbMesec;
    private JComboBox cbDan;
    private DefaultTableModel dtm = new DefaultTableModel();
    private int idTermina;
    private int idPredstave;


    public TerminiForma (){
        add(jPanel);
        setBounds(200,200,800,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("TERMINI FORMA");
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        unesiPredstaveUCombo();
        postaviKolone();
        postaviDatumIVremeUCombo();



        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table1.getSelectedRow();
                idTermina = Integer.parseInt(table1.getModel().getValueAt(red, 0).toString());
            }
        });
        cbPredstava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                dtm.setRowCount(0);
                String nazivPredstave = cbPredstava.getSelectedItem().toString();
                Predstava predstava = new Predstava();
                predstava.setNazivPredstave(nazivPredstave);
                idPredstave = (int) TransferKlasa.transferKlasa(predstava, 0, KonstanteFK.PREDSTAVE,KonstanteKPL.VRATI_ID_PREDSTAVE).getResponse();

                Termin termin = new Termin();
                List<Termin> listaTermina = (List<Termin>) TransferKlasa.transferKlasa(termin, idPredstave, KonstanteFK.TERMINI, KonstanteKPL.VRATI_PODATKE_TERMINI_TERMINI).getResponse();
                for (Termin termin1: listaTermina) {
                    Object [] red = {termin1.getIdTermina(), termin1.getVreme(), termin1.getDatum(), termin1.getbrojUlaznica(), termin1.getIdPredstave()};
                    dtm.addRow(red);
                }
            }
        });
        UNESITERMINButton.addActionListener(new ActionListener() {

            String vreme =null;
            String dateString=null;
            public void actionPerformed(ActionEvent actionEvent) {
                if (cbVremeSati.getSelectedItem().toString()!=null && cbVremeM.getSelectedItem().toString()!=null && cbGodina.getSelectedItem().toString() !=null
                        && cbMesec.getSelectedItem().toString()!=null && cbDan.getSelectedItem().toString()!=null){

                     vreme = cbVremeSati.getSelectedItem().toString() + ":"+cbVremeM.getSelectedItem().toString() + ":00";
                     dateString = cbGodina.getSelectedItem().toString() + "-"+cbMesec.getSelectedItem().toString() + "-" + cbDan.getSelectedItem().toString();
                }

                java.sql.Date datum = java.sql.Date.valueOf( dateString );

                int ukupanBrojKarata = Integer.parseInt(tfBrojKarata.getText());
                Termin termin = new Termin(vreme, datum, ukupanBrojKarata, idPredstave);
                TransferKlasa.transferKlasa(termin, 0, KonstanteFK.TERMINI, KonstanteKPL.UNOS_PODATAKA_TERMINI);
                postaviPodatkeUTabelu();
                izbrisiFildove();
            }
        });
        IZBRISITERMINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Termin termin = new Termin();
                termin.setIdTermina(idTermina);
                TransferKlasa.transferKlasa(termin, 0, KonstanteFK.TERMINI, KonstanteKPL.IZBRISI_TERMIN);
                postaviPodatkeUTabelu();
                izbrisiFildove();

            }
        });
    }

    private void postaviDatumIVremeUCombo() {

        String[] listaSati = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
                "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        for (String str: listaSati             ) {
            cbVremeSati.addItem(str);
        }
        String[] listaMinuta = new String[] {"00", "30"};
        for (String str: listaMinuta             ) {
            cbVremeM.addItem(str);
        }
        String[] listaGodina = new String[] {"2020", "2021", "2022"};
        for (String str: listaGodina             ) {
            cbGodina.addItem(str);
        }
        String[] listaMeseci = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        for (String str: listaMeseci             ) {
            cbMesec.addItem(str);
        }
        String[] listaDana = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
                "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        for (String str: listaDana             ) {
            cbDan.addItem(str);
        }

    }


    private void postaviPodatkeUTabelu() {
        dtm.setRowCount(0);
        Termin termin = new Termin();
        List<Termin> listaTermina = (List<Termin>) TransferKlasa.transferKlasa(termin, idPredstave, KonstanteFK.TERMINI, KonstanteKPL.VRATI_PODATKE_TERMINI).getResponse();
        for (Termin termin1: listaTermina) {
            Object [] red = {termin1.getIdTermina(), termin1.getVreme(), termin1.getDatum(), termin1.getbrojUlaznica(), termin1.getIdPredstave()};
            dtm.addRow(red);
        }
    }

    private void postaviKolone() {

        Object[]kolone = {"idTermina", "vreme", "datum", "brojUlaznica", "idPredstave"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
        dtm.addColumn(kolone[2]);
        dtm.addColumn(kolone[3]);
        dtm.addColumn(kolone[4]);

    }

    private void unesiPredstaveUCombo() {
        Predstava predstava = new Predstava();
        List<String> imenaPredstava = (List<String>) TransferKlasa.transferKlasa(predstava, 0, KonstanteFK.PREDSTAVE, KonstanteKPL.IMENA_PREDSTAVA).getResponse();
        for (String str: imenaPredstava             ) {
            cbPredstava.addItem(str);
        }
    }
    private void izbrisiFildove() {
        tfBrojKarata.setText("");

    }
}
