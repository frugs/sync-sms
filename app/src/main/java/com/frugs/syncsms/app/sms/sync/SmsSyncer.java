package com.frugs.syncsms.app.sms.sync;

import android.os.AsyncTask;
import com.frugs.syncsms.app.ReadOnlyConfiguration;
import com.frugs.syncsms.app.sms.Sms;
import com.frugs.syncsms.app.sms.SmsObserver;
import com.google.inject.Inject;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.net.SocketFactory.getDefault;

public class SmsSyncer implements SmsObserver {
    @Inject
    private ReadOnlyConfiguration config;

    @Inject
    private SmsJsonMapper smsJsonMapper;

    @Override
    public void notifySmsReceived(Sms sms) {
        new SyncSmsAsynchronously().execute(sms);
    }

    private class SyncSmsAsynchronously extends AsyncTask<Sms, Void, Void> {
        @Override
        protected Void doInBackground(Sms... params) {
            Sms sms = params[0];
            try {
                Socket socket = getDefault().createSocket();
                socket.connect(new InetSocketAddress(config.getClientAddress(), config.getClientPort()), 20000);

                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(smsJsonMapper.toJson(sms).toString().getBytes(UTF_8));
                outputStream.flush();

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
