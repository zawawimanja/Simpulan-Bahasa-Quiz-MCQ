package quiz.simpulanbahasa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;




import info.hoang8f.widget.FButton;

public class HomeScreen extends AppCompatActivity {

    FButton playGame,quit,settings;
    TextView tQ;
    MediaPlayer mediaPlayer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        settings=(FButton)findViewById(R.id.settings) ;
        playGame =(FButton)findViewById(R.id.playGame);
        tQ =(TextView) findViewById(R.id.tQ);







        //PlayGame button - it will take you to the MainGameActivity
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = MediaPlayer.create(HomeScreen.this, R.raw.push);
                mediaPlayer.start();
                Intent intent = new Intent(HomeScreen.this,MainGameActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                finish();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(HomeScreen.this, R.raw.push);
                mediaPlayer.start();
                Intent intent = new Intent(HomeScreen.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        //Typeface - this is for fonts style
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playGame.setTypeface(typeface);
        settings.setTypeface(typeface);
        tQ.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
