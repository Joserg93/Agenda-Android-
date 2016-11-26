package com.example.joser.agenda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;
/**
 * Created by joser on 16/10/2016.
 */
public class MainActivity extends AppCompatActivity {
    //instancia de la variable de conexion al a base de datos
    public static DBNotesDataSource db;
    //variable array-list de tipo note
    ArrayList<Note> note;
    //variable tipo listView
    ListView list;
    //variable tipo context para la base de datos
    final Context context = this;
    /**
     * metodo onCreate del activity principal
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instancias de los elementos de la vista
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        ImageButton add = (ImageButton) findViewById(R.id.add);
        //creacion de la base de datos
        db = new DBNotesDataSource(this);
        //abrir la conexion de la base de datos
        db.open();
        //cargar las notas de la base de datos
        note = db.load();
        //verifica que contenga registros
        if (note.size() == 0){
            //crea nuevos datos en la base de datos
            createData();
            //cargar las notas de la base de datos
            note = db.load();
        }
        //carga los datos en el listView
        display();
        //metodo onClick del button agregar
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Add.class);
                startActivityForResult(intent, 0);
                db.close();
                finish();
            }
        });
        //metodo onItemClick del listView
        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                //obtiene los datos del item seleccionado
                String item = list.getItemAtPosition(position).toString();
                //variable string para el titulo
                String title="";
                //variable string para la fecha
                String date="";
                //divide los datos en un array de string
                String[] search = item.split("\n");
                //divide los datos en un array de string
                String[] searchTitle = search[0].split(" ");
                //divide los datos en un array de string
                String[] searchDate = search[1].split(" ");
                //concatena el titulo
                for(int x=1; x<searchTitle.length;x++){
                    if(x==1){
                        title = title + searchTitle[x];
                    }else{
                        title = title + " " + searchTitle[x];
                    }
                }
                //concatena la fecha
                for(int x=2; x<searchDate.length;x++){
                    if(x==2){
                        date = date + searchDate[x];
                    }else{
                        date = date + " " + searchDate[x];
                    }
                }
                //variable de tipo note
                Note note;
                //carga todos los datos relacionados a ese registro de la base de datos
                note = db.loadView(title,date);
                //variable de tipo alertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        context);
                //se agrega el contenido del dialog
                builder.setMessage("Titulo: " + note.getTitle().toString() +
                        "\n" + "Hora: " + note.getDate().toString() + "\n" + "Lugar: " + note.getSite().toString() +
                        "\n" + "Descripcion: " + note.getDescrip().toString());
                //se agrega el titulo del dialog
                builder.setTitle("Detalle de Notas");
                //se agrega un button al dialog
                builder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        //button de tipo cancelar
                        dialog.cancel();
                    }
                });
                //se crea el alertDialog con las nuevas propiedades
                AlertDialog alertDialog = builder.create();
                //se muestra el alertDialog
                alertDialog.show();
            }
        });
    }
    /**
     * metodo para refrescar el listView
     */
    public void display(){
        //variable de tipo arrayAdapter
        ArrayAdapter<Note> adapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,note);
        //se setean los datos en el listView
        list.setAdapter(adapter);
    }
    /**
     * metodo para agregar datos si la base de datos esta vacia
     */
    private void createData(){
        //variable de tipo note
        Note note = new Note();
        note.setTitle("jugar pacman");
        note.setDate("01-02-2016 11:20:30");
        note.setSite("casa");
        note.setDescrip("jugar pacman en la compu");
        //agrega la nota a la base de datos
        db.create(note);
        note.setTitle("comer");
        note.setDate("01-02-2016 12:40:10");
        note.setSite("casa");
        note.setDescrip("hacer la comida");
        //agrega la nota a la base de datos
        db.create(note);
        note.setTitle("estudiar");
        note.setDate("01-02-2016 02:15:40");
        note.setSite("casa");
        note.setDescrip("estudiar la materia nueva");
        //agrega la nota a la base de datos
        db.create(note);
    }
}

