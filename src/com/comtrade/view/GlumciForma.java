package com.comtrade.view;

import com.comtrade.domen.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class GlumciForma extends JFrame{
    private JPanel jPanel;
    private JComboBox cbPozoriste;
    private JComboBox cbPredstava;
    private JTable table1;
    private JScrollPane jScrollPane;
    private JTextField tfImeGlumca;
    private JTextField tfPrezimeGlumca;
    private JButton UNESIButton;
    private JButton OBRISIButton;
    private JButton IZMENIButton;
    private JButton SVIGLUMCIButton;
    private JTable table2;
    private JScrollPane jScrollPane2;
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private int idPozorista;
    private int idPredstave;
    private int idGlumca;


    public GlumciForma(){

        add(jPanel);
        setBounds(200, 200, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("GLUMCI FORMA");
        unosPozoristaUCB();
        table1 = new JTable(dtm);
        table2=new JTable(dtm2);
        jScrollPane.setViewportView(table1);
        jScrollPane2.setViewportView(table2);
        postaviKolone2();
        postaviKolone();


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table1.getSelectedRow();
                tfImeGlumca.setText(table1.getModel().getValueAt(red, 0).toString());
                tfPrezimeGlumca.setText(table1.getModel().getValueAt(red, 1).toString());

            }
        });

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
                if (cbPredstava.getSelectedItem() != null) {
                    String nazivPredstave = cbPredstava.getSelectedItem().toString();
                    Predstava predstava = new Predstava();
                    predstava.setNazivPredstave(nazivPredstave);
                    idPredstave = (int) TransferKlasa.transferKlasa(predstava, 0, KonstanteFK.PREDSTAVE, KonstanteKPL.VRATI_ID_PREDSTAVE).getResponse();
                    postaviPodatkeUTabelu(idPredstave);
                }
            }
        });
        UNESIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String ime= tfImeGlumca.getText();
                String prezime = tfPrezimeGlumca.getText();
                Glumac glumac = new Glumac(ime, prezime);

                TransferKlasa.transferKlasa(glumac, 0, KonstanteFK.GLUMAC, KonstanteKPL.UPISI_GLUMCA);
                idGlumca= (int) TransferKlasa.transferKlasa(glumac, 0, KonstanteFK.GLUMAC, KonstanteKPL.VRATI_ID_GLUMCA).getResponse();
                Glumac_predstava glumac_predstava = new Glumac_predstava(idPredstave, idGlumca);
                TransferKlasa.transferKlasa(glumac_predstava, 0, KonstanteFK.GLUMAC, KonstanteKPL.UPIS_GLUMAC_PREDSTAVA);
            }
        });

        OBRISIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try  {
                    Glumac glumac = new Glumac();
                    glumac.setIdGlumca(idGlumca);
                    TransferKlasa.transferKlasa(glumac, 0, KonstanteFK.GLUMAC, KonstanteKPL.OBRISI_GLUMCA);
                    izbrisiFildove();
                    postaviPodatkeUTabelu2();

                }   catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table2.getSelectedRow();
                idGlumca= Integer.parseInt(table2.getModel().getValueAt(red, 0).toString());
            }
        });
        SVIGLUMCIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                postaviPodatkeUTabelu2();
            }
        });
    }

    private void postaviKolone() {
        Object[]kolone = {"ime glumca", "prezime glumca"};
            dtm.addColumn(kolone[0]);
            dtm.addColumn(kolone[1]);
    }

    private void izbrisiFildove() {
        tfImeGlumca.setText("");
        tfPrezimeGlumca.setText("");
    }

    private void postaviPodatkeUTabelu(int idPredstave) {

        dtm.setRowCount(0);
        Glumac glumac = new Glumac();
        List<Integer>listaIDGlumacauPredstavi = (List<Integer>) TransferKlasa.transferKlasa(glumac, idPredstave, KonstanteFK.GLUMAC, KonstanteKPL.VRATI_GLUMCE_U_PREDSTAVI).getResponse();
        glumac = new Glumac();
        List<Glumac> listaGlumaca = (List<Glumac>) TransferKlasa.transferKlasa(glumac, 0, KonstanteFK.GLUMAC, KonstanteKPL.VRATI_PODATKE_GLUMAC).getResponse();


        for (Glumac glumac1: listaGlumaca) {
            for (Integer idGlumca:listaIDGlumacauPredstavi                 ) {
                if (idGlumca==glumac1.getIdGlumca()){
                    Object [] red = {glumac1.getIme(), glumac1.getPrezime()};
                    dtm.addRow(red);
                }
            }
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

    private void postaviKolone2() {
        Object[]kolone = {"idGlumca", "ime glumca", "prezime glumca"};
        dtm2.addColumn(kolone[0]);
        dtm2.addColumn(kolone[1]);
        dtm2.addColumn(kolone[2]);
    }


    private void postaviPodatkeUTabelu2() {
        dtm2.setRowCount(0);
        Glumac glumac = new Glumac();
        List<Glumac> listaGlumaca = (List<Glumac>) TransferKlasa.transferKlasa(glumac, 0, KonstanteFK.GLUMAC, KonstanteKPL.VRATI_PODATKE_GLUMAC).getResponse();
        for (Glumac glumac1: listaGlumaca) {
            Object [] red = {glumac1.getIdGlumca(), glumac1.getIme(), glumac1.getPrezime()};
            dtm2.addRow(red);
        }
    }
}
