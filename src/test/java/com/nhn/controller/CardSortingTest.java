package com.nhn.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.nhn.controller.CardSorting;
import com.nhn.model.Card;

public class CardSortingTest {

	@Test
	public void test플레이어_카드덱_숫자로_정렬() throws Exception {
		ArrayList<Card> myCards = new ArrayList<Card>();
		
		myCards.add(new Card(4, Card.cardPatterns.clover));
		myCards.add(new Card(8, Card.cardPatterns.clover));
		myCards.add(new Card(4, Card.cardPatterns.clover));
		myCards.add(new Card(10, Card.cardPatterns.diamond));
		
		CardSorting.sortCardsByNumber(myCards);
		
		for(int i = 0 ; i < 3 ; i++){
			assertTrue("i번째 정렬 확인 완료",myCards.get(i).getCardNum() <= myCards.get(i+1).getCardNum());
		}
		
	}
	
	@Test
	public void test플레이어_카드덱_패턴기준_정렬() throws Exception {
		ArrayList<Card> myCards = new ArrayList<Card>();
		
		myCards.add(new Card(4, Card.cardPatterns.clover));
		myCards.add(new Card(8, Card.cardPatterns.diamond));
		myCards.add(new Card(13, Card.cardPatterns.spade));
		myCards.add(new Card(10, Card.cardPatterns.heart));
		
		CardSorting.sortCardsByPattern(myCards);
		
		for(int i = 0 ; i < 3 ; i++){
			assertTrue("i번째 정렬 확인 완료",myCards.get(i).getCardPattern().ordinal() <= myCards.get(i+1).getCardPattern().ordinal());
		}
	}

}
