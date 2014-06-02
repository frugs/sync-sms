package com.frugs.syncsms.app;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.frugs.syncsms.app.sms.SmsObserver;
import com.frugs.syncsms.app.sms.SmsReceiver;
import com.frugs.syncsms.app.sms.sync.SmsSyncer;
import com.google.inject.Binder;
import com.google.inject.Module;

import static android.provider.Telephony.Sms.Intents.SMS_RECEIVED_ACTION;
import static roboguice.RoboGuice.*;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        this.registerReceiver(new SmsReceiver(), new IntentFilter(SMS_RECEIVED_ACTION));
        sendBroadcast(new Intent(SMS_RECEIVED_ACTION));

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
