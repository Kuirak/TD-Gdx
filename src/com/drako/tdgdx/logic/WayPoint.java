package com.drako.tdgdx.logic;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.drako.tdgdx.helper.CollisionHelper;
import com.drako.tdgdx.helper.MeshObject;
import com.drako.tdgdx.helper.RayCastHelper;
import com.drako.tdgdx.helper.RayInfo;
import com.drako.tdgdx.helper.VisualDebugHelper;

public class WayPoint {
	public Vector2 position;
	public Vector2 target;
	private ArrayList<WayPoint> possibleNextWaypoints;
	private boolean isHitPoint;

	public static final int ALTERNATIVE_WAYS = 2;
	private static final float MAX_RAY_LENGTH = 100;
	// PlaceHolder:
	private ArrayList<Vector2> dirs;
	private float stepLength = 10;
	private RayInfo ri;
	private boolean hasCollided = false;
	//debugging
	boolean debug = true;
	
	

	public WayPoint(Vector2 position, Vector2 target, boolean isHitPoint) {
		
		this.position = position;
		this.target = target;
		this.isHitPoint = isHitPoint;
		ri = new RayInfo();
		dirs = new ArrayList<Vector2>();
		possibleNextWaypoints = new ArrayList<WayPoint>();
		if(debug){
			VisualDebugHelper.addPoint(position);
			VisualDebugHelper.addPoint(target);
		}
		Gdx.app.log("WayPoint", "init!");
	}


	public boolean CalculateNextPossibleWayPoints() {

		if (!this.isHitPoint) {
			Gdx.app.log("calcNextWp", "doForwardStep");
			return doForwardStep();
		} else {
			Gdx.app.log("calcNextWp", "doSideStep");
			dirs.add(new Vector2(0, 1));
			dirs.add(new Vector2(0, -1));
			return doSideSteps(dirs);
		}

	}

	// Is called when not Hitpoint
	private boolean doForwardStep() {
		// Ray from this Waypoint to target returns the Vector2 of the nearest
		// intersection
		Vector2 hit = CastRay(this.position, target);
		// if the ray hits the target without intersection - target reached!
		if (hasCollided == false) {
			Gdx.app.log("HitTarget?", "YES");
			return true;
		}
		// if not make this hitpoint new Waypoint
		WayPoint wp = new WayPoint(hit, this.target, true);
		// recursion on CalculateNextPossibleWayPoint if true add the new
		// Waypoint to the possible list
		// and return true because successfully fount a new Waypoint
		if (wp.CalculateNextPossibleWayPoints()) {
			possibleNextWaypoints.add(wp);
			return true;
		}
		Gdx.app.log("HitTarget?", "NO");
		return false;
	}

	// Is called when isHitpoint
	// gets a list with the Vectors in which direction to do the sidesteps
	private boolean doSideSteps(ArrayList<Vector2> dirs) {
		Gdx.app.log("doSideSteps", "started");
//TODO  don not  for what i need it  but it was planned
//		for (Vector2 dir : dirs) {
//			Vector2 hit = CastRay(this.position,
//					this.position.add(dir));
//		}

		// starts at the waypoint incrementing towards SideRayhitpoint
		float currentStep = 0;
		int numberOfWays = 0;
		Gdx.app.log("doSidesteps", ri.distanceToHit + "," + currentStep);
		int i = 0;
		while (i > 100) {
			i++;
			currentStep += stepLength;
			Gdx.app.log("doSideSteps", "In while");
			for (Vector2 dir : dirs) {

				if (doOneSideStep(dir, currentStep)) {
					numberOfWays++;
					Gdx.app.log("doSideSteps", "Found a way");
					if (numberOfWays >= ALTERNATIVE_WAYS) {
						return true;
					}
				}

			}
		}
		if (numberOfWays >= 1) {
			return true;
		} else {
			return false;

		}

	}

	private boolean doOneSideStep(Vector2 dir, float currentStep) {
		WayPoint wp = new WayPoint(this.position.add(dir.mul(currentStep)),
				this.target, false);
		if (wp.CalculateNextPossibleWayPoints()) {
			this.possibleNextWaypoints.add(wp);
			Gdx.app.log("doOneSideStep - nextWaypoint?", "YES!");
			return true;
		}
		Gdx.app.log("doOneSideStep - nextWaypoint?", "NO!");
		return false;

	}

	private Vector2 CastRay(Vector2 start, Vector2 end) {
		Gdx.app.log("CastRay", "start");
		ri.start= new Vector2(start);
		ri.end = new Vector2(end);
		float oldDst = MAX_RAY_LENGTH;
		ri.calculateDir();
		hasCollided = false;
		//  iterate over collidable objects
		for (MeshObject mesh : CollisionHelper.collidable) {
			
			Gdx.app.log("CastRay", "start iterating");
			if (oldDst > ri.distanceToHit
					&& RayCastHelper.IntersectLineCircle(mesh.getPosition(),
							mesh.getRadius(), ri)) {
				oldDst = ri.distanceToHit;
				hasCollided = true;
				Gdx.app.log("CastRay", "Collision!"+mesh);
			}

		}
		ri.calculateHit(ri.distanceToHit);
		if(debug){
			VisualDebugHelper.addLine(start, ri.hit);
		}
		return ri.hit;
	}

}
