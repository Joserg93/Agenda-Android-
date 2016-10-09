package com.example.joser.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by joser on 16/10/2016.
 */
public class DBNotesDataSource {
    //variables SQlite
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    //array de los atributos de la tabla note
    private static final String[] allcolums = {
            DBNotes.id,
            DBNotes.title,
            DBNotes.dt,
            DBNotes.site,
            DBNotes.descrip
    };
    /**
     * metodo constructor
     * @param context
     */
    public DBNotesDataSource(Context context){
        dbhelper = new DBNotes(context);
    }
    /**
     *metodo para abrir la conexion a la base de datos
     */
    public void open(){
        db = dbhelper.getWritableDatabase();
    }
    /**
     *metodo para cerrar la conexion a la base de datos
     */
    public void close(){
        dbhelper.close();
    }
    /**
     * metodo para agregar notas a la base de datos
     * @param note
     * @return
     */
    public Note create(Note note){
        //variable contentValues para insertar los datos a la base datos
        ContentValues values = new ContentValues();
        values.put(DBNotes.title,note.getTitle());
        values.put(DBNotes.dt,note.getDate());
        values.put(DBNotes.site,note.getSite());
        values.put(DBNotes.descrip,note.getDescrip());
        long insertID = db.insert("Note",null,values);
        note.setId(insertID);
        return note;
    }
    /**
     * metodo para cargar los datos de una nota en especifico
     * @param t
     * @param d
     * @return
     */
    public Note loadView(String t, String d){
        //string array con los argumentos de la consulta where
        String[] whereArgs = new String[]{t, d};
        //variable cursor que obtiene el resultado de la consulta a la base de datos
        Cursor c = db.query("Note", allcolums, "title =? AND date =?", whereArgs, null, null,null);
        //variable de clase note que guarda los campos del cursor
        Note note = loadItemsView(c);
        return note;
    }
    /**
     * metodo para cargar todas las notas
     * @return
     */
    public ArrayList<Note> load(){
        //variable cursor que obtiene el resultado de la consulta a la base de datos
        Cursor c = db.query("Note", allcolums, null, null, null, null,null);
        //variable de clase note que guarda los registros del cursor
        ArrayList<Note> note = loadItems(c);
        return note;
    }
    /**
     * metodo que retorna una clase note con la informacion del resgistro
     * @param cursor
     * @return
     */
    private Note loadItemsView(Cursor cursor){
        //variable array-list de tipo note
        ArrayList<Note> noteList = new ArrayList<Note>();
        //variable de tipo note
        Note note = new Note();;
        //verifica si el curso contiene registros
        if (cursor.getCount()>0){
            //ciclo para recorrer el cursor
            while (cursor.moveToNext()) {
                note.setId(cursor.getLong(cursor.getColumnIndex(DBNotes.id)));
                note.setTitle(cursor.getString(cursor.getColumnIndex(DBNotes.title)));
                note.setDate(cursor.getString(cursor.getColumnIndex(DBNotes.dt)));
                note.setSite(cursor.getString(cursor.getColumnIndex(DBNotes.site)));
                note.setDescrip(cursor.getString(cursor.getColumnIndex(DBNotes.descrip)));
                noteList.add(note);
            }
        }
        return note;
    }
    /**
     * metodo que retorna una lista note con la informacion de los resgistros
     * @param cursor
     * @return
     */
    private ArrayList<Note> loadItems(Cursor cursor){
        //variable array-list de tipo note
        ArrayList<Note> noteList = new ArrayList<Note>();
        //verifica si el curso contiene registros
        if (cursor.getCount()>0){
            //ciclo para recorrer el cursor
            while (cursor.moveToNext()) {
                Note note = new Note();
                note.setId(cursor.getLong(cursor.getColumnIndex(DBNotes.id)));
                note.setTitle(cursor.getString(cursor.getColumnIndex(DBNotes.title)));
                note.setDate(cursor.getString(cursor.getColumnIndex(DBNotes.dt)));
                note.setSite(cursor.getString(cursor.getColumnIndex(DBNotes.site)));
                note.setDescrip(cursor.getString(cursor.getColumnIndex(DBNotes.descrip)));
                noteList.add(note);
            }
        }
        return noteList;
    }
}
