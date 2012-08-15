package net.srirangan.tapasya.android;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.CheckBox;
import net.srirangan.tapasya.android.services.OmService;

import java.io.IOException;

public class Home extends Activity {

  private OmService omService;

  private boolean bound = false;

  private ServiceConnection omServiceConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      OmService.LocalBinder binder = (OmService.LocalBinder) service;
      omService = binder.getService();
      bound = true;
      omService.initialize();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      bound = false;
    }
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home);
    Intent intent = new Intent(this, OmService.class);
    bindService(intent, omServiceConnection, Context.BIND_AUTO_CREATE);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    omService.forceStop();
    if (bound) {
      unbindService(omServiceConnection);
      bound = false;
    }
  }

  public void toggleOmChants(View view) throws IOException {
    Boolean turnOn = ((CheckBox) view).isChecked();
    omService.toggle(turnOn);
  }

}
