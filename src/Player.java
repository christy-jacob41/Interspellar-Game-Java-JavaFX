import java.util.ArrayList;

public class Player // class with info about the player
{
	private String name; // name of player
	private ArrayList<Integer> scores; // ArrayList of scores
	private int highScore;
	private ArrayList<Rocket> rockets;
	private Rocket primaryRocket;
	private int coins;
	private String pRocketName;
	
	
	//constructor
	public Player(String n) // gets passed the players name
	{
		// initializes instance variables
		name = n; //sets passed name to this
		scores = new ArrayList<>();
		highScore = 0;
		AllPlayers.addPlayer(this); // adds this player to the arraylist of players
		rockets = new ArrayList<Rocket>();
		rockets.add(new Rocket("RocketShip.gif"));
		rockets.add(new Rocket("FXWing.gif"));
		rockets.add(new Rocket("Rocket3.gif"));
		rockets.add(new Rocket("MilleniumHawk.gif"));
		rockets.add(new Rocket("DebtStar.png"));
		buyBaseRocket();
		pRocketName = "Apollo '18";
		coins = 0;
	}
	
	//returns high Score
	public int getHighScore()
	{
		return highScore;
	}
	
	// returns player name
	public String getName()
	{
		return name;
	}
	
	// adds score and updates the high score
	public void addScore(int s)
	{
		scores.add(s); //adds the latest score
		coins += s;
		for(int i =0; i < scores.size(); i++) // checks all the scores
		{
			if(scores.get(i)>highScore)
				highScore = scores.get(i); // the highest score is set to high score
		}
	}
	
	//returns the latest score
	public int getLatestScore()
	{
		return scores.get(scores.size()-1);
	}
	
	public void changePrimaryRocket(int i)
	{
		if(rockets.get(i).getBought())
		{	
			primaryRocket = rockets.get(i);
			if(i == 0)
				pRocketName = "Apollo '18";
			if(i == 1)
				pRocketName = "FX - Wing";
			if(i == 2)
				pRocketName = "Black One";
			if(i == 3)
				pRocketName = "Millenium Hawk";
			if(i == 4)
				pRocketName = "Debt Star";
		}
	}
	
	public void buyBaseRocket()
	{
		rockets.get(0).setBought(true);
		primaryRocket = rockets.get(0);
	}
	
	public void buyFXWing()
	{
		rockets.get(1).setBought(true);
		primaryRocket = rockets.get(1);
	}
	
	public void buyBlackOne()
	{
		rockets.get(2).setBought(true);
		primaryRocket = rockets.get(2);
	}
	
	public void buyMilleniumHawk()
	{
		rockets.get(3).setBought(true);
		primaryRocket = rockets.get(3);
	}
	
	public void buyDebtStar()
	{
		rockets.get(4).setBought(true);
		primaryRocket = rockets.get(4);
	}
	public Rocket getPrimaryRocket()
	{
		return primaryRocket;
	}
	public String getPrimaryRocketName()
	{
		return pRocketName;
	}
	public ArrayList<Integer> getScores()
	{
		return scores;
	}
	public ArrayList<Rocket> getRockets()
	{
		return rockets;
	}
}
