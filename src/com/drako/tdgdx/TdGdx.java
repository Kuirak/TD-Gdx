package com.drako.tdgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.drako.tdgdx.helper.MeshObject;

public class TdGdx implements ApplicationListener {
	private PerspectiveCamera camera;
	private MeshObject meshObj;
	
	float rotate;
	@Override
	public void create() {
		Gdx.app.log("Create", "Start Init");
		camera = new PerspectiveCamera(67, 500, 300);
		meshObj = new MeshObject(0, 0, 10, 10,10);
	
		rotate =0;
		Gdx.app.log("Create", "Finished Init");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		camera.update();
		camera.apply(gl);
		
		gl.glPushMatrix();
		gl.glTranslatef(meshObj.getPosition().x,meshObj.getPosition().y, -40);
		gl.glRotatef(rotate, 0, 1, 0);
		meshObj.getMesh().render(GL10.GL_TRIANGLE_STRIP);
		gl.glPopMatrix();
		
		rotate++;
		
		
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
