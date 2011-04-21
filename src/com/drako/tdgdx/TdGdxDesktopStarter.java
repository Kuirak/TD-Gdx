package com.drako.tdgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class TdGdxDesktopStarter {
public static void main(String[] args) {
	new LwjglApplication(new TdGdx(), "Tower Defense Android - GDX", 500, 300, false);
}
}
