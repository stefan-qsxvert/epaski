package epaski.app;

import javax.swing.*;

import epaski.gui.Gui;
import epaski.send.Push;

public class EPSwingWorker extends SwingWorker<Gui, Integer> {
	
	Gui gui;
	Integer task;

	public EPSwingWorker(Gui gui, Integer task) {
		this.gui = gui;
		this.task = task;
	}

    @Override
    protected Gui doInBackground()  {

    	switch (task) {
    	case 1:
    		//split pdf
    		try {
			Ext ext = new Ext(gui);
			ext.cleanFiles();
    		PdfSplit split = new PdfSplit(gui);
    		split.pdfSplit();
    		PdfRename rename = new PdfRename(gui);
    		rename.pdfRename();
    		done();
    		}catch(Exception e) {
    			
    		}
    		gui.setSplitCtrl(true);
    		break;
    	case 2:
    		//zipp
    		try {
    		GenZipp zipp = new GenZipp(gui);
    		//zipp.setGui(gui);
    		zipp.genZipp();
    		done();
    		}catch (Exception e) {
			}
    		gui.setZippCtrl(true);
    		break;
    	case 3:
    		//send
    		try {
    		Push push = new Push(gui);
       	 	push.sendMail();
       	 	gui.setCheckBoxSelect(2);
       	 	done();
    		}catch(Exception e) {
    		}
       	 	gui.setSendCtrl(true);
       		break;
    	}
    	return null;
    	}
    }
