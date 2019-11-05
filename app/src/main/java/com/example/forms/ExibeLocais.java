package com.example.forms;

// --- Classes Importadas --
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ExibeLocais extends AppCompatActivity {

    // --- Declarando as Variáveis --
    private ListView minhaLista;
    private ArrayAdapter mAdapter;
    private ArrayList<Local> exibeLista;
    private LocalDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_locais);

        db = new LocalDB(this);
        minhaLista = (ListView) findViewById(R.id.listViewXML);
        exibeLista = db.findALL();
        mAdapter = new ArrayAdapter<Local>(this, android.R.layout.simple_list_item_1, exibeLista);
        minhaLista.setAdapter(mAdapter);

        // --- Enviando o valor salvo para o formulário --
        minhaLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == i){
                        Bundle bundle = new Bundle();
                        bundle.putString("loc", exibeLista.get(i).toString() );

                        Intent intent = new Intent(ExibeLocais.this, Viabilidade.class);
                        intent.putExtras(bundle);
                        intent.putExtras(getIntent().getExtras());

                        startActivity(intent);
                        finish();
                }
            }
        });
    }
}