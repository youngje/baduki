package com.nhn.Exceptions;

import com.nhn.model.Card;

@SuppressWarnings("serial")
public class DuplicatedCardExistException extends Exception {

	private Card duplicatedCard;
	
	public DuplicatedCardExistException(Card duplicatedCard) {
		this.duplicatedCard = duplicatedCard;
	}

	@Override
	public String getMessage() {
		return duplicatedCard.toString() + " 중복입니다. ";
	}
}
