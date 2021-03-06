package com.nhn.controller;
import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Test;

import com.nhn.Exceptions.DuplicatedCardExistException;
import com.nhn.controller.RuleManager;
import com.nhn.model.Card;
import com.nhn.model.HandRankings;
import com.nhn.model.Player;



public class RuleManagerTest {
	RuleManager ruleManager = new RuleManager();
	
	@Test(expected=DuplicatedCardExistException.class)
	public void 카드중복인경우() throws Exception {
		RuleManager ruleManager = new RuleManager();
		ArrayList<Card> cards = new ArrayList<Card>();
		String owner = "owner";
		
		cards.add(new Card(1, Card.cardPatterns.clover, owner));
		cards.add(new Card(2, Card.cardPatterns.spade, owner));
		cards.add(new Card(3, Card.cardPatterns.heart, owner));
		cards.add(new Card(1, Card.cardPatterns.clover, owner));
		
		assertSame(HandRankings.rankings.base, ruleManager.checkHandRankings(cards).getRanking());
		assertEquals(3, ruleManager.checkHandRankings(cards).getRankNum());
	}
	
	@Test
	public void 골프인경우() throws Exception {
		//Given
		RuleManager ruleManager = new RuleManager();
		ArrayList<Card> cards = new ArrayList<Card>();
		String owner = "owner";
		
		//When
		cards.add(new Card(1, Card.cardPatterns.clover, owner));
		cards.add(new Card(2, Card.cardPatterns.spade, owner));
		cards.add(new Card(3, Card.cardPatterns.heart, owner));
		cards.add(new Card(4, Card.cardPatterns.diamond, owner));
		
		//Then
		assertSame(HandRankings.rankings.golf, ruleManager.checkHandRankings(cards).getRanking());
		assertEquals(4, ruleManager.checkHandRankings(cards).getRankNum());
	}
	
	@Test
	public void 메이드인경우() throws Exception {
		//Given
		RuleManager ruleManager = new RuleManager();
		ArrayList<Card> cards = new ArrayList<Card>();
		
		//When
		cards.add(new Card(13, Card.cardPatterns.clover));
		cards.add(new Card(12, Card.cardPatterns.spade));
		cards.add(new Card(3, Card.cardPatterns.heart));
		cards.add(new Card(4, Card.cardPatterns.diamond));
		
		//Then
		assertSame(HandRankings.rankings.made, ruleManager.checkHandRankings(cards).getRanking());
		assertEquals(13, ruleManager.checkHandRankings(cards).getRankNum());
	}
	
	@Test
	public void 투베이스인경우() throws Exception {
		//Given
		RuleManager ruleManager = new RuleManager();
		ArrayList<Card> cards = new ArrayList<Card>();
		
		//When
		/*cards.add(new Card(13, Card.cardPatterns.heart));
		cards.add(new Card(13, Card.cardPatterns.spade));
		cards.add(new Card(3, Card.cardPatterns.heart));
		cards.add(new Card(4, Card.cardPatterns.diamond));*/
		
		cards.add(new Card(10, Card.cardPatterns.spade));
		cards.add(new Card(9, Card.cardPatterns.spade));
		cards.add(new Card(4, Card.cardPatterns.diamond));
		cards.add(new Card(5, Card.cardPatterns.diamond));
		
		//Then
		assertSame(HandRankings.rankings.twobase, ruleManager.checkHandRankings(cards).getRanking());
		assertEquals(9, ruleManager.checkHandRankings(cards).getRankNum());
	}
	
	@Test
	public void 베이스인경우() throws Exception {
		//Given
		RuleManager ruleManager = new RuleManager();
		ArrayList<Card> cards = new ArrayList<Card>();
		
		//When
		cards.add(new Card(2, Card.cardPatterns.clover));
		cards.add(new Card(9, Card.cardPatterns.spade));
		cards.add(new Card(10, Card.cardPatterns.heart));
		cards.add(new Card(10, Card.cardPatterns.diamond));
		
		//Then
		assertSame(HandRankings.rankings.base, ruleManager.checkHandRankings(cards).getRanking());
		assertEquals(10, ruleManager.checkHandRankings(cards).getRankNum());
	}
	
	@Test
	public void 노베이스인경우() throws Exception {
		//Given
		RuleManager ruleManager = new RuleManager();
		ArrayList<Card> cards = new ArrayList<Card>();
		
		//When
		cards.add(new Card(13, Card.cardPatterns.heart));
		cards.add(new Card(13, Card.cardPatterns.spade));
		cards.add(new Card(13, Card.cardPatterns.clover));
		cards.add(new Card(13, Card.cardPatterns.diamond));
		
		//Then
		assertSame(HandRankings.rankings.nobase, ruleManager.checkHandRankings(cards).getRanking());
		assertEquals(13, ruleManager.checkHandRankings(cards).getRankNum());
	}
	
	@Test
	public void 카드네장을_입력하면_패의종류를_알려준다() throws Exception {
		//Given
		RuleManager ruleManager = new RuleManager();
		ArrayList<Card> cards = new ArrayList<Card>();
		String owner = "owner";
		
		//When
		cards.add(new Card(1, Card.cardPatterns.clover, owner));
		cards.add(new Card(2, Card.cardPatterns.spade, owner));
		cards.add(new Card(3, Card.cardPatterns.heart, owner));
		cards.add(new Card(4, Card.cardPatterns.diamond, owner));
		
		//Then
		assertSame(HandRankings.rankings.golf, ruleManager.checkHandRankings(cards).getRanking());
	}
	
