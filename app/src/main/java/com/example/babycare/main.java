package com.example.babycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class main extends AppCompatActivity {
    Dialog pop;
    VideoView video;
    private MediaRecorder grabar;
    conexion CN = new conexion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();




        final VideoView video = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName() + "/" +R.raw.baby2;
        Uri uri = Uri.parse(videoPath);
        video.setVideoURI(uri);
        //video.setLooping(true);
        video.start();

        MediaController controls= new MediaController((this));
        video.setMediaController(controls);
        controls.setAnchorView(video);

        pop=new Dialog(this);
    }


    public void cambiar(View v)
    {
        Intent v1 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(v1);
    }


    public void popup(View v){
        TextView equis;
        Button ok;
        /*if(v.getId() == R.id.imageView4 || v.getId() == R.id.imageView){
            Intent v3 = new Intent(getApplicationContext(),index.class);
            startActivity(v3);}*/
        if(v.getId()== R.id.imageView3)
        {
            pop.setContentView(R.layout.camara);
            ImageView cam;
            cam=(ImageView)pop.findViewById(R.id.imgvcam);

            equis=(TextView) pop.findViewById(R.id.equis);//cerrar

            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)  {
                ActivityCompat.requestPermissions(main.this, new String[]{ Manifest.permission.CAMERA}, 1000);
            }

            cam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);
                }
            });
            equis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                }
            });

            pop.show();
        }
        else if(v.getId()==R.id.imageView5)
        {
            pop.setContentView(R.layout.microfono);


            ImageView mic, pla;
            mic=(ImageView)pop.findViewById(R.id.imageView2);
            pla=(ImageView)pop.findViewById(R.id.imageView3);
            equis=(TextView) pop.findViewById(R.id.equis);

            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(main.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
            }
            mic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(grabar == null) {
                        grabar = new MediaRecorder();
                        grabar.setAudioSource(MediaRecorder.AudioSource.MIC);
                        grabar.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        grabar.setOutputFile(getExternalCacheDir().getAbsolutePath()+"/mensaje.3gp");
                        grabar.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

                        try {
                            grabar.prepare();
                            grabar.start();
                        } catch (IOException e) {
                        }

                        Toast.makeText(getApplicationContext(), "Grabando", Toast.LENGTH_LONG).show();
                    }
                    else if(grabar!=null) {
                        grabar.stop();
                        grabar.release();
                        grabar=null;
                        Toast.makeText(getApplicationContext(), "Grabación detenida", Toast.LENGTH_LONG).show();
                    }
                }
            });

            pla.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer play=new MediaPlayer();
                    try{
                        play.setDataSource(getExternalCacheDir().getAbsolutePath()+"/mensaje.3gp");
                        play.prepare();
                    }
                    catch (Exception ee)
                    {

                    }
                    play.start();
                    Toast.makeText(getApplicationContext(),"Reproduciendo",Toast.LENGTH_LONG).show();
                }
            });

            equis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                }
            });
            pop.show();
        }
        else if(v.getId()==R.id.imageView4)
        {
            pop.setContentView(R.layout.musica);

            ImageView play,stop;


            play=(ImageView)pop.findViewById((R.id.imageView8));
            stop=(ImageView)pop.findViewById((R.id.imageView4));
            equis=(TextView) pop.findViewById(R.id.equis);
            final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mousou);
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(),"Reproduciendo",Toast.LENGTH_LONG).show();
                }
            });
            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    Toast.makeText(getApplicationContext(),"Detenido",Toast.LENGTH_LONG).show();
                }
            });
            equis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                }
            });
            pop.show();
        }
        else if(v.getId()==R.id.imageView7)
        {
            pop.setContentView(R.layout.info);
            TextView s11,s12,s13,s21,s22,s23,s31,s32,s33;
            TextView b11,b12,b13,b21,b22,b23,b31,b32,b33;
            TextView c11,c12,c13,c21,c22,c23,c31,c32,c33;

            s11=(TextView)pop.findViewById(R.id.s11);
            s12=(TextView)pop.findViewById(R.id.s12);
            s13=(TextView)pop.findViewById(R.id.s13);
            s21=(TextView)pop.findViewById(R.id.s21);
            s22=(TextView)pop.findViewById(R.id.s22);
            s23=(TextView)pop.findViewById(R.id.s23);
            s31=(TextView)pop.findViewById(R.id.s31);
            s32=(TextView)pop.findViewById(R.id.s32);
            s33=(TextView)pop.findViewById(R.id.s33);

            b11=(TextView)pop.findViewById(R.id.b11);
            b12=(TextView)pop.findViewById(R.id.b12);
            b13=(TextView)pop.findViewById(R.id.b13);
            b21=(TextView)pop.findViewById(R.id.b21);
            b22=(TextView)pop.findViewById(R.id.b22);
            b23=(TextView)pop.findViewById(R.id.b23);
            b31=(TextView)pop.findViewById(R.id.b31);
            b32=(TextView)pop.findViewById(R.id.b32);
            b33=(TextView)pop.findViewById(R.id.b33);

            c11=(TextView)pop.findViewById(R.id.c11);
            c12=(TextView)pop.findViewById(R.id.c12);
            c13=(TextView)pop.findViewById(R.id.c13);
            c21=(TextView)pop.findViewById(R.id.c21);
            c22=(TextView)pop.findViewById(R.id.c22);
            c23=(TextView)pop.findViewById(R.id.c23);
            c31=(TextView)pop.findViewById(R.id.c31);
            c32=(TextView)pop.findViewById(R.id.c32);
            c33=(TextView)pop.findViewById(R.id.c33);

            equis=(TextView) pop.findViewById(R.id.equis);//cerrar

            String x = CN.r_query("https://apisweb.000webhostapp.com/prueba/nativa_babycare/sueno.php");
            String y = CN.r_query("https://apisweb.000webhostapp.com/prueba/nativa_babycare/bano.php");
            String z = CN.r_query("https://apisweb.000webhostapp.com/prueba/nativa_babycare/comida.php");


            if(!x.toString().equalsIgnoreCase("sin datos")) {
                String[] datos_individual = x.split(",");
                int cont = Integer.parseInt(datos_individual[0]);
                int c = 1;

                    for (int i = 1; i <= cont; i++) {
                        if (i == 1) {
                            s11.setText(datos_individual[c]);
                            s12.setText(datos_individual[c + 1]);
                            s13.setText(datos_individual[c + 2]);
                        } else if (i == 2) {
                            s21.setText(datos_individual[c]);
                            s22.setText(datos_individual[c + 1]);
                            s23.setText(datos_individual[c + 2]);
                        } else if (i == 3) {
                            s31.setText(datos_individual[c]);
                            s32.setText(datos_individual[c + 1]);
                            s33.setText(datos_individual[c + 2]);
                        }
                        c = c + 4;
                    }

                if(cont >2)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Registros de Sueño llenos");
                    builder.setMessage("¿Borrar Registros?");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CN.r_query("https://apisweb.000webhostapp.com/prueba/nativa_babycare/delete_sueno.php");
                            Toast.makeText(getApplicationContext(),"Ya puedes Ingresar nuevos registros",Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"No se hicieron cambios",Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.show();

                }
                }

            if(!y.toString().equalsIgnoreCase("sin datos")) {
                String[] datos_individual = y.split(",");
                int cont = Integer.parseInt(datos_individual[0]);
                int c = 1;

                for (int i = 1; i <= cont; i++) {
                    if (i == 1) {
                        b11.setText(datos_individual[c]);
                        b12.setText(datos_individual[c + 1]);
                        b13.setText(datos_individual[c + 2]);
                    } else if (i == 2) {
                        b21.setText(datos_individual[c]);
                        b22.setText(datos_individual[c + 1]);
                        b23.setText(datos_individual[c + 2]);
                    } else if (i == 3) {
                        b31.setText(datos_individual[c]);
                        b32.setText(datos_individual[c + 1]);
                        b33.setText(datos_individual[c + 2]);
                    }
                    c = c + 4;
                }

                if(cont >2)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Registros de Baño llenos");
                    builder.setMessage("¿Borrar Registros?");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CN.r_query("https://apisweb.000webhostapp.com/prueba/nativa_babycare/delete_bano.php");
                            Toast.makeText(getApplicationContext(),"Ya puedes Ingresar nuevos registros",Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"No se hicieron cambios",Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.show();

                }
            }

            if(!z.toString().equalsIgnoreCase("sin datos")) {
                String[] datos_individual = z.split(",");
                int cont = Integer.parseInt(datos_individual[0]);
                int c = 1;

                for (int i = 1; i <= cont; i++) {
                    if (i == 1) {
                        c11.setText(datos_individual[c]);
                        c12.setText(datos_individual[c + 1]);
                        c13.setText(datos_individual[c + 2]);
                    } else if (i == 2) {
                        c21.setText(datos_individual[c]);
                        c22.setText(datos_individual[c + 1]);
                        c23.setText(datos_individual[c + 2]);
                    } else if (i == 3) {
                        c31.setText(datos_individual[c]);
                        c32.setText(datos_individual[c + 1]);
                        c33.setText(datos_individual[c + 2]);
                    }
                    c = c + 4;
                }

                if(cont >2)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Registros de Comida llenos");
                    builder.setMessage("¿Borrar Registros?");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CN.r_query("https://apisweb.000webhostapp.com/prueba/nativa_babycare/delete_comida.php");
                            Toast.makeText(getApplicationContext(),"Ya puedes Ingresar nuevos registros",Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"No se hicieron cambios",Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.show();

                }
            }

            pop.dismiss();

            equis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                }
            });
            pop.show();
        }
        else if(v.getId()==R.id.imageView2)
        {
            pop.setContentView(R.layout.rsueno);
            final EditText fecha, desde, hasta;
            ImageView ver;

            fecha=(EditText)pop.findViewById(R.id.editText3);
            desde=(EditText)pop.findViewById(R.id.editText4);
            hasta=(EditText)pop.findViewById(R.id.editText5);
            equis=(TextView) pop.findViewById(R.id.equis);
            ver=(ImageView) pop.findViewById(R.id.imageView2);

            equis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                }
            });
            ver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((fecha.getText().toString().equalsIgnoreCase("")) || (desde.getText().toString().equals("")) || (hasta.getText().toString().equals("")))
                    {
                        Toast.makeText(getApplicationContext(),"No se hicieron cambios",Toast.LENGTH_LONG).show();
                        pop.dismiss();
                    }
                    else {
                        CN.r_altas("https://apisweb.000webhostapp.com/prueba/nativa_babycare/nativa_sueno.php?", fecha.getText().toString(),
                                desde.getText().toString(),hasta.getText().toString());
                        Toast.makeText(getApplicationContext(),"Registro Correcto",Toast.LENGTH_LONG).show();
                        pop.dismiss();
                    }
                }
            });
            pop.show();
        }
        else if(v.getId()==R.id.imageView14)
        {
            pop.setContentView(R.layout.rcomida);
            final EditText fecha, hora, descripcion;
            ImageView ver;

            fecha=(EditText)pop.findViewById(R.id.editText3);
            hora=(EditText)pop.findViewById(R.id.editText4);
            descripcion=(EditText)pop.findViewById(R.id.editText5);
            equis=(TextView) pop.findViewById(R.id.equis);
            ver=(ImageView) pop.findViewById(R.id.imageView2);
            equis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                }
            });
            ver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((fecha.getText().toString().equalsIgnoreCase("")) || (hora.getText().toString().equals("")) || (descripcion.getText().toString().equals("")))
                    {
                        Toast.makeText(getApplicationContext(),"No se hicieron cambios",Toast.LENGTH_LONG).show();
                        pop.dismiss();
                    }
                    else {
                        CN.r_altas("https://apisweb.000webhostapp.com/prueba/nativa_babycare/nativa_comida.php?", fecha.getText().toString(),hora.getText().toString(),
                                descripcion.getText().toString());
                        Toast.makeText(getApplicationContext(),"Registro Correcto",Toast.LENGTH_LONG).show();
                        pop.dismiss();
                    }
                }
            });
            pop.show();
        }
        else if(v.getId()==R.id.imageView13)
        {
            pop.setContentView(R.layout.rbano);
            final EditText fecha, hora, tipo;
            ImageView ver;

            fecha=(EditText)pop.findViewById(R.id.editText3);
            hora=(EditText)pop.findViewById(R.id.editText4);
            tipo=(EditText)pop.findViewById(R.id.editText5);
            equis=(TextView) pop.findViewById(R.id.equis);
            ver=(ImageView) pop.findViewById(R.id.imageView2);

            equis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                }
            });
            ver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((fecha.getText().toString().equalsIgnoreCase("")) || (hora.getText().toString().equals("")) || (tipo.getText().toString().equals("")))
                    {
                        Toast.makeText(getApplicationContext(),"No se hicieron cambios",Toast.LENGTH_LONG).show();
                        pop.dismiss();
                    }
                    else {
                        CN.r_altas("https://apisweb.000webhostapp.com/prueba/nativa_babycare/nativa_bano.php?", fecha.getText().toString(),hora.getText().toString(),
                                tipo.getText().toString());
                        Toast.makeText(getApplicationContext(),"Registro Correcto",Toast.LENGTH_LONG).show();
                        pop.dismiss();
                    }
                }
            });
            pop.show();
        }
    }


}
