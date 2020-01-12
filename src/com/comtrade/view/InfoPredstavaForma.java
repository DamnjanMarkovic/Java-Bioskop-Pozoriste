package com.comtrade.view;

import com.comtrade.domen.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class InfoPredstavaForma extends JFrame {
    private JPanel jPanel;
    private JTable table1;
    private JScrollPane jScrollPane;
    private JLabel lblPozoriste;
    private JLabel lblPredstava;
    private DefaultTableModel dtm = new DefaultTableModel();


    public InfoPredstavaForma(int idPozorista, int idPredstave) {

        add(jPanel);
        setBounds(200, 200, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("INFORMACIJE O PREDSTAVI");

        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        postaviKolone();
        postaviPodatkeULabele(idPozorista, idPredstave);
        postaviPodatkeUTabelu(idPredstave);


    }

    private void postaviPodatkeULabele(int idPozorista, int idPredstave) {
        Pozoriste pozoriste = new Pozoriste();
        String nazivPozorista = (String) TransferKlasa.transferKlasa(pozoriste, idPozorista, KonstanteFK.POZORISTE, KonstanteKPL.VRATI_NAZIV_POZORISTA).getResponse();
        lblPozoriste.setText(nazivPozorista);
        Predstava predstava = new Predstava();
        String nazivPredstave = (String) TransferKlasa.transferKlasa(predstava, idPredstave, KonstanteFK.PREDSTAVE, KonstanteKPL.VRATI_NAZIV_PREDSTAVE).getResponse();
        lblPredstava.setText(nazivPredstave);
    }

    private void postaviKolone() {

        Object[] kolone = {"ime glumca", "prezime glumca"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
    }

    private void postaviPodatkeUTabelu(int idPredstave) {

        dtm.setRowCount(0);
        Glumac glumac = new Glumac();
        List<Integer> listaIDGlumacauPredstavi = (List<Integer>) TransferKlasa.transferKlasa(glumac, idPredstave, KonstanteFK.GLUMAC, KonstanteKPL.VRATI_GLUMCE_U_PREDSTAVI).getResponse();
        glumac = new Glumac();
        List<Glumac> listaGlumaca = (List<Glumac>) TransferKlasa.transferKlasa(glumac, 0, KonstanteFK.GLUMAC, KonstanteKPL.VRATI_PODATKE_GLUMAC).getResponse();


        for (Glumac glumac1 : listaGlumaca) {
            for (Integer idGlumca : listaIDGlumacauPredstavi) {
                if (idGlumca == glumac1.getIdGlumca()) {
                    Object[] red = {glumac1.getIme(), glumac1.getPrezime()};
                    dtm.addRow(red);
                }
            }
        }


    }
}
