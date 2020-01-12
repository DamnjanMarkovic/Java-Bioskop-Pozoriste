package com.comtrade.controlerPL;

import com.comtrade.domen.TransferKlasa;

public interface CommandBase {

    void execute (TransferKlasa transferKlasa);

}
