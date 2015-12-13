package br.com.interaje.easytrade.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by charles on 21/11/15.
 */
public class ClienteDatabaseHelper extends SQLiteOpenHelper {

    public static final String NOME_TABELA = "cliente";
    public static final String COLUMN_ID = "_id";
    public final static String COLUMN_NOME = "nome";
    public static final String COLUMN_TELEFONE = "telefone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_OBSERVACAO = "observacao";
    public static final String COLUMN_FOTO = "foto";

    public static final String CREATE_TABLE = "create table cliente"
            + "("
            + "_id" + " integer primary key autoincrement, "
            + "nome" + " text not null, "
            + "telefone" + " text not null, "
            + "email" + " text not null, "
            + "observacao" + " text, "
            + "foto" + " blob "
            + ");";


    public ClienteDatabaseHelper(Context context) {
        super(context, ClienteDatabase.DATABASE_NAME, null, ClienteDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

