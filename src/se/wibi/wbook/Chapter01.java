package se.wibi.wbook;

import java.io.IOException;
import java.io.InputStream;



import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockFragment;

public class Chapter01 extends SherlockFragment {
    String title;
    String headline;
    String text;
    String info;
	String[] order;
	String[] content;
    String[] headlines;
	
	TextView[] textView;
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//Fill arrays with content and what order the content to be grouped in
		order = new String[] {"title","headline","text","headline","text","info","img"};
		content = new String[] {"Nätverksteknik","Vad är ett Nätverk","En server är kort en dator som tillhandahåller en tjänst. Det kan vara att den visar en webbsida, att den fungerar som en central lagringspunkt. En server kan som t.ex. raspberry pi inte vara större än ett kreditkort och ändå vara fullt kapabel till att utföra en mängd uppgifter."
				+ "Även om en serverdator kan vara vilken dator som helst så prioriterar man ofta saker som mer minne, lagringskapacitet eller speciella typer av processorer medans man har mindre fokus på grafik eller ljudkort. Servrar tycker även inte om värme så de placerar inte sällan i speciella rum med avsedd kylning. I större system monteras de"
				+ "ofta i rack för att inte ta för stor plats.","Serveroperativsystem","Servrar har ofta varianter av Windows Server och gärna i 64 bitars variant men inte sällan finns det Linux och Unix på vissa typer av servrar. Ibland kan man ha behov av flera servrar i ett nätverk vilket man kan hjälpa med hjälp av virtuallisering. Virtuallisering"
				+ "är när man använder en eller flera fysiska servrar där man har flera and just lots of more text that don't really mean anything but well u know. it is good to have it here for later use. or what do ya think?", "info 2","serverfunktion:Kommentar"};
		//All headlines for overview
		headlines = new String[] {""};
		
		//Creates a Scrollview named Scroller (dark blue background)
		ScrollView scroller = new ScrollView(getActivity());
		scroller.setBackgroundColor(getResources().getColor(R.color.darkBackground));
		
		//Main LinearLayout that is in scroller.
		LinearLayout linLayout = new LinearLayout(getActivity());
		linLayout.setOrientation(LinearLayout.VERTICAL);	
		linLayout.setPadding(getDPI2(8),getDPI2(8),getDPI2(8),getDPI2(8));
		
		
		//Creates about info.
		TextView twHeadline = new TextView(getActivity());
		//Get Title
		String aboutTitle = "Kapitel 1: Översikt";
		//Set style on title
		twHeadline.setText(aboutTitle);
		twHeadline.setTextAppearance(getActivity(), R.style.aboutTitleText);
		linLayout.addView(twHeadline);
		//Set line under title
		linLayout.addView(getLinear(0,2, R.color.underlineLightBlue));
		//Get chapter short story
		String aboutText ="Vad är ett nätver, Ett nätverk på IT-Språk, Olika typer av nätver, Servrar, klienter, Operativsystem";
		TextView twAboutText = new TextView(getActivity());
		twAboutText.setText(aboutText);
		twAboutText.setTextAppearance(getActivity(), R.style.aboutText);
		linLayout.addView(twAboutText);
		
		
		//Rounded corner LinearLayout
		LinearLayout contentLayout = new LinearLayout(getActivity());
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//Margin, left, top, right, bottom
		params.setMargins(0,getDPI2(8),0,getDPI2(8));
		
		contentLayout.setLayoutParams(params);
		
		contentLayout.setOrientation(LinearLayout.VERTICAL);	
		contentLayout.setPadding(getDPI2(8),getDPI2(8),getDPI2(8),getDPI2(8));
		contentLayout.setBackgroundColor(getResources().getColor(R.color.white));

		Resources res = getResources();
		Drawable shape = res.getDrawable(R.drawable.whitecontent);
		//contentLayout.setBackground(shape);
		
		
		
		//TextView array with order of length
		textView = new TextView[order.length];

		//Writing to textView content and placying in LinLayout
        for(int i = 1; i < order.length;i++){
        	textView[i] = new TextView(getActivity());

        	if(order[i].equals("headline")){
        		textView[i].setTextAppearance(getActivity(), R.style.headlineText);
        		contentLayout.addView(textView[i]);
        		contentLayout.addView(getLinear(0,2,R.color.underlineLightBlue));
        	}
        	if(order[i].equals("text")){
        		textView[i].setTextAppearance(getActivity(), R.style.paragraphText);
        		contentLayout.addView(textView[i]);
        	}
        	//Orders got a pictures aswell and green background.
        	if(order[i].equals("info")){
        		//Creating a Horizontal LinearLayout for displaying image and image text next to eachother        		
        		LinearLayout ll = new LinearLayout(getActivity());
        		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)); 
        		ll.setOrientation(LinearLayout.HORIZONTAL);
        		
        		res = getResources();
        		shape = res.getDrawable(R.drawable.ideabox);
        		//ll.setBackground(shape);
        		
        		//The image
        		ImageView image = new ImageView(getActivity());
        		image.setImageResource(R.drawable.idea);
        		ll.addView(image);
        		//Setting text style
        		textView[i].setTextAppearance(getActivity(), R.style.infoText);
        		
        		//adding to the ll.
        		ll.addView(textView[i]);
        		//and adding to the top LinearLayout
        		contentLayout.addView(ll);
        	}
        	if(order[i].equals("img")){     		
        		//Image handling from assetsfolder
        		ImageView img = new ImageView(getActivity());
        		InputStream bitmap=null;

        		try {
        		    bitmap=getActivity().getAssets().open("serverfunktion.jpg");
        		    Bitmap bit = BitmapFactory.decodeStream(bitmap);
        		    img.setImageBitmap(bit);
        		} catch (IOException e) {
        		    e.printStackTrace();
        		} finally {
        		    if(bitmap!=null){
						try {
							bitmap.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        		    }
        		}

        		contentLayout.addView(img);       		
        		
        		//getting comments from Reg ex. Everything after : is a comment
        		textView[i].setTextAppearance(getActivity(), R.style.imgCommentText);
        		contentLayout.addView(textView[i]);
        	}
        	textView[i].setText(content[i]);
        }
        
        
        
        //retuning the view to be showed.   
        Button bt = new Button(getActivity());
        bt.setText("A Button");
        bt.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        
        bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Fragment fragment =  new Quiz();
				Bundle bundle = new Bundle();
				bundle.putString("currentQuestion","0");
				bundle.putString("currentCorrects","0");
				bundle.putString("chapter","Kapitel1");

				fragment.setArguments(bundle);
				
				FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
				transaction.replace(R.id.content_frame, fragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}
        	
        });
        
        linLayout.addView(contentLayout);
        linLayout.addView(bt);
        //Done with alll layouting. Adding it to the scroll.
        scroller.addView(linLayout);    
        return scroller;

    }
	public LinearLayout getLinear(int width, int height, int color){
		LinearLayout ll = new LinearLayout(getActivity());
		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, getDPI(height, metrics))); 
		ll.setOrientation(LinearLayout.HORIZONTAL);
		//ll.setBackgroundColor(getResources().getColor(color));
		
		return ll;
	}
	public int getDPI(int size, DisplayMetrics metrics){
        return (size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;        
    }
	public int getDPI2(int size){
		float scale = getResources().getDisplayMetrics().density;
		int sizeInDp = (int) (size*scale + 0.5f);
		return sizeInDp;
	}
 
}