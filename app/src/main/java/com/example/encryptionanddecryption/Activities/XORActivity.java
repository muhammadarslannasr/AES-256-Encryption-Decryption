package com.example.encryptionanddecryption.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.encryptionanddecryption.R;

public class XORActivity extends AppCompatActivity {

    static {
        System.loadLibrary("xor");
    }
    Button btnencode;
    Button btndecode;
    TextView sample_text;
    static String plainText = "Muhammad Arslan Nasr";
    String keyval = "thisIsAKey";
    String encryptedString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xor);
        btndecode = findViewById(R.id.btndecode);
        btnencode = findViewById(R.id.btnencode);
        sample_text = findViewById(R.id.sample_text);
        sample_text.setText(plainText);
        Log.i("sadsad",encodeData());

        btnencode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sampleString = "GeeksforGeeks";

                // Encrypt the string
                System.out.println("Encrypted String");
                encryptedString = encryptDecrypt(sampleString);
                sample_text.setText(encryptedString);
            }
        });

        btndecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sample_text.setText(encryptDecrypt(encryptedString));

            }
        });

    }

    public native String encodeData();

    static String encryptDecrypt(String inputString)
    {
        // Define XOR key
        // Any character value will work
        char xorKey = 'P';

        // Define String to store encrypted/decrypted String
        String outputString = "";

        // calculate length of input string
        int len = inputString.length();

        // perform XOR operation of key
        // with every caracter in string
        for (int i = 0; i < len; i++)
        {
            outputString = outputString +
                    Character.toString((char) (inputString.charAt(i) ^ xorKey));
        }

        System.out.println(outputString);
        return outputString;
    }
}
