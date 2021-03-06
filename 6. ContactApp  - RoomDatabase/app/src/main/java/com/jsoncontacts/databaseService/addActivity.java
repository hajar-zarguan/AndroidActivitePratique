package com.jsoncontacts.databaseService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.jsoncontacts.R;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jsoncontacts.MainActivity;
import com.jsoncontacts.models.Contact;

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
        setContentView(R.layout.manage_contact);
        firstName = (EditText) findViewById(R.id.name_edit);
        firstName = (EditText) findViewById(R.id.prenom_edit);
        email = (EditText) findViewById(R.id.email_edit);
        phone = (EditText) findViewById(R.id.phone_Edittxt);
        job = (EditText) findViewById(R.id.job_edit);

    }
    public void save(View view){

        database.contactDAO().insert(new Contact(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),job.getText().toString(),phone.getText().toString()));
        Toast.makeText(addActivity.this,"ajouter",Toast.LENGTH_SHORT).show();
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