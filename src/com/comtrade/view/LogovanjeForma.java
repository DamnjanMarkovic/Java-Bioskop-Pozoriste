package com.comtrade.view;

import com.comtrade.domen.Korisnik;
import com.comtrade.view.proxy.Proxy;
import com.comtrade.view.proxy.ProxyLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogovanjeForma extends JFrame{
    private JPanel jPanel;
    private JTextField tfUser;
    private JPasswordField pfPassword;
    private JButton LOGINButton;
    private JLabel tfUserName;
    private JButton NOVIKORISNIKButton;


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            LogovanjeForma logovanjeForma = new LogovanjeForma();
            logovanjeForma.setVisible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
    public LogovanjeForma() {

        add(jPanel);
        setBounds(200,200,400,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("LOGOVANJE FORMA");


        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String username=tfUser.getText();
                char[] niz = pfPassword.getPassword();
                String password = String.copyValueOf(niz);
                Korisnik korisnik = new Korisnik();
                korisnik.setUsername(username);
                korisnik.setPassword(password);
                Proxy proxy=new ProxyLogin();
                proxy.login(korisnik);
                tfUser.setText("");
                pfPassword.setText("");
            }
        });
        NOVIKORISNIKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NeregistrovaniKorisnikForma neregistrovaniKorisnikForma = new NeregistrovaniKorisnikForma();
                neregistrovaniKorisnikForma.setVisible(true);
            }
        });
    }
}
