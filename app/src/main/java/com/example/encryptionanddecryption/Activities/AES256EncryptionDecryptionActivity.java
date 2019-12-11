package com.example.encryptionanddecryption.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.encryptionanddecryption.Functions.Decrypt;
import com.example.encryptionanddecryption.Functions.Encrypt;
import com.example.encryptionanddecryption.R;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AES256EncryptionDecryptionActivity extends AppCompatActivity {
    Button btnencode;
    Button btndecode;
    TextView sample_text;
    private static String Key = "your key";
    String returnResult;
    private static String secretKey = "boooooooooom!!!!";
    private static String salt = "ssshhhhhhhhhhh!!!!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes256_encryption_decryption);
        btndecode = findViewById(R.id.btndecode);
        btnencode = findViewById(R.id.btnencode);
        sample_text = findViewById(R.id.sample_text);

        btnencode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                returnResult = encryptString(Key);
                sample_text.setText(returnResult);

            }
        });

        btndecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    SecretKeySpec decryptedResult = getKey(returnResult);
                    sample_text.setText(decryptedResult.toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String encryptString(String stringToEncode) throws NullPointerException {

        try {
            SecretKeySpec skeySpec = getKey(Key);
            byte[] clearText = stringToEncode.getBytes("UTF8");
            final byte[] iv = new byte[16];
            Arrays.fill(iv, (byte) 0x00);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
            String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
            return encrypedValue;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private static SecretKeySpec getKey(String password) throws UnsupportedEncodingException {
        int keyLength = 256;
        byte[] keyBytes = new byte[keyLength / 8];
        Arrays.fill(keyBytes, (byte) 0x0);
        byte[] passwordBytes = password.getBytes("UTF-8");
        int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
        System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        return key;
    }
}
