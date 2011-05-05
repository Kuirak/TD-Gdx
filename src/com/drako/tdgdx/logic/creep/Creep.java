package com.drako.tdgdx.logic.creep;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.drako.tdgdx.helper.TargetHelper;
import com.drako.tdgdx.logic.GameObject;

public class Creep extends GameObject{
	private int targeted;
	private Vector2 dir;
	private float health;
	private float armor;
	private int income;
	
	public Creep(){
		super();
		TargetHelper.possibleTargets.add(this);
	}
	
	
	public void isKilled(){
		TargetHelper.possibleTargets.remove(this);
	}
	
	
	@Override
	public void render(GL10 gl) {
		// TODO Auto-generated method stub
		
	}


	public void setTargeted(int targeted) {
		this.targeted = targeted;
	}


	public int getTargeted() {
		return targeted;
	}

}
