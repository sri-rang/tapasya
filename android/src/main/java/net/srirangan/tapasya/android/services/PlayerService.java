package net.srirangan.tapasya.android.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import net.srirangan.tapasya.android.R;

public class PlayerService extends Service {

  public class LocalBinder extends Binder {
    public PlayerService getService() {
      return PlayerService.this;
    }
  }

  private final IBinder binder = new LocalBinder();

  @Override
  public IBinder onBind(Intent intent) {
    return binder;
  }

  private MediaPlayer omPlayer;
  private MediaPlayer rainPlayer;
  private MediaPlayer forestPlayer;

  public boolean initialize() {
    omPlayer = MediaPlayer.create(this, R.raw.om);
    omPlayer.setLooping(true);
    rainPlayer = MediaPlayer.create(this, R.raw.rain);
    rainPlayer.setLooping(true);
    forestPlayer = MediaPlayer.create(this, R.raw.forest);
    forestPlayer.setLooping(true);
    return true;
  }

  public boolean forceStop() {
    if (omPlayer != null && omPlayer.isPlaying()) omPlayer.stop();
    if (rainPlayer != null && rainPlayer.isPlaying()) rainPlayer.stop();
    if (forestPlayer != null && forestPlayer.isPlaying()) forestPlayer.stop();
    return true;
  }

  public boolean toggleOm(boolean turnOn) {
    togglePlayer(omPlayer, turnOn);
    return true;
  }

  public boolean toggleRain(boolean turnOn) {
    togglePlayer(rainPlayer, turnOn);
    return true;
  }

  public boolean toggleForest(boolean turnOn) {
    togglePlayer(forestPlayer, turnOn);
    return true;
  }

  private void togglePlayer(MediaPlayer player, boolean turnOn) {
    if (turnOn) {
      if (player != null) player.start();
    } else {
      if (player != null && player.isPlaying()) player.pause();
    }
  }
}
