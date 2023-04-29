package nayely.gilesvaldez.actividad7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Tenemos que poner SQLiteOpenHelper para llamar a la base de datos
public class CBaseDatos extends SQLiteOpenHelper
{
    public CBaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Estamos en la base de datos y estamos creando la tabla
        sqLiteDatabase.execSQL("Create table Productos(CodPro int Primary key, Nombre text,Stock int,Precio int,Fecha text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
