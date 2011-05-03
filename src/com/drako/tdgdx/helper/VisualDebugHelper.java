package com.drako.tdgdx.helper;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.math.Vector2;

public class VisualDebugHelper {
	private static ImmediateModeRenderer renderer = new ImmediateModeRenderer();
	private static ArrayList<Vector2> points = new ArrayList<Vector2>();
	private static ArrayList<Vector2> lines = new ArrayList<Vector2>();
	public final static float DEPTH = -50;
	
	
	public static void addPoint(Vector2 v){
		v= new Vector2(v);
		points.add(v);
		Gdx.app.log("VisualDebugHelper", "AddedPoint at: " + v);
	}
	
	public static void drawPoints(GL10 gl){
		gl.glPointSize(5);
		renderer.begin(GL10.GL_POINTS);
		for (Vector2 point : points) {
			renderer.color(1, 0, 0, 1);
			renderer.vertex(point.x, point.y, DEPTH);
		}
		renderer.end();	
	}
	public static void addLine(Vector2 v1, Vector2 v2){
		v1= new Vector2(v1);
		v2= new Vector2(v2);
		lines.add(v1);
		lines.add(v2);
		Gdx.app.log("VisualDebugHelper", "Addedline betweed: " + v1 +" and "+ v2);
	}
	public static void drawLines(GL10 gl){
		gl.glLineWidth(1);
		renderer.begin(GL10.GL_LINES);
		for (int i = 0; i < lines.size(); i+=2) {
			renderer.color(0, 1, 0, 1);
			renderer.vertex(lines.get(i).x, lines.get(i).y, DEPTH);
			renderer.color(0, 1, 0, 1);
			renderer.vertex(lines.get(i+1).x, lines.get(i+1).y, DEPTH);
			
		}
		renderer.end();
		
	}
}
