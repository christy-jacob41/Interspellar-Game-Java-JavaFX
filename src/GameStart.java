import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.*;
import javafx.stage.*;
//import sun.nio.cs.ext.IBM037;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class GameStart extends Application
{
	Stage window;
	private static AllPlayers a;
	
	public static void main(String args[])
	{
		a = new AllPlayers();
		launch(args); //NEED THIS TO RUN PROGRAM
	}
	
	// start(Stage stage) is an abstract method in the Application class which our game extends(is a sub-class of)
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		// refers to FXML file where the menu was created
		Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
		// sets the name of the stage to InterSpellar
		window.setTitle("InterSpellar");
		//Sets the scene of the stage to the home menu as specified in the FXML file
		window.setScene(new Scene(root, 850, 476));
		// shows the stage
		window.show(); //NEED THIS TO SEE ANYTHING
	}
	
	public void enterPlayerMenu(Stage window) throws IOException
	{
		// refers to FXML file where player Menu was created
		Parent root = FXMLLoader.load(getClass().getResource("playerMenu.fxml"));
		// sets the scene to the player menu as specified in FXML file
		window.setScene(new Scene(root, 850, 476));
	}
	
	public void playGame(Stage window) throws IOException
	{
		Group root = new Group();
		Scene gameScreen = new Scene(root); // creates the gameScreen scene
		
		Canvas play = new Canvas(850,476); // where you can add all the items(rockets, asteroids, background...)
		root.getChildren().add(play); // adds the canvas to the scene
		
		ArrayList<String> input = new ArrayList<String>(); // takes in the input as Strings
		// we want an arraylist so we can let the player type more than one thing at a time
		// for example we need them to type complete words so we need to add each letter to the input Arraylist
		
		//Moves object left or right when key is pressed
		gameScreen.setOnKeyPressed(
				new EventHandler<KeyEvent>() //handles the event of a key being pressed
				{
					public void handle(KeyEvent e) //event handler
					{
						String code = e.getCode().toString(); // turns key input into string
						if(!input.contains(code)) // if code isn't already in the arraylist, add it
							input.add(code);
					}

				}
				);
		//stop moving object when key is released
		gameScreen.setOnKeyReleased(
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e)
					{
						String code = e.getCode().toString();
						if(input.contains(code))
							input.remove(code);
					}
				}
				);

		//Adds input as a string for typing part
		gameScreen.setOnKeyTyped(
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e)
					{
						String code = e.getCode().toString();
						input.add(code);
					}
				}
				);
		
		// Defines the graphics
		GraphicsContext graph = play.getGraphicsContext2D();
		
		//Creates the players rocket
		Rocket r = AllPlayers.getPlayer().getPrimaryRocket();
		
		// creates an arraylist of asteroid objects
		ArrayList<Asteroids> a = new ArrayList<>();
		
		//creates an arraylist of stars
		ArrayList<Stars> s = new ArrayList<>();
		// adds a few asteroids to start off
		for(int i =0; i < 3; i++)
		{
			Asteroids ast = new Asteroids();
			a.add(ast);
		}
		
		// adds a few stars at the beginning
		for(int i = 0; i < 3; i++)
		{
			s.add(new Stars());
		}
		
		//can't call primitives so i had to create a class
		LongV lastNanoTime = new LongV(System.nanoTime());
		
		//creates the animation which runs at 60 frames per second
		new AnimationTimer()
		{
			int ranNum;
			int armor = AllPlayers.getPlayer().getPrimaryRocket().getArmor();
			int numCoins;
			double time; //how long since the game started
			double timeX; //time multiplier for speed of asteroids
			double freqA; // frequency of asteroids
			public void handle(long currentNanoTime)
			{
				//makes the background black
				graph.setFill(Color.BLACK);
				//fills the background as black
				graph.fillRect(0, 0, 850, 476);
				
				//how long since the last frame was accessed
				double elapsed = (currentNanoTime - lastNanoTime.getValue())/1000000000.0;
				lastNanoTime.set(currentNanoTime);
				time += elapsed; // keeps track of how long since the game started
				// time multiplier for speed of asteroids
				timeX = 10*(time/2);
				freqA = (time/10)*0.005; // determines frequency of asteroids introduced
				
				if(time%0.5<freqA)
					a.add(new Asteroids());
				r.render(graph);
				
				if(time%0.5<0.003)
					s.add(new Stars());
				
				r.setVelocity(0);
				if(input.contains("LEFT"))
					r.setVelocity(-75);
				if(input.contains("RIGHT"))
					r.setVelocity(75);
				
				if((r.getPosition()<=0&& r.getVelocity()<0) || (r.getPosition()>=785 && r.getVelocity()>0))
					r.setVelocity(0);
				
				r.newPosition(elapsed);
				
				// draws asteroid
				for(Asteroids ast: a)
					ast.render(graph);
				
				// draws star
				for(Stars st: s)
				{	
					st.render(graph);
					st.newPosition(elapsed);
				}
				
				// makes Random Typing occurrences
		/*		ranNum = (int) (Math.random() * 1000);
				if(ranNum == 5)
				{
					typingScreen(window);
				}
				*/
				for(int i = 0; i < a.size(); i++)
				{
					Asteroids ast = a.get(i);
					ast.setVelocity(timeX);
					ast.newPosition(elapsed);										
					// checks to see if hitting asteroid
					if(r.getPosition()-ast.getPositionX()<=50 && r.getPosition()-ast.getPositionX()>=-50 && ast.getPositionY()>=390 && ast.getPositionY()<=470)
					{
						// First removes the asteroid so multiple collisions don't happen with the smame astroid. Then decrements armor and checks if ship can surivive 
						//	the impact or not  
						a.remove(i);
						armor--;
						if(armor < 1)
						{
							stop();
							AllPlayers.getPlayer().addScore((int)time);
							try {
								gameOverScreen(window);
							} catch (IOException e) {
								System.out.println("I suck");
							}
						}
					}
					
				}
				
				for(int i = 0; i < s.size(); i++)
				{
					Stars st = s.get(i);
					st.setVelocity(time/1.5);
					st.newPosition(elapsed);										
					
				
				}
				String scoreDisplay = "Score: " + (int)(time);
				String armorDisplay = "Armor: " + (armor - 1);
				Font f = Font.font( "Arial", FontWeight.BOLD, 30 );
				graph.setFont(f);
				graph.setFill(Color.AQUA);
				graph.fillText(scoreDisplay, 680, 36);
				graph.fillText(armorDisplay, 680, 450);
			}
		}.start();
		
		
		window.setScene(gameScreen);
	}
	
	public void gameOverScreen(Stage window) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("endScreen.fxml"));
		AnchorPane v = new AnchorPane();
		VBox x = new VBox(20);
		Label s = new Label("Score: " + AllPlayers.getPlayer().getLatestScore());
		s.setFont(new Font("Arial", 32));
		s.setTextFill(Color.BLUE);
		Label hi = new Label("High Score: " + AllPlayers.getPlayer().getHighScore());
		hi.setFont(new Font("Arial", 32));
		hi.setTextFill(Color.RED);
		x.getChildren().addAll(hi, s);
		v.getChildren().addAll(root, x);
		window.setScene(new Scene(v,850,476));
	}
	
	public void typingScreen(Stage window) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("typingScreen.fxml"));
		AnchorPane v = new AnchorPane();
		VBox x = new VBox(20);
		Label s = new Label("Type: " + AllPlayers.getPlayer().getLatestScore());
		s.setFont(new Font("Arial", 32));
		s.setTextFill(Color.BLUE);
		Label hi = new Label("High Score: " + AllPlayers.getPlayer().getHighScore());
		hi.setFont(new Font("Arial", 32));
		hi.setTextFill(Color.RED);
		x.getChildren().addAll(hi, s);
		v.getChildren().addAll(root, x);
		window.setScene(new Scene(v,850,476));
	}
	public void enterShopMain(Stage window) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("shopmain.fxml"));
		window.setScene(new Scene(root,850,476));
	}
	
	public void enterArmorMenu(Stage window) throws IOException
	{
		// refers to FXML file where shield menu was created
		Parent root = FXMLLoader.load(getClass().getResource("shieldLayout.fxml"));
		// sets the scene to the armor menu as specified in FXML file
		window.setScene(new Scene(root, 850, 476));
	}
	
	public void selectShipScreen(Stage window) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("selectShipScreen.fxml"));
		AnchorPane v = new AnchorPane();
		Label s = new Label("Current Rocket: " + AllPlayers.getPlayer().getPrimaryRocketName());
		s.setFont(new Font("Arial", 20));
		s.setTextFill(Color.ORANGE);
		String text = "Rockets Owned: ";
		for(int i = 0; i < 5; i++)
		{
			if(AllPlayers.getPlayer().getRockets().get(i).getBought())
			{
				text += AllPlayers.getPlayer().getRockets().get(i).getRocketName() + "|";
			}
		}
		Label temp = new Label(text);
		temp.setFont(new Font("Arial", 20));
		temp.setTextFill(Color.ORANGE);
		VBox d = new VBox(20);
		d.getChildren().addAll(s, temp);
		v.getChildren().addAll(root, d);
		window.setScene(new Scene(v, 850, 476));
	}
	
	public void highScoresScreen(Stage window) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("highScoresScreen.fxml"));
		StackPane stack = new StackPane();
		BorderPane p = new BorderPane();
		VBox v = new VBox(20);
		ArrayList<ScoreSorter> scores = new ArrayList<ScoreSorter>();
		for(int i = 0; i < AllPlayers.getPlayers().size(); i++)
		{
			ArrayList<Integer> temp = AllPlayers.getPlayers().get(i).getScores();
			int numSco = 0;
			while(numSco<5 && numSco<temp.size())
			{
				int high = 0;
				for(int j = 0; j < temp.size(); j++)
				{
					if(temp.get(j)>high)
					{		
						high = temp.get(j);
						temp.remove(j);
					}
					
				}
				scores.add(new ScoreSorter(AllPlayers.getPlayers().get(i).getName(), high));
				numSco++;
			}
		}
		
		Collections.sort(scores);
		for(int i = 0; i < scores.size(); i++)
		{
			System.out.println(scores.get(i).getScore());
		}
		
		int highScores = 1;
		String temp = "";
		
		while(highScores <= 5 && (highScores-1) <scores.size())
		{
			System.out.println("reached");
			temp += highScores + ". " + scores.get(highScores-1).getName() + ": " + scores.get(highScores-1).getScore() + "\n";   
			highScores++;
		}
		
		Label s = new Label(temp);
		s.setFont(new Font("Arial", 20));
		s.setTextFill(Color.WHITE);
		v.getChildren().add(s);
		p.setCenter(v);
		stack.getChildren().addAll(root, p);
		
		window.setScene(new Scene(stack, 850,476));
	}
	
}
