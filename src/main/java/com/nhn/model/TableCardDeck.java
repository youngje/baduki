package com.nhn.model;

import java.util.ArrayList;
import java.util.Collections;

public class TableCardDeck {

	private ArrayList<Card> cards;
	private static final int ONE_DECK = 52;
	private static final int SAME_SHAPE_OF_ONE_DECK = 13;
	private static final String DEALER = "dealer";
	
	public TableCardDeck() {
		super();
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> newCards) {
		this.cards = newCards;
	}

	public ArrayList<Card> prepareCardDeck() {
		//52장 newCards 세팅
		cards = new ArrayList<Card>();
		//expiredCards 초기화

		for (Card.cardPatterns cardPattern : Card.cardPatterns.values()) {
			for (int i = 1; i <= SAME_SHAPE_OF_ONE_DECK; i++) {
				cards.add(new Card(i, cardPattern, DEALER));
			}
		}
		return cards;
	}
	
	public ArrayList<Card> shuffleCardDeck(ArrayList<Card> cards) {
		Collections.shuffle(cards);
		return cards;
	}
	
	public ArrayList<Card> getCardsFromDeckBy(int numberOfRequestedCards, Player player) {
		ArrayList<Card> returnCards = new ArrayList<Card>();		
		
		for (int i = 0; i < ONE_DECK; i++) {
			if (cards.get(i).getOwner() == DEALER) {
			returnCards.add(cards.get(i));
			cards.get(i).setOwner(player.getPlayerId());
			}	
			if (returnCards.size() == numberOfRequestedCards) {
				break;
			}
		}
		return returnCards;
	}
	
	public void putExpiredCards(ArrayList<Card> expireCards) {
		for (int i = 0; i < expireCards.size(); i++) {
			for (int j = 0; j < ONE_DECK; j++) {
				if (cards.get(j).isSameCard(expireCards.get(i))) {
					cards.get(j).setOwner("deleted");
				}
			}
		}
	}

	public Card findCard(ArrayList<Card> cards, Card searchCard){
		for(Card card : cards) {
			if(card.isSameCard(searchCard)) {
				return card;
			}
		}
		return null;
	}
}
