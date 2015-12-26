package com.example.iltsk.fyp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends Activity {

    NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if(nfcAdapter!=null && nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC available!",Toast.LENGTH_LONG).show();
        }else{
            //finish();
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        enableForegroundDispatchSystem();
    }

    @Override
    protected void onPause() {
        super.onPause();

        disableForegroundDispatchSystem();
    }

    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);

        String tagContent = null;

        Toast.makeText(this, "NFC intent received", Toast.LENGTH_LONG).show();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        if(parcelables != null && parcelables.length > 0)
        {
            tagContent = readTextFromMessage((NdefMessage) parcelables[0]);
            Toast.makeText(this, tagContent, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No NDEF messages found!", Toast.LENGTH_SHORT).show();
        }
        int tempIndex = tagContent.indexOf(" ");
        String deviceName = tagContent.substring(0,tempIndex);
        String deviceAddess = tagContent.substring(tempIndex+1);

        Intent i = new Intent(this, MenuActivity.class);
        i.putExtra(MenuActivity.EXTRAS_DEVICE_NAME, deviceName);
        i.putExtra(MenuActivity.EXTRAS_DEVICE_ADDRESS, deviceAddess);
        startActivity(i);

    }

    private String readTextFromMessage(NdefMessage ndefMessage) {

        String tagContent = null;

        NdefRecord[] ndefRecords = ndefMessage.getRecords();

        if(ndefRecords != null && ndefRecords.length>0){

            NdefRecord ndefRecord = ndefRecords[0];

            tagContent = getTextFromNdefRecord(ndefRecord);

        }else
        {
            Toast.makeText(this, "No NDEF records found!", Toast.LENGTH_SHORT).show();
        }

        return tagContent;

    }

    public String getTextFromNdefRecord(NdefRecord ndefRecord) {
        String tagContent = null;
        try {
            byte[] payload = ndefRecord.getPayload();
            String a = "UTF-8";
            String b = "UTF-16";
            String textEncoding = ((payload[0] & 128) == 0) ? a : b;
            int languageSize = payload[0] & 0063;
            tagContent = new String(payload, languageSize + 1,
                    payload.length - languageSize - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("getTextFromNdefRecord", e.getMessage(), e);
        }
        return tagContent;
    }

    private void enableForegroundDispatchSystem() {

        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        IntentFilter[] intentFilters = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);
    }

    private void disableForegroundDispatchSystem() {
        nfcAdapter.disableForegroundDispatch(this);
    }
}
