package epaski.gui;

import static javax.swing.SwingConstants.CENTER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewDialog implements ActionListener{
	
    private JDialog dialog;
	
	public void msg(String msg) {
	dialog = new JDialog();
    JLabel label1 = new JLabel(msg);
    JButton button1 = new JButton("OK!");
    JPanel panel1 = new JPanel();
    dialog.setLayout(null);
    panel1.setLayout(null);
    panel1.setBounds(2,2,252,188);
    button1.setBounds(92,64,64,22);
    label1.setBounds(4,4,232,56);
    label1.setHorizontalAlignment(CENTER);
    label1.setVerticalTextPosition(CENTER);
    panel1.add(label1);
    panel1.add(button1);
    dialog.add(panel1);
    dialog.setBounds(496,496,256,128);
    button1.addActionListener(this);
    dialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
    if (e.getActionCommand().equals("OK!")){
        dialog.dispose();
    }
		
	}
	

}
