package com.example.readandwriteinternalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {


    private String TAG_WRITE_READ_FILE = "TAG_WRITE_READ_FILE";
    private String userEmalFileName = "userEmail.txt";
    private String cacheFileName = "customCache.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setTitle("dev2qa.com - Android Read Write Internal Storage File Example.");
        final EditText editText = (EditText)findViewById(R.id.write_read_file_edit_text);

        // Write to internal file button.
        Button writeToFileButton = (Button)findViewById(R.id.write_to_file_button);
        writeToFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = editText.getText().toString();
                if(TextUtils.isEmpty(userEmail))
                {
                    Toast.makeText(getApplicationContext(), "Input data can not be empty.", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    Context ctx = getApplicationContext();
                    /*
                    This comments code can also write data to android internal file.
                    File file = new File(getFilesDir(), userEmalFileName);
                    writeDataToFile(file, userEmail);
                    */
                    try
                    {
                        FileOutputStream fileOutputStream = ctx.openFileOutput(userEmalFileName, Context.MODE_PRIVATE);
                        writeDataToFile(fileOutputStream, userEmail);
                    }catch(FileNotFoundException ex)
                    {
                        Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
                    }
                    Toast.makeText(ctx, "Data has been written to file " + userEmalFileName, Toast.LENGTH_LONG).show();
                }
            }
        });






        // Read from internal file.
        Button readFromFileButton = (Button)findViewById(R.id.read_from_file_button);
        readFromFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Context ctx = getApplicationContext();
                    FileInputStream fileInputStream = ctx.openFileInput(userEmalFileName);
                    String fileData = readFromFileInputStream(fileInputStream);
                    if(fileData.length()>0) {
                        editText.setText(fileData);
                        editText.setSelection(fileData.length());
                        Toast.makeText(ctx, "Load saved data complete.", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(ctx, "Not load any data.", Toast.LENGTH_SHORT).show();
                    }
                }catch(FileNotFoundException ex)
                {
                    Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
                }
            }
        });





        // Write to internal cache file button.
        Button createCachedFileButton = (Button)findViewById(R.id.create_cached_file_button);
        createCachedFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = editText.getText().toString();
                if(TextUtils.isEmpty(userEmail))
                {
                    Toast.makeText(getApplicationContext(), "Input data can not be empty.", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    File file = new File(getCacheDir(), cacheFileName);
                    writeDataToFile(file, userEmail);
                    Toast.makeText(getApplicationContext(), "Cached file is created in file " + cacheFileName, Toast.LENGTH_LONG).show();
                }
            }
        });




        // Read from cache file.
        Button readCacheFileButton = (Button)findViewById(R.id.read_cached_file_button);
        readCacheFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Context ctx = getApplicationContext();
                    File cacheFileDir = new File(getCacheDir(), cacheFileName);
                    FileInputStream fileInputStream = new FileInputStream(cacheFileDir);
                    String fileData = readFromFileInputStream(fileInputStream);
                    if(fileData.length()>0) {
                        editText.setText(fileData);
                        editText.setSelection(fileData.length());
                        Toast.makeText(ctx, "Load saved cache data complete.", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(ctx, "Not load any cache data.", Toast.LENGTH_SHORT).show();
                    }
                }catch(FileNotFoundException ex)
                {
                    Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
                }
            }
        });



        // Write to internal temp file button.
        Button createTempFileButton = (Button)findViewById(R.id.create_temp_file_button);
        createTempFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String userEmail = editText.getText().toString();
                    if(TextUtils.isEmpty(userEmail))
                    {
                        Toast.makeText(getApplicationContext(), "Input data can not be empty.", Toast.LENGTH_LONG).show();
                        return;
                    }else {
                        // This method will create a temp file in android cache folder,
                        // temp file prefix is temp, suffix is .txt, each temp file name is unique.
                        File tempFile = File.createTempFile("temp", ".txt", getCacheDir());
                        String tempFileName = tempFile.getAbsolutePath();
                        writeDataToFile(tempFile, userEmail);
                        Toast.makeText(getApplicationContext(), "Temp file is created, file name is " + tempFileName, Toast.LENGTH_LONG).show();
                    }
                }catch(IOException ex)
                {
                    Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
                }
            }
        });
    }






    // This method will write data to file.
    private void writeDataToFile(File file, String data)
    {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            this.writeDataToFile(fileOutputStream, data);
            fileOutputStream.close();
        }catch(FileNotFoundException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }catch(IOException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }
    }
    // This method will write data to FileOutputStream.
    private void writeDataToFile(FileOutputStream fileOutputStream, String data)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
        }catch(FileNotFoundException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }catch(IOException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }
    }
    // This method will read data from FileInputStream.
    private String readFromFileInputStream(FileInputStream fileInputStream)
    {
        StringBuffer retBuf = new StringBuffer();
        try {
            if (fileInputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String lineData = bufferedReader.readLine();
                while (lineData != null) {
                    retBuf.append(lineData);
                    lineData = bufferedReader.readLine();
                }
            }
        }catch(IOException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }finally
        {
            return retBuf.toString();
        }
    }
}