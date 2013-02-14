package com.nhn.controller;

import java.util.Comparator;

import com.nhn.model.Player;


public class PlayerComparator implements Comparator<Player> {
	
	private static final int LEFT_IS_BIG = 1;
	private static final int RIGHT_IS_BIG = -1;
	private static final int SAME = 0;
	
	@Override
	public int compare(Player player1, Player player2) {
		
		int player1Pattern = player1.getHandrankings().getRanking().ordinal();
		int player2Pattern = player2.getHandrankings().getRanking().ordinal();
		
		int player1Num = player1.getHandrankings().getRankNum();
		int player2Num = player2.getHandrankings().getRankNum();
		
		int result;
		
		if ( player1Pattern > player2Pattern ) {
			result = LEFT_IS_BIG;
		} else if ( player2Pattern > player1Pattern ) {
			result = RIGHT_IS_BIG;
		} else { 
			if ( player1Num > player2Num ) {
				result = LEFT_IS_BIG;
			} else if ( player2Num > player1Num ) {
				result = RIGHT_IS_BIG;
			} else {
				result = SAME;
			}
		}
		return result;
	}
}
