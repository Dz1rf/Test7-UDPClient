package com.example.test1;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private TextView text1;
    private TextView textV2;
    private TextView textV3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);
        textV2 = findViewById(R.id.textV2);
        textV3 = findViewById(R.id.textV3);

        text1.setText("222");

        udpClient clientOn = new udpClient();
        try {
            clientOn.udpClientOn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}