package com.skylist.qrcodeattendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<AttendanceModel> dataset;
    private FloatingActionButton fab_addCheck;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate( R.layout.fragment_dashboard, container, false);

        dataset         = new ArrayList<AttendanceModel>();
        recyclerView    = view.findViewById(R.id.recyclerView);
        fab_addCheck    = view.findViewById(R.id.fab_add_check);

        fab_addCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });

        updateRecycleView();

        return view;
    }

    //ESPERA A LEITURA DA CAMERA ACONTECER PARA PREENCHER O ARRAY
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult( requestCode, resultCode, data );
        String dataQR[] =  new String[4];
        if(result != null){
            if(result.getContents() != null){
                dataQR = result.getContents().split(";");
                dataQR[2] = "Dispositivos moveis";
                dataQR[3] = "https://www.infoescola.com/wp-content/uploads/2017/09/UNITINS-600x423.jpg";

                dataset.add( new AttendanceModel( dataQR ) );
                updateRecycleView();
            }
        }
    }

    //ABRE A CAMERA PARA COMEÇAR O SCAN DO QRCODE DO ALUNO
    public void scanCode(){
        Log.i("resposta", "start scan" );
        IntentIntegrator integrator = new IntentIntegrator( getActivity() );
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Point at the student QRCode");
        integrator.setCameraId(0);
        integrator.initiateScan();
    }

    //FAZ O UPDATE DO RECYCLEVIEW
    public void updateRecycleView(){
        layoutManager = new LinearLayoutManager( getActivity() );
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(dataset);
        recyclerView.setAdapter(adapter);
    }

}
