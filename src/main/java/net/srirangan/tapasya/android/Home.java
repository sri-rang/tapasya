package net.srirangan.tapasya.android;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.io.IOException;

public class Home extends Activity {

  MediaPlayer mediaPlayer;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home);
  }

  @Override
  public void onStart() {
    super.onStart();
    mediaPlayer = MediaPlayer.create(this, R.raw.om);
    mediaPlayer.setLooping(true);
  }

  @Override
  public void onStop() {
    super.onStop();
    if (mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.stop();
  }

  public void toggleOmChants(View view) throws IOException {
    if (((CheckBox) view).isChecked()) {
      if (mediaPlayer != null) mediaPlayer.start();
    } else {
      if (mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.pause();
    }
  }

}
