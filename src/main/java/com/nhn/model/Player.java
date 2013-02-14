package com.nhn.model;

import java.util.ArrayList;

public class Player {
	private String playerId;

	private int currentMoney;
	private PlayerCardDeck playerCardDeck;
	private boolean isAlive;

	public Player() { }
	
	public Player(String playerId, HandRankings handrankings) {
		this.playerId = playerId;
		this.playerCardDeck = new PlayerCardDeck();
		this.playerCardDeck.setHandrankings(handrankings);
	}

	public Player(String playerId, String playerName, int currentMoney) {
		this.playerId = playerId;
		this.currentMoney = currentMoney;
	}
	
	public Player(String playerId, ArrayList<Card> cards) {
		this.playerId = playerId;
		this.playerCardDeck = new PlayerCardDeck(cards);
	}
	
	public Player(String playerId) {
		this.playerId = playerId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public int getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(int currentMoney) {
		this.currentMoney = currentMoney;
	}

	public PlayerCardDeck getPlayerCardDeck() {
		return playerCardDeck;
	}

	public void setPlayerCardDeck(PlayerCardDeck playerCardDeck) {
		this.playerCardDeck = playerCardDeck;
	}
	
	public HandRankings getHandrankings() {
		return this.playerCardDeck.getHandrankings();
	}

	public void setHandrankings(HandRankings handrankings) {
		this.playerCardDeck.setHandrankings(handrankings);
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public int betAmountOf(int money) {
		if(currentMoney >= money) {
			currentMoney = currentMoney - money;
			return money;
		} else {
			int allInMoney  = money - currentMoney;
			currentMoney = 0;
			return allInMoney;
		}
	}
	
	public int gainAountOf(int money) {
		currentMoney = currentMoney + money;
		return currentMoney;
	}
}
