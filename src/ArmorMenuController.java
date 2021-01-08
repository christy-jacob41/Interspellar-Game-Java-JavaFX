import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ArmorMenuController extends GameStart {
	
	private int small = 0;
	private int med = 0;
	private int large = 0;
	@FXML private Button smallButton;
	@FXML private Button mediumButton;
	@FXML private Button largeButton;
	@FXML private Button backToShopButton;
	
	public void smallButtonClicked(ActionEvent e) throws Exception
	{
		// if statement makes sure player cannot buy same upgrade more than once
		if(small == 0)
		{
			AllPlayers.getPlayer().getPrimaryRocket().upgradeArmor();
			small++;
		}
		
	}
	
	public void mediumButtonClicked(ActionEvent e) throws Exception
	{
		if(med == 0)
		{
			AllPlayers.getPlayer().getPrimaryRocket().upgradeArmor();
			med++;
		}
	}
	public void largeButtonClicked(ActionEvent e) throws Exception
	{	
		if(large == 0)
		{
			AllPlayers.getPlayer().getPrimaryRocket().upgradeArmor();
			large++;
		}
	}
	
	public void backToShopButtonClicked(ActionEvent e) throws Exception
	{
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		enterShopMain(window);
	}
	

}
