package com.tesoem.g7s21hglepf;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String idprocesa="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //ActivityResultLauncher <Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>()
      ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>(){
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK){
                Intent Data = result.getData();
                String vistaregresa = Data.getStringExtra("vista");
                if (vistaregresa.equals("Listar")){
                    idprocesa = Data.getStringExtra("idregresa");
                }
                Toast.makeText(MainActivity.this, "Regresamos de la actividad correctamente " + Data.getStringExtra("vista"), Toast.LENGTH_SHORT).show();
            }else if (result.getResultCode() == Activity.RESULT_CANCELED){
                Toast.makeText(MainActivity.this, "Se cancelo la operación", Toast.LENGTH_SHORT).show();
            }

        }
    });

    public void Acciones (View v){
        switch (v.getId()){
            case R.id.btnInserta: lanzaInsertar();
                break;
            case  R.id.btnListar: lanzarListado();
                break;
            case R.id.btnActualiza:lanzaActualizar();
                break;
            case R.id.btnElimina:lanzarEliminar();
                break;
            case R.id.btnTermina: finish();
                break;

        }
    }

    private void lanzaInsertar(){
        Intent intent = new Intent(this,RegistrarActivity.class);
        activityResultLauncher.launch(intent);
    }

    private void lanzarListado(){
        Intent intent = new Intent(this,listarActivity.class);
        activityResultLauncher.launch(intent);
    }

    private void lanzaActualizar(){
        Intent intent = new Intent(this,Activity_actualizar.class);
        intent.putExtra("idbuscar",idprocesa);
        activityResultLauncher.launch(intent);
    }

    private void lanzarEliminar(){
        Intent intent = new Intent(this,EliminarActivity.class);
        intent.putExtra("idbuscar",idprocesa);
        activityResultLauncher.launch(intent);

    }

}