package com.example.sportmeet2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText mEmail, mPassword;
    TextView mConnexion, mNewAccount;
    Button mLogin;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText)findViewById(R.id.editTextTextEmailAddress2);
        mPassword = (EditText)findViewById(R.id.editTextTextPassword2);
        mConnexion = (TextView) findViewById(R.id.textView);
        mNewAccount = (TextView) findViewById(R.id.text_connexion);
        mLogin = (Button) findViewById(R.id.button_connexion);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar3);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On formate les données récupérés en String
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                //Les erreurs vont apparaître dans des popups en dessous des textView
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Erreur : le champ email est vide");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Erreur : le champ password est vide");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Le mot de passe doit contenir au moins 6 caractères");
                    return;
                }

                mProgressBar.setVisibility(View.VISIBLE);
                //On test si les champs sont bien remplis : si oui alors l'utilisateur peut cliquer sur buttonValidation


                //authentification de l'utilisateur
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Connexion utilisateur", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), activity_deconnexion.class));
                        }else{

                            Toast.makeText(LoginActivity.this, "Erreur" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });


        mNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, activity_inscription.class);
                startActivity(intent);
            }
        });
    }
//les donnees dans la table → user (collection) → id →données user

}