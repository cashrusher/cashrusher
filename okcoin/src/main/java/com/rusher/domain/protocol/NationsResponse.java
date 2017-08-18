package com.rusher.domain.protocol;

import java.util.List;

public class NationsResponse extends Response {
	
	private List<Nation> nations;

	public List<Nation> getNations() {
		return nations;
	}

	public void setNations(List<Nation> nations) {
		this.nations = nations;
	}
}
