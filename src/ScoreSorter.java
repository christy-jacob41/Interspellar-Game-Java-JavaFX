
public class ScoreSorter implements Comparable
{
	private String name;
	private int score;
	public ScoreSorter(String a, int b)
	{
		name = a;
		score = b;
	}
	
	@Override
	public int compareTo(Object b)
	{
		ScoreSorter a = (ScoreSorter)b;
		if(this.getScore()>a.getScore())
			return 1;
		else if(this.getScore()<a.getScore())
			return -1;
		else if(this.getName().compareTo(a.getName())<0)
			return -1;
		else if(this.getName().compareTo(a.getName())>0)
			return 1;
		return 0;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getScore()
	{
		return score;
	}

}
