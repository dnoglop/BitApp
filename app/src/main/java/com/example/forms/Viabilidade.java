package com.example.forms;

// ---- Classes Importadas --
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Viabilidade extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public EditText mNome;
    public EditText mEmail;
    public EditText mCel;
    public EditText mCEP;
    public EditText mAlcance;
    public EditText mLigac;
    public EditText mStruct;
    public EditText mAltura;
    public Button btnEnviar;
    public ImageView iv;
    public Random number;

    public TextView coletada;

    public Button mCoord;

    public Spinner Estados;
    public Spinner Ligacoes;
    public Spinner Sinal;
    public Spinner Operadoras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- Linkando os objetos da tela --

        mNome       = (EditText)    findViewById(R.id.nomeID);
        mEmail      = (EditText)    findViewById(R.id.mailId);
        mCel        = (EditText)    findViewById(R.id.celID);
        mCEP        = (EditText)    findViewById(R.id.cepID);
        mAlcance    = (EditText)    findViewById(R.id.alcanceID);
        mStruct     = (EditText)    findViewById(R.id.estrutID);
        mAltura     = (EditText)    findViewById(R.id.alturaID);
        coletada    = (TextView)    findViewById(R.id.Coordenada);
        btnEnviar   = (Button)      findViewById(R.id.btnEnviar);
        iv          = (ImageView)   findViewById(R.id.logo);
        number      = new Random();

        // ---- Capturando os dados enviados a última activity --

        Intent intentLoc = getIntent();
        Bundle dados = intentLoc.getExtras();

        if (dados != null){
            String teste = dados.getString("loc");
            coletada.setText(teste);

            String nome = dados.getString("name");
            mNome.setText(nome);

            String email = dados.getString("email");
            mEmail.setText(email);

            String cel = dados.getString("cel");
            mCel.setText(cel);

            String cep = dados.getString("cep");
            mCEP.setText(cep);

            String alcance = dados.getString("alcance");
            mAlcance.setText(alcance);

            String altura = dados.getString("altura");
            mAltura.setText(altura);

            String estrutura = dados.getString("estrutura");
            mStruct.setText(estrutura);
        }

        // ---- Botão das Coordenadas --
        mCoord = (Button) findViewById(R.id.coorID);
        mCoord.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                name     = mNome.getText().toString();
                mail     = mEmail.getText().toString().trim();
                cel      = mCel.getText().toString();
                cep      = mCEP.getText().toString();
                alcance  = mAlcance.getText().toString();
                estrut   = mStruct.getText().toString();
                altura   = mAltura.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("email", mail);
                bundle.putString("cel", cel);
                bundle.putString("cep", cep);
                bundle.putString("alcance", alcance);
                bundle.putString("altura", estrut);
                bundle.putString("estrutura", altura);

                Intent intent = new Intent(Viabilidade.this, TelaLoc.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });

        // ---- Spinner dos estados --

        Estados = (Spinner) findViewById(R.id.estadoSpiner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Estados.setAdapter(adapter);
        Estados.setOnItemSelectedListener(this);

        // ---- Spinner das ligações ---

        Ligacoes = (Spinner) findViewById(R.id.ligacaoSpinner);
        ArrayAdapter<CharSequence> adapterLig = ArrayAdapter.createFromResource(this, R.array.op, android.R.layout.simple_spinner_item);
        adapterLig.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Ligacoes.setAdapter(adapterLig);
        Ligacoes.setOnItemSelectedListener(this);

        // ---- Spinner do Sinal  ---

        Sinal = (Spinner) findViewById(R.id.sinalSpinner);
        ArrayAdapter<CharSequence> adapterSinal = ArrayAdapter.createFromResource(this, R.array.sinal, android.R.layout.simple_spinner_item);
        adapterSinal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sinal.setAdapter(adapterSinal);
        Sinal.setOnItemSelectedListener(this);

        // ---- Spinner da Operadora  ---

        Operadoras = (Spinner) findViewById(R.id.operadoraSpinner);
        ArrayAdapter<CharSequence> adapterOperadora = ArrayAdapter.createFromResource(this, R.array.operadoras, android.R.layout.simple_spinner_item);
        adapterOperadora.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Operadoras.setAdapter(adapterOperadora);
        Operadoras.setOnItemSelectedListener(this);

        // ---- Botão para enviar o email ---

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

        String mail;
        String name;
        String cel;
        String cep;
        String alcance;
        String estrut;
        String altura;
        int id;

    private void sendMail() {

        mail     = mEmail.getText().toString().trim();
        name     = mNome.getText().toString();
        cel      = mCel.getText().toString();
        cep      = mCEP.getText().toString();
        alcance  = mAlcance.getText().toString();
        estrut   = mStruct.getText().toString();
        altura   = mAltura.getText().toString();
        id       = number.nextInt(((99999 - 00000) + 1) - 00000);  // Gerando o ID

        // --- Assunto do Email --
        String assunto = "Formulário sobre Estudo de Viabilidade - ID " + id;

        if (Estados.getSelectedItem().toString().equalsIgnoreCase("Estado") || Ligacoes.getSelectedItem().toString().equalsIgnoreCase("No local, consegue realizar ligações telefonicas?")
        || Sinal.getSelectedItem().toString().equalsIgnoreCase("No local possui sinal de rede?") || Operadoras.getSelectedItem().toString().equalsIgnoreCase("Qual a operadora de interesse para o local?")) {

        if (mEmail.getText().length() == 0 || mNome.getText().length() == 0 || mCel.getText().length() == 0
                || mCEP.getText().length() == 0 || mAlcance.getText().length() == 0
                || mLigac.getText().length() == 0 || mStruct.getText().length() == 0){

            Toast.makeText(Viabilidade.this, "Preencha todos os campos do formulário!", Toast.LENGTH_LONG).show();
        }
    }
        else {

            String ms = ("Olá, uma nova mensagem de " + name + "\n\n" + "Email: " + mail + "\n\n" + "Whatsapp: " + cel

                    + "\n\n" + "CEP: " + cep + "\n\n" + "Coordenadas: " + coletada.getText() + "\n\n" + "Localidade: " + Estados.getSelectedItem().toString()

                    + "\n\n" + "Alcance do sinal: " + alcance + "\n\n" + "Consegue realizar chamadas? " + Ligacoes.getSelectedItem().toString()

                    + "\n\n" + "Sinal no local: " + Sinal.getSelectedItem().toString() + "\n\n" + "Tem estrutura no local? " + estrut

                    + "\n\n" + "Altura da Estrutura: " + altura + "\n\n" + "Qual operadora de interesse? " + Operadoras.getSelectedItem().toString());

            //Send Mail
            JavaMailAPI javaMailAPI = new JavaMailAPI(this, assunto, ms, id);

            javaMailAPI.execute();

            // ---- Limpa os campos ---

            mEmail.setText("");
            mNome.setText("");
            mCel.setText("");
            mCEP.setText("");
            mAlcance.setText("");
            mStruct.setText("");
            mAltura.setText("");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}