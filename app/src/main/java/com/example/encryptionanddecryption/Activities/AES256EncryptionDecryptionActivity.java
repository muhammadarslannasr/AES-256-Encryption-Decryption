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
    static String plainText = "Muhammad Arslan Nasr";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes256_encryption_decryption);
        btndecode = findViewById(R.id.btndecode);
        btnencode = findViewById(R.id.btnencode);
        sample_text = findViewById(R.id.sample_text);
        sample_text.setText(plainText);
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert keyGenerator != null;
        keyGenerator.init(256);

        // Generate Key
        SecretKey key = keyGenerator.generateKey();

        // Generating IV.
        byte[] IV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        System.out.println("Original Text  : "+plainText);

//        byte[] cipherText = new byte[0];
//        try {
//            cipherText = encrypt(plainText.getBytes(),key, IV);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //System.out.println("Encrypted Text : "+Base64.getEncoder().encodeToString(cipherText));


        //System.out.println("DeCrypted Text : "+decryptedText);

        final byte[][] cipherText = {new byte[0]};

        btnencode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cipherText[0] = encrypt(plainText.getBytes(),key, IV);
                    sample_text.setText(cipherText[0].toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btndecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String decryptedText = decrypt(cipherText[0],key, IV);
                    sample_text.setText(decryptedText);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

//    public static String encryptString(String stringToEncode) throws NullPointerException {
//
//        try {
//            SecretKeySpec skeySpec = getKey(Key);
//            byte[] clearText = stringToEncode.getBytes("UTF8");
//            final byte[] iv = new byte[16];
//            Arrays.fill(iv, (byte) 0x00);
//            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
//            String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
//            return encrypedValue;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//
//    private static SecretKeySpec getKey(String password) throws UnsupportedEncodingException {
//        int keyLength = 256;
//        byte[] keyBytes = new byte[keyLength / 8];
//        Arrays.fill(keyBytes, (byte) 0x0);
//        byte[] passwordBytes = password.getBytes("UTF-8");
//        int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
//        System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
//        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
//        return key;
//    }

    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
        //Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        //Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

        //Create IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);

        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);

        return cipherText;
    }

    public static String decrypt(byte[] cipherText, SecretKey key, byte[] IV) throws Exception {
        //Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        //Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

        //Create IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);

        //Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        //Perform Decryption
        byte[] decryptedText = cipher.doFinal(cipherText);

        return new String(decryptedText);
    }
}
