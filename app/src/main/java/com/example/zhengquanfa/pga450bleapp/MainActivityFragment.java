package com.example.zhengquanfa.pga450bleapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private View main_view;
    public  BluetoothAdapter mBluetoothAdapter;
    private LeDeviceListAdapter mLeDeviceListAdapter;
    private Handler mHandler;
    private boolean mScanning = false;
    protected ListView mList;

    private static final int REQUEST_ENABLE_BT = 1;
    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;


    public MainActivityFragment() {
    }
/*
    @Override
    public void onResume() {
        super.onResume();
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        // Use this check to determine whether BLE is supported on the device.  Then you can
        // selectively disable BLE-related features.
        if (!getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(getActivity(), R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }

        // Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
        // BluetoothAdapter through BluetoothManager.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        // Checks if Bluetooth is supported on the device.
        if (mBluetoothAdapter == null) {
            Toast.makeText(getActivity(), R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
        if (!mBluetoothAdapter.isEnabled()) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
        //initial the viewlist
        mLeDeviceListAdapter = new LeDeviceListAdapter();
        mList = (ListView) getActivity().findViewById(R.id.listView);
        mList.setAdapter(mLeDeviceListAdapter);

        //start a 10s period task
        mHandler = new Handler();
    }
    Runnable runnable=new Runnable(){
        @Override
        public void run() {
            // scan 10s then close
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
            ProgressBar mypb = (ProgressBar) main_view.findViewById(R.id.progressBar);
            mypb.setVisibility(mypb.INVISIBLE);
            mScanning = false;
        }
    };
    View.OnClickListener mylistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int ID = v.getId();
            switch (ID) {
                case R.id.button_scan:
                    if(mScanning == false) {
                        ProgressBar mypb = (ProgressBar) main_view.findViewById(R.id.progressBar);
                        mypb.setVisibility(v.VISIBLE);
                        mScanning = true;
                        // Stops scanning after a pre-defined scan period.
                        mHandler.postDelayed(runnable, SCAN_PERIOD);
                        mBluetoothAdapter.startLeScan(mLeScanCallback);
                    }else{
                        ProgressBar mypb = (ProgressBar) main_view.findViewById(R.id.progressBar);
                        mypb.setVisibility(v.INVISIBLE);
                        mScanning = false;
                        mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    }
                    break;
                case R.id.button_connect:
                    break;
                case R.id.button_disconnect:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == getActivity().RESULT_OK) {
            }
            else if (resultCode == getActivity().RESULT_CANCELED) {
                Toast.makeText(getActivity(), R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Button scan,connect,disconnect;
        main_view = inflater.inflate(R.layout.fragment_main, container, false);
        scan = (Button) main_view.findViewById(R.id.button_scan);
        connect = (Button) main_view.findViewById(R.id.button_connect);
        disconnect = (Button) main_view.findViewById(R.id.button_disconnect);
        scan.setOnClickListener(mylistener );
        connect.setOnClickListener(mylistener );
        disconnect.setOnClickListener(mylistener );
        return main_view;
    }
    //*****************************************************************
    static class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
    }
    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    /*
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mLeDeviceListAdapter.addDevice(device);
                            mLeDeviceListAdapter.notifyDataSetChanged();
                        }
                    });*/
                }
            };
    private class LeDeviceListAdapter extends BaseAdapter {
        private ArrayList<BluetoothDevice> mLeDevices;
        private LayoutInflater mInflator;

        public LeDeviceListAdapter() {
            super();
            mLeDevices = new ArrayList<BluetoothDevice>();
            mInflator = getActivity().getLayoutInflater();
        }

        public void addDevice(BluetoothDevice device) {
            if(!mLeDevices.contains(device)) {
                mLeDevices.add(device);
            }
        }

        public BluetoothDevice getDevice(int position) {
            return mLeDevices.get(position);
        }

        public void clear() {
            mLeDevices.clear();
        }

        @Override
        public int getCount() {
            return mLeDevices.size();
        }

        @Override
        public Object getItem(int i) {
            return mLeDevices.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            // General ListView optimization code.
            if (view == null) {
                view = mInflator.inflate(R.layout.listitem_device, null);
                viewHolder = new ViewHolder();
                viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
                viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            BluetoothDevice device = mLeDevices.get(i);
            final String deviceName = device.getName();
            if (deviceName != null && deviceName.length() > 0)
                viewHolder.deviceName.setText(deviceName);
            else
                viewHolder.deviceName.setText(R.string.unknown_device);
            viewHolder.deviceAddress.setText(device.getAddress());
            return null;

        }
    }
}
