package  com.tp3.mysqlliteroom;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView ls = (RecyclerView) findViewById(R.id.recycler_view);
        DBconnection db = new DBconnection(this);
        ArrayList<String> liste = db.getAllRecord();
        ArrayAdapter Adapter = new ArrayAdapter<>
                (this,android.R.layout.simple_list_item_1,liste);
        ls.setAdapter(Adapter);
    }

    public void Enregistrer(View view) {
        TextView nom = findViewById(R.id.edit_text);
        RecyclerView ls = (RecyclerView) findViewById(R.id.recycler_view);
        DBconnection db = new DBconnection(this);
        db.insertNewAdmin(nom.getText().toString());
        ArrayList<String> arrayListe = db.getAllRecord();
        ArrayAdapter myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayListe);
        ls.setAdapter(myAdapter);
        nom.setText("");
    }
    public void Update(View view) {
        TextView chNom = findViewById(R.id.edit_text);
        EditText chID = findViewById(R.id.edit_text);
        RecyclerView ls = (RecyclerView) findViewById(R.id.recycler_view);
        DBconnection db = new DBconnection(this);
        db.updateRow(chNom.getText().toString(),Integer.parseInt(chID.getText().toString()));
        ArrayList<String> arrayListe = db.getAllRecord();
        ArrayAdapter myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayListe);
        ls.setAdapter(myAdapter);
        chNom.setText(""); chID.setText("");
    }
    public void supprimer(View view) {
        TextView chId = findViewById(R.id.edit_text);
        RecyclerView ls = (RecyclerView) findViewById(R.id.recycler_view);
        DBconnection db = new DBconnection(this);
        db.deleteRow(Integer.parseInt(chId.getText().toString())); //supp la ligne en question
        ArrayList<String> arrayL = db.getAllRecord();
        RecyclerView.Adapter myAdapter = new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        };
        ls.setAdapter(myAdapter);
        chId.setText("");
    }



}
