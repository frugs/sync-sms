package com.frugs.syncsms.clientapplication;

import com.frugs.syncsms.contract.NetworkConfig;
import com.frugs.syncsms.contract.Sms;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class SmsSyncerServer {
    private final ExecutorService executor = new ScheduledThreadPoolExecutor(4);

    private final SmsReceiverFactory smsReceiverFactory;

    private boolean running = false;

    public SmsSyncerServer(SmsReceiverFactory smsReceiverFactory) {
        this.smsReceiverFactory = smsReceiverFactory;
    }

    public void startServer() throws IOException, ExecutionException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(NetworkConfig.defaultPort);

        while (running) {
            final Socket socket = serverSocket.accept();
            executor.submit(new Runnable() {
                public void run() {
                    try {

                        String jsonInput = IOUtils.toString(socket.getInputStream());
                        Sms sms = new Gson().fromJson(jsonInput, Sms.class);

                        smsReceiverFactory.createSmsReceiver().receiveSms(sms);
                    } catch (Throwable throwable) {
                        throw new RuntimeException(throwable);
                    }
                }
            }).get();
        }
    }

    public void stopServer() {
        running = false;
    }
}
