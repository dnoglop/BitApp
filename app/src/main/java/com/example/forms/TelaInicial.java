package com.example.forms;

// --- Classes Importadas --
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

public class TelaInicial extends AppCompatActivity {

    // ---- Variável do tipo GridLayout ----
    GridLayout mainGrid;

    // --- Declarando as variáveis --
    public Button insta;
    public Button face;
    public Button you;
    public ImageView atria;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

       toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayShowTitleEnabled(false);

        insta   = (Button) findViewById(R.id.btn_insta);
        face    = (Button) findViewById(R.id.btn_face);
        you     = (Button) findViewById(R.id.btn_you);
        atria   = (ImageView) findViewById(R.id.iv_atria);


        // --- Configurando os botoes --
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "fb://page/1815539822038110";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "https://www.instagram.com/bitelectronics/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        you.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "https://www.youtube.com/channel/UCwQXuBpCJDjjUBFWIh0kL6Q";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        atria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "https://atriajr.com.br/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        // ---- Chamando o GrindLayout na inicialização ----
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        // ---- Função para identificar qual botão pressionou ----
        setSingleEvent(mainGrid);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_config, menu );
        return true;
    }


    // ---- Função que identifica qual card foi pressionado e abre a tela correspondente ----
    private void setSingleEvent(GridLayout mainGrid) {

        // Loop para analizar cada card separado
        for (int i = 0; i < mainGrid.getChildCount(); i++) {

            // Como todos são cardview, vamos apenas separar para analisar eles separadamente
            CardView cardView = (CardView) mainGrid.getChildAt(i);

            // ---- variável para receber o valor de i (ou seja, qual card foi pressionado) ----
            final int finalI = i;

            // ---- Verificando se algum cardview foi pressionado ----
            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    // ---- para cada if, uma nova activity será aberta ----

                    if(finalI == 0) {

                        Intent intent = new Intent(TelaInicial.this, TelaLoc.class);
                        startActivity(intent); // Iniciando a proxima tela
                    }

                    if(finalI == 1) {

                        Intent intent1 = new Intent(TelaInicial.this, Viabilidade.class);
                        startActivity(intent1);
                    }

                    if(finalI == 2) {

                        Intent intent1 = new Intent(TelaInicial.this, Manutencao.class);
                        startActivity(intent1);
                    }

                    if(finalI == 3) {

                        Intent intent1 = new Intent(TelaInicial.this, Suporte.class);
                        startActivity(intent1);
                    }

                    if(finalI == 4) {

                        Intent intent1 = new Intent(TelaInicial.this, TelaLoc.class);
                        startActivity(intent1);
                    }
                }
            });
        }
    }
}
// =============================================== F I M ========================================