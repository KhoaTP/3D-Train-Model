
/*JAVA 3D PROJECT:
 * My program consists of a train and a track with a sphere object on the
 * track that it will collide with, the sphere will change colour from blue
 * -> red then red -> blue each time it collides with the train. The train
 * moves from one end of the grass land to the other. The keeps resetting.
 * It also has trees and with the tree tops being pyramids, which is the
 * shape I chose to create.
 * 
 */
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Example3D extends JFrame {

	// The canvas to be draw upon
	public Canvas3D myCanvas3D;

	// CONSTRUCTOR - creating the simple universe
	public Example3D() {

		// Mechanism for closing the window and ending the program.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		getContentPane().add("Center", myCanvas3D);

		// First generate it using the Canvas.
		SimpleUniverse universe = new SimpleUniverse(myCanvas3D);

		// set viewing platform
		TransformGroup cameraTG = universe.getViewingPlatform().getViewPlatformTransform();

		// starting position of the viewing platform
		Vector3f translate = new Vector3f();
		Transform3D T3D = new Transform3D();

		// set distance from screen
		translate.set(0.0f, 0.0f, 10.5f);
		T3D.setTranslation(translate);
		cameraTG.setTransform(T3D);

		// The scene is generated in this method.
		BranchGroup scene = createSceneGraph();

		// Add everything to the universe.
		universe.addBranchGraph(scene);

		// Add some light to the scene
		addLight(universe);

		// Show the canvas/window.
		setTitle("Priyan's Java 3D Project");
		setSize(700, 700);
		setVisible(true);

	} // end Example3D()

	public BranchGroup createSceneGraph() {

		// -------------------- COLORS ------------------- //
		Appearance blueApp = new Appearance();
		Color3f blueColor = new Color3f(0.0f, 0.0f, 1.0f);
		ColoringAttributes blueCA = new ColoringAttributes();
		blueCA.setColor(blueColor);
		blueApp.setColoringAttributes(blueCA);

		Appearance redApp = new Appearance();
		Color3f redColor = new Color3f(0.8f, 0.0f, 0.0f);
		ColoringAttributes redCA = new ColoringAttributes();
		redCA.setColor(redColor);
		redApp.setColoringAttributes(redCA);

		Appearance greenApp = new Appearance();
		Color3f greenColor = new Color3f(0.0f, 1.0f, 0.0f);
		ColoringAttributes greenCA = new ColoringAttributes();
		greenCA.setColor(greenColor);
		greenApp.setColoringAttributes(greenCA);

		// BLUE
		Color3f ambientColourBlue = new Color3f(0.0f, 0.0f, 0.6f);
		Color3f emissiveColourBlue = new Color3f(0.0f, 0.0f, 0.8f);
		Color3f diffuseColourBlue = new Color3f(0.0f, 0.0f, 0.6f);
		Color3f specularColourBlue = new Color3f(0.0f, 0.0f, 1.9f);
		float shininessBlue = 150.0f;
		Appearance newBlueApp = new Appearance();
		newBlueApp.setMaterial(new Material(ambientColourBlue, emissiveColourBlue, diffuseColourBlue,
				specularColourBlue, shininessBlue));

		// RED
		Color3f ambientColourRed = new Color3f(0.6f, 0.0f, 0.0f);
		Color3f emissiveColourRed = new Color3f(0.7f, 0.0f, 0.0f);
		Color3f diffuseColourRed = new Color3f(0.6f, 0.0f, 0.0f);
		Color3f specularColourRed = new Color3f(0.8f, 0.0f, 0.0f);
		float shininessRed = 20.0f;
		Appearance newRedApp = new Appearance();
		newRedApp.setMaterial(
				new Material(ambientColourRed, emissiveColourRed, diffuseColourRed, specularColourRed, shininessRed));

		// ---------------------- END OF COLORS --------------------- //

		// creating the bounds of the universe
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 10.0);

		// creating branch group
		BranchGroup objRoot = new BranchGroup();

		// MAIN TransformGroup for objRoot
		TransformGroup mainTG = new TransformGroup();
		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		// TRAIN Transform Group being created and rotated
		Transform3D trainTrans = new Transform3D();
		trainTrans.rotY(Math.PI / 2);
		trainTrans.setScale(new Vector3d(2.0, 2.0, 2.0));
		trainTrans.setTranslation(new Vector3d(0.0, 0.18, 0.0));
		TransformGroup mainTrainTG = new TransformGroup();
		TransformGroup trainTG = Train3D.createTrain();
		trainTG.setTransform(trainTrans);

		// BACKGROUND TransformGroup being created
		TransformGroup bgTG = Background3D.createBackground();

		// Create the rotate behaviour node
		MouseRotate behavior = new MouseRotate();
		behavior.setTransformGroup(mainTG);
		objRoot.addChild(behavior);
		behavior.setSchedulingBounds(bounds);

		// Create the zoom behaviour node
		MouseZoom behavior2 = new MouseZoom();
		behavior2.setTransformGroup(mainTG);
		objRoot.addChild(behavior2);
		behavior2.setSchedulingBounds(bounds);

		// Create the translate behaviour node
		MouseTranslate behavior3 = new MouseTranslate();
		behavior3.setTransformGroup(mainTG);
		objRoot.addChild(behavior3);
		behavior3.setSchedulingBounds(bounds);

		// ------------------ MOVEMENT OF TRAIN -------------------- //

		// movement from left to right
		Transform3D xAxis = new Transform3D();
		float maxRight = 3.0f;
		// alpha for the left to right movement
		// Alpha(number_of_times_for_movement,time_movement_takes)
		Alpha cylAlphaR = new Alpha(-1, 5000);

		// Interpolator for the movement
		// PosInt(theAlpha, TGroup_to_attach_to,
		// axis_of_movement_default_X_Axis, start_position, end_position)
		PositionInterpolator cylMoveR = new PositionInterpolator(cylAlphaR, mainTrainTG, xAxis, -3.5f, maxRight);

		// setting bounds for the train
		BoundingSphere bounds1 = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1.0);
		cylMoveR.setSchedulingBounds(bounds1);

		// add the movements to the train transformation group
		mainTrainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mainTrainTG.addChild(cylMoveR);

		// ---------------------- END OF MOVEMENT ------------------ //

		// ------------- START OF COLLISION BEHAVIOUR ----------- //

		// creating spheres used for collision
		Sphere redSphere = new Sphere(0.2f, newRedApp);
		Sphere blueSphere = new Sphere(0.2f, newBlueApp);

		// switch for red and blue sphere
		Switch colourSwitch = new Switch();
		colourSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);

		// add the spheres to the Switch
		colourSwitch.addChild(redSphere); // child 0
		colourSwitch.addChild(blueSphere); // child 1

		// set red sphere visible at the start
		colourSwitch.setWhichChild(0);

		// transformation group for the spheres.
		Transform3D tfSphere = new Transform3D();
		tfSphere.setTranslation(new Vector3f(0.7f, 0.3f, 0.0f));
		TransformGroup sphereTG = new TransformGroup(tfSphere);
		sphereTG.addChild(colourSwitch);

		// collision bounds for the spheres
		colourSwitch.setCollisionBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 0.1f));

		// false as do not want background interfering with collision
		bgTG.setCollidable(false);

		// CollisionBehaviour class takes care of changing the colour of the
		// sphere when the train touches it.
		CollisionBehaviour cb = new CollisionBehaviour(blueSphere, colourSwitch, bounds);
		mainTG.addChild(cb);

		// ------------- END OF COLLISON BEHAVIOUR ------------- //

		// adding nodes
		objRoot.addChild(mainTG);
		mainTG.addChild(mainTrainTG);
		mainTrainTG.addChild(trainTG);
		mainTG.addChild(bgTG);
		mainTG.addChild(sphereTG);

		// ALWAYS COMPILE objRoot
		objRoot.compile();
		return objRoot;
	}

	public void addLight(SimpleUniverse su) {

		// branch group for light
		BranchGroup bgLight = new BranchGroup();
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 500.0);

		// setting up colour of lights
		Color3f alColor = new Color3f(0.2f, 0.2f, 0.2f);
		AmbientLight aLgt = new AmbientLight(alColor);
		aLgt.setInfluencingBounds(bounds);
		// add light to branch group
		bgLight.addChild(aLgt);

		// directional light
		Color3f lightColour1 = new Color3f(0.02f, 0.02f, 0.2f);
		Vector3f lightDir1 = new Vector3f(0.0f, 1.0f, 0.0f);
		DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
		light1.setInfluencingBounds(bounds);
		bgLight.addChild(light1);

		Vector3f lightDir2 = new Vector3f(1.0f, -1.0f, 0.5f);
		DirectionalLight light2 = new DirectionalLight(lightColour1, lightDir2);
		light2.setInfluencingBounds(bounds);
		bgLight.addChild(light2);

		// add light to the universe
		su.addBranchGraph(bgLight);
	}

	public static void main(String[] args) {
		// creating Example 3D object that creates application
		new Example3D();
	}

}