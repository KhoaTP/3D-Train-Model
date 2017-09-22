import java.awt.Container;
import java.awt.Font;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Text2D;
import com.sun.j3d.utils.image.TextureLoader;

public class Background3D {

	public static TransformGroup createBackground() {

		// BACKGROUND TransformGroup for objRoot to return
		TransformGroup bgTG = new TransformGroup();

		// ----------------- COLORS & TEXTURES ---------------------- //
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

		Appearance silverApp = new Appearance();
		Color3f silverColor = new Color3f(0.7f, 0.8f, 09f);
		ColoringAttributes silverCA = new ColoringAttributes();
		silverCA.setColor(silverColor);
		silverApp.setColoringAttributes(silverCA);

		// BLUE APPEARANCE

		// 1 ambient color: amount of ambient light reflected
		// 2 emissive color: light emitted
		// 3 diffuse color: used to calculate amount of ...
		// 4 specular color: ... diffuse and specular reflection
		// 5 shininess value: larger value ==> shinier
		Color3f ambientColour = new Color3f(0.0f, 0.0f, 0.2f);
		Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.8f);
		Color3f diffuseColour = new Color3f(0.0f, 0.0f, 0.5f);
		Color3f specularColour = new Color3f(0.0f, 0.0f, 1.5f);
		float shininess = 10.0f;
		Appearance newBlueApp = new Appearance();
		newBlueApp.setMaterial(new Material(ambientColour, emissiveColour, diffuseColour, specularColour, shininess));

		// GREEN APPEARANCE
		Color3f ambientColourG = new Color3f(0.0f, 0.4f, 0.0f);
		Color3f emissiveColourG = new Color3f(0.0f, 0.7f, 0.0f);
		Color3f diffuseColourG = new Color3f(0.0f, 0.7f, 0.0f);
		Color3f specularColourG = new Color3f(0.0f, 1.5f, 0.0f);
		float shininessG = 1.0f;
		Appearance newGreenApp = new Appearance();
		newGreenApp.setMaterial(
				new Material(ambientColourG, emissiveColourG, diffuseColourG, specularColourG, shininessG));

		// RED APPEARANCE
		Color3f ambientColourRed = new Color3f(0.2f, 0.0f, 0.0f);
		Color3f emissiveColourRed = new Color3f(0.8f, 0.0f, 0.0f);
		Color3f diffuseColourRed = new Color3f(0.8f, 0.0f, 0.0f);
		Color3f specularColourRed = new Color3f(10.5f, 0.0f, 0.0f);
		float shininessRed = 1.0f;
		Appearance newRedApp = new Appearance();
		newRedApp.setMaterial(
				new Material(ambientColourRed, emissiveColourRed, diffuseColourRed, specularColourRed, shininessRed));

		// GRASS TEXTURE
		// Texture map
		TextureLoader tl1 = new TextureLoader("grass-texture.jpg", "LUMINANCE", new Container());
		Texture texture1 = tl1.getTexture();
		texture1.setBoundaryModeS(Texture.WRAP);
		texture1.setBoundaryModeT(Texture.WRAP);
		texture1.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
		// Texture attributes
		TextureAttributes textureAttribute1 = new TextureAttributes();
		textureAttribute1.setTextureMode(TextureAttributes.MODULATE);
		// Appearance used when creating object with int
		Appearance grassTexture = new Appearance();
		grassTexture.setTexture(texture1);
		grassTexture.setTextureAttributes(textureAttribute1);
		// Material
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
		grassTexture.setMaterial(new Material(white, green, green, green, 0.8f));
		int primFlags1 = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

		// WOOD TEXTURE
		// Texture map
		TextureLoader tl2 = new TextureLoader("tree-bark-texture.jpg", "LUMINANCE", new Container());
		Texture texture2 = tl2.getTexture();
		texture2.setBoundaryModeS(Texture.WRAP);
		texture2.setBoundaryModeT(Texture.WRAP);
		texture2.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
		// Texture attributes
		TextureAttributes textureAttribute2 = new TextureAttributes();
		textureAttribute2.setTextureMode(TextureAttributes.MODULATE);
		// Appearance used when creating object with int
		Appearance woodTexture = new Appearance();
		woodTexture.setTexture(texture1);
		woodTexture.setTextureAttributes(textureAttribute1);
		// Material
		Color3f brown = new Color3f(0.35f, 0.15f, 0.1f);
		woodTexture.setMaterial(new Material(white, brown, brown, brown, 1.0f));
		int primFlags2 = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		// -------------------- END OF COLORS & TEXTURES ------------------- //

		// --------------START OF MAKING BACKGROUND------------------- //

		// GROUND BOX
		Box ground = new Box(2.0f, 0.05f, 1.0f, primFlags1, grassTexture);
		Transform3D groundTrans = new Transform3D();
		groundTrans.setScale(new Vector3d(2.0, 2.0, 2.0));
		groundTrans.setTranslation(new Vector3d(0.0, -0.27, 0.0));
		TransformGroup groundTG = new TransformGroup(groundTrans);

