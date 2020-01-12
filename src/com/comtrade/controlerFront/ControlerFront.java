package com.comtrade.controlerFront;

import com.comtrade.controlerPL.*;
import com.comtrade.controlerPL.ControlerGlumac;
import com.comtrade.domen.TransferKlasa;

public class ControlerFront {

    private static ControlerFront instance;
    private ControlerFront(){

    }

    public static ControlerFront getInstance() {
        if (instance== null){
            instance= new ControlerFront();
        }
        return instance;
    }
    public void  execute (TransferKlasa transferKlasa){

        CommandBase commandBase=null;

        switch (transferKlasa.getKonstanteFK()){

            case KORISNIK:
                commandBase= new ControlerKorisnik();
                break;
            case POZORISTE:
                commandBase= new ControlerPozoriste();
                break;
            case PREDSTAVE:
                commandBase= new ControlerPredstava();
                break;
            case TERMINI:
                commandBase= new ControlerTermini();
                break;
            case REZERVACIJA:
                commandBase= new ControlerRezervacija();
                break;
            case GLUMAC:
                commandBase= new ControlerGlumac();
                break;

            default:
                break;

        }commandBase.execute(transferKlasa);



    }



}
