package com.example.distantdb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MainActivity extends AppCompatActivity {
    Task[] tasksArray;
    ArrayList<Task> RealTasks=new ArrayList<>();
    Button btn;
    EditText edit;
    ListView listTasks;
    ArrayList<String> tasksData=null;
    ArrayAdapter<String> adapter=null;
    String newTask=null;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = "http://192.168.43.141:8083";
        GetAllTasks();
        listTasks=findViewById(R.id.listTasks);
        btn=findViewById(R.id.addButton);
        edit=findViewById(R.id.editTask);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 newTask=edit.getText().toString();
                InsertTask(newTask);
                GetAllTasks();
            }
        });
        listTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String t=  parent.getAdapter().getItem(position).toString();
                Task task = RealTasks.get(position);
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Modifier tache");
                alert.setMessage("Modifier votre tache");
                final EditText input = new EditText(MainActivity.this);
                input.setText(task.getTask());
                alert.setView(input);
                alert.setPositiveButton("d'accord", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        task.setTask(value);
                        UpdateTask(task,position);
                    }
                });
                alert.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });
                alert.show();
            }
        });
        listTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Task task = RealTasks.get(i);
                        DeleteTask(task,i);
                    }
                });
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("confirmation");
                alertDialog.setMessage(" Etes vous sur de supprimer cette tache");
                alertDialog.show();

                return true;
            }
        });
    }
    public void GetAllTasks(){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {

            @Override
            public void run() {
                //do in background
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Gson gson=new Gson();
                                tasksArray=gson.fromJson(response.toString(),Task[].class);
                                tasksData=new ArrayList<>();
                                for(Task task:tasksArray)
                                {
                                    RealTasks.add(task);
                                    System.out.println(task.toString());
                                    tasksData.add(task.getTask());
                                }
                                adapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,tasksData);
                                listTasks.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("error response",error.toString());
                            }
                        }

                );

                requestQueue.add(jsonObjectRequest);
            }
       });}


    public void InsertTask(String newTask){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {

                //do in background
                 MainActivity.this.runOnUiThread(()->{
                        Toast.makeText(MainActivity.this, "Insertion des données",Toast.LENGTH_SHORT).show();
                 });
                JSONObject jsonobject = new JSONObject();
                try {
                    jsonobject.put("tache", newTask);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        jsonobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Gson gson=new Gson();
                                Task tas=gson.fromJson(response.toString(),Task.class);

                                    RealTasks.add(tas);
                                    tasksData.add(tas.getTask());
                                adapter.notifyDataSetChanged();
                                MainActivity.this.runOnUiThread(()->{
                                    Toast.makeText(MainActivity.this,"Tâche ajoutée avec succes",Toast.LENGTH_LONG).show();
                                });
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("erreur",error.toString());
                            }
                        }

                );

                requestQueue.add(jsonObjectRequest);
            }
        });

    }

    public void DeleteTask(Task task,int i){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {

                //do in background
                MainActivity.this.runOnUiThread(()->{
                    Toast.makeText(MainActivity.this, "Supprission des données",Toast.LENGTH_SHORT).show();
                });
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.DELETE,
                        url+"/"+task.getId(),
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                tasksData.remove(task.getTask());
                                RealTasks.remove(i);
                                adapter.notifyDataSetChanged();
                                MainActivity.this.runOnUiThread(()->{
                                    Toast.makeText(MainActivity.this,"Tâche supprime avec succes",Toast.LENGTH_LONG).show();
                                });
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("erreur",error.toString());
                            }
                        }

                );

                requestQueue.add(jsonObjectRequest);
            }
        });
    }
    public void UpdateTask(Task task, int i){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {

                //do in background
                MainActivity.this.runOnUiThread(()->{
                    Toast.makeText(MainActivity.this, "Modification des données",Toast.LENGTH_SHORT).show();
                });
                JSONObject jsonobject = new JSONObject();
                try {
                    jsonobject.put("task", task.getTask());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.PUT,
                        url+"/"+task.getId(),
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                tasksData.remove(RealTasks.get(i).getTask());
                                tasksData.add(task.getTask());
                                RealTasks.remove(i);
                                adapter.notifyDataSetChanged();
                                MainActivity.this.runOnUiThread(()->{
                                    Toast.makeText(MainActivity.this,"Tâche modifier avec succes",Toast.LENGTH_LONG).show();
                                });
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("error response",error.toString());
                            }
                        }
                );
                requestQueue.add(jsonObjectRequest);
            }
        });

    }

      }





