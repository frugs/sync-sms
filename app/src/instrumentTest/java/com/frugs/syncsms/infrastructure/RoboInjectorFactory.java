package com.frugs.syncsms.infrastructure;

import android.app.Application;
import com.google.inject.Injector;
import com.google.inject.Module;
import roboguice.RoboGuice;

import static roboguice.RoboGuice.getBaseApplicationInjector;
import static roboguice.RoboGuice.setBaseApplicationInjector;

public class RoboInjectorFactory {
    public static Injector createInjector(Module module) {
        Application application = new Application();
        setBaseApplicationInjector(application, RoboGuice.DEFAULT_STAGE, module);
        return getBaseApplicationInjector(application);
    }
}
