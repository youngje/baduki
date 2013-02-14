package com.nhn.model;

import org.junit.Test;

import com.nhn.model.Card;

import junit.framework.TestCase;

public class CardTest extends TestCase {

	@Test
	public void test카드_객체_생성() throws Exception {
		//Given
		Card card = new Card();
		//When

		//Then
		assertNotNull("카드 객체 생성", card);
	}

	@Test
	public void test변수_2개_있는_카드_객체_생성() throws Exception {
		//Given
		Card card = new Card(2, Card.cardPatterns.clover);
		
		//Then
				assertNotNull("카드 객체 생성", card);
	}
	
	@Test
	public void test변수_3개_있는_카드_객체_생성() throws Exception {
		//Given
		Card card = new Card(2, Card.cardPatterns.clover, "eunah");
				
		//Then
		assertNotNull("카드 객체 생성", card);
	}
	
	@Test
	public void test같은_카드인지_확인() throws Exception {
		//Given
		Card card1 = new Card(2, Card.cardPatterns.clover);
		Card card2 = new Card(2, Card.cardPatterns.clover);
		
		//Then
		assertTrue("같은 카드", card1.isSameCard(card2));
	}
}
