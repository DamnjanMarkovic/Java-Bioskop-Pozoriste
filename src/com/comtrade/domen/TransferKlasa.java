package com.comtrade.domen;

import com.comtrade.controlerFront.ControlerFront;

public class TransferKlasa {

    private Object request;
    private int message;
    private KonstanteFK konstanteFK;
    private KonstanteKPL konstanteKPL;
    private Object response;

    public static TransferKlasa transferKlasa(Object object, int message, KonstanteFK konstanteFK, KonstanteKPL konstanteKPL){

        TransferKlasa transferKlasa = new TransferKlasa();
        transferKlasa.setRequest(object);
        transferKlasa.setMessage(message);
        transferKlasa.setKonstanteFK(konstanteFK);
        transferKlasa.setKonstanteKPL(konstanteKPL);
        ControlerFront.getInstance().execute(transferKlasa);


        return transferKlasa;
    }


    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public KonstanteFK getKonstanteFK() {
        return konstanteFK;
    }

    public void setKonstanteFK(KonstanteFK konstanteFK) {
        this.konstanteFK = konstanteFK;
    }

    public KonstanteKPL getKonstanteKPL() {
        return konstanteKPL;
    }

    public void setKonstanteKPL(KonstanteKPL konstanteKPL) {
        this.konstanteKPL = konstanteKPL;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
