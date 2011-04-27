package com.drako.tdgdx.logic;

import java.util.ArrayList;
import com.drako.tdgdx.helper.RayCastHelper;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.drako.tdgdx.helper.RayInfo;

public class WayPoint {
	public Vector2 position;
	private ArrayList<WayPoint> possibleNextWaypoints;
	private boolean isHitPoint;
	private WayPoint target;
	public static final int ALTERNATIVE_WAYS = 2;
	private static final float MAX_RAY_LENGTH = 100;
	// PlaceHolder:
	private ArrayList<Vector2> dirs;
	private float stepLength = 0.5f;
	private RayInfo ri;

	public WayPoint(Vector2 position, WayPoint lastWp, WayPoint target,
			boolean isHitPoint) {

	}

	public boolean CalculateNextPossibleWayPoints() {

		if (!this.isHitPoint) {
			return doForwardStep();
		} else {
			return doSideSteps(dirs);
		}

	}
	//Is called when  not Hitpoint
	private boolean doForwardStep() {
		// Ray from this Waypoint to target returns the Vector2 of the nearest
		// intersection
		Vector2 hit = CastRay(this.position, target.position);
		// if the ray hits the target without intersection - target reached!
		if (hit == target.position) {
			return true;
		}
		// if not make this hitpoint new Waypoint
		WayPoint wp = new WayPoint(hit, this, this.target, true);
		// recursion on CalculateNextPossibleWayPoint if true add the new
		// Waypoint to the possible list
		// and return true because successfully fount a new Waypoint
		if (wp.CalculateNextPossibleWayPoints()) {
			possibleNextWaypoints.add(wp);
			return true;
		}
		return false;
	}
	//Is called when isHitpoint
	// gets a list with the Vectors in which direction to do the sidesteps
	private boolean doSideSteps(ArrayList<Vector2> dirs) {

		for (Vector2 dir : dirs) {
			Vector2 hit = CastRay(this.position,
					this.position.add(dir.mul(MAX_RAY_LENGTH)));
		}

		// starts at the waypoint incrementing towards SideRayhitpoint
		float currentStep = 0;
		int numberOfWays = 0;
		while (currentStep < ri.distanceToHit) {
			currentStep += stepLength;

			for (Vector2 dir : dirs) {

				if (doOneSideStep(dir, currentStep)) {
					numberOfWays++;
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
				this, this.target, false);
		if (wp.CalculateNextPossibleWayPoints()) {
			this.possibleNextWaypoints.add(wp);
			return true;
		}
		return false;

	}

	private Vector2 CastRay(Vector2 start, Vector2 end) {
		ri.start =start;
		ri.end = end;
		float oldDst =300; //TODO max distance
		ri.calculateDir();
		//TODO iterate over collidable objects 
		for (MeshObject mesh : collision) {
			if (oldDst > ri.distanceToHit && RayCastHelper.IntersectLineCircle(mesh.position, mesh.radius, ri)){
				oldDst = ri.distanceToHit;
			}
			
		}
		ri.calculateHit(ri.distanceToHit);
		return ri.hit;
	}

	private float CastRayGetLength(Vector2 position2, Vector2 position3) {
		// TODO Ray stuff
		return null;
	}

}
