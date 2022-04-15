package com.jsoncontacts;
import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jsoncontacts.adapters.ContactsAdapter;
import com.jsoncontacts.databaseService.AppDataBase;
import com.jsoncontacts.databaseService.EditActivity;
import com.jsoncontacts.databaseService.addActivity;
import com.jsoncontacts.models.Contact;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements ContactsAdapter.SelectedContact{

    RecyclerView recyclerView;
    AppDataBase db;
    List<Contact> contacts;
    LinearLayoutManager linearLayoutManager;

    Contact contact1 = new Contact("Hajar", "Zarguan", "Ingénieur d'état", "0678119921", "hqjar.err98@gmail.com");
    Contact contact2 = new Contact("Hiba", "Sadiki", "Technicien spécialisé", "0610969037", "hiba.sadiki@gmail.com");
    Contact contact3 = new Contact("Houda", "Alaoui", "Ched de service", "0625867047", "houda.alaoui@gmail.com");
    Contact contact4 = new Contact("Siham", "lamine", "Directrice ", "0612345678", "siham.lamine@gmail.com");
    Contact contact5 = new Contact("Lamia", "Aron", "Superviseur", "0612326598", "lamia.aron@gmail.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //  Toolbar toolbar = findViewById(R.id.toolbar);
       //  Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        db = AppDataBase.getInstance(this);

        db.contactDAO().insert(contact1);
        db.contactDAO().insert(contact2);
        db.contactDAO().insert(contact3);
        db.contactDAO().insert(contact4);
        db.contactDAO().insert(contact5);

        contacts = db.contactDAO().getAll();
        ContactsAdapter adapter = new ContactsAdapter(contacts, this, this, this);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        contacts.clear();
        contacts.addAll(db.contactDAO().getAll());
        adapter.notifyDataSetChanged();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent myIntent = new Intent(MainActivity.this, addActivity.class);
                //startActivity(myIntent);

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.manage_contact);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                EditText name = dialog.findViewById(R.id.name_edit);
                EditText prenom = dialog.findViewById(R.id.prenom_edit);
                EditText email = dialog.findViewById(R.id.email_edit);
                EditText job = dialog.findViewById(R.id.job_edit);
                EditText phone = dialog.findViewById(R.id.phone_Edittxt);
                Button create = dialog.findViewById(R.id.create);
                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(name.getText().toString().trim()) || TextUtils.isEmpty(email.getText().toString().trim() )|| TextUtils.isEmpty(job.getText().toString().trim()) || TextUtils.isEmpty(phone.getText().toString().trim())) {
                            Toast.makeText(MainActivity.this, "Sil vous plqite rend tout les champs !", Toast.LENGTH_SHORT).show();
                        } else {
                            Contact contact = new Contact();
                            contact.setFirstName(prenom.getText().toString().trim());
                            contact.setLastName(name.getText().toString().trim());
                            contact.setJob(job.getText().toString().trim());
                            contact.setEmail(email.getText().toString().trim());
                            contact.setPhone(phone.getText().toString().trim());

                            //create.setOnClickListener( );
                        }
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });








        // shqred preferences pqrt
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean isItFirstRun = prefs.getBoolean("isItFirstRun", true);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{
                        Manifest.permission.CALL_PHONE
                },
                1);
        if (isItFirstRun) {

            //Load data
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isItFirstRun", false);
            editor.apply();
        }
    }


    /////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String textQuery) {
                RefreshListView((ArrayList<Contact>) db.contactDAO().findByName("%"+textQuery+"%"));
                return false;
            }
        });
        return true;
    }

////////////////////////////////////////////////////////////////////////////////////



        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            //noinspection SimplifiableIfStatement
            return super.onOptionsItemSelected(item);
        }

        @Override
        public void selectedContact(Contact contact) {
            Intent myIntent = new Intent(MainActivity.this, EditActivity.class);
            myIntent.putExtra("ID",String.valueOf(contact.getID()));
            startActivity(myIntent);


        }
        public void RefreshListView(List<Contact> contacts){
            ContactsAdapter adapter = new ContactsAdapter(contacts,this,this,this);
            recyclerView.setAdapter(adapter);
        }




    }

