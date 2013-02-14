package com.nhn.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.nhn.model.Card;
import com.nhn.model.Player;
import com.nhn.model.TableCardDeck;

public class TableCardDeckTest {
	
	@Test
	public void test테이블_카드덱_생성() throws Exception {
		TableCardDeck tableCardDeck = new TableCardDeck();
		
		assertNotNull("tableCardDeck 객체 생성", tableCardDeck);
	}

	@Test
	public void test테이블_카드덱에_52장_카드준비() throws Exception {
		TableCardDeck tableCardDeck = new TableCardDeck();
		
		tableCardDeck.prepareCardDeck();
		
		assertEquals("카드 52장 준비 완료", 52, (tableCardDeck.prepareCardDeck()).size());
	}

	@Test
	public void test테이블_카드덱_52장_셔플() throws Exception {
		TableCardDeck tableCardDeck = new TableCardDeck();
		ArrayList<Card> newCards = new ArrayList<Card>();
		
		tableCardDeck.prepareCardDeck();
		tableCardDeck.shuffleCardDeck(newCards);
		
		assertNotSame("sorting 완료", tableCardDeck.shuffleCardDeck(newCards), tableCardDeck.prepareCardDeck());
	}
	
	@Test
	public void test요청한_카드를_나눠줌() throws Exception {
		TableCardDeck tableCardDeck = new TableCardDeck();
		Player player = new Player("id","name",10000);
		
		tableCardDeck.prepareCardDeck();
		tableCardDeck.getCardsFromDeckBy(2, player);
		
		assertEquals("Deck으로부터 카드 get!", 2, tableCardDeck.getCardsFromDeckBy(2, player).size());
	}

	@Test
	public void test버린_카드를_리스트에_넣음() throws Exception {
		TableCardDeck tableCardDeck = new TableCardDeck();
		ArrayList<Card> newCards = new ArrayList<Card>();

		tableCardDeck.prepareCardDeck();
		
		newCards.add(new Card(4, Card.cardPatterns.clover, "으나"));
		newCards.add(new Card(8, Card.cardPatterns.clover, "으나"));
		newCards.add(new Card(4, Card.cardPatterns.spade, "으나"));
		newCards.add(new Card(10, Card.cardPatterns.diamond, "으나"));
				
		tableCardDeck.putExpiredCards(newCards);
		System.out.println(tableCardDeck.getCards());
		System.out.println(tableCardDeck.findCard(tableCardDeck.getCards(), newCards.get(0)));

		assertTrue("expire 완료", tableCardDeck.findCard(tableCardDeck.getCards(), newCards.get(0)).getOwner().equals("deleted"));
		assertTrue("expire 완료", tableCardDeck.findCard(tableCardDeck.getCards(), newCards.get(1)).getOwner().equals("deleted"));
		assertTrue("expire 완료", tableCardDeck.findCard(tableCardDeck.getCards(), newCards.get(2)).getOwner() == "deleted");
		assertTrue("expire 완료", tableCardDeck.findCard(tableCardDeck.getCards(), newCards.get(3)).getOwner() == "deleted");
	}

	@Test
	public void test동일카드의_주인이_같은이름_확인() throws Exception {
		//Given
		TableCardDeck tableCardDeck = new TableCardDeck();
		Card card = new Card(4, Card.cardPatterns.clover, "dealer");
		
		//When
		tableCardDeck.prepareCardDeck();
		
		//Then
		assertNotNull("owner 확인", tableCardDeck.findCard(tableCardDeck.getCards(), card).getOwner().equals("dealer"));
	}
}
