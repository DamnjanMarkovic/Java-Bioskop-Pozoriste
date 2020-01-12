package com.comtrade.view;

import com.comtrade.domen.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class NoviKorisnikForma extends JFrame{
    private JPanel jPanel;
    private JComboBox comboBox1;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JTextField tfTelefon;
    private JTextField tfMail;
    private JButton NOVIKORISNIKButton;
    private JButton IZMENIButton;
    private JButton OBRISIButton;
    private JTable table1;
    private JScrollPane jScrollPane;
    private DefaultTableModel dtm = new DefaultTableModel();
    private int idKorisnika;
    private int status;

    public NoviKorisnikForma(){


        add(jPanel);
        setBounds(200,200,600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("NOVI KORISNIK FORMA");
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        postaviPodatkeUCombo();
        postaviKolone();
        postaviPodatkeUTabelu();


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String tekstCB =  comboBox1.getSelectedItem().toString();
                if (tekstCB.equalsIgnoreCase("administrator"))status = 1;
                else if (tekstCB.equalsIgnoreCase("registrovani korisnik")) status=2;

            }
        });
        NOVIKORISNIKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String username = tfUsername.getText();
                String password = tfPassword.getText();
                String telefon = tfTelefon.getText();
                String mail = tfMail.getText();

                Korisnik korisnik = new Korisnik(username, password, telefon, mail, status);

                TransferKlasa.transferKlasa(korisnik, 0, KonstanteFK.KORISNIK, KonstanteKPL.UNOS_PODATAKA_KORISNIK);
                postaviPodatkeUTabelu();
                izbrisiFildove();


            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table1.getSelectedRow();
                idKorisnika = Integer.parseInt(table1.getModel().getValueAt(red, 0).toString());
                tfUsername.setText(table1.getModel().getValueAt(red, 1).toString());
                tfPassword.setText(table1.getModel().getValueAt(red, 2).toString());
                tfTelefon.setText((table1.getModel().getValueAt(red, 3).toString()));
                tfMail.setText(table1.getModel().getValueAt(red, 4).toString());


            }
        });
        IZMENIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
             Korisnik korisnik = new Korisnik(idKorisnika, tfUsername.getText(), tfPassword.getText(),
                        tfTelefon.getText(), tfMail.getText(), status);
                TransferKlasa.transferKlasa(korisnik, 0, KonstanteFK.KORISNIK, KonstanteKPL.IZMENA_IMENA_KORISNIKA);
                postaviPodatkeUTabelu();
                izbrisiFildove();
            }
        });
        OBRISIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Korisnik korisnik = new Korisnik();
                korisnik.setIdKorisnika(idKorisnika);
                TransferKlasa.transferKlasa(korisnik, 0, KonstanteFK.KORISNIK, KonstanteKPL.OBRISI_KORISNIKA);
                postaviPodatkeUTabelu();
                izbrisiFildove();

            }
        });
    }

    private void izbrisiFildove() {
        tfUsername.setText("");
        tfPassword.setText("");
        tfMail.setText("");
        tfTelefon.setText("");
    }


    private void postaviPodatkeUTabelu() {
        dtm.setRowCount(0);
        Korisnik korisnik = new Korisnik();
        List<Korisnik> listaPredstava = (List<Korisnik>) TransferKlasa.transferKlasa(korisnik, 0, KonstanteFK.KORISNIK, KonstanteKPL.VRATI_PODATKE_KORISNIK).getResponse();
        for (Korisnik korisnik1: listaPredstava) {
            Object [] red = {korisnik1.getIdKorisnika(), korisnik1.getUsername(), korisnik1.getPassword(), korisnik1.getTelefon(), korisnik1.getMail(), korisnik1.getStatus()};

                dtm.addRow(red);
        }
    }

    private void postaviKolone() {
        Object[] kolone = {"idKorisnika", "username", "password", "telefon", "mail", "status"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
        dtm.addColumn(kolone[2]);
        dtm.addColumn(kolone[3]);
        dtm.addColumn(kolone[4]);
        dtm.addColumn(kolone[5]);
    }


    private void postaviPodatkeUCombo() {
        comboBox1.addItem("administrator");
        comboBox1.addItem("registrovani korisnik");
    }

}
