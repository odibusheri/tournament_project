package tournament_code.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ChooseGameView extends VBox {
	private ToggleGroup tglGameType;
	private RadioButton rbTennis;
	private RadioButton rbSoccer;
	private RadioButton rbBasketBall;

	public ChooseGameView() {
		setPadding(new Insets(50));
		setSpacing(10);
		setAlignment(Pos.CENTER_LEFT);
		tglGameType = new ToggleGroup();
		rbTennis = new RadioButton("Tennis");
		rbSoccer = new RadioButton("Soccer");
		rbBasketBall = new RadioButton("Basketball");
		rbTennis.setToggleGroup(tglGameType);
		rbSoccer.setToggleGroup(tglGameType);
		rbBasketBall.setToggleGroup(tglGameType);

		getChildren().addAll(rbTennis, rbSoccer, rbBasketBall);
	}

	public String getGameType() throws Exception {
		RadioButton selected = (RadioButton) tglGameType.getSelectedToggle();
		if (selected == null) {
			throw new Exception("Please choose a game from the menu");
		}
		return selected.getText();
	}

}
