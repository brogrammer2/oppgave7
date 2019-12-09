

import javafx.scene.control.Button;

public class CharButton extends Button {
	private char hiddenChar;
	private boolean clicked;

	public CharButton(char hiddenChar) {
		super();
		this.hiddenChar = hiddenChar;
		clicked = false;
		setStyle("-fx-font-size: 14pt;");
	}

	public boolean equals(CharButton other) {
		return hiddenChar == other.getHiddenChar();
	}

	public char getHiddenChar() {
		return hiddenChar;
	}

	public void toggleClick() {
		clicked = !clicked;

		if (clicked) {
			setText(toString());
			setDisable(true);

		} else {
			setText(" ");
			setDisable(false);
		}
	}

	public void setCorrect() {
		setStyle("-fx-background-color: lightblue; -fx-font-size: 14pt;");
	}

	public String toString() {
		return hiddenChar + "";
	}
}
