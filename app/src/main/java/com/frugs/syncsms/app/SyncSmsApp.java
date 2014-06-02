package com.frugs.syncsms.app;

import android.app.Application;
import com.frugs.syncsms.app.sms.SmsObserver;
import com.frugs.syncsms.app.sms.sync.SmsSyncer;
import com.google.inject.Binder;
import com.google.inject.Module;

import static roboguice.RoboGuice.DEFAULT_STAGE;
import static roboguice.RoboGuice.newDefaultRoboModule;
import static roboguice.RoboGuice.setBaseApplicationInjector;

public class SyncSmsApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        setBaseApplicationInjector(this, DEFAULT_STAGE, newDefaultRoboModule(this), new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(SmsObserver.class).to(SmsSyncer.class);
                binder.bind(ReadOnlyConfiguration.class).to(HardCodedConfiguration.class);
            }
        });
    }
}
