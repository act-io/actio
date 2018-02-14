package com.example.sigrundish.actio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    //Your key is a public constant EXTRA_MESSAGE because the next activity uses the key to retrieve the text value.
    //It's a good practice to define keys for intent extras using your app's package name as a prefix.
    public static final String EXTRA_MESSAGE = "com.example.sigrundish.actio.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(MainActivity.this, Login.class);
                MainActivity.this.startActivity(loginIntent);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerIntent = new Intent(MainActivity.this, Register.class);
                MainActivity.this.startActivity(registerIntent);
//                final Client socket1 = new Client("127.0.0.1", 1234);
//                socket1.setClientCallback(new Client.ClientCallback () {
//                    @Override
//                    public void onMessage(String message) {
//                        System.out.println("1");
//                    }
//
//                    @Override
//                    public void onConnect(Socket socket) {
//                        System.out.println("2");
//                        socket1.send("Hello World!\n");
//                        socket1.disconnect();
//                    }
//
//                    @Override
//                    public void onDisconnect(Socket socket, String message) {
//                        System.out.println("3");
//
//                    }
//
//                    @Override
//                    public void onConnectError(Socket socket, String message) {
//                        System.out.println("4");
//                    }
//                });
//
//                socket1.connect();


            }
        });



    }



}
