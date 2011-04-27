package com.drako.tdgdx.helper;

import com.badlogic.gdx.math.Vector2;



public class RayCastHelper {
	//requires a rayinfo with precalculated dir;
	public static boolean IntersectLineCircle(Vector2 circleCenter, float r,RayInfo ri) {
		
		Vector2 vOrginNew = circleCenter.sub(ri.start);
		
		
		
		float b = vOrginNew.x * ri.rayDir.x + ri.rayDir.y * vOrginNew.y;
		float c = r * r - (vOrginNew.len2());
		float a = ri.dirLen2; // Len2() is x*x + y*y
		float b2ac = b * b + a * c;
		// Negative doesn't have square root.
		if ((b2ac) <= 0) {
			return false;
		}
		float diva = 1.0F / a;
		float sqrtb2ac = (float) Math.sqrt((b2ac));
		float l1 = (b - sqrtb2ac) * diva;
		float l2 = (b + sqrtb2ac) * diva;

		// we need the closest intersection point.. so find smaller l.
		// To get the other end , just change the comparison operator.
		 ri.distanceToHit = Math.min(l1, l2);

		
		

		return true;
	}
}
