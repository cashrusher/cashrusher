package com.rusher.domain.protocol;

import java.util.List;

public class WalletsResponse extends Response {

	private List<Wallet> wallets;

	public List<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
	}
	
	
	
}
