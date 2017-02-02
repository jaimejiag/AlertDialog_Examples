package com.jaime.dialogsalert;

import android.app.AlertDialog;
import android.support.v4.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DialogCustomized.onDialogCustomizedListener{
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);

        String[] listValues = new String[] {"Sencillo", "Lista", "Radio Buttons", "CheckBoxes", "Personalizado"};

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listValues);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Simple")
                                .setMessage("Dialog simple")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(MainActivity.this, "Positivo ", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(MainActivity.this, "Negativo", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                        break;

                    case 1:
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        final String[] frutas = new String[] {"Naranja", "Plátano", "Manzana"};
                        builder1.setTitle("Frutas")
                                .setItems(frutas, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(MainActivity.this, frutas[i], Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                        break;

                    case 2:
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                        final String[] estados = new String[] {"Soltero", "Casado", "Viudo"};
                        builder2.setTitle("Estado Civil")
                                .setSingleChoiceItems(estados, -1, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(MainActivity.this, estados[i], Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setPositiveButton("Enviar", null).show();
                        break;

                    case 3:
                        AlertDialog.Builder builder3 = new AlertDialog.Builder(MainActivity.this);
                        final String[] consolas = new String[] {"Xbox", "PlayStation", "Wii"};
                        final ArrayList seleccionados = new ArrayList();
                        builder3.setTitle("Consolas")
                                .setMultiChoiceItems(consolas, null, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                        if (b)
                                            seleccionados.add(i);
                                        else if (seleccionados.contains(i))
                                            seleccionados.remove(i);
                                        Toast.makeText(MainActivity.this, "Opciones seleccionadas: " + seleccionados.size(), Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setPositiveButton("Enviar", null).show();
                        break;

                    case 4:
                        FragmentManager manager = getSupportFragmentManager();
                        new DialogCustomized().show(getFragmentManager(), "customized");
                }
            }
        });
    }


    @Override
    public void getMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
