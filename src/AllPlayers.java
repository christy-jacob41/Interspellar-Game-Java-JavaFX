import java.util.ArrayList;

public class AllPlayers
{
	static ArrayList<Player> players;
	
	public AllPlayers()
	{
		// instantiates the arraylist of players
		players = new ArrayList<>(); 
	}
	public static void addPlayer(Player p) // adds a PLayer object to the arraylist
	{
		players.add(p);
	}
	
	public static Player getPlayer()
	{
		return players.get(players.size()-1);
	}
	
	public static ArrayList<Player> getPlayers()
	{
		return players;
	}

}
