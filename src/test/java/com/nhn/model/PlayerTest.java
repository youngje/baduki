package com.nhn.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.nhn.model.Player;

public class PlayerTest {

	@Test
	public void test플레이어_객체_생성() throws Exception {
		//given
		Player player = new Player();
		
		//then
		assertNotNull("player 생성 성공", player);
	}

	@Test
	public void test플레이어_변수있는_객체_생성() throws Exception {
		//given
		Player player = new Player("id","name",10000);
		
		//then
		assertNotNull("player 생성 성공", player);
	}
	
	@Test
	public void test베팅한후_금액변화() throws Exception {
		//given
		Player player = new Player();
		
		//when
		player.setCurrentMoney(20000);
		
		//then
		assertEquals("bet 됨", 10000 , player.betAmountOf(10000));
	}

	@Test
	public void test획득한후_금액변화() throws Exception {
		//given
		Player player = new Player();
				
		//when
		player.setCurrentMoney(20000);
		
		//then
		assertEquals("gain 됨", 30000 , player.gainAountOf(10000) );
	}

}
