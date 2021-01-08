import java.util.ArrayList;

import javafx.scene.canvas.*;
import javafx.scene.image.Image;

public class Asteroids {

	private ArrayList<Image> filename;
	private double velocityY;
	private double positionY;
	private double positionX; 
	private Image image;
	
	public Asteroids()
	{
		filename = new ArrayList<>();
		filename.add(new Image("Asteroid1.gif", 150, 200, true, false));
		filename.add(new Image("Asteroid2.gif", 150, 200, true, false));
		filename.add(new Image("Asteroid3.gif", 150, 200, true, false));
		filename.add(new Image("Asteroid4.gif", 150, 200, true, false));
		filename.add(new Image("Asteroid5.gif", 150, 200, true, false));
		filename.add(new Image("Asteroid6.gif", 150, 200, true, false));
		filename.add(new Image("asteroid.png", 64, 64, true, false));
		velocityY = 0;
		positionY = 0;
		positionX = Math.random()*800;
		image = filename.get((int)(Math.random()*7));
	}

	public void setVelocity(double y)
	{
		velocityY = y;
	}
	public double getPositionX()
	{
		return positionX;
	}
	public double getPositionY()
	{
		return positionY;
	}
	public void newPosition(double t)
	{
		positionY += (velocityY*t);
	}
	public void render(GraphicsContext g)
	{
		g.drawImage(image, positionX, positionY);
	}
}
