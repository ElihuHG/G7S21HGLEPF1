package com.tesoem.g7s21hglepf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.tesoem.g7s21hglepf.DAO.ConexionDAO;
import com.tesoem.g7s21hglepf.DTO.DatosDTO;

import java.util.ArrayList;
import java.util.List;

public class listarActivity extends AppCompatActivity {
    Button btnRegresar, btncancel;
    GridView gridListar;
    List<DatosDTO> lista = new ArrayList<DatosDTO>();
    ArrayList<String> listaGrid = new ArrayList<String>();
    String seleccionid="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        btnRegresar = findViewById(R.id.btnRegresar);
        btncancel = findViewById(R.id.btncancel);
        gridListar = findViewById(R.id.gridListar);

        CargarDatos();
        gridListar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                seleccionid = listaGrid.get(i);
                Toast.makeText(listarActivity.this, "valor seleccionado " + seleccionid, Toast.LENGTH_SHORT).show();

            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("vista","Listar");
                intent.putExtra("idregresa",seleccionid);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    private void CargarDatos(){
        ConexionDAO conexionDAO = new ConexionDAO(this);
        conexionDAO.ABRIR_CONEXION();
        if (conexionDAO.CARGAR_TODOS()){
            lista = conexionDAO.getListado();
            CargarGrid();
        }else Toast.makeText(this, "No se pudo cargar el contenido", Toast.LENGTH_SHORT).show();
        conexionDAO.CERRAR_CONEXION();
    }

    private void CargarGrid(){
        DatosDTO datosDTO;
        ArrayAdapter<String> adaptador;
        listaGrid.add("ID");
        listaGrid.add("Nombre");
        for (int a=0; a<lista.size();a++) {
            datosDTO = new DatosDTO();
            datosDTO = lista.get(a);
            listaGrid.add(String.valueOf(datosDTO.getId()));
            listaGrid.add(datosDTO.getNombre());
        }
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaGrid);
        gridListar.setAdapter(adaptador);


    }
}