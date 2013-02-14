package com.nhn.model;

public class Card {
	
	private int cardNum;
	public enum cardPatterns {
		spade(0), clover(1), heart(2), diamond(3);
		cardPatterns(int value) { 
			this.value = value; 
		}
		private final int value;
		public int value() { 
			return value;
		};
	};
	
	private cardPatterns cardPattern;
	private String ownerName;
		
	public Card() { }
	
	public Card(int cardNum, cardPatterns cardPattern) {
		this.cardNum = cardNum;
		this.cardPattern = cardPattern;
	}

	public Card(int cardNum, cardPatterns cardPattern, String owner) {
		this.cardNum = cardNum;
		this.cardPattern = cardPattern;
		this.ownerName = owner;
	}

	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	public String getOwner() {
		return ownerName;
	}

	public void setOwner(String owner) {
		this.ownerName = owner;
	}

	public cardPatterns getCardPattern() {
		return cardPattern;
	}

	public void setCardPattern(cardPatterns cardPattern) {
		this.cardPattern = cardPattern;
	}

	public boolean isSameCard(Card card) {
		if (this.getCardInfo().equals(card.getCardInfo())) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getCardInfo() {
		return this.getCardPattern().name() + String.valueOf(this.getCardNum());
	}
	
	@Override
	public String toString() {
		return "Card [cardNum=" + cardNum + ", cardPattern=" + cardPattern	+ "]";
	}
	
}
