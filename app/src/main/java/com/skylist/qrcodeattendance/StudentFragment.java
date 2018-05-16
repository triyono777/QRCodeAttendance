package com.skylist.qrcodeattendance;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class StudentFragment extends Fragment {

    private View view;
    private ImageView student_qrCode;
    private EditText student_name;
    private EditText student_registration;
    private Button btn_generate;
    private Switch swt_remember;
    final static String PREFS_NAME = "STUDENT_DATA";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_student, container, false);

        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        //INICIALIZAÇÃO DE VARIAVEIS
        student_qrCode          = view.findViewById(R.id.id_qr_code_student);
        student_name            = view.findViewById(R.id.id_name_student);
        student_registration    = view.findViewById(R.id.id_registration_number);
        btn_generate            = view.findViewById(R.id.id_btn_generate);
        swt_remember            = view.findViewById(R.id.id_switch_remember);

        //FAZ UMA CHECAGEM SE EXISTE VALORES NAS PREFERENCIAS
        if( sharedPreferences.getBoolean("swt_state", false) ){
            student_name.setText( sharedPreferences.getString("student_name", null) );
            student_registration.setText( sharedPreferences.getString("student_registration", null) );
        }

        boolean swt_last_state = sharedPreferences.getBoolean("swt_state", false);
        swt_remember.setChecked( swt_last_state );

        //ADCIONA UM LISTENER AO SWITCH
        swt_remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( swt_remember.isChecked() ){
                    Toast.makeText(getActivity().getApplicationContext(), "Saving name and registration number..", Toast.LENGTH_SHORT).show();

                    editor.putString( "student_name", student_name.getText().toString()  );
                    editor.putString( "student_registration", student_registration.getText().toString() );
                    editor.putBoolean( "swt_state", true );
                    editor.commit();
                }
                if( !swt_remember.isChecked() ) {
                    editor.clear();
                    editor.commit();
                }
            }
        });
        //LISTENER DO BOTÃO DE GERAR QRCODE
        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateQRCode( student_name.getText().toString() , student_registration.getText().toString() );
            }
        });

        return view;
    }

    //GERAÇÃO DO QR CODE
    private void generateQRCode( String name, String registration ){
        String text = "";

        //CHECAGEM PARA N GERAR UM QR CODE VAZIO
        if( !name.equals("") ){
            text = name +";"+registration;

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try{ //É CRIADO UM BITMAP PARA ARMAZENAR O QRCODE GERADO, E DEPOIS É COLOCADO NO LUGAR DO IMAGEVIEW NO FRAGMENTO
                BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, view.getWidth(), view.getWidth());
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                student_qrCode.setImageBitmap(bitmap);

            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Toast.makeText(getActivity().getApplicationContext(), "Fill in the fields", Toast.LENGTH_SHORT).show();
        }
    }
}
