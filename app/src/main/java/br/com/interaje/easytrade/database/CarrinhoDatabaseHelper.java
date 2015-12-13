package br.com.interaje.easytrade.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by charles on 21/11/15.
 */
public class CarrinhoDatabaseHelper extends SQLiteOpenHelper {

    public static final String NOME_TABELA = "carrinho";
    public static final String COLUMN_ID = "_id";
    public final static String COLUMN_PRODUTO = "produto_id";

    public static final String CREATE_TABLE = "create table carrinho"
            + "("
            + "_id" + " integer primary key autoincrement, "
            + "produto_id" + " integer not null "
            + ");";


    public CarrinhoDatabaseHelper(Context context) {
        super(context, CarrinhoDatabase.DATABASE_NAME, null, CarrinhoDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

