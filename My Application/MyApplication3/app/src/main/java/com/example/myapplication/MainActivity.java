package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ListView lstvw;
    private ArrayAdapter aAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();


        Button temp = (Button) findViewById(R.id.button);
        final TextView text = (TextView) findViewById(R.id.textView);

        temp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (bAdapter == null) {
                    text.setText("NO Connection");
                } else if (!bAdapter.isEnabled()) {
                    text.setText("Good connection");
                    ArrayList dList = new ArrayList();
                    Set<BluetoothDevice> pDevices = bAdapter.getBondedDevices();
                    if (pDevices.size()>0){
                        for(BluetoothDevice device : pDevices) {
                            String dName = device.getName();
                            String dAddress = device.getAddress();
                            dList.add("Name: " + dName + "Device Address: " + dAddress);
                        }
                    }
                    lstvw = (ListView) findViewById(R.id.deviceList);
                    aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,dList);
                    lstvw.setAdapter(aAdapter);

                }

                // Code here executes on main thread after user presses button
            }
        });
    }
}
