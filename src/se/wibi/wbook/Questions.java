package se.wibi.wbook;

public class Questions {
	private int id;
	private String question;
	private String optA;
	private String optB;
	private String optC;
	private String optD;
	private String cathegory;
	private String answer;
	public Questions(){
		id = 0;
	  	question = "";
	    optA = "";
	    optB = "";
	    optC = "";
	    optD = "";
	    cathegory = "";
	    answer = "";
	}
	public Questions(String question, String optA, String optB, String optC,String optD, String cathegory, String answer){
		this.question = question;
		this.optA = optA;
		this.optB = optB;
		this.optC = optC;
		this.optD = optD;
		this.cathegory = cathegory;
	    this.answer = answer;
	}
	public int getid(){return this.id;}
	public String getquestion(){return this.question;}
	public String getoptA(){return this.optA;}
	public String getoptB(){return this.optB;}
	public String getoptC(){return this.optC;}
	public String getoptD(){return this.optD;}
	public String getcathegory(){return this.cathegory;}
	public String getanswer(){return this.answer;}
	
	public void setid(int id){this.id = id;}
	public void setquestion(String question){this.question = question;}
	public void setoptA(String optA){this.optA = optA;}
	public void setoptB(String optB){this.optB = optB;}
	public void setoptC(String optC){this.optC = optC;}
	public void setoptD(String optD){this.optD = optD;}
	public void setcathegory(String cathegory){this.cathegory = cathegory;}
	public void setanswer(String answer){this.answer = answer;}
}
