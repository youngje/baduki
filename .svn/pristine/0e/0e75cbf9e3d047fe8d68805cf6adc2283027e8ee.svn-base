package com.nhn.model;

import java.util.ArrayList;

public class PlayerCardDeck {
	private ArrayList<Card> receivedCards;
	private ArrayList<Card> selectedCards;
	private HandRankings handrankings;
	
	public PlayerCardDeck() {
		super();
		this.selectedCards = new ArrayList<Card>();
		this.receivedCards = new ArrayList<Card>();
		this.handrankings = new HandRankings();
	}
	
	public PlayerCardDeck(ArrayList<Card> cards) {
		this.receivedCards = cards;
	}
	
	public ArrayList<Card> getReceivedCards() {
		return receivedCards;
	}

	public void setReceivedCards(ArrayList<Card> receivedCards) {
		this.receivedCards = receivedCards;
	}
	
	public ArrayList<Card> getSelectedCards() {
		return selectedCards;
	}
	
	public void setSelectedCards(ArrayList<Card> selectedCards) {
		this.selectedCards = selectedCards;
	}
	
	public void selectCardForChange(Card card) {
		selectedCards.add(card);
	}
	
	public HandRankings getHandrankings() {
		return handrankings;
	}
	
	public void setHandrankings(HandRankings handrankings) {
		this.handrankings = handrankings;
	}
	
	public void changeCards(ArrayList<Card> selectedCards, ArrayList<Card> newGetCards) {
		this.selectedCards.clear();
		this.receivedCards.removeAll(selectedCards);
		this.receivedCards.addAll(newGetCards);
	}
	
}
