package com.example.beniamino.scunamobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.dto.DTO;

import java.io.IOException;

import Util.DTOMaker;
import Util.DatiServer;

/**
 * A placeholder fragment containing a simple view.
 */
public class StanzeActivityFragment extends Fragment {

    private Button StanzaUnoButton;
    private Button StanzaDueButton;

    private Button RisultatoButton;

    public StanzeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_stanze, container, false);
        StanzaUnoButton = (Button) root.findViewById(R.id.button2);
        StanzaDueButton = (Button) root.findViewById(R.id.button4);
        RisultatoButton = (Button) root.findViewById(R.id.risultato);

        StanzaUnoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StanzaUnoPress();
            }
        });

        StanzaDueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StanzaDuePress();
            }
        });
        return root;
    }

    private void StanzaDuePress() {
        new DuePress().execute("AutenticazioneStanza", DatiServer.getInstance().getIndirizzoIP(),DatiServer.getInstance().getPorta().toString());

    }

    private void StanzaUnoPress() {
        new UnoPress().execute("AutenticazioneStanza", DatiServer.getInstance().getIndirizzoIP(),DatiServer.getInstance().getPorta().toString());

    }

    public class UnoPress extends AsyncTask<String, Void, Object>{

        @Override
        protected Object doInBackground(String... params) {
            DTO autenticazione= new DTO();
            Object taskobject = new Object();

            if(params[0].equals("AutenticazioneStanza")){
                autenticazione = DTOMaker.getSingletonInstance().getAutenticazioneStanza("gioele","stanza1");
            }
            try {
                taskobject =(DTO) ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService.getSingletonInstance().RichiediAlServer(autenticazione,params[1],Integer.parseInt(params[2]));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return taskobject;
        }

        @Override
        protected void onPostExecute(Object o) {
            DTO dto = (DTO) o;
            if (dto.getFunzione().equals("RispostaAutenticazioneStanza")){
                Boolean buonfine = (Boolean) dto.getOggettiTrasferimento().get(0);
                if (buonfine){
                    RisultatoButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    RisultatoButton.setBackgroundColor(Color.RED);
                }
            }
        }
    }
    public class DuePress extends AsyncTask<String, Void, Object>{

        @Override
        protected Object doInBackground(String... params) {
            DTO autenticazione= new DTO();
            Object taskobject = new Object();

            if(params[0].equals("AutenticazioneStanza")){
                autenticazione = DTOMaker.getSingletonInstance().getAutenticazioneStanza("gioele","stanza2");
            }
            try {
                taskobject =(DTO) ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService.getSingletonInstance().RichiediAlServer(autenticazione,params[1],Integer.parseInt(params[2]));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return taskobject;
        }

        @Override
        protected void onPostExecute(Object o) {
            DTO dto = (DTO) o;
            if (dto.getFunzione().equals("RispostaAutenticazioneStanza")){
                Boolean buonfine = (Boolean) dto.getOggettiTrasferimento().get(0);
                if (buonfine){
                    RisultatoButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    RisultatoButton.setBackgroundColor(Color.RED);
                }
            }
        }
    }

}

