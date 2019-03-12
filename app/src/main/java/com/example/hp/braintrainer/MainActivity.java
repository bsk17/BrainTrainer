package com.example.hp.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int rightAnswerButton;
    // score and numOfQuestions will keep the track of points
    int score = 0;
    int numOfQuestions = 0;

    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    Button exitButton;

    TextView sumTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;

    ConstraintLayout gameLayout;

    // creating array of answers
    ArrayList<Integer> answers = new ArrayList<Integer>();

    // onclick function of buttons
    public void chooseAnswer(View view){
        // checks the right answer
        if(Integer.toString(rightAnswerButton).equals(view.getTag().toString())){
            // this will display the message
            resultTextView.setText("CORRECT :)");
            score++;
        }else {
            resultTextView.setText("INCORRECT :(");
        }
        numOfQuestions++;
        // this will set the scoreboard
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numOfQuestions));
        // calling this function to generate questions after each answer
        questions();
    }

    // this function will generate question and then can be used to regenerate
    public void questions(){
        // creating 2 random digits for problem the numbers will be from 0 to 21
        Random randnumber = new Random();
        int a = randnumber.nextInt(21);
        int b = randnumber.nextInt(21);

        int type = randnumber.nextInt(4);

        if(type == 0) {

            sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
            // this random number will decide the right answer button upto 4
            rightAnswerButton = randnumber.nextInt(4);

            // after each time we choose answer we want to clear the answers from the array for the new answers
            answers.clear();

            // this loop is to add answers in th arrayList
            for (int i = 0; i < 4; i++) {
                if (i == rightAnswerButton) {
                    answers.add(a + b);
                } else {
                    // this while loop is to verify that there are no two correct answer
                    int wrongAnswer = randnumber.nextInt(41);

                    while (wrongAnswer == (a + b)) {
                        wrongAnswer = randnumber.nextInt(41);
                    }
                    answers.add(wrongAnswer);
                }
            }
        }else if(type == 1){

            sumTextView.setText(Integer.toString(a)+ " - " +Integer.toString(b) );
            // this random number will decide the right answer button up to 4
            rightAnswerButton = randnumber.nextInt(4);

            // after each time we choose answer we want to clear the answers from the array for the new answers
            answers.clear();

            // this loop is to add answers in th arrayList
            for (int i=0;i<4;i++){
                if (i == rightAnswerButton){
                    answers.add(a-b);
                }else {
                    // this while loop is to verify that there are no two correct answer
                    int wrongAnswer =randnumber.nextInt(41);

                    while(wrongAnswer == (a-b) ){
                        wrongAnswer =randnumber.nextInt(41);
                    }
                    answers.add(wrongAnswer);
                }
            }
        }else if(type == 2){
            sumTextView.setText(Integer.toString(a)+ " * " +Integer.toString(b) );
            // this random number will decide the right answer button upto 4
            rightAnswerButton = randnumber.nextInt(4);

            // after each time we choose answer we want to clear the answers from the array for the new answers
            answers.clear();

            // this loop is to add answers in th arrayList
            for (int i=0;i<4;i++){
                if (i == rightAnswerButton){
                    answers.add(a*b);
                }else {
                    // this while loop is to verify that there are no two correct answer
                    int wrongAnswer =randnumber.nextInt(400);

                    while(wrongAnswer == (a*b) ){
                        wrongAnswer =randnumber.nextInt(400);
                    }
                    answers.add(wrongAnswer);
                }
            }
        }else{
            sumTextView.setText(Integer.toString(a)+ " / " +Integer.toString(b) );
            // this random number will decide the right answer button upto 4
            rightAnswerButton = randnumber.nextInt(4);

            // after each time we choose answer we want to clear the answers from the array for the new answers
            answers.clear();

            // this loop is to add answers in th arrayList
            for (int i=0;i<4;i++){
                if (i == rightAnswerButton){
                    answers.add(a/b);
                }else {
                    // this while loop is to verify that there are no two correct answer
                    int wrongAnswer =randnumber.nextInt(41);

                    while(wrongAnswer == (a/b) ){
                        wrongAnswer =randnumber.nextInt(41);
                    }
                    answers.add(wrongAnswer);
                }
            }
        }

        // this is to set the answers to the buttons from the array using index numbers
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    // function to perform when start button is pressed
    public void start(View view){
        // to make the button invisble after we start the game
        startButton.setVisibility(View.INVISIBLE);

        // to make the gameLayout visible
        gameLayout.setVisibility(View.VISIBLE);

        // to start the fucntioning of all the connected functions
        playAgain(findViewById(R.id.timerTextView));
    }

    // onclick function of play again
    public void playAgain(View view){
        // we basically reset all the values

        score = 0;
        numOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numOfQuestions));
        questions();
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText(" ");
        // to set the timer
        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // we are using valueOf and not Parse because the variable is long and not integer
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Times UP!!");
                //this will make the play again button visible
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    // this function will exit the app
    public void exit(View view){
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        exitButton = findViewById(R.id.exitButton);
        gameLayout = findViewById(R.id.gameLayout);

        //to initially make the button visible only for one time
        startButton.setVisibility(View.VISIBLE);
        // to initially hide the button
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
