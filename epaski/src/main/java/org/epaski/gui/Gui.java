package org.epaski.gui;

import org.epaski.app.EPSwingWorker;
import org.epaski.app.Ext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class Gui implements ActionListener {
	
	private Boolean splitCtrl = true, zippCtrl = true, sendCtrl = true;
		
	private Gui gui;
    public Gui(){
    }
    public Gui(Gui gui){
        this.gui = gui;
    }
	
	public void setGui(Gui gui) {
		this.gui = gui;
	}
		
    private JButton[] button = new JButton[7];
    private JTextField[] textField = new JTextField[10];
    private JPasswordField[] passwordField = new JPasswordField[2];
    private JPanel[] panel = new JPanel[4];
    private JCheckBox[] checkBox = new JCheckBox[9];
    private JLabel[] label = new JLabel[10];
    public JProgressBar[] progressBar = new JProgressBar[3];
    
    public Boolean getCheckBoxSelected(int i){
        return checkBox[i].isSelected();
    }
    public String getPasswordFildText(int i){
        return String.valueOf(passwordField[i].getPassword());
    }
    public void setCheckBoxSelect(int i){
        checkBox[i].setSelected(true);
    }
    public String getTextFieldText(int i) {
    	return textField[i].getText();
    }
    public void setLabelText(int i, String txt) {
    	label[i].setText(txt);
    }
    
    
    public void okno(){
    	
    	panel(2,2,380, 172, 0, "mail");
        panel(2,180,380, 172, 1, "ścieżki");
        panel(384,2,380, 172, 2, "proxy");
        panel(384,180,380, 172, 3, "inne");
        
        addProgressBar( 24, 80, 112, 18, 0);
        addProgressBar( 140, 80, 112, 18, 1);
        addProgressBar( 256, 80, 112, 18, 2);
        
        addButton(180, 24, 112, 24, "wybierz nfo", 2);
        addButton(180, 52, 112, 24, "wybierz pliki", 3);
        addButton(180, 80, 112, 24, "wybierz paski", 5);
        addButton(24, 108, 112, 24, "podziel pdf", 4);
        addButton(140, 108, 112, 24, "zippuj", 0);
        addButton(256, 108, 112, 24, "wyślij", 1);
        
        addPasswordField(8, 136, 176, 24, "hasloMail", 0);
        addPasswordField(8, 108, 176, 24, "proxy hasło", 1);
        
        addTextField(8, 24, 176, 24, "", "mail host:port", 2);
        addTextField(8, 108, 176, 24, "", "login", 3);
        addTextField(8, 52, 176, 24, "", "temat", 4);
        addTextField(8, 80, 176, 24, "", "od", 5);
        
        addTextField(8, 24, 176, 24, "c:\\epaski\\nfo\\", "sciezka", 0);
        addTextField(8, 52, 176, 24, "c:\\epaski\\pliki\\", "sciezka", 1);
        addTextField(8, 80, 176, 24, "c:\\epaski\\paski\\", "paski", 9);

        addTextField(8, 24, 176, 24, "", "proxy host", 6);
        addTextField(8, 52, 176, 24, "", "proxy port", 7);
        addTextField(8, 80, 176, 24, "", "proxy user", 8);
        
        addCheckBox(296, 24, 112, 24, "TLS", 6,0);
        addCheckBox(296, 52, 112, 24, "SSL", 7,0);
        addCheckBox(296, 80, 112, 24, "PLAIN", 8,0);
        addCheckBox(24, 48, 112, 24, "debuguj wysyłkę", 0,3);
        addCheckBox(140, 48, 112, 24, "wyślij przez proxy", 1,3);
        addCheckBox(256, 48, 112, 24, "test wysyłki", 2,3);
        addCheckBox(24, 20, 112, 24, "nowa wysyłka", 3,3);
	    addCheckBox(140, 20, 112, 24, "pełny raport", 4,3);
	    addCheckBox(256, 20, 112, 24, "tryb cichy", 5,3);
	    
	    
        setCheckBoxSelect(2);
        setCheckBoxSelect(3);
        setCheckBoxSelect(6);
        
        addLabel( 208, 24, 256, 24, "smtp host:port", 0, 0);
        addLabel( 208, 108, 48, 24, "login", 1,0);
        addLabel( 208, 52, 48, 24, "temat", 2,0);
        addLabel( 208, 132, 48, 24, "hasło", 3,0);
        addLabel( 208, 80, 48, 24, "od", 4,0);
        addLabel( 208, 80, 48, 24, "login", 6,2);
        addLabel( 208, 52, 48, 24, "port", 7,2);
        addLabel( 208, 108, 48, 24, "hasło", 8,2);
        addLabel( 208, 24, 48, 24, "host", 9,2);
        
    	JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setBounds( 168,168,816,392);
        frame.setDefaultCloseOperation(3);
        frame.setTitle("epaski 1.0");
        
        frame.setVisible(true);
        frame.setResizable(false);
          
        panel[3].add(progressBar[0]);
        panel[3].add(progressBar[1]);
        panel[3].add(progressBar[2]);
        
        panel[1].add(button[2]);
        panel[1].add(button[3]);
        panel[1].add(button[5]);
        panel[3].add(button[4]);
        panel[3].add(button[0]);
        panel[3].add(button[1]);
        
        panel[0].add(passwordField[0]);
        panel[2].add(passwordField[1]);
        panel[0].add(textField[2]);
        panel[0].add(textField[3]);
        panel[0].add(textField[4]);
        panel[0].add(textField[5]);
        
        panel[1].add(textField[0]);
        panel[1].add(textField[1]);
        panel[1].add(textField[9]);
        
        panel[2].add(textField[6]);
        panel[2].add(textField[7]);
        panel[2].add(textField[8]);
        
        panel[3].add(checkBox[0]);
        panel[3].add(checkBox[1]);
        panel[3].add(checkBox[2]);
        panel[3].add(checkBox[3]);
        panel[3].add(checkBox[4]);
        panel[3].add(checkBox[5]);
        panel[0].add(checkBox[6]);
        panel[0].add(checkBox[7]);
        panel[0].add(checkBox[8]);
        
        panel[0].add(label[0]);
        panel[0].add(label[1]);
        panel[0].add(label[2]);
        panel[0].add(label[3]);
        panel[0].add(label[4]);
        
        panel[2].add(label[6]);
        panel[2].add(label[7]);
        panel[2].add(label[8]);
        panel[2].add(label[9]);

        frame.add(panel[0]);
        frame.add(panel[1]);
        frame.add(panel[2]);
        frame.add(panel[3]);
        
        //addButton(24, 108, 112, 24, "testy", 6);
        frame.setVisible(true);
        
        importSettings();
        progrress();
        
 	    }
    
    public void progrress() {
    	
    	for (int i = 0; i < 100; i++) {
    		
        	progressBar[0].setValue(i+1);
        	progressBar[1].setValue(i+1);
        	progressBar[2].setValue(i+1);
        	
        	try {
        	Thread.sleep(5);
        	}catch (Exception e) {
        		System.out.println(e.toString());
			}
        }
    	 	
    	for (int i = 0; i < 100; i++) {
    		
        	progressBar[0].setValue(100-i-1);
        	progressBar[1].setValue(100-i-1);
        	progressBar[2].setValue(100-i-1);
        	
        	try {
        	Thread.sleep(5);
        	}catch (Exception e) {
        		System.out.println(e.toString());
			}
        }
    }
    
    public void panel(int x, int y, int w, int h, int i, String title) {
        panel[i] = new JPanel();
        panel[i].setLayout(null);
        panel[i].setBounds(x,y,w,h);
        panel[i].setBorder(BorderFactory.createTitledBorder(title));
        }

    public void addButton(int x, int y, int w, int h, String txt, int i) {
        button[i] = new JButton(txt);
        button[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
        button[i].setBounds(x,y,w,h);
        button[i].setBackground(null);
        button[i].addActionListener(this);
    }
    
    public void addTextField(int x, int y, int w, int h, String sciezka, String txt, int i){
        textField[i] = new JTextField(txt);
        textField[i].setBounds(x,y,w,h);
        textField[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
        textField[i].setText(sciezka);
    }
    
    public void addPasswordField(int x, int y, int w, int h, String sciezka, int i){
        passwordField[i] = new JPasswordField(null);
        passwordField[i].setBounds(x,y,w,h);
        passwordField[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
    }
    
    public void addCheckBox(int x, int y, int w, int h,String txt,int i, int panelId){
    	checkBox[i] = new JCheckBox(txt);
        checkBox[i].setBounds(x,y,w,h);
        checkBox[i].addActionListener(this);
        checkBox[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
    }
    
    public void addLabel(int x, int y, int w, int h, String txt, int i, int panelId) {
        label[i] = new JLabel(txt);
        label[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
        label[i].setBounds(x,y,w,h);
        label[i].setBackground(null);
    }
    public void addProgressBar(int x, int y, int w, int h, int i) {
    	progressBar[i] = new JProgressBar(0,100);
    	progressBar[i].setBounds(x, y, w, h);
     	progressBar[i].setStringPainted(true);
     	progressBar[i].setBorderPainted(true);
     	progressBar[i].setVisible(true);
     	progressBar[i].paintImmediately(x, y, w, h);
	}
    public void dialog(String msg){
    	NewDialog dialog = new NewDialog();
    	dialog.msg(msg);		
    }
    
    public void exportSettings() {
    	
    	String path = "C:/epaski/config/config.cfg";
    	String[][] cont = new String[2][10];
    	
    	try {
    		BufferedWriter fileWriter = new BufferedWriter(new FileWriter( path, false));
    		
    		cont[0][0] = "sciezka_nfo=";
    		cont[0][1] = "sciezka_pliki=";
    		cont[0][2] = "smtp=";
    		cont[0][3] = "login=";
    		cont[0][4] = "temat=";
    		cont[0][5] = "od=";
    		cont[0][6] = "proxy_host=";
    		cont[0][7] = "proxy_port=";
    		cont[0][8] = "proxy_login=";
    		cont[0][9] = "sciezka_paski=";
    		
    		cont[1][0] = getTextFieldText(0) + System.lineSeparator();
    		cont[1][1] = getTextFieldText(1) + System.lineSeparator();
    		cont[1][2] = getTextFieldText(2) + System.lineSeparator();
    		cont[1][3] = getTextFieldText(3) + System.lineSeparator();
    		cont[1][4] = getTextFieldText(4) + System.lineSeparator();
    		cont[1][5] = getTextFieldText(5) + System.lineSeparator();
    		cont[1][6] = getTextFieldText(6) + System.lineSeparator();
    		cont[1][7] = getTextFieldText(7) + System.lineSeparator();
    		cont[1][8] = getTextFieldText(8) + System.lineSeparator();
    		cont[1][9] = getTextFieldText(9) + System.lineSeparator();
    		
    		for (int i = 0; i < textField.length; i++) {
    		fileWriter.write(cont[0][i] + cont[1][i]);
    		}
    		fileWriter.close();
    	}catch(Exception e) {
    		System.out.println(e.toString());
    	}
    }
    
    
    public void importSettings() {
 
    	String path = "C:/epaski/config/config.cfg";
    	try {
    	BufferedReader fileReader = new BufferedReader(new FileReader(path));
    	String red = null;
    	int i = 0;
    	while ((red = fileReader.readLine()) != null ) {
    		String[] val = red.split("=");
    		textField[i++].setText(val[1]);
    	}
    	fileReader.close();
    	}catch(Exception e) {
    	}
    }
    
    public void actionPerformed(ActionEvent a){
        	
        if (a.getSource() == button[3]) {
            Ext ext = new Ext();
            textField[1].setText(ext.fileChooser("c:\\epaski\\pliki\\"));
        }

        if (a.getSource() == button[2]) {
            Ext ext = new Ext();
            textField[0].setText(ext.fileChooser("c:\\epaski\\nfo\\"));
        }
        if (a.getSource() == button[5]) {
            Ext ext = new Ext();
            textField[9].setText(ext.fileChooser("c:\\epaski\\paski\\"));
        }

        if (a.getSource() == button[0]) {
        	//genzipp
        	gui.exportSettings();
        	if (zippCtrl) {
        		zippCtrl = false;
            Ext ext = new Ext();
            ext.logBcp("logNfo.txt");
            ext.logBcp("logZipp.txt");
            ext.logBcp("logErr.txt");
            
            EPSwingWorker swingWorker = new EPSwingWorker(gui, 2);
            swingWorker.execute();
        	}else {}
    }

        
        if (a.getSource() == button[1]) {
        		//send
        		gui.exportSettings();
        		if (sendCtrl) {
        			sendCtrl = false;
                    Ext ext = new Ext();
                    ext.logBcp("logNfo.txt");
                    ext.logBcp("logErr.txt");
                    ext.logBcp("logSent.txt");
        		
        		EPSwingWorker swingWorker = new EPSwingWorker(gui, 3);
                swingWorker.execute();
        		}else {}
        		
        }

        if (a.getSource() == button[4]) {
        	//split pdf
        	gui.exportSettings();
        	if (splitCtrl) {
        		splitCtrl = false;
                Ext ext = new Ext();
                ext.logBcp("logSplit.txt");
                ext.logBcp("logErr.txt");

        	EPSwingWorker swingWorker = new EPSwingWorker(gui,1);
        	swingWorker.execute();
        	}else {}
        }
  
       if ( a.getSource() == checkBox[5]) {
        	checkBox[4].setSelected(!checkBox[5].isSelected());
        	checkBox[0].setSelected(!checkBox[5].isSelected());
        }
       if ( a.getSource() == checkBox[6]) {
       		checkBox[7].setSelected(!checkBox[6].isSelected());
       		checkBox[8].setSelected(!checkBox[6].isSelected());
       }
       if ( a.getSource() == checkBox[7]) {
          	checkBox[6].setSelected(!checkBox[7].isSelected());
          	checkBox[8].setSelected(!checkBox[7].isSelected());
       }
       if ( a.getSource() == checkBox[8]) {
          	checkBox[7].setSelected(!checkBox[8].isSelected());
          	checkBox[6].setSelected(!checkBox[8].isSelected());
       }
       
    	
    	if (a.getActionCommand().equals( "testy")) {

    	}
       }

	public Boolean getZippCtrl() {
		return zippCtrl;
	}
	public void setZippCtrl(Boolean zippCtrl) {
		this.zippCtrl = zippCtrl;
	}
	public Boolean getSplitCtrl() {
		return splitCtrl;
	}
	public void setSplitCtrl(Boolean splitCtrl) {
		this.splitCtrl = splitCtrl;
	}
	public Boolean getSendCtrl() {
		return sendCtrl;
	}
	public void setSendCtrl(Boolean sendCtrl) {
		this.sendCtrl = sendCtrl;
	}
    
    }
   
