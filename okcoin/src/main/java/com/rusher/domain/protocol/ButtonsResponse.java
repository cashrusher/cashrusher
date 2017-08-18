package com.rusher.domain.protocol;

import java.util.List;

public class ButtonsResponse extends Response {
	
	private List<Button> buttons;

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}
	
}
