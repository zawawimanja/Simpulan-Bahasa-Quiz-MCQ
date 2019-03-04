package quiz.simpulanbahasa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class GameWon extends Activity {

    MediaPlayer mediaPlayer;
    public static String result="highscore";
    public static final String mypreference = "Result";
    TextView score1,highscore1;
    Intent a = new Intent();
    SharedPreferences   sharedpreferences;
    int score=0;
    Typeface tb;
    private InterstitialAd interstitialAd;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won);
        score1=(TextView)findViewById(R.id.score);
        highscore1=(TextView)findViewById(R.id.highscore);

        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");

        score1.setTypeface(tb);
        highscore1.setTypeface(tb);

        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        interstitialAd.setAdUnitId(getString(R.string.inter_ad_unit_id));
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(GameWon.this, MainGameActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });

        //get from main
        String a1= getIntent().getStringExtra("a");
        score=Integer.parseInt(a1);

        score1.setText("Markah Terkini:\t"+a1);

        setHighscore(score);
        int last=getHighScore();
        highscore1.setText("Markah Tertinggi:\t"+String.valueOf(last));


    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {

            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            startGame();
//            count++;
//            if(count==2){
//                Intent intent = new Intent(GameWon.this, MainGameActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra("EXIT", true);
//                startActivity(intent);
//            }
        }
    }

    private void startGame() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
        else{
            Intent intent = new Intent(GameWon.this, MainGameActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
    }

    public void setHighscore(int score){
        final SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
        int highScore=sharedPreferences.getInt(result,0);
        if(score > highScore){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt(result,score);
            editor.commit();
        }
    }

    public int getHighScore(){
        final SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
        return sharedPreferences.getInt(result,score);
    }

    //This is onclick listener for button
    //it will navigate from this activity to MainGameActivity
    public void PlayAgain(View view) {
        mediaPlayer = MediaPlayer.create(GameWon.this, R.raw.push);
        mediaPlayer.start();
        startGame();
//        Intent intent = new Intent(GameWon.this, MainGameActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("EXIT", true);
//        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GameWon.this, HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
}
