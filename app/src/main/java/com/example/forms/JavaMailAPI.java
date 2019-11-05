package com.example.forms;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI extends AsyncTask<Void,Void,Void>  {

    //Variables
    private Context mContext;
    private Session mSession;
    private String mSubject;
    private String mMessage;
    public int id;

    private ProgressDialog mProgressDialog;

    //Constructor
    public JavaMailAPI(Context mContext, String mSubject, String mMessage, int id) {
        this.mContext = mContext;
        this.mSubject = mSubject;
        this.mMessage = mMessage;
        this.id = id;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Show progress dialog while sending email
        mProgressDialog = ProgressDialog.show(mContext,"Enviando Mensagem", "Aguarde...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismiss progress dialog when message successfully send
        mProgressDialog.dismiss();

        //Show success toast
        Toast.makeText(mContext,"Mensagem Enviada, retornamos ela em breve!",Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        mSession = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Utils.EMAIL, Utils.SENHA);
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(mSession);
            MimeMessage cliente = new MimeMessage(mSession);

            //Setting sender address
            mm.setFrom(new InternetAddress(Utils.EMAIL));

            //Adding receiver
            cliente.addRecipient(Message.RecipientType.TO, new InternetAddress("d169692@dac.unicamp.br"));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress("lucas_barreto2@outlook.com"));

            //Adding subject
            mm.setSubject(mSubject);
            cliente.setSubject(mSubject + " - Bit Electronics");

            //Adding message
            mm.setText(mMessage);
            cliente.setText("Olá a sua mensagem foi recebida com sucesso e logo retornaremos combinado?"
            + "\n" + "O seu código de identificação é esse: " + id + "\n" + "Não perca esse número combinado?!");

            //Sending email
            Transport.send(mm);
            Transport.send(cliente);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}