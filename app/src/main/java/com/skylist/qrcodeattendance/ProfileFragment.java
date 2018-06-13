package com.skylist.qrcodeattendance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    EditText subject_text   = null ;
    Button bSave            = null;
    final static String PREFS_NAME = "SUBJECT_DATA";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate( R.layout.fragment_profile, container, false );

        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        subject_text    = (EditText) view.findViewById(R.id.id_edSubject);
        bSave           = view.findViewById(R.id.id_save);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    editor.clear();
                    editor.commit();
                    editor.putString("subject_name", subject_text.getText().toString());
                    editor.commit();

                    Toast.makeText(getActivity().getApplicationContext(), "Saving subject name..", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        Log.i("sharedpreference", sharedPreferences.getString("subject_name", "someone_subject"));

        return view;
    }

}
