package com.comtrade.view;

import com.comtrade.domen.Korisnik;
import com.comtrade.view.proxy.Proxy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NeregistrovaniKorisnikForma extends JFrame implements Proxy {
    private JPanel jPanel;
    private JTextField tfUsername;
    private JTextField tfTelefon;
    private JTextField textField3;
    private JLabel tfMail;
    private JButton POSALJIZAHTEVADMINISTRATORUButton;
    private DefaultTableModel dtm = new DefaultTableModel();

    public NeregistrovaniKorisnikForma(){

        add(jPanel);
        setBounds(200,200,600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("NOVI KORISNIK FORMA");
    }

    @Override
    public void login(Korisnik korisnik) {
        setVisible(true);
    }
}
