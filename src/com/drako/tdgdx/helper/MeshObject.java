package com.drako.tdgdx.helper;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.math.Vector2;

public class MeshObject {
	private Mesh mesh;
	private float width,length,height;
	private Vector2 position;
	private float radius;
	
	
	public MeshObject(float x, float y,float width,float length){
		mesh = new Mesh(false, 4, 0, new VertexAttribute(Usage.Position,2,"a_position"));
		mesh = generateRect(mesh,width,length);
		this.position = new Vector2(x, y);
		this.radius = width;
		this.width = width;
		this.length = length;
		
			
		
	}	
	public MeshObject(float x, float y,float width,float length,float height){
		
		mesh = new Mesh(false, 8, 14, new VertexAttribute(Usage.Position,3,"a_position"));
		mesh = generateBlock(mesh,width,length,height);
		this.position = new Vector2(x, y);
		this.radius = width;
		this.width = width;
		this.length = length;
		this.height = height;
		
	}
	
	public boolean intersect2D(MeshObject meshObj) {
		//MeshObj Origin is in the middle
		float yUp, yDown, xLeft, xRight;
		yUp = position.y + (length / 2);
		yDown = position.y - (length / 2);
		xLeft = position.x - (width / 2);
		xRight = position.x + (width / 2);
		// meshObj 
		float yUp2, yDown2, xLeft2, xRight2;
		yUp2 = meshObj.getPosition().y + (meshObj.getLength() / 2);
		yDown2 = meshObj.getPosition().y - (meshObj.getLength() / 2);
		xLeft2 = meshObj.getPosition().x - (meshObj.getWidth() / 2);
		xRight2 = meshObj.getPosition().x + (meshObj.getWidth() / 2);
		if (yDown2 < yUp && yUp2 > yDown && xRight2 > xLeft && xLeft2 < xRight) {

			return true;
		} else
			return false;

	}
	public void render(GL10 gl){
		gl.glPushMatrix();
		gl.glTranslatef(position.x,position.y, -50);
		mesh.render(GL10.GL_TRIANGLE_STRIP);
		gl.glPopMatrix();
	}

	private Mesh generateRect(Mesh mesh, float width, float length) {
		mesh.setVertices(new float[]{width / 2, length / 2, -width / 2, length / 2,
				width / 2, -length / 2, -width / 2, -length / 2});
		return mesh;
	}
	
	private Mesh generateBlock(Mesh mesh,float width,float length,float height) {
		mesh.setVertices(new float[]{width/2, length/2,height/2,-width/2, length/2,height/2,-width/2, -length/2,height/2,width/2, -length/2,height/2,
									-width/2, -length/2,-height/2,-width/2, length/2,-height/2,width/2, length/2,-height/2,width/2, -length/2,-height/2});
		mesh.setIndices(new short[]{0,1,5,2,4,7,5,6,0,7,3,2,0,1});
		
		return mesh;
	}
	
	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getWidth() {
		return width;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getLength() {
		return length;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getHeight() {
		return height;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	public float getRadius() {
		return radius;
	}
	
}
