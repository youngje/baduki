package com.nhn.controller;

import java.util.ArrayList;

import com.nhn.Exceptions.CardOwnerUnmatchedException;
import com.nhn.model.Card;
import com.nhn.model.Player;
import com.nhn.model.TableCardDeck;

public class GameTable {
	
	private int minimunBet = 50;
	private int seedMoney = 10000;
	private int dealerFeeRate = 3;
	
	static final int FIRST_RAISE_POINT = 1;
	static final int SECOND_RAISE_POINT = 2;
	static final int THIRD_RAISE_POINT = 2;
	static final int LAST_RAISE_POINT = 1;
	
	private ArrayList<Player> players;
	private ArrayList<Player> lastPlayers;
	private TableCardDeck tableCardDeck;
	private BetManager betManager;
	private RuleManager ruleManager;
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public TableCardDeck getTableCardDeck() {
		return tableCardDeck;
	}

	public GameTable() {
		this.players = new ArrayList<Player>();
		this.lastPlayers = new ArrayList<Player>();
		this.tableCardDeck = new TableCardDeck();
		this.betManager = new BetManager();
		this.ruleManager = new RuleManager();
	}

	public void prepareGame() {
		
		betManager.setMinimumBet(minimunBet);
		betManager.setDealerFeeRate(dealerFeeRate);
		betManager.setPlayersOnBetPockets(players);
		betManager.setPlayersBetPoints(players , FIRST_RAISE_POINT);
		betManager.gatherHelloMoneyFrom(players);
		

		//카드배분하고
		tableCardDeck.prepareCardDeck();
		for (Player player : players) {
			tableCardDeck.getCardsFromDeckBy(4, player);			
		}

	}
	
	public void chooseFirstPlayer() {
		//선 정하기
	}

	public void checkCards() throws CardOwnerUnmatchedException {
		for (Player player : players) {
			ArrayList<Card> cards = player.getPlayerCardDeck().getReceivedCards();
			for (Card card : cards) {
				if (tableCardDeck.findCard(cards, card) == null) {
					throw new CardOwnerUnmatchedException();
				}			
			}		
		}			
	}
	
	
	/*
	 *  게임 끝나고 상금을 배분코자 할때 this.winnersWinMoney(lastPlayers) 실행.
	 */
	
	//죽은 사람 걸러내서 마지막까지 남은사람들
	public void beforeCheckWinner(ArrayList<Player> players) {
		for (Player remain : players) {
			if(remain.isAlive()) {
				lastPlayers.add(remain);
			}
		}
	}
	
	//주어진 사람들 중 1등에게 배분하고도 판돈이 남으면 차등에게 배분, 또 그러면 차등에게 배분...
	public void winnersWinMoney(ArrayList<Player> waitingPlayers) {
		ArrayList<Player> winners = new ArrayList<Player>();
		winners = ruleManager.selectWinner(waitingPlayers);

		if(!betManager.isAllMoneyGivenTo(winners)) {
			for (Player winner : winners) {
				waitingPlayers.remove(winner);
				winnersWinMoney(waitingPlayers);
			}
		}
	}
	

	
}
