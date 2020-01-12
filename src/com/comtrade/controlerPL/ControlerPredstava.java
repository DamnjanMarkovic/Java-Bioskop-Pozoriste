package com.comtrade.controlerPL;

import com.comtrade.domen.TransferKlasa;
import com.comtrade.sistemskaOperacija.OpstaSistemskaOperacija;
import com.comtrade.sistemskaOperacija.pozoriste.*;
import com.comtrade.sistemskaOperacija.predstava.*;

public class ControlerPredstava implements CommandBase {
    @Override
    public void execute(TransferKlasa transferKlasa) {
        OpstaSistemskaOperacija opstaSistemskaOperacija=null;

        switch (transferKlasa.getKonstanteKPL()){

            case VRATI_PODATKE_PREDSTAVA:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiKolonePredstava();
                break;
            case UNOS_PODATAKA_PREDSTAVA:
                opstaSistemskaOperacija = new SistemskaOperacijaUnosPodatakaPredstava();
                break;
            case OBRISI_PREDSTAVU:
                opstaSistemskaOperacija = new SistemskaOperacijaObrisiPredstavu();
                break;
            case IZMENA_IMENA_PREDSTAVE:
                opstaSistemskaOperacija = new SistemskaOperacijaIzmeniPredstavu();
                break;
            case IMENA_PREDSTAVA:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiImenaPredstava();
                break;
            case VRATI_ID_PREDSTAVE:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiIdPredstava();
                break;
            case VRATI_NAZIV_PREDSTAVE:
                opstaSistemskaOperacija = new SistemskaOperacijaVratiNazivPredstava();
                break;



            default:
                break;


        }opstaSistemskaOperacija.IzvrsiSistemskuOperaciju(transferKlasa);

    }
}