		// TRAIN TRACK RAILS
		Box line1 = new Box(1.8f, 0.03f, 0.02f, silverApp);
		Transform3D trackLine1Trans = new Transform3D();
		trackLine1Trans.setScale(new Vector3d(2.0, 2.0, 2.0));
		trackLine1Trans.setTranslation(new Vector3d(0.0, -0.1, 0.15));
		TransformGroup trackLine1TG = new TransformGroup(trackLine1Trans);

		Box line2 = new Box(1.8f, 0.03f, 0.02f, silverApp);
		Transform3D trackLine2Trans = new Transform3D();
		trackLine2Trans.setScale(new Vector3d(2.0, 2.0, 2.0));
		trackLine2Trans.setTranslation(new Vector3d(0.0, -0.1, -0.15));
		TransformGroup trackLine2TG = new TransformGroup(trackLine2Trans);

		// TRACK PIECES
		for (float i = -3.6f; i < 4.0f; i += 0.4f) {
			Box track = new Box(0.08f, 0.02f, 0.25f, primFlags2, woodTexture);
			Transform3D trackTrans = new Transform3D();
			trackTrans.setTranslation(new Vector3d(i, -0.14, 0.0));
			TransformGroup tracksTG = new TransformGroup(trackTrans);
			bgTG.addChild(tracksTG);
			tracksTG.addChild(track);
		}

		// MAKING TREES

		// Tree Stalk
		for (float i = -3.6f; i < 4.0f; i += 0.9f) {
			Cylinder stalk = new Cylinder(0.2f, 1.0f, primFlags2, woodTexture);
			Transform3D treeTrans = new Transform3D();
			treeTrans.setTranslation(new Vector3d(i, 0.2, -1.5));
			TransformGroup treeTG = new TransformGroup(treeTrans);
			bgTG.addChild(treeTG);
			treeTG.addChild(stalk);
		}

		// Tree Top
		for (float i = -3.6f; i < 4.0f; i += 0.9f) {
			Transform3D treeTrans = new Transform3D();
			treeTrans.setTranslation(new Vector3d(i, 0.7, -1.5));
			TransformGroup treeTG = new TransformGroup(treeTrans);

			// PYRAMID SHAPE

			// Setting dimensions for each corner of pyramid
			Point3f west = new Point3f(-0.4f, 0.0f, 0.0f);
			Point3f north = new Point3f(0.0f, 0.0f, -0.4f);
			Point3f top = new Point3f(0.0f, 0.521f, 0.0f);
			Point3f east = new Point3f(0.4f, 0.0f, 0.0f);
			Point3f south = new Point3f(0.0f, 0.0f, 0.4f);

			TriangleArray treeTopPyramid = new TriangleArray(18, TriangleArray.COORDINATES);

			treeTopPyramid.setCoordinate(0, east);
			treeTopPyramid.setCoordinate(1, top);
			treeTopPyramid.setCoordinate(2, south);
			treeTopPyramid.setCoordinate(3, south);
			treeTopPyramid.setCoordinate(4, top);
			treeTopPyramid.setCoordinate(5, west);
			treeTopPyramid.setCoordinate(6, west);
			treeTopPyramid.setCoordinate(7, top);
			treeTopPyramid.setCoordinate(8, north);
			treeTopPyramid.setCoordinate(9, north);
			treeTopPyramid.setCoordinate(10, top);
			treeTopPyramid.setCoordinate(11, east);
			treeTopPyramid.setCoordinate(12, east);
			treeTopPyramid.setCoordinate(13, south);
			treeTopPyramid.setCoordinate(14, west);
			treeTopPyramid.setCoordinate(15, west);
			treeTopPyramid.setCoordinate(16, north);
			treeTopPyramid.setCoordinate(17, east);

			GeometryInfo geometryInf = new GeometryInfo(treeTopPyramid);
			NormalGenerator ng = new NormalGenerator();
			ng.generateNormals(geometryInf);

			GeometryArray pyramidShape = geometryInf.getGeometryArray();
			// creating new shape
			Shape3D treeTop = new Shape3D(pyramidShape, newGreenApp);
			// END OF PYRAMID SHAPE

			// adding treetop to tree transform group
			bgTG.addChild(treeTG);
			treeTG.addChild(treeTop);
		}

		// ADD 2D TEXT
		Transform3D textTrans = new Transform3D();
		textTrans.setTranslation(new Vector3d(0.0, -0.5, 3.0));
		TransformGroup textTG = new TransformGroup(textTrans);
		Text2D text2D = new Text2D("Priyan's Java 3D Project", new Color3f(0.9f, 1.0f, 1.0f), "Helvetica", 36,
				Font.ITALIC);

		// make edge relations with the scene graph nodes
		bgTG.addChild(groundTG);
		groundTG.addChild(ground);
		bgTG.addChild(trackLine1TG);
		trackLine1TG.addChild(line1);
		bgTG.addChild(trackLine2TG);
		trackLine2TG.addChild(line2);
		bgTG.addChild(textTG);
		textTG.addChild(text2D);

		return bgTG;

	}

}
