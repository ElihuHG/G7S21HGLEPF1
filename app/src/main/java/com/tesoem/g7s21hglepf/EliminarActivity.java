package com.tesoem.g7s21hglepf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tesoem.g7s21hglepf.DAO.ConexionDAO;
import com.tesoem.g7s21hglepf.DTO.DatosDTO;

public class EliminarActivity extends AppCompatActivity {
    DatosDTO datosDTO = new DatosDTO();
    EditText txtenombre,txteedad,txtecorreo;
    Button btneaceptar,btnecancelar;
    String idelimina="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        Bundle datos = getIntent().getExtras();
        String idb = datos.getString("idbuscar");

        txtenombre = findViewById(R.id.txtenombre);
        txteedad = findViewById(R.id.txteedad);
        txtecorreo = findViewById(R.id.txtecorreo);
        btneaceptar = findViewById(R.id.btneaceptar);
        btnecancelar = findViewById(R.id.btnecancelar);

        String[] parametrocondiciones = new String[]{idb};

        ConexionDAO conexionDAO = new ConexionDAO(this);
        conexionDAO.ABRIR_CONEXION();

        if(conexionDAO.CONSULTA_ID(parametrocondiciones)){
            datosDTO = conexionDAO.regresaID();
            txtenombre.setText(datosDTO.getNombre());
            txteedad.setText(String.valueOf(datosDTO.getEdad()));
            txtecorreo.setText(datosDTO.getCorreo());
            idelimina = String.valueOf(datosDTO.getId());
        }else{
            Toast.makeText(this, "No se pudo recuperar la informacion", Toast.LENGTH_SHORT).show();
        }

        conexionDAO.CERRAR_CONEXION();
    }

    public  void acciones(View v){
        switch (v.getId()){
            case R.id.btneaceptar:
                eliminacion();
                break;
            case R.id.btnecancelar:
                cancelar();
                break;

        }
    }

    private  void eliminacion(){
        String[] condiciones = new String[]{idelimina};
        ConexionDAO conexionDAO = new ConexionDAO(this);
        conexionDAO.ABRIR_CONEXION();
        if (conexionDAO.eliminar(condiciones)){
            Toast.makeText(this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("vista","Elimina");
            setResult(Activity.RESULT_OK,intent);
            finish();
        }else {
            Toast.makeText(this, "Error al eliminar elementos", Toast.LENGTH_SHORT).show();
            btnecancelar.setActivated(false);
        }
    }
    private  void cancelar(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}