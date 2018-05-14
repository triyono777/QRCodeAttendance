package com.skylist.qrcodeattendance;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<AttendanceModel> dataset;
    private FloatingActionButton addAttendance;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_dashboard, container, false);

        dataset         = new ArrayList<AttendanceModel>();
        recyclerView    = view.findViewById(R.id.recyclerView);
        //addAttendance   = view.findViewById(R.id.id_add_attendance);
        Log.i("TAG", String.valueOf(container.findViewById(R.id.recyclerView)));

        fillList();
        updateRecycleView();
/*
        addAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( view.getId() == R.id.id_add_attendance ) {
                    fillList();
                    updateRecycleView();
                }
            }
        });
*/
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = getActivity().getApplicationContext();
    }

    public void initializeVars(View view ){

    }

    public void updateRecycleView(){
        layoutManager = new LinearLayoutManager( getActivity() );
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(dataset);
        recyclerView.setAdapter(adapter);
    }

    void fillList(){
        dataset.add( new AttendanceModel( "Matheus Pantoja Filgueira", "10101010", "Dispositivos Moveis", "https://mylogo.com" ) );
        dataset.add( new AttendanceModel( "Matheus", "10101010", "Dispositivos Moveis", "https://mylogo.com" ) );
        dataset.add( new AttendanceModel( "Matheus", "10101010", "Dispositivos Moveis", "https://mylogo.com" ) );
    }
}
