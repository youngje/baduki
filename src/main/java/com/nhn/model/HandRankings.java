package com.nhn.model;

public class HandRankings {
	public enum rankings { golf, second, third, made, base, twobase, nobase };
	
	private rankings ranking;
	private int rankNum;
	
	public HandRankings() { }
	
	public HandRankings(rankings ranking, int rankNum) {
		this.ranking = ranking;
		this.rankNum = rankNum;
	}
	
	public rankings getRanking() {
		return ranking;
	}

	public void setRanking(rankings ranking) {
		this.ranking = ranking;
	}

	public int getRankNum() {
		return rankNum;
	}

	public void setRankNum(int rankNum) {
		this.rankNum = rankNum;
	}

	@Override
	public String toString() {
		return "HandRankings [" + ranking + rankNum + "]";
	}

}
