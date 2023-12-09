package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite_OpenHelper extends SQLiteOpenHelper {

    public SQLite_OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE usuarios(_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Nombre TEXT, Rfc TEXT, Correo TEXT, Password TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void abrir() {
        this.getWritableDatabase();
    }

    public void cerrar(){
        this.close();
    }

    public void insertarRegistro(String nombre, String rfc, String correo, String password){
        ContentValues usuario = new ContentValues();
        usuario.put("Nombre",nombre);
        usuario.put("Rfc",rfc);
        usuario.put("Correo",correo);
        usuario.put("Password",password);

        this.getWritableDatabase().insert("usuarios",null,usuario);
    }

    public Cursor getUsuarioByCorreoPassword(String email, String pas) throws SQLException {
        Cursor m_cursor = null;

        m_cursor = this.getReadableDatabase().query("usuarios",new String[]{"_ID",
                "Nombre","Rfc","Correo","Password"},"Correo LIKE '"+email+"' " +
                "AND Password LIKE '"+pas+"'",null,null,null,null);

        return m_cursor;
    }
}
