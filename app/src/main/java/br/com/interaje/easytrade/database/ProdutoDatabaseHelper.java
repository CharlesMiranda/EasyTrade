package br.com.interaje.easytrade.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by charles on 21/11/15.
 */
public class ProdutoDatabaseHelper extends SQLiteOpenHelper {

    public static final String NOME_TABELA = "produto";
    public static final String COLUMN_ID = "_id";
    public final static String COLUMN_NOME = "nome";
    public static final String COLUMN_VALOR = "valor";
    public static final String COLUMN_QUANTIDADE = "quantidade";
    public static final String COLUMN_DESCRICAO = "descricao";
    public static final String COLUMN_FOTO = "foto";

    public static final String CREATE_TABLE = "create table produto"
            + "("
            + "_id" + " integer primary key autoincrement, "
            + "nome" + " text not null, "
            + "valor" + " real not null, "
            + "quantidade" + " integer not null, "
            + "descricao" + " text, "
            + "foto" + " blob "
            + ");";


    public ProdutoDatabaseHelper(Context context) {
        super(context, ProdutoDatabase.DATABASE_NAME, null, ProdutoDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*String sql = " drop table produto";
        db.execSQL(sql);

        onCreate(db);*/
    }
}

