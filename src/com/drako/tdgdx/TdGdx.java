package com.drako.tdgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.drako.tdgdx.helper.CollisionHelper;
import com.drako.tdgdx.helper.MeshObject;
import com.drako.tdgdx.helper.VisualDebugHelper;
import com.drako.tdgdx.logic.WayPoint;


public class TdGdx implements ApplicationListener {
	private OrthographicCamera camera;
	private PerspectiveCamera cam;
	private MeshObject meshObj;
	private MeshObject meshObj2;
	private GL10 gl;
	private Vector2 spawn;
	private Vector2 target;
	private WayPoint wp;
	private SpriteBatch batch;
	private BitmapFont font;
	//debug
	boolean debug =true;
	
	@Override
	public void create() {
		Gdx.app.log("Create", "Start Init");
		camera = new OrthographicCamera( 500, 300);
		cam = new PerspectiveCamera(67, 500, 300);
		meshObj = new MeshObject(0, 0, 10, 10,10);
		meshObj2 = new MeshObject(10, 20, 10, 10,10);
		CollisionHelper.collidable.add(meshObj);
		CollisionHelper.collidable.add(meshObj2);
		spawn= new Vector2(-50,5);
		target = new Vector2(50, 30);
		font = new BitmapFont(Gdx.files.internal("data/fontarial.fnt"), Gdx.files.internal("data/fontarial.png"), false);
		gl = Gdx.graphics.getGL10();
		batch = new SpriteBatch();
		if(debug){
			VisualDebugHelper.addLine(spawn, target);
		}
		Gdx.app.log("Create", "Finished Init");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//uncomment for 3D
//		cam.update();
//		cam.apply(gl);
		camera.update();
		camera.apply(gl);
		batch.setProjectionMatrix(camera.combined);
		batch.setTransformMatrix(camera.view);
		
		if(Gdx.input.justTouched()){
			wp = new WayPoint(spawn, target, false);
			wp.CalculateNextPossibleWayPoints();
			
		}
		if(debug){
			
			VisualDebugHelper.drawPoints(gl);
			VisualDebugHelper.drawLines(gl);
		}
		gl.glColor4f(1, 1, 1, 1);
		meshObj.render(gl);
		meshObj2.render(gl);
		batch.begin();
		font.draw(batch, "fps:"+Gdx.graphics.getFramesPerSecond(), -220, 140);
		batch.end();
		
		
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
