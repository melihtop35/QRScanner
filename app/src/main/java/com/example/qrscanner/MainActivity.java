package com.example.qrscanner;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {
    Button btn_scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v -> {
            scanCode();
        });
    }
    private String decryptAES(String encryptedText, String key) {
        try {
            byte[] keyBytes = key.getBytes("UTF-8");
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            String[] encryptedValues = encryptedText.split(";");
            StringBuilder decryptedBuilder = new StringBuilder();

            for (String value : encryptedValues) {
                byte[] decryptedBytes = cipher.doFinal(android.util.Base64.decode(value, android.util.Base64.DEFAULT));
                decryptedBuilder.append(new String(decryptedBytes));
            }

            return decryptedBuilder.toString();
        } catch (Exception e) {
            Log.e("AES Decryption", "Decryption error: " + e.getMessage());
            return "";
        }
    }

    private void scanCode() {
        ScanOptions options=new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Result");
        String decryptedText = decryptAES(result.getContents(), "1234567890123456"); // Anahtarınızı buraya girin
        if (decryptedText != null) {
            builder.setMessage(decryptedText);
        } else {
            builder.setMessage("Decryption failed.");
        }
        builder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).show();
    });

}