package com.drako.tdgdx.logic;

import com.badlogic.gdx.graphics.GL10;

public class Turret extends GameObject{
	private float range;
	
	public Turret(float x,float y,float colR,float range){
		getPosition().set(x, y);
		setColR(colR);
		this.range = range;
		
	}
	@Override
	public void render(GL10 gl) {
		// TODO Auto-generated method stub
		
	}
	public void setRange(float range) {
		this.range = range;
	}
	public float getRange() {
		return range;
	}

}
