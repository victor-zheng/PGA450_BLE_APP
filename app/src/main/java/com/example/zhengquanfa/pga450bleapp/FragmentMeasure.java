package com.example.zhengquanfa.pga450bleapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMeasure.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMeasure#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FragmentMeasure extends Fragment {

    public FragmentMeasure() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_measure, container, false);
    }
}
