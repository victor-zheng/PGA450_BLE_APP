package com.example.zhengquanfa.pga450bleapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    View main_view;
    public MainActivityFragment() {
    }
    View.OnClickListener mylistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int ID = v.getId();
            switch (ID) {
                case R.id.button_scan:
                    ProgressBar  mypb = (ProgressBar)main_view.findViewById(R.id.progressBar);
                    mypb.setVisibility(v.VISIBLE);
                    break;
                case R.id.button_connect:
                    break;
                case R.id.button_disconnect:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Button scan,connect,disconnect;
        main_view = inflater.inflate(R.layout.fragment_main, container, false);
        scan = (Button) main_view.findViewById(R.id.button_scan);
        connect = (Button) main_view.findViewById(R.id.button_connect);
        disconnect = (Button) main_view.findViewById(R.id.button_disconnect);
        scan.setOnClickListener(mylistener );
        connect.setOnClickListener(mylistener );
        disconnect.setOnClickListener(mylistener );
        return main_view;
    }
}
