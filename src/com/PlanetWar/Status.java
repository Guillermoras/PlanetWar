package com.PlanetWar;

public class Status {
private long id;
private long gameWin;
private long gameLost;
private long gameDraw;
private long statusGameWin;
private long statusGameLost;
private long statusGameDraw;
	
	
	public Status() {
		// TODO Auto-generated constructor stub
	}


	long getStatusGameDraw() {
		return statusGameDraw;
	}


	void setStatusGameDraw(long statusGameDraw) {
		this.statusGameDraw = statusGameDraw;
	}


	long getStatusGameLost() {
		return statusGameLost;
	}


	void setStatusGameLost(long statusGameLost) {
		this.statusGameLost = statusGameLost;
	}


	long getStatusGameWin() {
		return statusGameWin;
	}


	void setStatusGameWin(long statusGameWin) {
		this.statusGameWin = statusGameWin;
	}


	long getGameDraw() {
		return gameDraw;
	}


	void setGameDraw(long gameDraw) {
		this.gameDraw = gameDraw;
	}


	long getGameLost() {
		return gameLost;
	}


	void setGameLost(long gameLost) {
		this.gameLost = gameLost;
	}


	long getGameWin() {
		return gameWin;
	}


	void setGameWin(long gameWin) {
		this.gameWin = gameWin;
	}


	long getId() {
		return id;
	}


	void setId(long id) {
		this.id = id;
	}

}
