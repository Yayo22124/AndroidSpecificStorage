package com.example.specificstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView txtFileName, txtFileContent;
    private ListView lvFiles;
    private Button btnSave;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private String fileName, fileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asignacion de variables
        txtFileName = findViewById(R.id.txt_file_name);
        txtFileContent = findViewById(R.id.txt_filecontent);
        lvFiles = findViewById(R.id.lv_files);
        updateList();
        btnSave = findViewById(R.id.btn_save);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileName = txtFileName.getText().toString();
                fileContent = txtFileContent.getText().toString();
                cleanFields();
                try {
                    fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
                    fileOutputStream.write(fileContent.getBytes());
                    InputStreamReader inputStreamReader = new InputStreamReader();
                    BufferedReader bufferedReader = new BufferedReader(fileOutputStream);
                    String line = bufferedReader.readLine();
                    while (line != null){
                        fileContent+=line + "\n";
                    }
                    updateList();
                    Toast.makeText(MainActivity.this, "Receta Guardada \n Correctamente", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected void updateList() {
        String[] dataOrigins = fileList();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, dataOrigins);
        lvFiles.setAdapter(adapter);
    }

    protected void cleanFields() {
        txtFileContent
    }

}