package com.example.beniamino.scunamobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import org.dto.DTO;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService;
import ClientConnectionTest.Fondation.TecnicalService.IComRemoteService;
import Util.DTOMaker;
import Util.DatiServer;

import static java.security.AccessController.getContext;

/**
 * A placeholder fragment containing a simple view.
 */
public class TentativoPingFragment extends Fragment {

    private EditText Nome;
    private EditText Indirizzo;
    private EditText Porta;
    private Button AggiungiButton;

    public TentativoPingFragment() {
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
        DatiServer.getInstance().setIndirizzoIP(Indirizzo.getText().toString());
        DatiServer.getInstance().setPorta(Integer.parseInt(Porta.getText().toString()));

        System.out.println("Sono quìììì");
        new ConcreteRemoteService().execute("Ping",DatiServer.getInstance().getIndirizzoIP(),DatiServer.getInstance().getPorta().toString());
        //ConcreteRemoteService.getSingletonInstance().RichiediAlServer(DTOMaker.getSingletonInstance().getPingDTO(),indirizzoIP,Integer.parseInt(porta));



    }
    public class ConcreteRemoteService extends AsyncTask<String, Void, Object> implements IComRemoteService {


        private  ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService singletonInstance = null;


        public  ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService getSingletonInstance() {
            if (singletonInstance == null) {
                singletonInstance = new ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService();
            }
            return singletonInstance;
        }


        @Override
        public DTO RichiediAlServer(DTO dto, String indirizzo, Integer porta) throws IOException {

            Socket clientSocket = new Socket(indirizzo, porta);
            DTO risp = null;
            try {
                ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
                objectOutput.writeObject(dto);

                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                risp = (DTO) objectInputStream.readObject();


            } catch (EOFException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                clientSocket.close();
            }


            return risp;
        }

        @Override
        protected Object doInBackground(String... params) {
            DTO ping= new DTO();
            Object taskobject = new Object();

            if(params[0].equals("Ping")){
                ping = DTOMaker.getSingletonInstance().getPingDTO();
            }
            try {
                taskobject =(DTO) ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService.getSingletonInstance().RichiediAlServer(ping,params[1],Integer.parseInt(params[2]));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return taskobject;
            //diewaufauyfuayqrfquyebueywueybehf
        }

        @Override
        protected void onPostExecute(Object o) {
            DTO dto = (DTO) o;
            if (dto.getFunzione().equals("Ping")){
                //Siamo okay, cambiamo la view
                Intent intent = new Intent(getActivity(), StanzeActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        }
    }

}
