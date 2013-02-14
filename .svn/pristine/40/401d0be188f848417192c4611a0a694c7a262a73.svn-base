package com.nhn.Exceptions;

import java.util.ArrayList;

import com.nhn.model.Card;

@SuppressWarnings("serial")
public class CardsLengthUnmatchException extends Exception {
	
	private ArrayList<Card> cards;
	private int expectedCardLength;
	
	public CardsLengthUnmatchException(ArrayList<Card> cards, int expectedCardLength) {
		this.cards = cards;
		this.expectedCardLength = expectedCardLength;
	}
	
	@Override
	public String getMessage() {
		return "Expected Cards length is " + this.expectedCardLength + " but, actual " + cards.size();
	} 

}
