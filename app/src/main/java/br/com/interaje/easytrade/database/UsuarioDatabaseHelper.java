package br.com.interaje.easytrade.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by charles on 21/11/15.
 */
public class UsuarioDatabaseHelper extends SQLiteOpenHelper {

    public static final String NOME_TABELA = "usuario";
    public static final String COLUMN_ID = "_id";
    public final static String COLUMN_NOME = "nome";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_SENHA = "senha";

    public static final String CREATE_TABLE = "create table usuario"
            + "("
            + "_id" + " integer primary key autoincrement, "
            + "nome" + " text not null, "
            + "email" + " text not null, "
            + "senha" + " text not null "
            + ");";


    public UsuarioDatabaseHelper(Context context) {
        super(context, UsuarioDatabase.DATABASE_NAME, null, UsuarioDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

