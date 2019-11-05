package com.example.forms;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TelaLoc extends AppCompatActivity {

    // ---- Declaração das variaveis --

    private Button btnGps;
    private Button localizacoes;
    private Button btSalva;
    private Button btnEnvio;

    private TextView txtLatitude;
    private TextView txtLongitude;

    private LocalDB db;

    // ---- Inflando a tela com os componentes --

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);

        // --- Linkando os objetos da tela com as variaveis --

        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);
        db = new LocalDB(this);

        // --- Verifica se o gps está ligado --
        btnGps = (Button) findViewById(R.id.btnGps);
        btnGps.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                pedirPermissoes();
            }
        });

        // --- Botão de salvar coord --

        btSalva = (Button)findViewById(R.id.btSalva);
        btSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtLatitude.getText().length() == 0 || txtLongitude.getText().length() == 0){

                    Toast.makeText(TelaLoc.this, "Aguarde a localização ser encontrada", Toast.LENGTH_LONG).show();
                } else {

                    double latitude= Double.parseDouble(txtLatitude.getText().toString());
                    double longitude = Double.parseDouble(txtLongitude.getText().toString());

                    Local local = new Local(0, latitude, longitude);

                    long id = db.salvaLocal(local);

                    if(id!=-1){ Toast.makeText(TelaLoc.this, "Localização Salva", Toast.LENGTH_LONG).show(); }

                    else{ Toast.makeText(TelaLoc.this,"Ops! Não foi possível cadastrar a localização", Toast.LENGTH_LONG).show(); }
                } // Fim do else
            } // Fim do onClick
        });


        // --- Botão de localizações salvas --
        localizacoes = (Button) findViewById(R.id.btLocal);
        localizacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaLoc.this, ExibeLocais.class);
                intent.putExtras(getIntent().getExtras());
                startActivity(intent);
            }
        });


        // --- Botão enviar para o forms --
        btnEnvio = (Button) findViewById(R.id.btCor);
        btnEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtLatitude.getText().length() == 0 || txtLongitude.getText().length() == 0){

                    Toast.makeText(TelaLoc.this, "Aguarde a localização ser carregada!", Toast.LENGTH_LONG).show();
                }

                else {
                    double lat = Double.parseDouble(txtLatitude.getText().toString());
                    double log = Double.parseDouble(txtLongitude.getText().toString());

                    Bundle bundle = new Bundle();
                    bundle.putString("loc", Double.toString(lat) + "; " + Double.toString(log));

                    Intent intent = new Intent(TelaLoc.this, Viabilidade.class);
                    intent.putExtras(bundle);
                    intent.putExtras(getIntent().getExtras());

                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void pedirPermissoes() {

        LocationManager locationManager = (LocationManager) getSystemService( LOCATION_SERVICE );
        boolean isGPSEnabled = locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER );

        if (!isGPSEnabled) {
            Toast.makeText(this, "Precisa ligar o seu GPS", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }*/
        else {
            Toast.makeText(this, "Aguarde, carregando a sua localização", Toast.LENGTH_LONG).show();
            configurarServico();
        }
    }

    private void showSettingsAlert() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configurarServico();
                } else {
                    Toast.makeText(this, "Não vai funcionar!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void configurarServico(){
        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    atualizar(location);
                }

                public void onStatusChanged(String provider, int status, Bundle extras) { }

                public void onProviderEnabled(String provider) { }

                public void onProviderDisabled(String provider) { }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }catch(SecurityException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void atualizar(Location location)
    {
        Double latPoint = location.getLatitude();
        Double lngPoint = location.getLongitude();

        txtLatitude.setText(latPoint.toString());
        txtLongitude.setText(lngPoint.toString());
    }
}