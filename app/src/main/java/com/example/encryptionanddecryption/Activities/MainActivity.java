package com.example.encryptionanddecryption.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.encryptionanddecryption.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
//    static {
//     //   System.loadLibrary("native-lib");
//        System.loadLibrary("aes256");
//    }
    @BindView(R.id.gotoenc)
    Button gotoenc;
    @BindView(R.id.gotodec)
    Button gotodec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.gotoenc)
    public void setGotoenc(){
        Intent i=new Intent(MainActivity.this,Encryption.class);
        startActivity(i);
    }


    @OnClick(R.id.gotodec)
    public void setGotodec(){
        Intent i=new Intent(MainActivity.this,Decryption.class);
        startActivity(i);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     * @param helloworld
     */
    //public native String aesEncode(String helloworld);
}
