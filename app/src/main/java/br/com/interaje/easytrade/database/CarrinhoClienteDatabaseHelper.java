package br.com.interaje.easytrade.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by charles on 21/11/15.
 */
public class CarrinhoClienteDatabaseHelper extends SQLiteOpenHelper {

    public static final String NOME_TABELA = "carrinho_cliente";
    public static final String COLUMN_ID = "_id";
    public final static String COLUMN_CLIENTE = "cliente_id";

    public static final String CREATE_TABLE = "create table carrinho_cliente"
            + "("
            + "_id" + " integer primary key autoincrement, "
            + "cliente_id" + " integer not null "
            + ");";


    public CarrinhoClienteDatabaseHelper(Context context) {
        super(context, CarrinhoClienteDatabase.DATABASE_NAME, null, CarrinhoClienteDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

