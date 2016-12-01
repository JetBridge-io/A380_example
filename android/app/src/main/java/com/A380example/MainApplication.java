package com.A380example;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.jetbridge.A380.A380;
import com.jetbridge.A380.A380Package;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {
  private String mAppKey = "1da2b694-e320-4764-95c1-503dbe99da43";
  private String mServer = "http://192.168.0.15:3000";

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public ReactInstanceManager getReactInstanceManager() {
      if (!A380.Get().isInitialized()) {
        A380.Get().init(this.getApplication().getApplicationContext(), "0", mAppKey, mServer);
      }

      return super.getReactInstanceManager();
    }

    @Override
    protected boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected String getJSBundleFile() {
      return A380.Get().getValidBundlePath(mAppKey);
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new A380Package(mAppKey, mServer)
      );
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
      return mReactNativeHost;
  }
}
