package com.example.forms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class LocalDB extends SQLiteOpenHelper {

    public static final String TAG = "sql";
    public static final String NOME_BANCO = "MeuBancodeDados.db";
    public static final int VERSAO_BANCO = 15;
    public static final String TABLE_NAME = "locais";
    public static final String COLUNA1 = "latitude";
    public static final String COLUNA2 = "longitude";

    public LocalDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUNA1 + " DOUBLE,"
                    + COLUNA2 + " DOUBLE )";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        Log.d(TAG, "Tabela " + TABLE_NAME + " criada com sucesso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long salvaLocal(Local local) {
        long id = local.get_id();
        SQLiteDatabase db = getWritableDatabase(); // abre conexão com o banco de dados
        try {
            ContentValues valores = new ContentValues();
            valores.put(COLUNA1, local.getLatitude());
            valores.put(COLUNA2, local.getLongitude());

            if (id != 0) {
                String[] whereArgs = new String[]{String.valueOf(id)};
                int count = db.update(TABLE_NAME, valores, "_id=?", whereArgs);
                return count;
            } else {
                id = db.insert(TABLE_NAME, null, valores);
                return id;
            }
        } catch (SQLException e) {
            Log.d(TAG, "Falha ao se conectar ao Banco de Dados ");
        } finally {
            db.close();
        }
        return id;
    }

    public ArrayList<Local> findALL() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Local> lista = new ArrayList<Local>();
        try {
            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    long id = c.getLong(c.getColumnIndex("_id"));
                    double latitude = c.getDouble(c.getColumnIndex("latitude"));
                    double longitude = c.getDouble(c.getColumnIndex("longitude"));

                    Local currentContact = new Local(id, latitude, longitude);
                    lista.add(currentContact);
                } while (c.moveToNext());
            }
            return lista;
        } finally {
            db.close();
        }
    }

    public Local buscaLocal(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

        //SQLiteDatabase db =getReadableDatabase();
        //Cursor c = db.rawQuery("SELECT latitude, longitude FROM locais WHERE _id = ?", new String[] {String.valueOf(id)});
        c.moveToPosition(id);

        double latitude = c.getDouble(1);
        double longitude = c.getDouble(2);


        Local transicao = new Local(id, latitude, longitude);
        c.close();

        return transicao;

    }
}