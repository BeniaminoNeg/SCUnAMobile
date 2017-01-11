package ClientConnectionTest.Fondation.TecnicalService;


import android.os.AsyncTask;
import android.os.StrictMode;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Util.DTO;
import Util.DTOMaker;


/**
 * Created by gioele on 04/03/16.
 */
public class ConcreteRemoteService extends AsyncTask<String, Void, Object> implements IComRemoteService  {


    private static ConcreteRemoteService singletonInstance = null;


    public static ConcreteRemoteService getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ConcreteRemoteService();
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
          taskobject =(DTO) ConcreteRemoteService.getSingletonInstance().RichiediAlServer(ping,params[1],Integer.parseInt(params[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return taskobject;
        //diewaufauyfuayqrfquyebueywueybehf
    }
}
