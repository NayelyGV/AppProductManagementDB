package nayely.gilesvaldez.actividad7;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Date;

public class CProductos {
    ArrayAdapter<String> Adaptador;
    public void  Guardar(Context Contexto, int Cod, String Nom, int Sto, int Pre, String Fec)
    {
        //Aqui se llama la base de datos y se le pone nombre
        CBaseDatos Obj1=new CBaseDatos(Contexto, "BDVentas.db", null,1);
        //Se hace un metodo con la clase y lo enlazamos y decimos que es de escritura
        SQLiteDatabase Obj2=Obj1.getWritableDatabase();
        //Guardamos los 4 datos en la variable reg
        ContentValues Reg=new ContentValues();
        Reg.put("CodPro",Cod);
        Reg.put("Nombre",Nom);
        Reg.put("Stock",Sto);
        Reg.put("Precio",Pre);
        Reg.put("Fecha", Fec);

        //Con el obj2 llamanos al metodo insertar y luego lo cerramos
        Obj2.insert("Productos",null,Reg);
        Obj2.close();
    }
    public void Mostrar(Context Contexto, GridView Gv)
    {
        //Aqui se llama la base de datos y se le pone nombre
        CBaseDatos Obj1=new CBaseDatos(Contexto, "BDVentas.db", null,1);
        //Se hace un metodo con la clase y lo enlazamos y decimos que es de lectura
        SQLiteDatabase Obj2=Obj1.getReadableDatabase();
        //Aqui hacemos una consulta
        Cursor Cursor1=Obj2.rawQuery("Select * From Productos", null);
        //Declaramos una variable entera para saber cuantas filas trajo la consulta
        int NroF=Cursor1.getCount();
        //Va a tener la cantidad de filas +1 y eso se multiplica *4 datos
        String[] Datos=new  String[(NroF+1)*5];

        Datos[0]="COD PROD";
        Datos[1]="NOMBRE";
        Datos[2]="STOCK";
        Datos[3]="PRECIO";
        Datos[4]="FECHA";
        int VisDatos=4;
        //Esto significa que luego de que rellenara esos datos vuelve al primero
        if (Cursor1.moveToFirst())
        {
            do {
                VisDatos++;
                Datos[VisDatos]=Cursor1.getString(0);
                VisDatos++;
                Datos[VisDatos]=Cursor1.getString(1);
                VisDatos++;
                Datos[VisDatos]=Cursor1.getString(2);
                VisDatos++;
                Datos[VisDatos]=Cursor1.getString(3);
                VisDatos++;
                Datos[VisDatos]=Cursor1.getString(4);
            }
            //El de arriba repetira lo mismo hasta que se mueva al siguiente
            while (Cursor1.moveToNext());
        }
        //Cerramos el obj2
        Obj2.close();
        Adaptador = new ArrayAdapter<String>(Contexto, android.R.layout.simple_list_item_1,Datos);
        Gv.setAdapter(Adaptador);
    }

