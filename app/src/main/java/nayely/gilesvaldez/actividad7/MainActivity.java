package nayely.gilesvaldez.actividad7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText Codi,Nomb,Stoc,PreU,campoFecha;
    GridView Gv;
    int año,mes,dia,hora,min,seg;
    //static  final int TIPO_DIALOGO=0;
    //static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;

    //Llamamos a la clase productos
    CProductos Obj=new CProductos();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Codi=findViewById(R.id.Codigo);
        Nomb=findViewById(R.id.Nombre);
        Stoc=findViewById(R.id.Stock);
        PreU=findViewById(R.id.Precio);
        Gv=findViewById(R.id.Grid);
        campoFecha=findViewById(R.id.Fecha);

        Obj.Mostrar(MainActivity.this,Gv);

    }

    public void mostrarFecha(View vista)
    {
       /* DateTimeFormatter dtf=null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime now=LocalDateTime.now();
            dtf=DateTimeFormatter.ofPattern("YYYY");
            String year= dtf.format(now);
            dtf=DateTimeFormatter.ofPattern("MM");
            String month= dtf.format(now);
            dtf=DateTimeFormatter.ofPattern("dd");
            String day= dtf.format(now);
            dtf=DateTimeFormatter.ofPattern("dd-MM-YYYY");
            String customFormat= dtf.format(now);
            campoFecha.setText(customFormat);
        }*/

        final Calendar calendario=Calendar.getInstance();
        /*DateFormat dateFormat=new SimpleDateFormat("dd/mm/yyyy");
        String strDate=dateFormat.format(calendario);*/
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mes=calendario.get(Calendar.MONTH)+1;
        año=calendario.get(Calendar.YEAR);



        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                campoFecha.setText(dayOfMonth+"-"+(monthOfYear)+"-"+year);

            }

        },dia,mes,año);
        //DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        //String strDate=dateFormat.format(calendario);
        datePickerDialog.show();

        /*HORA
        hora=calendario.get(Calendar.HOUR_OF_DAY);
        min=calendario.get(Calendar.MINUTE);
        seg=calendario.get(Calendar.SECOND);
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                campoFecha.setText(hourOfDay+"/"+minute+"/");
            }
        },hora,min,false);
        timePickerDialog.show();*/


    }

    public void Guardar(View vista)
    {
        int Cod,Sto,Pre;
        String Nom;
        String Fec;

        Cod=Integer.parseInt(Codi.getText().toString());
        Nom=Nomb.getText().toString();
        Sto=Integer.parseInt(Stoc.getText().toString());
        Pre=Integer.parseInt(PreU.getText().toString());
        Fec=campoFecha.getText().toString();
        //isEmpty=significa cuando esta vacio
        //En este caso si el codigo y el nombre estan vacio mostrara el siguiente texto
        if (Codi.getText().toString().isEmpty()||Nom.isEmpty())
        {
            //Mostrar datos incompletos
            Toast.makeText(this,"Datos Incompletos",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Obj.Guardar(MainActivity.this,Cod,Nom,Sto,Pre,Fec);
            Obj.Mostrar(MainActivity.this,Gv);
            //Aqui limpiamos los datos
            Codi.setText("");
            Nomb.setText("");
            Stoc.setText("");
            PreU.setText("");
            campoFecha.setText("");
        }

    }

    public void Buscar(View vista)
    {
        //Declaramos una variable para guardar los datos
        ContentValues Reg= new ContentValues();
        //Declaramos una variable de tipo entero para traer los datos del diseño
        int CodBuscar=Integer.parseInt(Codi.getText().toString());
        //Aqui hacemos una comparacion si esta vacio o no el codigo, y que hara en ambos casos
        if(Codi.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Ingrese el codigo", Toast.LENGTH_SHORT).show();
        }
        else{
            //Aqui se hace la busqueda

            //Llamamos el metodo
            Reg=Obj.Buscar(MainActivity.this, CodBuscar);
            //Guardamos los 3 datos en la variable
            Nomb.setText(Reg.get("Nombre").toString());
            Stoc.setText(Reg.get("Stock").toString());
            PreU.setText(Reg.get("Precio").toString());
            campoFecha.setText(Reg.get("Fecha").toString());
        }

    }
    public void Eliminar(View vista)
    {

        //Declaramos una variable de tipo entero para traer los datos del diseño
        int CodEliminar=Integer.parseInt(Codi.getText().toString());
        //Aqui hacemos una comparacion si esta vacio o no el codigo, y que hara en ambos casos
        if(Codi.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Ingrese el codigo", Toast.LENGTH_SHORT).show();
        }
        else{

            Obj.Eliminar(MainActivity.this, CodEliminar);
            Obj.Mostrar(MainActivity.this,Gv);
            //Eliminamos las variables
            Codi.setText("");
            Nomb.setText("");
            Stoc.setText("");
            PreU.setText("");
            campoFecha.setText("");
        }
    }
    public void Modificar(View vista)
    {
        int Cod,Sto,Pre;
        String Nom;
        String Fec;
        Cod=Integer.parseInt(Codi.getText().toString());
        Nom=Nomb.getText().toString();
        Sto=Integer.parseInt(Stoc.getText().toString());
        Pre=Integer.parseInt(PreU.getText().toString());
        Fec=campoFecha.getText().toString();
        //isEmpty=significa cuando esta vacio
        //En este caso si el codigo y el nombre estan vacio mostrara el siguiente texto
        if (Codi.getText().toString().isEmpty()||Nom.isEmpty())
        {
            //Mostrar datos incompletos
            Toast.makeText(this,"Datos Incompletos",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Obj.Modificar(MainActivity.this,Cod,Nom,Sto,Pre,Fec);
            Obj.Mostrar(MainActivity.this,Gv);
            //Aqui limpiamos los datos
            Codi.setText("");
            Nomb.setText("");
            Stoc.setText("");
            PreU.setText("");
            campoFecha.setText("");
        }
    }
    public void Informacion(View vista)
    {
        Obj.Informacion(MainActivity.this,Gv);
    }

}