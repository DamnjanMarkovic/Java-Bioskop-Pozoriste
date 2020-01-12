package com.comtrade.view;

import com.comtrade.domen.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.util.List;

public class PredstavaForma extends JFrame{
    private JPanel jPanel;
    private JComboBox cbIzaberiPozoriste;
    private JTextField tfPredstava;
    private JButton UNESIPREDSTAVUButton;
    private JButton IZMENIPREDSTAVUButton;
    private JButton IZBRISIPREDSTAVUButton;
    private JTable table1;
    private JScrollPane jScrollPane;
    private JLabel lblidPozorista;
    private JLabel lblIDPozorista;
    private DefaultTableModel dtm = new DefaultTableModel();
    private int idPozorista;
    private int idPredstave;

    public PredstavaForma(){

        add(jPanel);
        setBounds(200,200,600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("PREDSTAVE FORMA");
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        unesiPozoristaUCombo();
        postaviKolone();



        cbIzaberiPozoriste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    dtm.setRowCount(0);
                    String nazivPozorista = cbIzaberiPozoriste.getSelectedItem().toString();
                    Pozoriste pozoriste = new Pozoriste();
                    pozoriste.setNazivPozorista(nazivPozorista);
                    idPozorista = (int) TransferKlasa.transferKlasa(pozoriste, 0, KonstanteFK.POZORISTE,KonstanteKPL.VRATI_ID_POZORISTA).getResponse();
                    postaviPodatkeUTabelu();
            }
        });
        UNESIPREDSTAVUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String nazivPredstave = tfPredstava.getText();
                Predstava predstava = new Predstava();
                predstava.setNazivPredstave(nazivPredstave);
                predstava.setIdPozorista(idPozorista);
                TransferKlasa.transferKlasa(predstava, 0, KonstanteFK.PREDSTAVE, KonstanteKPL.UNOS_PODATAKA_PREDSTAVA);
                postaviPodatkeUTabelu();
                izbrisiFildove();
            }
        });
        IZBRISIPREDSTAVUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try  {
                Predstava predstava = new Predstava();
                predstava.setIdPredstave(idPredstave);
                TransferKlasa.transferKlasa(predstava, 0, KonstanteFK.PREDSTAVE, KonstanteKPL.OBRISI_PREDSTAVU);
                postaviPodatkeUTabelu();
                izbrisiFildove();
                }   catch(Exception e){
                e.printStackTrace();
                }

            }
        });


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table1.getSelectedRow();
                idPredstave = Integer.parseInt(table1.getModel().getValueAt(red, 0).toString());
                tfPredstava.setText(table1.getModel().getValueAt(red, 1).toString());
            }
        });
        IZMENIPREDSTAVUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                    String nazivPredstave = tfPredstava.getText();
                    Predstava predstava = new Predstava(idPredstave, nazivPredstave, idPozorista);
                    TransferKlasa.transferKlasa(predstava, 0, KonstanteFK.PREDSTAVE, KonstanteKPL.IZMENA_IMENA_PREDSTAVE);
                    postaviPodatkeUTabelu();
                    izbrisiFildove();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "ne mozete izbrisati predstavu koja ima zakazane termine!");
                }
            }
        });
    }
    private void postaviPodatkeUTabelu() {
            dtm.setRowCount(0);
            Predstava predstava = new Predstava();
            List<Predstava> listaPredstava = (List<Predstava>) TransferKlasa.transferKlasa(predstava, idPozorista, KonstanteFK.PREDSTAVE, KonstanteKPL.VRATI_PODATKE_PREDSTAVA).getResponse();
            for (Predstava predstava1: listaPredstava) {
                Object [] red = {predstava1.getIdPredstave(), predstava1.getNazivPredstave(), predstava1.getIdPozorista()};
                dtm.addRow(red);
            }
    }
    private void postaviKolone() {
        Object[]kolone = {"idPredstave", "nazivPredstave", "idPozorista"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
        dtm.addColumn(kolone[2]);

    }
    private void unesiPozoristaUCombo() {
        Pozoriste pozoriste = new Pozoriste();
        List<String> imenaPozorista = (List<String>) TransferKlasa.transferKlasa(pozoriste, 0, KonstanteFK.POZORISTE, KonstanteKPL.IMENA_POZORISTA).getResponse();
        for (String str: imenaPozorista             ) {
            cbIzaberiPozoriste.addItem(str);
        }
    }

    private void izbrisiFildove() {
        tfPredstava.setText("");
    }
}
