package com.nhn.Exceptions;

@SuppressWarnings("serial")
public class CardOwnerUnmatchedException extends Exception {
	@Override
	public String getMessage(){
		return "CardDeck's Information and PlayerDeck's Information is not Same[Owner]";
	}
}
