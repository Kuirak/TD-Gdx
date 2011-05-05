package com.drako.tdgdx.logic.turret;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.drako.tdgdx.helper.TargetHelper;
import com.drako.tdgdx.logic.GameObject;
import com.drako.tdgdx.logic.creep.Creep;

public class Turret extends GameObject{
	private float range;
	private DamageType damage;
	private int costs;
	private int maxTargets;
	
	public Turret(float x,float y,float colR,float range){
		getPosition().set(x, y);
		setColR(colR);
		this.range = range;
		
	}
	
	
	public void detectCreep(){
		Vector2 tmpV;
		float temp;
		float range2 = range*range;
		for (Creep c : TargetHelper.possibleTargets) {
			if( c.getTargeted()<maxTargets){
				tmpV = new Vector2(c.getPosition());
				temp = tmpV.sub(this.getPosition()).len2();
				if (temp < range2 ){
					//add
				}
				if (temp > range2 ){
					//remove
				}
				
			}
			
		}
	}
	
	
	
	
	
	public void doShoot(){
		damage.shoot();
	}
	
	public void doUpgrade(){
		damage.upgrade();
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