    public ContentValues Buscar(Context Contexto, int Cod)
    {
        //Aqui se llama la base de datos y se le pone nombre
        CBaseDatos Obj1=new CBaseDatos(Contexto, "BDVentas.db", null,1);
        //Se hace un metodo con la clase y lo enlazamos y decimos que es de lectura
        SQLiteDatabase Obj2=Obj1.getReadableDatabase();

        ContentValues Reg=new ContentValues();
        //Aqui hacemos una consulta
        //Si luego de select ponemos * significa todos sus datos, en total 4 datos
        Cursor Cursor1=Obj2.rawQuery("Select Nombre, Stock,Precio,Fecha From Productos where CodPro= "+Cod, null);
        if(Cursor1.moveToFirst())
        {
            //O sale con los datos que encontro
            Reg.put("Nombre",Cursor1.getString(0));
            Reg.put("Stock",Cursor1.getString(1));
            Reg.put("Precio",Cursor1.getString(2));
            Reg.put("Fecha",Cursor1.getString(3));
        }
        else
        {
            //O sale con el texto no hay
            Reg.put("Nombre","No hay");
            Reg.put("Stock","No hay");
            Reg.put("Precio","No hay");
            Reg.put("Fecha","No hay");
        }
        //Cerramos
        Obj2.close();
        //Retornamos Reg
        return Reg;
    }
    public void  Eliminar(Context Contexto, int Cod) {
        //Aqui se llama la base de datos y se le pone nombre
        CBaseDatos Obj1 = new CBaseDatos(Contexto, "BDVentas.db", null, 1);
        //Se hace un metodo con la clase y lo enlazamos y decimos que es de escritura
        SQLiteDatabase Obj2 = Obj1.getWritableDatabase();
        //Se crea la variable para eliminar
        //Dice que si codpro es igual a cod se elimina
        int CantidadEliminados=Obj2.delete("Productos","CodPro= " +Cod,null);
        if (CantidadEliminados==0)
        {
            Toast.makeText(Contexto,"No existe dicho codigo", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Contexto,"Producto Eliminado", Toast.LENGTH_SHORT).show();
        }
        Obj2.close();
    }
    public void  Modificar(Context Contexto, int Cod, String Nom, int Sto,int Pre,String Fec)
    {
        //Aqui se llama la base de datos y se le pone nombre
        CBaseDatos Obj1=new CBaseDatos(Contexto, "BDVentas.db", null,1);
        //Se hace un metodo con la clase y lo enlazamos y decimos que es de escritura
        SQLiteDatabase Obj2=Obj1.getWritableDatabase();
        //Guardamos los 4 datos en la variable reg
        ContentValues Reg=new ContentValues();
        Reg.put("CodPro",Cod);
        Reg.put("Nombre",Nom);
        Reg.put("Stock",Sto);
        Reg.put("Precio",Pre);
        Reg.put("Fecha",Fec);


        int CantidadModificados=Obj2.update("Productos",Reg,"CodPro= "+Cod,null);
        if(CantidadModificados==0)
        {
            Toast.makeText(Contexto,"No hay ese codigo", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Contexto,"Datos Modificados", Toast.LENGTH_SHORT).show();
        }
        Obj2.close();
    }
    public void Informacion(Context Contexto, GridView Gv)
    {
        //Aqui se llama la base de datos y se le pone nombre
        CBaseDatos Obj1=new CBaseDatos(Contexto, "BDVentas.db", null,1);
        //Se hace un metodo con la clase y lo enlazamos y decimos que es de lectura
        SQLiteDatabase Obj2=Obj1.getReadableDatabase();
        //Aqui hacemos una consulta
        Cursor Cursor1=Obj2.rawQuery("Select * From Productos where Stock<50", null);
        //Declaramos una variable entera para saber cuantas filas trajo la consulta
        int NroF=Cursor1.getCount();
        //Va a tener la cantidad de filas +1 y eso se multiplica *4 datos
        String[] Datos=new  String[(NroF+1)*5];

        Datos[0]="COD PROD";
        Datos[1]="NOMBRE";
        Datos[2]="STOCK";
        Datos[3]="PRECIO";
        Datos[4]="FECHA";
        int VisDatos=4;
        //Esto significa que luego de que rellenara esos datos vuelve al primero
        if (Cursor1.moveToFirst())
        {
            do {
                VisDatos++;
                Datos[VisDatos]=Cursor1.getString(0);
                VisDatos++;
                Datos[VisDatos]=Cursor1.getString(1);
                VisDatos++;
                Datos[VisDatos]=Cursor1.getString(2);
                VisDatos++;
                Datos[VisDatos]=Cursor1.getString(3);
                VisDatos++;
                Datos[VisDatos]=Cursor1.getString(4);
            }
            //El de arriba repetira lo mismo hasta que se mueva al siguiente
            while (Cursor1.moveToNext());
        }
        //Cerramos el obj2
        Obj2.close();
        Adaptador = new ArrayAdapter<String>(Contexto, android.R.layout.simple_list_item_1,Datos);
        Gv.setAdapter(Adaptador);
    }
}
