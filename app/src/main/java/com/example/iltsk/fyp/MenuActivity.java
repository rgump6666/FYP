package com.example.iltsk.fyp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bluetoothlibrary.BluetoothLeService;

/**
 * Created by Iltsk on 20/11/2015.
 */
public class MenuActivity extends FragmentActivity implements View.OnClickListener {
    private static final int REQUEST_ENABLE_BT = 1;
    private final static String TAG = MenuActivity.class.getSimpleName();

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGattCharacteristic characteristic;
    private String mDeviceName;
    private String mDeviceAddress;
    private BluetoothLeService mBluetoothLeService = new BluetoothLeService();
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize(getApplicationContext())) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            //mBluetoothLeService.connect(mDeviceAddress, getApplicationContext());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    Button btnChairControl;
    Button btnAutoAdjust;
    Button btnReviewRecord;
    Button btnViewCurrentSittingPosition;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setView();

        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        Intent i = getIntent();
        mDeviceName = i.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = i.getStringExtra(EXTRAS_DEVICE_ADDRESS);
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        /*try {
            boolean result1 = mBluetoothLeService.initialize(getApplicationContext());
            Toast.makeText(this, "Bluetooth Adapter result=" + result1, Toast.LENGTH_SHORT).show();

            if (mBluetoothLeService != null) {
                final boolean result = mBluetoothLeService.connect(mDeviceAddress, getApplicationContext());
                Toast.makeText(this, "Connect request result=" + result, Toast.LENGTH_SHORT).show();
            }

            boolean result = mBluetoothLeService.writeCharacteristic(getApplicationContext());
            Toast.makeText(this, "write character request result=" + result, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }*/
        setFragment(new ChairControlFragment());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnChairControl:
            {
                setFragment(new ChairControlFragment());
                break;
            }
            case R.id.btnAutoAdjust:
            {
                setFragment(new AutoAdjustFragment());
                break;
            }
            case R.id.btnReviewRecord:
            {
                setFragment(new ReviewRecordFragment());
                break;
            }
            case R.id.btnViewCurrentSittingPosition:
            {
                setFragment(new ViewCurrentSittingPositionFragment());
                break;
            }
        }
    }

    private void setFragment(Fragment fragment){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.function, fragment);
        fragmentTransaction.commit();
    }

    private void setView(){
        btnChairControl=(Button)findViewById(R.id.btnChairControl);
        btnAutoAdjust=(Button)findViewById(R.id.btnAutoAdjust);
        btnReviewRecord=(Button)findViewById(R.id.btnReviewRecord);
        btnViewCurrentSittingPosition=(Button)findViewById(R.id.btnViewCurrentSittingPosition);
        btnChairControl.setOnClickListener(this);
        btnAutoAdjust.setOnClickListener(this);
        btnReviewRecord.setOnClickListener(this);
        btnViewCurrentSittingPosition.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
    }
}