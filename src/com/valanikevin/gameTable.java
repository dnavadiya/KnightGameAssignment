package com.valanikevin;

public class gameTable {

	static int gameTable[][]=null;
	static int tempValue = 00;
	
	public gameTable() {
		
	}
	
	public gameTable(int[][] newGameTable) {
		gameTable = newGameTable;
	}
	
	static public int getTempValue() {
		return tempValue;
	}
	
	static public void setTempValue(int value) {
		tempValue = value;
	}
	
	static public int[][] getGameTable(){
		return gameTable;
	}
	
	static public void setGameTable(int[][] newGameTable) {
		gameTable = newGameTable;
	}
}
