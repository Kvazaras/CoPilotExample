package com.example.copilotexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvMain = findViewById(R.id.tvMain);

        //call open file for write
        openFileForWrite();

        //set click for tvmain
        this.tvMain.setOnClickListener(v -> {
            String text = this.tvMain.getText().toString();
            this.tvMain.setText(String.valueOf(getNumbersCount(text)));

            //change tvmain text color to RGB 255 125 0
            this.tvMain.setTextColor(0xFFFF7D00);

            //tvmain append text from read file
            this.tvMain.append(readFile());
        });
    }

    //get words count in string
    public int getWordsCount(String str) {
        int count = 0;
        if (str != null && !str.isEmpty()) {
            String[] words = str.split("\\s+");
            count = words.length;
        }
        return count;
    }

    //get numbers count in string
    public int getNumbersCount(String str) {
        int count = 0;
        if (str != null && !str.isEmpty()) {
            String[] words = str.split("\\s+");
            for (String word : words) {
                if (word.matches("\\d+")) {
                    count++;
                }
            }
        }
        return count;
    }

    //open new activity
    public void openNewActivity() {
//        Intent intent = new Intent(this, NewActivity.class);
//        startActivity(intent);
    }

    //open file for read
    public void openFileForRead() {
        try {
            InputStream inputStream = openFileInput("file.txt");
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                String result = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    //read file and return string
    public String readFile() {
        String ret = "";
        try {
            InputStream inputStream = openFileInput("file.txt");
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }

    //open file for write
    public void openFileForWrite() {
        String string = "Hello world!";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("file.txt", Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}