import java.util.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.geometry.*;

public class CollisionBehaviour extends Behavior {

	// This class uses two spheres - red and blue
	public Switch sphereSwitch;
	public Primitive shape3D;

	// declare initial and process stimulus (arrays of) criteria
	public WakeupCriterion[] initialCriteria;
	public WakeupCriterion[] procStimCriteria;

	// public WakeupOr(WakeupCriterion[] criteria) - logical OR of the
	// (objects in the) criteria
	public WakeupOr initial_wakeUpCondition;
	public WakeupOr procStim_wakeUpCondition;

	// CONSTRUCTOR
	public CollisionBehaviour(Primitive shape, Switch theSwitch, Bounds theBounds) {
		shape3D = shape;
		sphereSwitch = theSwitch;
		setSchedulingBounds(theBounds);
	}

	public void initialize() {
		initialCriteria = new WakeupCriterion[2];
		initialCriteria[0] = new WakeupOnCollisionEntry(sphereSwitch);
		initialCriteria[1] = new WakeupOnCollisionExit(sphereSwitch);

		initial_wakeUpCondition = new WakeupOr(initialCriteria);
		wakeupOn(initial_wakeUpCondition);

		procStimCriteria = new WakeupCriterion[2];
		procStimCriteria[0] = new WakeupOnCollisionEntry(sphereSwitch);
		procStimCriteria[1] = new WakeupOnCollisionExit(sphereSwitch);

		System.out.println();
		if (((WakeupCondition) initial_wakeUpCondition) instanceof WakeupOr) {
			System.out.println("*initial new wakeupOn cylinder collision ENTRY OR EXIT *");
		} else {
			System.out.println("*initial new wakeupOn cylinder collison ENTRY AND EXIT *");
		}
	}

	public void processStimulus(Enumeration criteria) {
		// Here we define what happens when a collision occurs.
		//System.out.println("process stimulus called");

		while (criteria.hasMoreElements()) {

			WakeupCriterion theCriterion = (WakeupCriterion) criteria.nextElement();
			// IF statements for switching the colour
			// train entry if statements
			if (theCriterion instanceof WakeupOnCollisionEntry) {
				if (sphereSwitch.getWhichChild() == 0) {
					sphereSwitch.setWhichChild(1);
					//System.out.println("red --> blue*");
				} else {

					if (sphereSwitch.getWhichChild() == 1) {
						sphereSwitch.setWhichChild(0);
						//System.out.println("blue --> red*");
					}
				}
			} else {
				// train exit if statements
				if (theCriterion instanceof WakeupOnCollisionExit) {

					if (sphereSwitch.getWhichChild() == 0) {
						sphereSwitch.setWhichChild(0);
						 System.out.println("red --> blue");

					} else {

						if (sphereSwitch.getWhichChild() == 1) {
							sphereSwitch.setWhichChild(1);
							 System.out.println("blue --> red");
						}
					}
				} // end if exit

			} // end else

		}

		procStim_wakeUpCondition = new WakeupOr(procStimCriteria);
		wakeupOn(procStim_wakeUpCondition);

	}
}
