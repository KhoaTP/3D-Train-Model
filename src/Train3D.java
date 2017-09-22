import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix4d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;

public class Train3D {

	public static TransformGroup createTrain() {

		// TRAIN TransformGroup for objRoot to return
		TransformGroup trainTG = new TransformGroup();

		// ----------------- COLORS ------------------------- //
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

		// BLUE APPEARANCE
		
		// 1 ambient color: amount of ambient light reflected
		// 2 emissive color: light emitted
		// 3 diffuse color: used to calculate amount of ...
		// 4 specular color: ... diffuse and specular reflection
		// 5 shininess value: larger value ==> shinier
		Color3f ambientColourBlue = new Color3f(0.0f, 0.0f, 0.2f);
		Color3f emissiveColourBlue = new Color3f(0.0f, 0.0f, 0.8f);
		Color3f diffuseColourBlue = new Color3f(0.0f, 0.0f, 0.5f);
		Color3f specularColourBlue = new Color3f(0.0f, 0.0f, 1.5f);
		float shininessBlue = 10.0f;
		Appearance newBlueApp = new Appearance();
		newBlueApp.setMaterial(new Material(ambientColourBlue, emissiveColourBlue, diffuseColourBlue, specularColourBlue,
				shininessBlue));

		// GREEN APPEARANCE
		Color3f ambientColourG = new Color3f(0.0f, 0.5f, 0.0f);
		Color3f emissiveColourG = new Color3f(0.0f, 0.7f, 0.0f);
		Color3f diffuseColourG = new Color3f(0.0f, 0.8f, 0.0f);
		Color3f specularColourG = new Color3f(0.0f, 4.0f, 0.0f);
		float shininessG = 0.8f;
		Appearance newGreenApp = new Appearance();
		newGreenApp.setMaterial(new Material(ambientColourG, emissiveColourG, diffuseColourG,
				specularColourG, shininessG));

		// RED APPEARANCE
		Color3f ambientColourRed = new Color3f(0.2f, 0.0f, 0.0f);
		Color3f emissiveColourRed = new Color3f(0.8f, 0.0f, 0.0f);
		Color3f diffuseColourRed = new Color3f(0.8f, 0.0f, 0.0f);
		Color3f specularColourRed = new Color3f(10.5f, 0.0f, 0.0f);
		float shininessRed = 1.0f;
		Appearance newRedApp = new Appearance();
		newRedApp.setMaterial(new Material(ambientColourRed, emissiveColourRed, diffuseColourRed, specularColourRed,
				shininessRed));

		// ------------------------- END OF COLORS ----------------------- //

		// --------------------- START OF MAKING TRAIN --------------- //
		
		// -- MAIN TRAIN BODY -- //
		Box trainBody = new Box(0.09f, 0.04f, 0.15f, newBlueApp);

		// -- CABIN BOX OBJECT -- //
		Box cabin = new Box(-0.045f, 0.04f, 0.02f, newBlueApp);
		Transform3D cabinTrans = new Transform3D();
		cabinTrans.setScale(new Vector3d(2.0, 2.0, 2.0));
		cabinTrans.setTranslation(new Vector3d(0.0, 0.11, -0.11));
		TransformGroup trainCabinTG = new TransformGroup(cabinTrans);

		// -- FRONT WHEELS RIGHT -- //
		Cylinder frontRWheel = new Cylinder(0.025f, 0.025f, newRedApp);
		Transform3D frontRTrans = new Transform3D();
		frontRTrans.rotZ(Math.PI / 2);
		frontRTrans.setScale(new Vector3d(2.0, 2.0, 2.0));
		frontRTrans.setTranslation(new Vector3d(-0.07, -0.06, 0.1));
		TransformGroup frontRWheelTG = new TransformGroup(frontRTrans);

		// -- FRONT WHEELS LEFT -- //
		Cylinder frontLWheel = new Cylinder(0.025f, 0.025f, newRedApp);
		Transform3D frontLTrans = new Transform3D();
		frontLTrans.rotZ(Math.PI / 2);
		frontLTrans.setScale(new Vector3d(2.0, 2.0, 2.0));
		frontLTrans.setTranslation(new Vector3d(0.07, -0.06, 0.1));
		TransformGroup fWheelLTG = new TransformGroup(frontLTrans);

		// -- REAR WHEELS RIGHT -- //
		Cylinder rearRWheel = new Cylinder(0.025f, 0.025f, newRedApp);
		Transform3D rearRWheelTrans = new Transform3D();
		rearRWheelTrans.rotZ(Math.PI / 2);
		rearRWheelTrans.setScale(new Vector3d(2.0, 2.0, 2.0));
		rearRWheelTrans.setTranslation(new Vector3d(-0.07, -0.06, -0.1));
		TransformGroup rearWheelRTG = new TransformGroup(rearRWheelTrans);

		// -- REAR WHEELS LEFT -- //
		Cylinder rearLWheel = new Cylinder(0.025f, 0.025f, newRedApp);
		Transform3D rearLWheelTrans = new Transform3D();
		rearLWheelTrans.rotZ(Math.PI / 2);
		rearLWheelTrans.setScale(new Vector3d(2.0, 2.0, 2.0));
		rearLWheelTrans.setTranslation(new Vector3d(0.07, -0.06, -0.1));
		TransformGroup rearWheelLTG = new TransformGroup(rearLWheelTrans);

		// -- CHIMNEY -- //
		Cylinder chimney = new Cylinder(0.006f, 0.06f, newRedApp);
		Transform3D chimneyTrans = new Transform3D();
		chimneyTrans.setScale(new Vector3d(2.0, 2.0, 2.0));
		chimneyTrans.setTranslation(new Vector3d(0.0, 0.18, 0.03));
		TransformGroup chimneyTG = new TransformGroup(chimneyTrans);

		// -- CHIMNEY CONE -- //
		Cone chimneyCone = new Cone(0.009f, 0.03f, newRedApp);
		Transform3D coneTrans = new Transform3D();
		coneTrans.rotX(Math.PI);
		coneTrans.setScale(new Vector3d(2.0, 2.0, 2.0));
		coneTrans.setTranslation(new Vector3d(0.0, 0.22, 0.03));
		TransformGroup chimneyConeTG = new TransformGroup(coneTrans);

		// --- CYLINDER TRAIN BODY --- //
		Cylinder trainBody2 = new Cylinder(0.025f, 0.11f, newGreenApp);
		Transform3D cylinderTrans = new Transform3D();
		cylinderTrans.rotX(Math.PI / 2);
		cylinderTrans.setScale(new Vector3d(2.0, 2.0, 2.0));
		cylinderTrans.setTranslation(new Vector3d(0.0, 0.08, 0.041));
		TransformGroup trainBodyTG = new TransformGroup(cylinderTrans);

		// -------------------- END OF MAKING TRAIN ---------------------- //

		// make edge relations with the scene graph nodes
		trainTG.addChild(trainBody);
		trainTG.addChild(trainCabinTG);
		trainCabinTG.addChild(cabin);
		trainTG.addChild(frontRWheelTG);
		frontRWheelTG.addChild(frontRWheel);
		trainTG.addChild(fWheelLTG);
		fWheelLTG.addChild(frontLWheel);
		trainTG.addChild(rearWheelRTG);
		rearWheelRTG.addChild(rearRWheel);
		trainTG.addChild(rearWheelLTG);
		rearWheelLTG.addChild(rearLWheel);
		trainTG.addChild(chimneyTG);
		chimneyTG.addChild(chimney);
		trainTG.addChild(chimneyConeTG);
		chimneyConeTG.addChild(chimneyCone);
		trainTG.addChild(trainBodyTG);
		trainBodyTG.addChild(trainBody2);

		return trainTG;

	}

}
