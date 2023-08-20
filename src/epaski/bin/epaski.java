package epaski.bin;

import java.io.File;

import epaski.gui.Gui;
import epaski.send.Push;

public class epaski {
	
	public Gui gui = new Gui();
    //Push push = new Push(gui);
	
	public static void main(String[] args) {
		epaski epaski = new epaski();
		epaski.startGui();
		}

    public void startGui(){ //start aplikacji

        File[] folder = new File[7];
         folder[0] = new File("c:/epaski");
         folder[1] = new File("c:/epaski/msg");
         folder[2] = new File("c:/epaski/config");
         folder[3] = new File("c:/epaski/pliki");
         folder[4] = new File("c:/epaski/nfo");
         folder[5] = new File("c:/epaski/log");
         folder[6] = new File("c:/epaski/paski");

        for (int i = 0; i <folder.length; i++)
            if (!folder[i].exists()){
             folder[i].mkdir();
            }
         
         gui.okno();
         gui.setGui(gui);
         


   }

}