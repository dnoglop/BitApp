package com.example.forms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Random;

public class Manutencao extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    // --- Tirar essa linha depois de adicionar o player de video --
    public static  String API_KEY = "AIzaSyA4YLfkzpc0o-mg_aekndtiX22pIq57_74";

    // --- Declarando as variáveis --
    public EditText mNome;
    public EditText mEmail;
    public EditText mCel;
    public EditText mCEP;
    public EditText mCidade;
    public EditText mRua;
    public EditText mNumber;
    public EditText mComplemento;
    public EditText mBairro;
    public EditText mModelo;
    public EditText mEtiqueta;
    public EditText mNotas;
    public EditText mProblema;
    public Button btnEnviar;
    public ImageView iv;
    public Random number;

    public Spinner Estados;

    public String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manutencao);

        // --- Linkando os elementos da tela com as variaveis criadas --


        // --- Verifica de a ultima activity enviou alguma coisa atraves da TAG 'ma' --
        /*Intent intent1 = getIntent();
        Bundle dados = intent1.getExtras();
        op = dados.getString("ma");*/  // Armazenando a informação na variavel 'op'

        /*switch (op){
            case "videosCA":
                btnIntegral.setText("Playlit Integrais");
                btnDerivada.setText("Playlist Derivadas");
                btnLimite.setText("Playlist Limites");
                break;

            case "videosGA":
                btnIntegral.setText("Geometria Analítica");
                btnDerivada.setText("Geometria Plana");
                btnLimite.setText("Algebra Linear");
                break;
        }*/

        // --- Iniciando o player de video do youtube --
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.video_youtube1);
        youTubePlayerView.initialize(API_KEY, this);

    }

    // --- Verifica se a inicialização deu erro --
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Falha na Inicialização", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

        // --- Iniciando os Listeners --
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        player.cueVideo("wnhvlp3BzRU");
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
        }
        @Override
        public void onVideoStarted() {
        }
    };
}
// ============================================================= F I M ==============================================
