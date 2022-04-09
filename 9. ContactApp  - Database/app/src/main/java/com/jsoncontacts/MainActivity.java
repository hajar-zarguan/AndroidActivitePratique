package com.jsoncontacts;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
import com.jsoncontacts.models.Contact;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AppDataBase db;
    List<Contact> contacts;
    LinearLayoutManager linearLayoutManager;
   // String jsonFileString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Contact contact1 = new Contact("Ayoub", "LAMINE SADIKI", "Ingénieur d'état", "0678119921", "sadiki.err98@gmail.com");
        Contact contact2 = new Contact("Amine", "Sadiki", "Technicien spécialisé", "0610969037", "amine.sadiki@gmail.com");
        Contact contact3 = new Contact("Ahmed", "Alaoui", "Ched de service", "0625867047", "ahmed.alaoui@gmail.com");
        Contact contact4 = new Contact("Siham", "lamine", "Directrice ", "0612345678", "siham.lamine@gmail.com");
        Contact contact5 = new Contact("Jack", "Aron", "Superviseur", "+33612326598", "jack.aron@gmail.com");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

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
            public void onClick(View view) {


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
            // jsonFileString = getJsonFromAssets(getApplicationContext(), "data.json");
            // createInternalFile(jsonFileString);
            //Load data
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isItFirstRun", false);
            editor.apply();
        } else {
            try {

                //  jsonFileString = sb.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
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

      //  Gson gson = new Gson();
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
        void RefreshListView(List<Contact> contacts){
            ContactsAdapter adapter = new ContactsAdapter(contacts,this,selectedContact();,this);
            recyclerView.setAdapter(adapter);
        }

    }

