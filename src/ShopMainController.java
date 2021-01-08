import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ShopMainController extends GameStart
{

	@FXML private Button armorButton;
	@FXML private Button newShipButton;
	@FXML private Button playerMenuButton;
	
	public void armorButtonClicked(ActionEvent e) throws Exception
	{
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		enterArmorMenu(window);
	}
	
	public void newShipButtonClicked(ActionEvent e) throws Exception
	{
		
	}
	public void playerMenuButtonClicked(ActionEvent e) throws Exception
	{
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		enterPlayerMenu(window);
	}
}
