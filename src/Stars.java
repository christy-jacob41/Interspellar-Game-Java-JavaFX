import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Stars
{
	private double positionY;
	private double velocityY;
	private double positionX;
	private Image image;
	public Stars()
	{
		positionY = 0;
		velocityY = 10;
		positionX = Math.random()*840;
		image = new Image("star.png", 15,15,true, false);
		
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
		positionY += velocityY*t;
	}
	
	public void render(GraphicsContext g)
	{
		g.drawImage(image, positionX, positionY);
	}
}
