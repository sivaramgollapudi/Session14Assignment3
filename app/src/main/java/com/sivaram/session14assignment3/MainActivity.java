package com.sivaram.session14assignment3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    Button saveFileButton, checkFileButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TypeCast Button from XML To Java
        saveFileButton = (Button)findViewById(R.id.saveFileButton);
        checkFileButton = (Button)findViewById(R.id.checkFileButton);

        saveFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write Data INto Internal Storage
                writeFileToInternalStorage(getApplicationContext(),"session14Assignment2.txt","Write SomeThing to Text File");
            }
        });

        checkFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Absolute App Path including the FIle Name and folder.
                String path = getApplicationContext().getFilesDir().getAbsolutePath() + "/appDir/session14Assignment2.txt";
                // Retrive File based on the path.
                File file = new File(path);
                if (file.exists()) // If Exists display file exists toast
                    Toast.makeText(MainActivity.this,  "File Exists", Toast.LENGTH_SHORT).show();
                else // if not exists display file not found toast/
                    Toast.makeText(MainActivity.this, "File Not Found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void writeFileToInternalStorage(Context context, String fileName, String contentsToWrite){
        // Create file object with current context and required folder.
        File file = new File(context.getFilesDir(),"appDir");

        // If File not exists just create file.
        if(!file.exists()){
            file.mkdir();
        }
        try{
            // Create File Object.
            File textFile = new File(file,fileName);
            // Write Data into file
            FileWriter fileWriter =new FileWriter(textFile);

            // Add Contents into file
            fileWriter.append(contentsToWrite);

            // Close File Writer Object
            fileWriter.flush();
            fileWriter.close();


            // Display Toast.
            Toast.makeText(context, "File Created Successfully...", Toast.LENGTH_SHORT).show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
