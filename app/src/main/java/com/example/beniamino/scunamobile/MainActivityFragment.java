package com.example.beniamino.scunamobile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import java.io.IOException;

import ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private EditText Nome;
    private EditText Indirizzo;
    private EditText Porta;
    private Button AggiungiButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("Sono quìììì");

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Nome = (EditText) root.findViewById(R.id.editText2);
        Indirizzo = (EditText) root.findViewById(R.id.editText3);
        Porta = (EditText) root.findViewById(R.id.editText4);

        AggiungiButton = (Button) root.findViewById(R.id.button);

        AggiungiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Aggiungi();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return root;
    }

    private void Aggiungi() throws IOException {
        String nome = Nome.getText().toString();
        String indirizzoIP = Indirizzo.getText().toString();
        String porta = Porta.getText().toString();

        System.out.println("Sono quìììì");
        new ConcreteRemoteService().execute("Ping",indirizzoIP,porta);
        //ConcreteRemoteService.getSingletonInstance().RichiediAlServer(DTOMaker.getSingletonInstance().getPingDTO(),indirizzoIP,Integer.parseInt(porta));



    }
}
