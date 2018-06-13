package com.skylist.qrcodeattendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static android.content.Context.MODE_PRIVATE;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<AttendanceModel> dataset;
    private FloatingActionButton fab_addCheck;
    private View view;

    FirebaseDatabase db;
    private DatabaseReference mDatabase;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate( R.layout.fragment_dashboard, container, false);

        db              = FirebaseDatabase.getInstance();
        mDatabase   = FirebaseDatabase.getInstance().getReference();

        dataset         = new ArrayList<AttendanceModel>();
        recyclerView    = view.findViewById(R.id.recyclerView);
        fab_addCheck    = view.findViewById(R.id.fab_add_check);

        fab_addCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });

        loadListFirebase();
        //updateRecycleView();

        return view;
    }

    public void loadListFirebase(){
        final List<AttendanceModel> copyBd = new ArrayList<>();
        Query query1 = mDatabase.child("presenca");
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for( DataSnapshot ds : dataSnapshot.getChildren()){
                    AttendanceModel cdia = ds.getValue(AttendanceModel.class);
                    Log.i("SAIDA", cdia.getStudentName()+" ,"+cdia.getSubject()+" ,"+cdia.getDate());
                    dataset.add(cdia);
                }

                updateRecycleView();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void saveNewPresence( int id, String nome, String regNumber, String subject , String url){

        String currentDate = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss").format(new Date());
        //String date[] = currentDate.split("-");

        DatabaseReference myref = db.getReference("presenca");

        AttendanceModel attendanceModel = new AttendanceModel( id, nome, regNumber, subject, url, currentDate );
        myref.child(currentDate+"-"+regNumber).setValue(attendanceModel);
        /*
        CheckDoDia cd = new CheckDoDia( institution, passOfDay, preceptor, materia,date[0]+"-"+date[1]+"-"+date[2], date[3] );
        myref.child("students").child( mAuth.getCurrentUser().getUid() ).child("MATERIAS").child(currentDate).setValue(cd);
        myref.child("students").child(mAuth.getCurrentUser().getUid()).child("name").setValue( mAuth.getCurrentUser().getDisplayName() );
        */
    }

    //ESPERA A LEITURA DA CAMERA ACONTECER PARA PREENCHER O ARRAY
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult( requestCode, resultCode, data );
        String dataQR[] =  new String[4];
        if(result != null){
            if(result.getContents() != null){
                String dataQR2[] = result.getContents().split(";");

                dataQR[0] = dataQR2[0];
                dataQR[1] = dataQR2[1];

                dataQR[2] = "Dispositivos moveis";
                dataQR[3] = "https://www.infoescola.com/wp-content/uploads/2017/09/UNITINS-600x423.jpg";
                //dataset.add( new AttendanceModel( dataQR ) );
                saveNewPresence( 10,dataQR[0], dataQR[1], dataQR[2], dataQR[3] );
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
