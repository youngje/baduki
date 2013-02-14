package com.nhn.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.nhn.model.Card;


public class CardSorting {

	public static ArrayList<Card> sortCardsByNumber(ArrayList<Card> cards) {
		Collections.sort(cards, new Comparator<Card>() { 
			public int compare(Card compare1, Card compare2) {
				return compare1.getCardNum() - compare2.getCardNum();
			}
		});
		return cards;
	}
	
	public static ArrayList<Card> sortCardsByPattern(ArrayList<Card> cards) {
		Collections.sort(cards, new Comparator<Card>() {
			public int compare(Card compare1, Card compare2) {
				return compare1.getCardPattern().ordinal() - compare2.getCardPattern().ordinal();
			}
		});
		
		return cards;
	}
	
}
