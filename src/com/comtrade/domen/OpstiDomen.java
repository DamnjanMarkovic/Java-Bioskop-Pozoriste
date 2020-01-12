package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface OpstiDomen {


    String vratiNazivTabele();

    String vratiKoloneSave();

    String vratiVrednosti();

    PreparedStatement vratiPreparedStatementSave(PreparedStatement preparedStatement);




    OpstiDomen procitajObjekte(ResultSet resultSet);

    String vratiKolonuBrisanje();

    String vratiVrednostUpitnik();

    String vratiKoloneIzmena();

    String izmenePoVrednosti();

    PreparedStatement vratiPreparedStatementUpdate(PreparedStatement preparedStatement);

    String nazivKolone();


    String nazivIDKolone();

    String nazivNazivKolone();

    PreparedStatement vratiIdPreparedStatement(PreparedStatement preparedStatement);

    String vratiVrednostUpitnikID();

    String vratiImeTrazeneKolone();
}
