package it.polimi.ingsw.client;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.ModelView;
import it.polimi.ingsw.model.Worker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class guiTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                ClientBackEnd model=new ClientBackEnd(null);


                model.players=new ArrayList<>();
                model.players.add("mar");
                model.players.add("car");
                model.players.add("tar");
                HashMap<String,String>tmp=new HashMap<>();
                tmp.put("mar","minotaur");
                tmp.put("tar","zeus");
                tmp.put("car","ares");
                model.playersGods=tmp;
                model.playersColor.put("mar", Color.PINK);
                model.playersColor.put("car", Color.BLUE);
                model.playersColor.put("tar", Color.GRAY);
                model.getGui().playersPanel(model);
                model.getGui().createGrid(model);



            Model model2=new Model();
            model2.getGrid().getTile(0,0).levelUp().levelUp();
            model2.getGrid().getTile(0,1).levelUp();
                model2.getGrid().getTile(4,1).levelUp().levelUp().levelUp();
                model2.getGrid().getTile(4,3).levelUp().levelUp().levelUp().levelUp();
            ModelView message=model2.updateState();
            model.update(message);


            }
        });
    }
}
