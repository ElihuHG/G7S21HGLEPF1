package com.tesoem.g7s21hglepf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tesoem.g7s21hglepf.DAO.ConexionDAO;
import com.tesoem.g7s21hglepf.DAO.DatosHelper;
import com.tesoem.g7s21hglepf.DTO.DatosDTO;


public class Activity_actualizar extends AppCompatActivity {
    EditText txtnombre,txtedad,txtcorreo;
    DatosDTO datosDTO= new DatosDTO();
    Button btnguardar, btncancelar;
    String idactualiza="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        Bundle datos = getIntent().getExtras();
        String idb = datos.getString("idbuscar");

        txtnombre = findViewById(R.id.txtnombre);
        txtedad = findViewById(R.id.txtedad);
        txtcorreo = findViewById(R.id.txtcorreo);
        btnguardar = findViewById(R.id.btnguardar);
        btncancelar = findViewById(R.id.btncancelar);

        String[] parametrocondiciones = new String[]{idb};

        ConexionDAO conexionDAO = new ConexionDAO(this);
        conexionDAO.ABRIR_CONEXION();

        if(conexionDAO.CONSULTA_ID(parametrocondiciones)){
            datosDTO = conexionDAO.regresaID();
            txtnombre.setText(datosDTO.getNombre());
            txtedad.setText(String.valueOf(datosDTO.getEdad()));
            txtcorreo.setText(datosDTO.getCorreo());
            idactualiza = String.valueOf(datosDTO.getId());
        }else {
            Toast.makeText(this, "No se pudo recuperar la informacion", Toast.LENGTH_SHORT).show();
        }

        conexionDAO.CERRAR_CONEXION();
    }

    public void acciones(View v){
        switch (v.getId()){
            case R.id.btnguardar: btnguarda();
            break;
            case R.id.btncancelar: btncancela();
            break;
        }
    }

    private  void btnguarda(){
        String [] condiciones = new String[]{idactualiza};
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatosHelper.tabladatos.TABLA_NOMBRE, txtnombre.getText().toString());
        contentValues.put(DatosHelper.tabladatos.TABLA_EDAD,Integer.valueOf(txtedad.getText().toString()));
        contentValues.put(DatosHelper.tabladatos.TABLA_CORREO, txtcorreo.getText().toString());

        ConexionDAO conexionDAO = new ConexionDAO(this);
        conexionDAO.ABRIR_CONEXION();

        if(conexionDAO.ACTUALIZA_DATOS(contentValues,condiciones)) {
            Toast.makeText(this, "Se actualizaron los datos", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("vista", "actualizar");
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else {
            Toast.makeText(this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
            btnguardar.setActivated(false);
        }
    }
    private void btncancela(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}
