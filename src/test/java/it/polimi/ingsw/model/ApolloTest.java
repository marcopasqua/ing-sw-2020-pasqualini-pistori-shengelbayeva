package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.model.State.End;
import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ApolloTest {
    Model model;
    Player apollo;
    Player opponent;

    @Before
    public void testSetUpGrid() {
        apollo=new Apollo("playertest");
        apollo.addWorker();
        apollo.addWorker();
        opponent=new AbstractPlayer("opponent");
        opponent.addWorker();
        model=new Model();
        model.getGrid().getTile(new Coordinate(2, 0)).setWorker(apollo.getWorker(0));
        model.getGrid().getTile(new Coordinate(3, 0)).setWorker(apollo.getWorker(1));
        model.getGrid().getTile(new Coordinate(2,1)).setWorker(opponent.getWorker(0));
    }

    @Test
    public void TestPower(){
        model.setCurrentState(new Select());
        apollo.makeSelection(model,new Coordinate(2,0));
        Coordinate destination = new Coordinate(2,1);
        assertTrue(model.getCurrentState() instanceof Move);
        assertFalse("cannot move because is a apollo worker",apollo.makeMovement(model, new Coordinate(3,0)));
        apollo.makeMovement(model, destination);
        assertEquals("apollo should move", apollo.getWorker(0), model.getGrid().getTile(destination).getWorker());
        assertEquals("opponent worker is now in the selection tile",opponent.getWorker(0),model.getGrid().getTile(new Coordinate(2,0)).getWorker());
        assertTrue(model.getCurrentState() instanceof Build);
        apollo.makeBuild(model,new Coordinate(2,2));
        assertTrue(model.getCurrentState() instanceof End);


    }


    @Test
    public void TestFail(){
        model.setCurrentState(new Select());
        Coordinate from=new Coordinate(2,0);
        apollo.makeSelection(model,from);
        Coordinate destination = new Coordinate(2,1);
        model.getGrid().getTile(destination).levelUp().levelUp();
        assertEquals(2, model.getGrid().getTile(2, 1).getHeight().ordinal());
        assertTrue(model.getCurrentState() instanceof Move);
        apollo.makeMovement(model, destination);
        assertEquals("apollo not move", apollo.getWorker(0), model.getGrid().getTile(from).getWorker());
        assertEquals("opponent worker not move",opponent.getWorker(0),model.getGrid().getTile(destination).getWorker());
        assertTrue(model.getCurrentState() instanceof Move);

    }


}


