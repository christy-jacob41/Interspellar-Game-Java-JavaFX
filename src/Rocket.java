import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rocket
{
	private Image image;
	private String fileName; // name of file containing picture
	private double velocityX;
	private double positionX;
	private int armor;
	private boolean bought;
	private String name;
	
	public Rocket(String s)
	{
		fileName = s;
		velocityX = 0;
		positionX = 425;
		armor = 1;
		image = new Image(fileName, 140, 100, false, false);
		bought = false;
		if(fileName.equals("RocketShip.gif"))
			name = "Apollo '18";
		if(fileName.equals("FXWing.gif"))
			name = "FX - Wing";
		if(fileName.equals("Rocket3.gif"))
			name = "Black One";
		if(fileName.equals("MilleniumHawk.gif"))
			name = "Millenium Hawk";
		if(fileName.equals("DebtStar.png"))
			name = "Debt Star";
	}
	
	public void setVelocity(double a)
	{
		velocityX = a;
	}
	
	public double getPosition()
	{
		return positionX;
	}
	
	public void setBought(boolean b)
	{
		bought = b;
	}
	public boolean getBought()
	{
		return bought;
	}
	public double getVelocity()
	{
		return velocityX;
	}
	
	public void newPosition(double t)
	{
		positionX += velocityX*t;
	}
	public void render(GraphicsContext g) //draws the rocket
	{
		g.drawImage(image, positionX, 390);
	}
	
	public String getRocketName()
	{
		return name;
	}
	
	public void upgradeArmor()
	{
		armor++;
	}
	public int getArmor()
	{
		return armor;
	}
}
