package com.comtrade.view;

import com.comtrade.domen.KonstanteFK;
import com.comtrade.domen.KonstanteKPL;
import com.comtrade.domen.Pozoriste;
import com.comtrade.domen.TransferKlasa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PozoristeForma extends JFrame{
    private JPanel jPanel;
    private JTextField tfNaziv;
    private JTextField tfUlica;
    private JTextField tfBroj;
    private JTextField tfgrad;
    private JButton UNESIButton;
    private JButton IZMENIButton;
    private JButton IZBRISIButton;
    private JTable table1;
    private JScrollPane jScrollPane;
    private DefaultTableModel dtm = new DefaultTableModel();
    private int idPozorista;

    public PozoristeForma(){

        add(jPanel);
        setBounds(200,200,600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("POZORISTE FORMA");
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        postaviKolone();
        postaviPodatkeUTabelu();

        UNESIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String nazivPozorista = tfNaziv.getText();
                String ulica = tfUlica.getText();
                int broj = Integer.parseInt(tfBroj.getText());
                String grad = tfgrad.getText();
                Pozoriste pozoriste = new Pozoriste(nazivPozorista, ulica, broj, grad);
                TransferKlasa.transferKlasa(pozoriste, 0, KonstanteFK.POZORISTE, KonstanteKPL.UNOS_PODATAKA);
                postaviPodatkeUTabelu();
                izbrisiFildove();
            }
        });




        IZBRISIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Pozoriste pozoriste = new Pozoriste();
                pozoriste.setIdPozorista(idPozorista);
                TransferKlasa.transferKlasa(pozoriste, 0, KonstanteFK.POZORISTE, KonstanteKPL.OBRISI_POZORISTE);
                postaviPodatkeUTabelu();
                izbrisiFildove();
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table1.getSelectedRow();
                idPozorista = Integer.parseInt(table1.getModel().getValueAt(red, 0).toString());
                tfNaziv.setText(table1.getModel().getValueAt(red, 1).toString());
                tfUlica.setText(table1.getModel().getValueAt(red, 2).toString());
                tfBroj.setText(String.valueOf(Integer.parseInt(table1.getModel().getValueAt(red, 3).toString())));
                tfgrad.setText(table1.getModel().getValueAt(red, 4).toString());
            }
        });
        IZMENIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String nazivPozorista = tfNaziv.getText();
                String ulica = tfUlica.getText();
                int broj = Integer.parseInt(tfBroj.getText());
                String grad = tfgrad.getText();
                Pozoriste pozoriste = new Pozoriste(idPozorista, nazivPozorista, ulica, broj, grad);
                TransferKlasa.transferKlasa(pozoriste, 0, KonstanteFK.POZORISTE, KonstanteKPL.IZMENA_PODATAKA_O_POZORISTU);
                postaviPodatkeUTabelu();
                izbrisiFildove();
            }
        });

    }

    private void postaviPodatkeUTabelu() {
        dtm.setRowCount(0);
        Pozoriste pozoriste = new Pozoriste();
        List<Pozoriste> listaPozorista = (List<Pozoriste>) TransferKlasa.transferKlasa(pozoriste, 0, KonstanteFK.POZORISTE, KonstanteKPL.VRATI_PODATKE).getResponse();
        for (Pozoriste pozor: listaPozorista) {
            Object [] red = {pozor.getIdPozorista(), pozor.getNazivPozorista(), pozor.getUlica(), (pozor.getBroj()), pozor.getGrad()};
            dtm.addRow(red);
        }
    }

    private void izbrisiFildove() {
        tfNaziv.setText("");
        tfUlica.setText("");
        tfBroj.setText("");
        tfgrad.setText("");

    }

    private void postaviKolone() {
        Object[]kolone = {"idPozorista", "nazivPozorista", "ulica", "broj", "grad"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
        dtm.addColumn(kolone[2]);
        dtm.addColumn(kolone[3]);
        dtm.addColumn(kolone[4]);
    }
}
