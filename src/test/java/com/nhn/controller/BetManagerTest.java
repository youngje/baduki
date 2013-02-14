package com.nhn.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Test;

import com.nhn.controller.BetManager;
import com.nhn.model.Player;

public class BetManagerTest {
	
	@Test
	public void test딜러비_차감_잘되는지_확인() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		
		//When
		betmanager.setDealerFeeRate(5);
		
		//Then
		assertEquals(95 , betmanager.takeDealerFeeFrom(100));
	}

	@Test
	public void test카드배분_전_모든플레이어의_개별포켓이_초기화되는지확인() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();
		
		Player player1 = new Player();
		
		//When
		players.add(player1);
		betmanager.setPlayersOnBetPockets(players);
		
		//Then
		assertEquals(0 , (int)betmanager.getEachBetMoneyPocket().get(players.get(0)));
	}
	
	@Test
	public void test해당_플레이어의_레이스포인트가_있는경우_확인() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		Player player1 = new Player();
		
		//When
		betmanager.getEachBetPoints().put(player1, 1);
		
		//Then
		assertEquals(true , betmanager.isPlayerPointAvailable(player1));
	}
	
	@Test
	public void test해당_플레이어의_레이스포인트가_없는경우_확인() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		Player player1 = new Player();
		
		//When
		betmanager.getEachBetPoints().put(player1, 0);
		
		//Then
		assertEquals(false , betmanager.isPlayerPointAvailable(player1));
	}
	
	@Test
	public void test레이스_전_플레이어가_돈이_모자라는지_확인() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		Player player = new Player();
		
		//When
		player.setCurrentMoney(200);
		
		//Then
		assertEquals(false , betmanager.isPlayerBalanceAvailable(player, 300));
	}
	
	@Test
	public void test레이스_전_플레이어가_돈이_충분한지_확인() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		Player player = new Player();
		
		//When
		player.setCurrentMoney(400);
		
		//Then
		assertEquals(true , betmanager.isPlayerBalanceAvailable(player, 300));
	}
	
	@Test
	public void test모든_플레이어가_다음_턴으로_갈_준비가_된_경우() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();
		
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		
		//When
		players.add(player1);
		players.add(player2);
		players.add(player3);
		
		betmanager.setPlayersBetPoints(players, 0);
		
		//Then
		assertEquals(true , betmanager.isAllPlayersDone(players));
	}
	
	@Test
	public void test모든_플레이어가_다음_턴으로_갈_준비가_안된_경우() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();
		
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		
		//When
		players.add(player1);
		players.add(player2);
		players.add(player3);
		
		betmanager.setPlayersBetPoints(players, 0);
		betmanager.getEachBetPoints().put(player2, 1);
		
		//Then
		assertEquals(false , betmanager.isAllPlayersDone(players));
	}
	
	
	@Test
	public void test카드배분_전_모든플레이어가_학교가는지_확인() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();

		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		
		//When
		player1.setCurrentMoney(250);
		player2.setCurrentMoney(350);
		player3.setCurrentMoney(450);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		betmanager.setMinimumBet(50);
		betmanager.setCurrentTableMoney(0);
		
		betmanager.gatherHelloMoneyFrom(players);
		
		//Then
		assertEquals(150 , betmanager.getCurrentTableMoney());
		assertEquals(200 , players.get(0).getCurrentMoney());
		assertEquals(300 , players.get(1).getCurrentMoney());
		assertEquals(400 , players.get(2).getCurrentMoney());
		
		assertEquals(50 , (int)betmanager.getEachBetMoneyPocket().get(players.get(0)));
		assertEquals(50 , (int)betmanager.getEachBetMoneyPocket().get(players.get(1)));
		assertEquals(50 , (int)betmanager.getEachBetMoneyPocket().get(players.get(2)));
	}
	
	
	@Test
	public void test콜_작동() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();

		Player player1 = new Player();
		
		//When
		player1.setCurrentMoney(250);
		players.add(player1);
		betmanager.setMinimumBet(0);
		betmanager.setCurrentTableMoney(900);
		betmanager.setCurrentRaiseMoney(100);
		
		betmanager.gatherHelloMoneyFrom(players);
		betmanager.setPlayersBetPoints(players,1);
		betmanager.call(player1);
		
		//Then
		assertEquals(1000 , betmanager.getCurrentTableMoney());
		assertEquals(150 , players.get(0).getCurrentMoney());
		assertEquals(100 , (int)betmanager.getEachBetMoneyPocket().get(players.get(0)));
		assertEquals(0, (int)betmanager.getEachBetPoints().get(players.get(0)));
	}

	@Test
	public void test삥_작동() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();

		Player player1 = new Player();
		
		//When
		player1.setCurrentMoney(250);
		players.add(player1);
		betmanager.setMinimumBet(10);
		betmanager.setCurrentTableMoney(900);
		betmanager.setCurrentRaiseMoney(0);
		
		betmanager.gatherHelloMoneyFrom(players);
		betmanager.setPlayersBetPoints(players,1);
		betmanager.bing(player1);
		
		//Then
		assertEquals(920 , betmanager.getCurrentTableMoney());
		assertEquals(10 , betmanager.getCurrentRaiseMoney());
		assertEquals(230 , players.get(0).getCurrentMoney());
		assertEquals(20 , (int)betmanager.getEachBetMoneyPocket().get(players.get(0)));
		assertEquals(0, (int)betmanager.getEachBetPoints().get(players.get(0)));
	}
	
	@Test
	public void test쿼터_작동() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();

		Player player1 = new Player();
		
		//When
		player1.setCurrentMoney(500);
		players.add(player1);
		betmanager.setMinimumBet(100);
		betmanager.setCurrentRaiseMoney(50);
		
		betmanager.gatherHelloMoneyFrom(players);
		betmanager.setPlayersBetPoints(players,1);
		betmanager.quater(player1);
		
		//Then
		assertEquals(175 , betmanager.getCurrentTableMoney());
		assertEquals(75 , betmanager.getCurrentRaiseMoney());
		assertEquals(325 , players.get(0).getCurrentMoney());
		assertEquals(175 , (int)betmanager.getEachBetMoneyPocket().get(players.get(0)));
		assertEquals(0, (int)betmanager.getEachBetPoints().get(players.get(0)));
	}
	
	@Test
	public void test하프_작동() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();

		Player player1 = new Player();
		
		//When
		player1.setCurrentMoney(500);
		players.add(player1);
		betmanager.setMinimumBet(100);
		betmanager.setCurrentRaiseMoney(50);
		
		betmanager.gatherHelloMoneyFrom(players);
		betmanager.setPlayersBetPoints(players,1);
		betmanager.half(player1);
		
		//Then
		assertEquals(200 , betmanager.getCurrentTableMoney());
		assertEquals(100 , betmanager.getCurrentRaiseMoney());
		assertEquals(300 , players.get(0).getCurrentMoney());
		assertEquals(200 , (int)betmanager.getEachBetMoneyPocket().get(players.get(0)));
		assertEquals(0, (int)betmanager.getEachBetPoints().get(players.get(0)));
	}
	
	@Test
	public void test따당_작동() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();

		Player player1 = new Player();
		
		//When
		player1.setCurrentMoney(500);
		players.add(player1);
		betmanager.setMinimumBet(100);
		betmanager.setCurrentRaiseMoney(50);
		
		betmanager.gatherHelloMoneyFrom(players);
		betmanager.setPlayersBetPoints(players,1);
		betmanager.dadang(player1);
		
		//Then
		assertEquals(200 , betmanager.getCurrentTableMoney());
		assertEquals(100 , betmanager.getCurrentRaiseMoney());
		assertEquals(300 , players.get(0).getCurrentMoney());
		assertEquals(200 , (int)betmanager.getEachBetMoneyPocket().get(players.get(0)));
		assertEquals(0, (int)betmanager.getEachBetPoints().get(players.get(0)));
	}
	
	@Test
	public void test체크_작동() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();

		Player player1 = new Player();
		
		//When
		player1.setCurrentMoney(500);
		players.add(player1);
		betmanager.setMinimumBet(100);
		betmanager.setCurrentRaiseMoney(0);
		
		betmanager.gatherHelloMoneyFrom(players);
		betmanager.setPlayersBetPoints(players,1);
		betmanager.check(player1);
		
		//Then
		assertEquals(100 , betmanager.getCurrentTableMoney());
		assertEquals(0 , betmanager.getCurrentRaiseMoney());
		assertEquals(400 , players.get(0).getCurrentMoney());
		assertEquals(100 , (int)betmanager.getEachBetMoneyPocket().get(players.get(0)));
		assertEquals(0, (int)betmanager.getEachBetPoints().get(players.get(0)));
	}
	
	@Test
	public void test다이_작동() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();

		Player player1 = new Player();
		
		//When
		player1.setCurrentMoney(500);
		players.add(player1);
		betmanager.setMinimumBet(100);
		betmanager.setCurrentRaiseMoney(0);
		
		betmanager.gatherHelloMoneyFrom(players);
		betmanager.setPlayersBetPoints(players,1);
		betmanager.die(player1);
		
		//Then
		assertEquals(100 , betmanager.getCurrentTableMoney());
		assertEquals(0 , betmanager.getCurrentRaiseMoney());
		assertEquals(400 , players.get(0).getCurrentMoney());
		assertEquals(100 , (int)betmanager.getEachBetMoneyPocket().get(players.get(0)));
		assertEquals(0, (int)betmanager.getEachBetPoints().get(players.get(0)));
	}
	
	@Test
	public void test상금_배분1_다른시점에_올인한_두_유저와_끝까지_배팅한_유저_총_세명이_승리() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Player> winners = new ArrayList<Player>();

		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		Player player5 = new Player();
		
		//When
		player1.setCurrentMoney(0);
		player2.setCurrentMoney(0);
		player3.setCurrentMoney(0);
		player4.setCurrentMoney(0);
		player5.setCurrentMoney(0);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		
		winners.add(player2);
		winners.add(player3);
		winners.add(player4);
		
		betmanager.setMinimumBet(0);
		betmanager.setCurrentRaiseMoney(0);
		betmanager.setDealerFeeRate(10);
		betmanager.setPlayersOnBetPockets(players);
		betmanager.gatherHelloMoneyFrom(players);
		
		betmanager.getEachBetMoneyPocket().put(player1, 20);
		betmanager.getEachBetMoneyPocket().put(player2, 40);
		betmanager.getEachBetMoneyPocket().put(player3, 30);
		betmanager.getEachBetMoneyPocket().put(player4, 60);
		betmanager.getEachBetMoneyPocket().put(player5, 60);
		
		boolean foo = betmanager.isAllMoneyGivenTo(winners);
		
		//Then
		assertEquals(true, foo);
		assertEquals(0 , player1.getCurrentMoney());
		assertEquals(56 , player2.getCurrentMoney());
		assertEquals(42 , player3.getCurrentMoney());
		assertEquals(92 , player4.getCurrentMoney());
		assertEquals(0 , player5.getCurrentMoney());
	}
	
	@Test
	public void test상금_배분2_일찌감치_올인한_유저가_이겨서_2등이가지는경우() throws Exception {
		//Given
		BetManager betmanager = new BetManager();
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Player> winners = new ArrayList<Player>();

		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		Player player5 = new Player();
		
		//When
		player1.setCurrentMoney(0);
		player2.setCurrentMoney(0);
		player3.setCurrentMoney(0);
		player4.setCurrentMoney(0);
		player5.setCurrentMoney(0);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		
		winners.add(player3);
		
		betmanager.setMinimumBet(0);
		betmanager.setCurrentRaiseMoney(0);
		betmanager.setDealerFeeRate(10);
		betmanager.setPlayersOnBetPockets(players);
		betmanager.gatherHelloMoneyFrom(players);
		
		betmanager.getEachBetMoneyPocket().put(player1, 20);
		betmanager.getEachBetMoneyPocket().put(player2, 40);
		betmanager.getEachBetMoneyPocket().put(player3, 10);
		betmanager.getEachBetMoneyPocket().put(player4, 10);
		betmanager.getEachBetMoneyPocket().put(player5, 50);
		
		boolean foo = betmanager.isAllMoneyGivenTo(winners);
		
		//Then
		assertEquals(false, foo);
		assertEquals(0 , player1.getCurrentMoney());
		assertEquals(0 , player2.getCurrentMoney());
		assertEquals(45 , player3.getCurrentMoney());
		assertEquals(0 , player4.getCurrentMoney());
		assertEquals(0 , player5.getCurrentMoney());
	}
}
