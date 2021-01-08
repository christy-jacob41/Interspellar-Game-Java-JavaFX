import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import java.io.IOException;

// This class is a controller of the FMXL file for home
// It controls what happens when the "Enter" button is clicked
public class EndScreenController extends GameStart
{
	@FXML private Button playerMenuButton;
	// ActionEvent gets event information
	// In this case clicking Enter
	public void playerMenuClicked(ActionEvent event) throws Exception
	{
		// gets window information from the event
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		enterPlayerMenu(window);
		
	}
	
}
