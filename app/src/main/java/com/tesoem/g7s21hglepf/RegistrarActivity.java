package com.tesoem.g7s21hglepf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tesoem.g7s21hglepf.DAO.ConexionDAO;
import com.tesoem.g7s21hglepf.DAO.DatosHelper;
import com.tesoem.g7s21hglepf.DAO.DatosHelper.tabladatos;


public class RegistrarActivity extends AppCompatActivity {
    EditText txtNombre,txtEdad,txtCorreo;
    Button btnGuardar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnGuardar=findViewById(R.id.btnGuardar);
        btnCancelar=findViewById(R.id.btnCancelar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("vista","Registrar");
                guardar();
                setResult(Activity.RESULT_OK,intent);
                finish();

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();

            }
        });



    }

    private void guardar(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(tabladatos.TABLA_NOMBRE,txtNombre.getText().toString());
        contentValues.put(tabladatos.TABLA_EDAD,Integer.valueOf(txtEdad.getText().toString()));
        contentValues.put(tabladatos.TABLA_CORREO,txtCorreo.getText().toString());

        ConexionDAO conexion = new ConexionDAO(this);
        conexion.ABRIR_CONEXION();
        if (conexion.INSERTAR_DATOS(contentValues)){
            Toast.makeText(this, "Se grabaron los datos correctamente", Toast.LENGTH_SHORT).show();
            Log.i("verificacion","guardado correcto");
        }else{
            Toast.makeText(this, "Error al grabar los datos", Toast.LENGTH_SHORT).show();
            Log.i("verificacion","guardado incorrecto");
        }
        conexion.CERRAR_CONEXION();

    }
}