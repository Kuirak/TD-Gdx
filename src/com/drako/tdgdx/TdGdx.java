package com.drako.tdgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector2;
import com.drako.tdgdx.helper.CollisionHelper;
import com.drako.tdgdx.helper.MeshObject;
import com.drako.tdgdx.logic.WayPoint;


public class TdGdx implements ApplicationListener {
	private PerspectiveCamera camera;
	private MeshObject meshObj;
	private GL10 gl;
	private Vector2 spawn;
	private Vector2 target;
	private WayPoint wp;
	
	@Override
	public void create() {
		Gdx.app.log("Create", "Start Init");
		camera = new PerspectiveCamera(67, 500, 300);
		meshObj = new MeshObject(0, 0, 10, 10,10);
		CollisionHelper.collidable.add(meshObj);
		spawn= new Vector2(-100,0);
		target = new Vector2(100, 0);
		
		gl = Gdx.graphics.getGL10();
		Gdx.app.log("Create", "Finished Init");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		camera.apply(gl);
		if(Gdx.input.justTouched()){
			wp = new WayPoint(spawn, target, false);
			wp.CalculateNextPossibleWayPoints();
		}
		
		
		meshObj.render(gl);
		
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	

}
