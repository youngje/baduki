package com.nhn.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.nhn.model.Card;
import com.nhn.model.Player;
import com.nhn.model.PlayerCardDeck;
import com.nhn.model.TableCardDeck;

public class GameTableTest {

	@Test
	public void test게임테이블_세팅() {
		// Given
		GameTable gameTable = new GameTable();

		// When

		// Then
		assertNotNull("게임테이블 세팅완료", gameTable);
	}

	@Test
	public void test게임테이블에서_게임준비완료() throws Exception {
		// Given
		GameTable gameTable = new GameTable();

		// When
		gameTable.prepareGame();

		// Then
		// assertNotNull("게임테이블 MinimumBet 세팅완료", gameTable.prepareGame());
	}

	@Test
	public void test사용자가_가진_카드정보와_게임테이블덱이_가지고있는_정보가_일치() throws Exception {
		//Given
		GameTable gameTable = new GameTable();
		Player player = new Player("으나");
		
		TableCardDeck getGameTableCardDeck = gameTable.getTableCardDeck();
		PlayerCardDeck getPlayerCardDeck = player.getPlayerCardDeck();
		
		//When
		getGameTableCardDeck.prepareCardDeck();
//		getGameTableCardDeck.getCardsFromDeckBy(4, player);
//
//		//Then
//		System.out.println(getPlayerCardDeck.getReceivedCards().get(0));
//		System.out.println(getGameTableCardDeck.findCard(getGameTableCardDeck.getCards(), getPlayerCardDeck.getReceivedCards().get(0)).getOwner());
//		assertTrue("다들 제대로 된 카드 잘 가지고 있는지 playerDeck과 tableDeck의 owner와 확인", getGameTableCardDeck.findCard(getGameTableCardDeck.getCards(), getPlayerCardDeck.getReceivedCards().get(0)).getOwner().equals("으나"));
	}
	
}
