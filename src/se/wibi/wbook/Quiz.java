package se.wibi.wbook;


import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.ActionBar.LayoutParams;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;



public class Quiz extends SherlockFragment implements View.OnClickListener{
	private int currentQuestion;
	private String currentChapter;
	private String currentCorrectAnswers;
	Questions quest = new Questions();
	List<Questions> quesList;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//Getting all bundles and making sure that they are not empty.
		Bundle bundle = this.getArguments();
		currentQuestion = Integer.parseInt(bundle.getString("currentQuestion"));
		//getting all question ID's
		int questions[] = bundle.getIntArray("questionId");
		
		currentCorrectAnswers = bundle.getString("currentCorrects");
		currentChapter = bundle.getString("chapter");
		Log.i("TAGG", currentChapter);
		//No questionID excists, so create a new List of Questions.
		if(questions == null){
			quesList = getAllQuestions();
			questions = getQuestionsId(quesList);
		}
		if(currentCorrectAnswers == null)
			currentCorrectAnswers = "";
		//creating a ScrollView for all containing all content.
		ScrollView scroller = new ScrollView(getActivity());
		scroller.setBackgroundColor(getResources().getColor(R.color.darkBackground));
		
		//All content will go in a linear layout that will be placer in the scrollview
		LinearLayout linLayout = new LinearLayout(getActivity());
		linLayout.setOrientation(LinearLayout.VERTICAL);	
		
		//Setting some params.
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		//Margin, left, top, right, bottom
		params.setMargins(0,getDPI(8),0,getDPI(8));
		
		
		//Getting questions from the database from specific ID
		DbHelper db = new DbHelper(getActivity());
		quest = db.getQuestionsByID(questions[currentQuestion]);
		
		
		TextView twQuiz = new TextView(getActivity());
		twQuiz.setText(quest.getquestion());
		linLayout.addView(twQuiz);
		for (int j = 0; j < 4; j++) {
			 Button btnTag = new Button(getActivity());
			 btnTag.setBackgroundColor(getResources().getColor(R.color.white));
			 Resources res = getResources();
			 Drawable shape = res.getDrawable(R.drawable.whitecontent);
			 btnTag.setBackground(shape);
			 if(j==0)
				 btnTag.setText(quest.getoptA() + (j+(1*4)));
			 if(j==1)
				 btnTag.setText(quest.getoptB() + (j+(1*4)));
			 if(j==2)
				 btnTag.setText(quest.getoptC() + (j+(1*4)));
			 if(j==3)
				 btnTag.setText(quest.getoptD() + (j+(1*4)));
			 btnTag.setId(j+(1*4));
			 btnTag.setOnClickListener(this);
			 linLayout.addView(btnTag,params);
         }		
		
		 //Nedräknare
		final TextView twCounter = new TextView(getActivity());
		twCounter.setTextAppearance(getActivity(), R.style.aboutTitleText);
		twCounter.setText("qwe");
		new CountDownTimer(30000, 1000) {
			public void onTick(long millisUntilFinished) {
				twCounter.setText("seconds remaining: " + millisUntilFinished / 1000);
		    }
			public void onFinish() {
				twCounter.setText("done!");
		     }
		  }.start();
		linLayout.setLayoutParams(params);
		linLayout.addView(twCounter);
		scroller.addView(linLayout);		
		return scroller;
	 }
	private int[] getQuestionsId(List<Questions> quesList) {
		//Just getting first 10 Questions ID's and placing in the array.
		//Setting it to 200 just incase. should be dynamic.
		int id[] = new int[200];
		int i = 0;
		for (Questions qs : quesList){
           id[i] = qs.getid();
           i++;
		}
		
		return id;
	}

	private List<Questions> getAllQuestions() {
		//Creating a new List from database with questions and shuffling the questions in the List.
		DbHelper db= new DbHelper(getActivity());
		//Should get all questions by chapter.
		List<Questions> ql = db.getAllQuestions();
		long seed = System.nanoTime();
		Collections.shuffle(ql, new Random(seed));
		db.close();
		return ql;
	}
	public int getDPI(int size){
		float scale = getResources().getDisplayMetrics().density;
		int sizeInDp = (int) (size*scale + 0.5f);
		return sizeInDp;
	}
	
	@Override
	public void onClick(View v) {
		
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
		Fragment fragment = new Quiz();
		
		transaction.replace(R.id.content_frame, fragment);
		transaction.addToBackStack(null);
		transaction.commit();

		
	}
}
