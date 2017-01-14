package com.example.beniamino.scunamobile;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.dto.DTO;

import java.io.IOException;
import java.math.BigInteger;

import Util.AccessoStanza;
import Util.DTOMaker;
import Util.DatiCarta;
import Util.DatiServer;

public class StanzeActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    private Button RisultatoButton;

   private RadioGroup radioGroup;
    private RadioButton radioButtonPremuto;

    private TextView contenuto;

    public static final String TAG = "NfcDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stanze);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        contenuto = (TextView) findViewById(R.id.contenuto);
        RisultatoButton = (Button) findViewById(R.id.risultato);
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup) ;


        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if(nfcAdapter != null && nfcAdapter.isEnabled()){
            Toast.makeText(this,"NFC avaiable",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"NFC not avaiable",Toast.LENGTH_LONG).show();

        }

    }



    @Override
    protected void onNewIntent(Intent intent) {


        handleIntent(intent);

        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {

        Intent intent = new Intent(this,StanzeActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        IntentFilter[] intentFilters = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this,pendingIntent,intentFilters,null);


        super.onResume();
    }


    @Override
    protected void onPause() {
        nfcAdapter.disableForegroundDispatch(this);

        super.onPause();
    }


    private void handleIntent(Intent intent) {
        Toast.makeText(this,"NFC intent received",Toast.LENGTH_LONG).show();
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButtonPremuto = (RadioButton) findViewById(selectedId);

        String NomeStanza = (String) radioButtonPremuto.getText();

        AccessoStanza.getSingletonInstance().setIdStanza(NomeStanza);

        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Log.d(TAG, "UID: " + bin2hex(tag.getId()));
            contenuto.setText(bin2hex(tag.getId()));

            DatiCarta.getSingletonInstance().setIdCarta(bin2hex(tag.getId()));

            new LettaCarta().execute("AutenticazioneStanza", DatiServer.getInstance().getIndirizzoIP(),DatiServer.getInstance().getPorta().toString());

        }


    }

    static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1,data));
    }


    public class LettaCarta extends AsyncTask<String, Void, Object> {

        @Override
        protected Object doInBackground(String... params) {
            DTO autenticazione= new DTO();
            Object taskobject = new Object();

            if(params[0].equals("AutenticazioneStanza")){
                autenticazione = DTOMaker.getSingletonInstance().getAutenticazioneStanza(DatiCarta.getSingletonInstance().getIdCarta(),AccessoStanza.getSingletonInstance().getIdStanza());
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
