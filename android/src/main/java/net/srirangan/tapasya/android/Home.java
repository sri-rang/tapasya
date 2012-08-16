package net.srirangan.tapasya.android;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.CompoundButton;
import net.srirangan.tapasya.android.services.PlayerService;

import java.io.IOException;

public class Home extends Activity {

  private PlayerService playerService;

  private boolean bound = false;

  private ServiceConnection omServiceConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      PlayerService.LocalBinder binder = (PlayerService.LocalBinder) service;
      playerService = binder.getService();
      bound = true;
      playerService.initialize();
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
    Intent intent = new Intent(this, PlayerService.class);
    bindService(intent, omServiceConnection, Context.BIND_AUTO_CREATE);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    playerService.forceStop();
    if (bound) {
      unbindService(omServiceConnection);
      bound = false;
    }
  }

  public void toggleOm(View view) throws IOException {
    if (bound) playerService.toggleOm(((CompoundButton) view).isChecked());
  }

  public void toggleRain(View view) throws IOException {
    if (bound) playerService.toggleRain(((CompoundButton) view).isChecked());
  }

  public void toggleForest(View view) throws IOException {
    if (bound) playerService.toggleForest(((CompoundButton) view).isChecked());
  }

}
