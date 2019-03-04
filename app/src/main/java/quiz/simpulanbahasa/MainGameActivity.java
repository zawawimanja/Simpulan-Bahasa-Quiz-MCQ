package quiz.simpulanbahasa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Collections;
import java.util.List;


import info.hoang8f.widget.FButton;

public class MainGameActivity extends AppCompatActivity {

    Button buttonA, buttonB, buttonC, buttonD;
    TextView questionText, triviaQuizText, timeText, resultText, coinText,life;
    TriviaQuizHelper triviaQuizHelper;
    TriviaQuestion currentQuestion;
    List<TriviaQuestion> list;
    int qid = 0;
    int timeValue = 20;
    int coinValue = 0;
    int lifeValue = 5;
    CountDownTimer countDownTimer;
    Typeface tb, sb;
    Intent a=new Intent();
    MediaPlayer mediaPlayer,mediaPlayer1;


    private AdView adView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-3644088453072578~1347065445");

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        adView = findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);


        mediaPlayer1 = MediaPlayer.create(this, R.raw.clock);
        mediaPlayer1.start();
        mediaPlayer1.setLooping(true);




        //Initializing variables
        questionText = (TextView) findViewById(R.id.triviaQuestion);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);
        triviaQuizText = (TextView) findViewById(R.id.triviaQuizText);
        timeText = (TextView) findViewById(R.id.timeText);
        resultText = (TextView) findViewById(R.id.resultText);
        coinText = (TextView) findViewById(R.id.coinText);
        life = (TextView) findViewById(R.id.life);

        //Setting typefaces for textview and buttons - this will give stylish fonts on textview and button etc
        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        triviaQuizText.setTypeface(sb);
        questionText.setTypeface(tb);
        buttonA.setTypeface(tb);
        buttonB.setTypeface(tb);
        buttonC.setTypeface(tb);
        buttonD.setTypeface(tb);
        timeText.setTypeface(tb);
        resultText.setTypeface(sb);
        coinText.setTypeface(tb);

        //Our database helper class
        triviaQuizHelper = new TriviaQuizHelper(this);
        //Make db writable
        triviaQuizHelper.getWritableDatabase();

        //It will check if the ques,options are already added in table or not
        //If they are not added then the getAllOfTheQuestions() will return a list of size zero
        if (triviaQuizHelper.getAllOfTheQuestions().size() == 0) {
            //If not added then add the ques,options in table
            triviaQuizHelper.allQuestion();
        }

        //This will return us a list of data type TriviaQuestion
        list = triviaQuizHelper.getAllOfTheQuestions();

        //Now we gonna shuffle the elements of the list so that we will get questions randomly
        Collections.shuffle(list);

        //currentQuestion will hold the que, 4 option and ans for particular id
        currentQuestion = list.get(qid);

        //countDownTimer
        countDownTimer = new CountDownTimer(22000, 1000) {
            public void onTick(long millisUntilFinished) {


                //here you can have your logic to set text to timeText
                timeText.setText(String.valueOf(timeValue) + "\"");

                //With each iteration decrement the time by 1 sec
                timeValue -= 1;

                //This means the user is out of time so onFinished will called after this iteration
                if (timeValue == -1) {

                    //Since user is out of time setText as time up
                    resultText.setText(getString(R.string.timeup));

                    //Since user is out of time he won't be able to click any buttons
                    //therefore we will disable all four options buttons using this method
                    disableButton();
                }
            }

            //Now user is out of time
            public void onFinish() {
                //We will navigate him to the time up activity using below method
                timeUp();
            }
        }.start();

        //This method will set the que and four options
        updateQueAndOptions();


    }


    public void updateQueAndOptions() {


        //This method will setText for que and options
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());
        buttonD.setText(currentQuestion.getOptD());


        timeValue = 20;

        //Now since the user has ans correct just reset timer back for another que- by cancel and start
        countDownTimer.cancel();
        countDownTimer.start();



        mediaPlayer1.start();


        //set the value of coin text
        coinText.setText(String.valueOf(coinValue));


        life.setText(String.valueOf(lifeValue));

        String a2=String.valueOf(coinValue);

        if(lifeValue==0){
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            a.putExtra("EXIT", true);
            a.putExtra("a",coinText.getText().toString());
            a.setClass(getApplicationContext(),GameWon.class);
            startActivity(a);
        }

    }

    //Onclick listener for first button
    public void buttonA(View view) {

        //compare the option with the ans if yes then make button color green
        if (currentQuestion.getOptA().equals(currentQuestion.getAnswer())) {
        //    buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            //Check if user has not exceeds the que limit
            if (qid < list.size() - 1) {

                //Now since user has ans correct increment the coinvalue
                coinValue++;

                //Now disable all the option button since user ans is correct so
                //user won't be able to press another option button after pressing one button
                disableButton();

                //Show the dialog that ans is correct
                correctDialog(currentQuestion.getAnswer());
            }
            //If user has exceeds the que limit just navigate him to GameWon activity
            else {

                gameWon();

            }
        }
        //User ans is wrong then just navigate him to the PlayAgain activity
        else {
            correctDialog("false");
            lifeValue--;



        }
    }

    //Onclick listener for sec button
    public void buttonB(View view) {

        if (currentQuestion.getOptB().equals(currentQuestion.getAnswer())) {
        //    buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {

                //Now since user has ans correct increment the coinvalue
                coinValue++;
                disableButton();
                correctDialog(currentQuestion.getAnswer());
            } else {
                gameWon();
            }
        } else {
            correctDialog("false");
            lifeValue--;


        }
    }

    //Onclick listener for third button
    public void buttonC(View view) {

        if (currentQuestion.getOptC().equals(currentQuestion.getAnswer())) {
         //   buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                //Now since user has ans correct increment the coinvalue
                coinValue++;
                disableButton();
                correctDialog(currentQuestion.getAnswer());
            } else {
                gameWon();
            }
        } else {
            correctDialog("false");
            lifeValue--;


        }
    }

    //Onclick listener for fourth button
    public void buttonD(View view) {

        if (currentQuestion.getOptD().equals(currentQuestion.getAnswer())) {
        //    buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                //Now since user has ans correct increment the coinvalue
                coinValue++;
                disableButton();
                correctDialog(currentQuestion.getAnswer());
            } else {
                gameWon();
            }
        } else {
            correctDialog("false");
            lifeValue--;


        }
    }

    //This method will navigate from current activity to GameWon
    public void gameWon() {
        Intent intent = new Intent(this, GameWon.class);
        startActivity(intent);
        finish();
    }

    //This method is called when user ans is wrong
    //this method will navigate user to the activity PlayAgain
    public void gameLostPlayAgain() {
        Intent intent = new Intent(this, PlayAgain.class);
        startActivity(intent);
        finish();
    }

    //This method is called when time is up
    //this method will navigate user to the activity Time_Up
    public void timeUp() {
        Intent intent = new Intent(this, Time_Up.class);
        startActivity(intent);
        finish();
    }

    //If user press home button and come in the game from memory then this
    //method will continue the timer from the previous time it left
    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
        mediaPlayer1.start();
    }

    //When activity is destroyed then this will cancel the timer
    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    //This will pause the time
    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
        mediaPlayer1.pause();
        if (adView != null) {
            adView.pause();
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adView != null) {
            adView.destroy();
        }

    }




    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }



    //This dialog is show to the user after he ans correct
    public void correctDialog(String input) {
        countDownTimer.cancel();

        final Dialog dialogCorrect = new Dialog(MainGameActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();


        //Since the dialog is show to user just pause the timer in background



        TextView explain = (TextView) dialogCorrect.findViewById(R.id.explain);
        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
        FButton buttonNext = (FButton) dialogCorrect.findViewById(R.id.dialogNext);

        //Setting type faces
        correctText.setTypeface(sb);
        buttonNext.setTypeface(sb);
        explain.setTypeface(sb);

        if(input.equals(currentQuestion.getAnswer())){
            mediaPlayer = MediaPlayer.create(MainGameActivity.this, R.raw.correct);
            mediaPlayer.start();
            correctText.setText("Betul");
            explain.setText("Jawapan yang betul ialah  "+currentQuestion.getAnswer());

        }else{
            mediaPlayer = MediaPlayer.create(MainGameActivity.this, R.raw.wrong);
            mediaPlayer.start();
            correctText.setText("Salah");
            correctText.setTextColor(Color.RED);
            explain.setText("Jawapan yang betul ialah "+currentQuestion.getAnswer());
        }


        //OnCLick listener to go next que
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = MediaPlayer.create(MainGameActivity.this, R.raw.push);


                mediaPlayer.start();

                //This will dismiss the dialog
                dialogCorrect.dismiss();
                //it will increment the question number
                qid++;
                //get the que and 4 option and store in the currentQuestion
                currentQuestion = list.get(qid);
                //Now this method will set the new que and 4 options
                updateQueAndOptions();

                //reset the color of buttons back to white
             //   resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                enableButton();
            }
        });
    }



    //This method will make button color white again since our one button color was turned green
//    public void resetColor() {
//        buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
//        buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
//        buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
//        buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
//    }

    //This method will disable all the option button
    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    //This method will all enable the option buttons
    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }





}

