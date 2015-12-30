package br.com.interaje.easytrade.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import br.com.interaje.easytrade.activites.Login;
import br.com.interaje.easytrade.model.Cliente;

/**
 * Created by lacroiix on 30/12/2015.
 */
public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "EasyTradePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_ID = "id";

    public static final String KEY_NAME = "name";

    public static final String KEY_PHONE = "phone";

    public static final String KEY_EMAIL = "email";

    public static final String KEY_ID_GCM_USER = "idGcmUser";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create Login session
     *
     * @param user
     */
    public void createLoginSession(Cliente user) {
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_NAME, user.getNome());

        editor.putString(KEY_PHONE, user.getTelefone());

        editor.putLong(KEY_ID, user.getId());

        editor.putString(KEY_EMAIL, user.getEmail());

        // commit changes
        editor.commit();
    }

    /**
     * Check login method will check user login status If false it will redirect
     * user to login page Else won't do anything.
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            Toast.makeText(_context, "Por favor, faça login novamente.",
                    Toast.LENGTH_SHORT).show();
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    /**
     * Get stored session data
     */
    public Cliente getUserDetails() {
        Cliente user = new Cliente();

        user.setId(pref.getLong(KEY_ID, 0l));

        user.setNome(pref.getString(KEY_NAME, "Usuário não cadastrado."));

        user.setEmail(pref.getString(KEY_EMAIL, "Que tal se registrar ou acessar o sistema?"));

        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * *
     */
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    /**
     * Add BOOLEAN params to session.
     *
     * @param param
     * @param value
     */
    public void addSessionParam(String param, Boolean value) {
        editor.putBoolean(param, value);
        editor.commit();
    }

    /**
     * Add STRING params to session.
     *
     * @param param
     * @param value
     */
    public void addSessionParam(String param, String value) {
        editor.putString(param, value);
        editor.commit();
    }

    /**
     * Add LONG params to session.
     *
     * @param param
     * @param value
     */
    public void addSessionParam(String param, Long value) {
        editor.putLong(param, value);
        editor.commit();
    }

    /**
     * Return a boolean session param from session
     *
     * @param paramName
     * @return
     */
    public Boolean getBooleanSessionParam(String paramName) {
        return pref.getBoolean(paramName, false);
    }

    /**
     * Return a String session param from session
     *
     * @param paramName
     * @return
     */
    public String getStringSessionParam(String paramName) {
        return pref.getString(paramName, null);
    }
}