package com.drako.tdgdx.helper;

import com.badlogic.gdx.math.Vector2;

public class RayInfo {
	public Vector2 start;
	public Vector2 end;
	public Vector2 hit;
	public float distanceToHit =0;
	public Vector2 rayDir;
	public float dirLen2;
	
	public void calculateHit(float dst) {
		hit.set(rayDir.mul(dst).add(start));
	}

	public void calculateDir() {
		rayDir = (end.sub(start)).nor();
		dirLen2 = rayDir.len2();
	}
}
