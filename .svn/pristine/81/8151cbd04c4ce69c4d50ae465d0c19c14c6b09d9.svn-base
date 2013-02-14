package com.nhn.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.nhn.model.Card;
import com.nhn.model.PlayerCardDeck;

public class PlayerCardDeckTest {

	@Test
	public void test플레이어_카드덱_생성() throws Exception {
		PlayerCardDeck playerCardDeck = new PlayerCardDeck();
		
		assertNotNull("Card Deck 생성완료", playerCardDeck);
	}

	@Test
	public void test교환할_카드를_선택하여_리스트에_넣음() throws Exception {
		PlayerCardDeck playerCardDeck = new PlayerCardDeck();
		Card myCard = new Card(4, Card.cardPatterns.clover, null);
		
		playerCardDeck.selectCardForChange(myCard);
		
		assertTrue("selectCardForChange 확인 완료", playerCardDeck.getSelectedCards().contains(myCard));
	}

}
