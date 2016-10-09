package com.example.joser.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joser on 20/11/2016.
 */
public class Add extends AppCompatActivity {
    //instancia de la variable de conexion al a base de datos del activity principal
    DBNotesDataSource db = MainActivity.db;
    /**
     * metodo onCreate del activity agregar
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instancias de los elementos de la vista
        setContentView(R.layout.activity_add);
        final EditText title = (EditText) findViewById(R.id.TextTitle);
        final EditText site = (EditText) findViewById(R.id.TextSite);
        final EditText descrip = (EditText) findViewById(R.id.TextDescription);
        final TextView date = (TextView) findViewById(R.id.textHora);
        ImageButton add = (ImageButton) findViewById(R.id.add);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        //variable simpleDateFormat para octener la fecha del sistema
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String dt = s.format(new Date());
        date.setText(dt);
        //metodo onClick del button agregar
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verifica que los campos no esten bacios
                if(title.length()>0 & site.length()>0 & descrip.length()>0){
                    //variable de tipo nota para guardar los datos
                    Note note = new Note();
                    note.setTitle(title.getText().toString());
                    note.setDate(date.getText().toString());
                    note.setSite(site.getText().toString());
                    note.setDescrip(descrip.getText().toString());
                    //abrir la conexion de la base de datos
                    db.open();
                    //agregar la nueva nota a la tabla
                    db.create(note);
                    //cerrar la conexion de la base de datos
                    db.close();
                    //intent para desplegar el activity principal
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivityForResult(intent, 0);
                    //finalizacion del activity agregar
                    finish();
                }else{
                    Log.i("Notas","Hay campos vacios que son requeridos por el sistema!!");
                }
            }
        });
        //metodo onClick del button volver
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent para desplegar el activity principal
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                //finalizacion del activity agregar
                finish();
            }
        });
    }
}
