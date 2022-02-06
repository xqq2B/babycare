package com.example.babycare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Dialog pop;


    conexion CN=new conexion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);//siempre se ponen permiso entrada salida de datos

        pop=new Dialog(this);
    }

    public void popup(View v){
        TextView equis;
        ImageView ok;

        final EditText us,pas,email;

        pop.setContentView(R.layout.register);
        us=(EditText)pop.findViewById((R.id.editText3));
        pas=(EditText)pop.findViewById((R.id.editText4));
        email=(EditText)pop.findViewById((R.id.editText5));


        equis=(TextView) pop.findViewById(R.id.equis);
        ok=(ImageView) pop.findViewById(R.id.imageView2);

        equis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((us.getText().toString().equalsIgnoreCase("")) || (pas.getText().toString().equals("")) || (email.getText().toString().equals("")))
                {
                    Toast.makeText(getApplicationContext(),"No se hicieron cambios",Toast.LENGTH_LONG).show();
                    pop.dismiss();
                }
                else {
                   CN.r_altas("https://apisweb.000webhostapp.com/prueba/nativa_babycare/altas.php?", us.getText().toString(),pas.getText().toString(),email.getText().toString());
                    Toast.makeText(getApplicationContext(),"Registro Correcto",Toast.LENGTH_LONG).show();
                    pop.dismiss();
                }
            }
        });

        pop.show();
    }

    public void cambiar(View v) {
        final EditText us, pas;



        us = (EditText) findViewById((R.id.editText));
        pas = (EditText) findViewById((R.id.editText2));
        String x = CN.r_datos("https://apisweb.000webhostapp.com/prueba/nativa_babycare/login.php?", us.getText().toString(), pas.getText().toString());
        if (x.equalsIgnoreCase("ok")) {
            Toast.makeText(getApplicationContext(),"Bienvenido "+us.getText().toString(),Toast.LENGTH_LONG).show();
            Intent v1 = new Intent(getApplicationContext(), main.class);
            startActivity(v1);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Intenta de nuevo",Toast.LENGTH_LONG).show();
            us.setText("");pas.setText("");

        }
}}