	@Test
	public void winner가_한명인경우() throws Exception {
		//Given
		ArrayList<Player> players = new ArrayList<Player>();
		
		//When
		Player 골프4 = new Player("골프4", new HandRankings(HandRankings.rankings.golf, 4)); 
		
		players.add(골프4);
		players.add(new Player("낫띵5", new HandRankings(HandRankings.rankings.nobase, 5)));
		players.add(new Player("베이스7", new HandRankings(HandRankings.rankings.base, 7)));
		players.add(new Player("메이드10", new HandRankings(HandRankings.rankings.made, 10)));
		players.add(new Player("투베이스8", new HandRankings(HandRankings.rankings.twobase, 8)));
		
		//Then
		assertEquals("1등은 골프인 사람이 나와야 한다.", 1, ruleManager.selectWinner(players).size());
		assertSame(골프4, ruleManager.selectWinner(players).get(0));
	}
	
	@Test
	public void winner가_두명인_경우() throws Exception {
		//Given
		ArrayList<Player> players = new ArrayList<Player>();
		
		//When
		Player 골프4 = new Player("골프4", new HandRankings(HandRankings.rankings.golf, 4));
		Player 두번째골프 = new Player("두번째골프4", new HandRankings(HandRankings.rankings.golf, 4));
		
		players.add(골프4);
		players.add(new Player("낫띵5", new HandRankings(HandRankings.rankings.nobase, 5)));
		players.add(new Player("베이스7", new HandRankings(HandRankings.rankings.base, 7)));
		players.add(new Player("메이드10", new HandRankings(HandRankings.rankings.made, 10)));
		players.add(두번째골프);
		
		//Then
		assertEquals("1등은 두명이다.", 2, ruleManager.selectWinner(players).size());
		assertSame(골프4, ruleManager.selectWinner(players).get(0));
		assertSame(두번째골프, ruleManager.selectWinner(players).get(1));
	}
	
	@Test
	public void 승리자가_한명인_경우() throws Exception {
		//Given
		ArrayList<Player> players = new ArrayList<Player>();
		
		//When
		Player 골프4 = new Player("골프4", new HandRankings(HandRankings.rankings.golf, 4)); 
		
		players.add(골프4);
		players.add(new Player("메이드10", new HandRankings(HandRankings.rankings.made, 10)));
		players.add(new Player("베이스7", new HandRankings(HandRankings.rankings.base, 7)));
		players.add(new Player("투베이스8", new HandRankings(HandRankings.rankings.twobase, 8)));
		players.add(new Player("낫띵5", new HandRankings(HandRankings.rankings.nobase, 5)));
		
		//Then
		assertTrue("승리자가 한명이면 true", ruleManager.isOnlyWinner(players, 0));
	}
	
	@Test
	public void 승리자가_두명인_경우() throws Exception {
		//Given
		ArrayList<Player> players = new ArrayList<Player>();
		
		//When
		Player 골프4 = new Player("골프4", new HandRankings(HandRankings.rankings.golf, 4)); 
		
		players.add(골프4);
		players.add(new Player("두번쨰골프4", new HandRankings(HandRankings.rankings.golf, 4)));
		players.add(new Player("베이스7", new HandRankings(HandRankings.rankings.base, 7)));
		players.add(new Player("투베이스8", new HandRankings(HandRankings.rankings.twobase, 8)));
		players.add(new Player("낫띵5", new HandRankings(HandRankings.rankings.nobase, 5)));
		
		//Then
		assertFalse("승리자가 두명이면 false", ruleManager.isOnlyWinner(players, 0));
	}
	
	@Test
	public void 승리자_찾기() throws Exception {
		//Given
		RuleManager ruleManager = new RuleManager();
		ArrayList<Card> cards1 = new ArrayList<Card>();
		ArrayList<Card> cards2 = new ArrayList<Card>();
		ArrayList<Card> cards3 = new ArrayList<Card>();
		ArrayList<Player> players = new ArrayList<Player>();
		
		//When
		cards1.add(new Card(3, Card.cardPatterns.spade));
		cards1.add(new Card(3, Card.cardPatterns.diamond));
		cards1.add(new Card(2, Card.cardPatterns.heart));
		cards1.add(new Card(1, Card.cardPatterns.diamond));
		
		cards2.add(new Card(10, Card.cardPatterns.spade));
		cards2.add(new Card(9, Card.cardPatterns.spade));
		cards2.add(new Card(4, Card.cardPatterns.diamond));
		cards2.add(new Card(5, Card.cardPatterns.diamond));
		
		cards3.add(new Card(8, Card.cardPatterns.clover));
		cards3.add(new Card(7, Card.cardPatterns.clover));
		cards3.add(new Card(10, Card.cardPatterns.diamond));
		cards3.add(new Card(9, Card.cardPatterns.heart));
		
		players.add(new Player("으나", cards1));
		players.add(new Player("성호", cards2));
		players.add(new Player("영제", cards3));
		
		//Then
		for(Player player : players) {
			player.setHandrankings(ruleManager.checkHandRankings(player.getPlayerCardDeck().getReceivedCards()));
		}
		
		assertSame("승리자가 찾아진다!!!", "으나", ruleManager.selectWinner(players).get(0).getPlayerId());
	}
}
