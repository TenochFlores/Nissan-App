package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLite_OpenHelper extends SQLiteOpenHelper {

    private static final String CREATE_TABLE_CARRO =
            "CREATE TABLE carro(_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Nombre TEXT, Precio TEXT);";

    // Agrega el código para crear la tabla "Pedidos"
    private static final String CREATE_TABLE_PEDIDOS =
            "CREATE TABLE pedidos(_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Cliente_ID INTEGER, Carro_ID INTEGER, " +
                    "FOREIGN KEY(Cliente_ID) REFERENCES usuarios(_ID)," +
                    "FOREIGN KEY(Carro_ID) REFERENCES carro(_ID));";

    public SQLite_OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE usuarios(_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Nombre TEXT, Rfc TEXT, Correo TEXT, Password TEXT);";

        db.execSQL(query);
        db.execSQL(CREATE_TABLE_CARRO); // Agregado para crear la tabla "carro"
        db.execSQL(CREATE_TABLE_PEDIDOS);
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

    public void insertarCarro(String nombre, String precio) {
        ContentValues carroValues = new ContentValues();
        carroValues.put("Nombre", nombre);
        carroValues.put("Precio", precio);

        this.getWritableDatabase().insert("carro", null, carroValues);
    }

    public void insertarPedido(int clienteID, int carroID) {
        ContentValues pedidoValues = new ContentValues();
        pedidoValues.put("Cliente_ID", clienteID);
        pedidoValues.put("Carro_ID", carroID);

        this.getWritableDatabase().insert("pedidos", null, pedidoValues);
    }

    public Cursor getUsuarioByCorreoPassword(String email, String pas) throws SQLException {
        Cursor m_cursor = null;

        m_cursor = this.getReadableDatabase().query("usuarios",new String[]{"_ID",
                "Nombre","Rfc","Correo","Password"},"Correo LIKE '"+email+"' " +
                "AND Password LIKE '"+pas+"'",null,null,null,null);

        return m_cursor;
    }

    public Cursor getPedidosDeUnCliente(int clienteID) throws SQLException {
        String query = "SELECT * FROM pedidos WHERE Cliente_ID = " + clienteID;
        return this.getReadableDatabase().rawQuery(query, null);
    }

    public Cursor getPedidoById(int pedidoId) throws SQLException {
        String query = "SELECT * FROM pedidos WHERE _ID = " + pedidoId;
        return this.getReadableDatabase().rawQuery(query, null);
    }

    public Cursor getCarroById(int carroId) throws SQLException {
        String query = "SELECT * FROM carro WHERE _ID = " + carroId;
        return this.getReadableDatabase().rawQuery(query, null);
    }

    public Cursor getUsuarioById(int usuarioId) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE _ID = " + usuarioId;
        return this.getReadableDatabase().rawQuery(query, null);
    }
}
