package com.example.sportmeet2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class activity_inscription extends AppCompatActivity {
    public static final String TAG = "TAG";
    FirebaseAuth mAuth;
    FirebaseFirestore mFireStore;
    EditText mNom, mPrenom, mEmail, mPassword, mTelephone ;
    Button buttonValidation;
    TextView mTextView ;
    ProgressBar progressBar;
    String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        mNom = (EditText) findViewById(R.id.Name);
        mPrenom = (EditText) findViewById(R.id.PersonName);
        mEmail = (EditText) findViewById(R.id.emailAddress);
        mPassword = (EditText) findViewById(R.id.Password);
        mTelephone = (EditText) findViewById(R.id.Phone);
        buttonValidation = (Button) findViewById(R.id.button_validation);
        mTextView = (TextView) findViewById(R.id.textView);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();
        mFireStore = FirebaseFirestore.getInstance();


        //On vérifie si l'utilisateur est déjà inscrit
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), activity_deconnexion.class));
            finish();// On termine cette activité
        }



        buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On formate les données email et mdp en String
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String nom = mNom.getText().toString();
                String prenom = mPrenom.getText().toString();
                String phone = mTelephone.getText().toString();

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

                progressBar.setVisibility(View.VISIBLE);
                //On test si les champs sont bien remplis : si oui alors l'utilisateur peut cliquer sur buttonValidation


                //création de l'inscription de l'utilisateur dans firebase
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //On vérifie si l'inscription de l'utilisateur dans firebase à été un succès ou non
                        if(task.isSuccessful()){
                            Toast.makeText(activity_inscription.this, "Création Utilisateur", Toast.LENGTH_SHORT).show();
                            //On crée la collection utilisateur
                          /*  UserId = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = mFireStore.collection("utilisateur").document(UserId);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Nom", nom);
                            user.put("Prénom", prenom);
                            user.put("Email", email);
                            user.put("Téléphone", phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: profile utilisateur créé pour" + UserId) ;
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure" + e.toString());
                                }
                            });*/


                            startActivity(new Intent(getApplicationContext(), activity_deconnexion.class));

                        }

                        else{
                            Toast.makeText(activity_inscription.this, "Erreur" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });

    }



}