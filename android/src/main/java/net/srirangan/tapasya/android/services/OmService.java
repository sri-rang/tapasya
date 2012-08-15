package net.srirangan.tapasya.android.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import net.srirangan.tapasya.android.R;

public class OmService extends Service {

  public class LocalBinder extends Binder {
    public OmService getService() {
      return OmService.this;
    }
  }

  private final IBinder binder = new LocalBinder();

  @Override
  public IBinder onBind(Intent intent) {
    return binder;
  }

  private MediaPlayer mediaPlayer;

  public boolean initialize() {
    mediaPlayer = MediaPlayer.create(this, R.raw.om);
    mediaPlayer.setLooping(true);
    return true;
  }

  public boolean toggle(boolean turnOn) {
    if (turnOn) {
      if (mediaPlayer != null) mediaPlayer.start();
    } else {
      if (mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.pause();
    }
    return true;
  }

  public boolean forceStop() {
    if (mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.stop();
    return true;
  }
}
