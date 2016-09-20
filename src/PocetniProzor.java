import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.event.*;

public class PocetniProzor {
	JFrame prozor;
	PanelZaIzbor panelZaIzbor;
	PanelZaPozadinu panelZaPozadinu;
	
	public PocetniProzor(){
		prozor = new JFrame("Slagalica2012 by Zayim4D");
		prozor.setResizable(false);
		panelZaPozadinu = new PanelZaPozadinu();
		panelZaIzbor = new PanelZaIzbor();
		prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prozor.getContentPane().add(panelZaPozadinu);
		prozor.getContentPane().add(panelZaIzbor,BorderLayout.SOUTH);
		prozor.setSize(800, 550);
		prozor.setVisible(true);
		
	}
	
	public void kreni(){
		
	}
}

class PanelZaPozadinu extends JPanel{
	BufferedImage pozadina;
	static final long serialVersionUID = 100;
	
	public PanelZaPozadinu(){
		try {
			pozadina = ImageIO.read(new File("files/bg_01.png"));
			setPreferredSize(new Dimension(800,500));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Pozadina nije pronaðena!");
		}
	}
	
	public void paint (Graphics g){
		g.drawImage(pozadina,0,0,800,500,null);
	}
}

class PanelZaIzbor extends JPanel{
	JButton size3,size4,size5;
	FrameZaIgru frameZaIgru;
	static final long serialVersionUID = 200;
	
	public PanelZaIzbor(){
		setBackground(Color.getHSBColor(0.64f,0.87f,0.12f));
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0.1;
		setLayout(new GridBagLayout());
		Font font = new Font("Arial",Font.BOLD|Font.ITALIC,15);
		DugmeListener dugmeListener = new DugmeListener();
		
		size3=new JButton("3 x 3"); size3.setFont(font); size3.addActionListener(dugmeListener);
		size3.setBackground(Color.getHSBColor(0.49f,0.95f,0.95f)); size3.setForeground(Color.getHSBColor(0.64f,0.87f,0.12f));
		size4=new JButton("4 x 4"); size4.setFont(font); size4.addActionListener(dugmeListener);
		size4.setBackground(Color.getHSBColor(0.49f,0.95f,0.95f)); size4.setForeground(Color.getHSBColor(0.64f,0.87f,0.12f));
		size5=new JButton("5 x 5"); size5.setFont(font); size5.addActionListener(dugmeListener);
		size5.setBackground(Color.getHSBColor(0.49f,0.95f,0.95f)); size5.setForeground(Color.getHSBColor(0.64f,0.87f,0.12f));
				
		add(size3,c); add(size4,c); add(size5,c);
		setPreferredSize(new Dimension(800,50));
	}
	
	class DugmeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int level=1;
			boolean lagana=true;
			JButton dugme=(JButton)(e.getSource());
			if (size3.equals(dugme)) level=1;
			else if (size4.equals(dugme)) level=2;
			else level=3;
			
			frameZaIgru = new FrameZaIgru(level,lagana);
		}
	}
}