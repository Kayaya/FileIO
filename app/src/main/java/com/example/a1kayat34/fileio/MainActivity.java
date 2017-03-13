package com.example.a1kayat34.fileio;

import android.app.AlertDialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save){
            try
            {
                PrintWriter pw =
                        new PrintWriter( new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() +"/data.txt"));

                EditText text = (EditText) findViewById(R.id.write);

                pw.println(text.getText().toString());
                pw.close(); // close the file to ensure data is flushed to file
            }
            catch(IOException e)
            {
                new AlertDialog.Builder(this).setMessage("ERROR: " + e).
                        setPositiveButton("OK", null).show();
            }

            return true;
        }
        if(item.getItemId() == R.id.load){
            EditText text = (EditText) findViewById(R.id.write);
            try
            {
                FileReader fr = new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath() + "/data.txt");
                BufferedReader reader = new BufferedReader(fr);
                String line = "", wholeFile = "";
                while((line = reader.readLine()) != null)
                {
                    wholeFile = wholeFile + line + "\n";
                }

                reader.close();
                //set a text to EditText
                text.setText(wholeFile);

            }
            catch(IOException e)
            {
                new AlertDialog.Builder(this).setMessage("ERROR: " + e).
                        setPositiveButton("OK", null).show();

            }
            return true;
        }
        return false;
    }
}
