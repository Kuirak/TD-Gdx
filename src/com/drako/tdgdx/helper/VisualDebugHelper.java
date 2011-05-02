package com.drako.tdgdx.helper;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.math.Vector2;

public class VisualDebugHelper {
	private static ImmediateModeRenderer renderer = new ImmediateModeRenderer();
	private static ArrayList<Vector2> points = new ArrayList<Vector2>();
	public final static float DEPTH = -40;
	public static void addPoint(Vector2 v){
		points.add(v);
		Gdx.app.log("VisualDebugHelper", "AddedPoint at: " + v);
	}
	
	public static void drawPoints(GL10 gl){
		gl.glPointSize(10);
		renderer.begin(GL10.GL_POINTS);
		renderer.color(1, 0, 0, 1);
		for (Vector2 point : points) {
			renderer.vertex(point.x, point.y, DEPTH);
		}
		renderer.end();	
	}
}
