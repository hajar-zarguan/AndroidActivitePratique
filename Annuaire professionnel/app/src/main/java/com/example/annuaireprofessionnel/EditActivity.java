package com.example.annuaireprofessionnel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity {
    AppDataBase database;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText phone;
    EditText job;
    String string;
    TextView id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        database=AppDataBase.getInstance(this);

       
        setContentView(R.layout.activity_edit);

        firstName = (EditText) findViewById(R.id.firstNameInput);
        lastName = (EditText) findViewById(R.id.lastNameInput);
        email = (EditText) findViewById(R.id.emailInput);
        phone = (EditText) findViewById(R.id.phoneInput);
        job = (EditText) findViewById(R.id.jobInput);

        Intent i = getIntent();
        if(i.hasExtra("ID")){
            string = i.getStringExtra("ID");
        }
        Contact contact = database.contactDAO().findByID(Integer.parseInt(string));


        firstName.setText(contact.getFirstName());
        lastName.setText(contact.getLastName());
        email.setText(contact.getEmail());
        phone.setText(contact.getPhone());
        job.setText(contact.getJob());
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

    }

    public void save(View view){

        database.contactDAO().update(new Contact(Integer.parseInt(string),firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),job.getText().toString(),phone.getText().toString()));
        Toast.makeText(EditActivity.this,"Updated",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(myIntent);
    }


}