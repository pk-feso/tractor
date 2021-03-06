package ru.sbt.test.refactoring;

import junit.framework.TestCase;
import ru.sbt.test.refactoring.action.OrientationEnum;
import ru.sbt.test.refactoring.exception.TractorInDitchException;

/**
 * @author Ben
 *
 */
public class TractorTest extends TestCase {

	public void testShouldMoveForward(){
		Tractor tractor = new Tractor();
		tractor.move("F");
		assertEquals(0, tractor.getPositionX());
		assertEquals(1, tractor.getPositionY());
	}

	public void testShouldTurn(){
		Tractor tractor = new Tractor();
		tractor.move("T");
        assertEquals(OrientationEnum.EAST, tractor.getOrientation());
        tractor.move("T");
        assertEquals(OrientationEnum.SOUTH, tractor.getOrientation());
        tractor.move("T");
        assertEquals(OrientationEnum.WEST, tractor.getOrientation());
        tractor.move("T");
        assertEquals(OrientationEnum.NORTH, tractor.getOrientation());
    }

	public void testShouldTurnAndMoveInTheRightDirection(){
		Tractor tractor = new Tractor();
		tractor.move("T");
		tractor.move("F");		
		assertEquals(1, tractor.getPositionX());
		assertEquals(0, tractor.getPositionY());
		tractor.move("T");
		tractor.move("F");		
		assertEquals(1, tractor.getPositionX());
		assertEquals(-1, tractor.getPositionY());
		tractor.move("T");
		tractor.move("F");		
		assertEquals(0, tractor.getPositionX());
		assertEquals(-1, tractor.getPositionY());
		tractor.move("T");
		tractor.move("F");		
		assertEquals(0, tractor.getPositionX());
		assertEquals(0, tractor.getPositionY());		
	}
	
	public void testShouldThrowExceptionIfFallsOffPlateau(){
		Tractor tractor = new Tractor();
		tractor.move("F");
		tractor.move("F");
		tractor.move("F");
		tractor.move("F");
		tractor.move("F");
		try{
			tractor.move("F");
			fail("Tractor was expected to fall off the plateau");
		}catch(TractorInDitchException expected){
		}
	}
}
