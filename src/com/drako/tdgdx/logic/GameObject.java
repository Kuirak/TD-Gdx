package com.drako.tdgdx.logic;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	private Vector2 position;
	private float colR;
	public final static float DEPTH = -50;
	
	public abstract void render(GL10 gl);
	
	public void translate(Vector2 v,GL10 gl){
		gl.glPushMatrix();
		gl.glTranslatef(v.x, v.y, DEPTH);
		gl.glPopMatrix();
	}
	

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setColR(float colR) {
		this.colR = colR;
	}

	public float getColR() {
		return colR;
	}
	
	
}
