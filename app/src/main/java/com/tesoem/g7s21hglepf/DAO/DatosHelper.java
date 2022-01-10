package com.tesoem.g7s21hglepf.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatosHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "dbg7s21";
    private static int DB_VERSION = 1;

    public static class tabladatos{
        public static String TABLA_NAME= "tbdatos";
        public static String TABLA_ID = "id";
        public static String TABLA_NOMBRE = "nombre";
        public static String TABLA_EDAD = "edad";
        public static String TABLA_CORREO = "correo";
    }

    private static String CREAR_TABLA = "create table " + tabladatos.TABLA_NAME + "(" + tabladatos.TABLA_ID + " integer not null primary key autoincrement, " + tabladatos.TABLA_NOMBRE + " varchar, " + tabladatos.TABLA_EDAD + " integer, " + tabladatos.TABLA_CORREO + " varchar)";

    private static String ELIMINA_TABLA = "drop table " + tabladatos.TABLA_NAME;

    public DatosHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ELIMINA_TABLA);
        onCreate(db);

    }
}
