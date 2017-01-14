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



    public StanzeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_stanze, container, false);
        return root;
    }


}

