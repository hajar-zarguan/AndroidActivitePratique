package com.example.annuaireprofessionnel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class addActivity extends AppCompatActivity {

    AppDataBase database;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText phone;
    EditText job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database=AppDataBase.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        firstName = (EditText) findViewById(R.id.firstNameInput);
        lastName = (EditText) findViewById(R.id.lastNameInput);
        email = (EditText) findViewById(R.id.emailInput);
        phone = (EditText) findViewById(R.id.phoneInput);
        job = (EditText) findViewById(R.id.jobInput);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(addActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
    public void save(View view){

        database.contactDAO().insert(new Contact(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),job.getText().toString(),phone.getText().toString()));
        Toast.makeText(addActivity.this,"Added",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(addActivity.this, MainActivity.class);
        startActivity(myIntent);
    }
    public void clear(View view)
    {
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        job.setText("");
        phone.setText("");
    }

}