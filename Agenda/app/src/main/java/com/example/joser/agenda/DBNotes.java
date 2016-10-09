package com.example.joser.agenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by joser on 16/10/2016.
 */
public class DBNotes extends SQLiteOpenHelper {
    //variable string con la sentencia de creacion de la tabla en la base de datos
    String sqlCreate = "CREATE TABLE Note ( id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, date TEXT, site TEXT, description TEXT )";
    //variables string de los atributos de la tabla de la base de datos
    public static final String id = "id";
    public static final String title = "title";
    public static final String dt = "date";
    public static final String site = "site";
    public static final String descrip = "description";
    public static final String DBName = "DBNotes";
    public static final int DBV = 1;
    /**
     * metodo onCreate de la base de datos
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }
    /**
     * metodo onUpgrate de la base datos
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    /**
     * metodo constructor de la clase de la base de datos
     * @param context
     */
    public DBNotes(Context context) {
        super(context, DBName, null, DBV);
    }
}
