package com.jsoncontacts.databaseService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.jsoncontacts.R;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jsoncontacts.MainActivity;
import com.jsoncontacts.databaseService.AppDataBase;
import com.jsoncontacts.models.Contact;

public class EditActivity  extends AppCompatActivity {
    AppDataBase database;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText phone;
    EditText job;
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=AppDataBase.getInstance(this);
        setContentView(R.layout.manage_contact);
        firstName = (EditText) findViewById(R.id.name_edit);
        lastName = (EditText) findViewById(R.id.prenom_edit);
        email = (EditText) findViewById(R.id.email_edit);
        phone = (EditText) findViewById(R.id.phone_Edittxt);
        job = (EditText) findViewById(R.id.job_edit);
        Button create = findViewById(R.id.create);

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
        create.setText("modifier");



    }


    // edit
    public void save(View view){

        database.contactDAO().update(new Contact(Integer.parseInt(string),firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),job.getText().toString(),phone.getText().toString()));
        Toast.makeText(EditActivity.this,"modifier",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(myIntent);
    }


}