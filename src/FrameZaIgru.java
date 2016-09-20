import javax.swing.*;

import java.awt.Color;
import  java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import java.awt.event.*;

public class FrameZaIgru extends JComponent{
	JFrame frame;
	PanelZaSlike panelZaSlike;
	static final long serialVersionUID = 400;
	Matrica matrica;
	long vrijemePocetka, vrijemeKraja;
	double vrijemeIgre;
	
	public FrameZaIgru(int level, boolean lagana){
		matrica=new Matrica(level,lagana);
		frame = new JFrame ("by zayim4D");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panelZaSlike=new PanelZaSlike(level,lagana);
		TipkaListener tipkaListener = new TipkaListener();
		frame.addKeyListener(tipkaListener);
		frame.requestFocus();
		frame.getContentPane().add(panelZaSlike);
		frame.repaint();
		
		frame.setSize(665, 687);
		frame.setVisible(true);
		vrijemePocetka=System.nanoTime();
	}
	
	class PanelZaSlike extends JPanel{
		static final long serialVersionUID = 300;
		int stranica,dimenzijaSlike;
		
		BufferedImage[] slike;
		
		public PanelZaSlike(int level, boolean lagana){
			
			stranica=matrica.vratiStranicu();
			slike=new BufferedImage[stranica*stranica];
			dimenzijaSlike=(int)(660/stranica);
			
			String putanja="files/nums/";
			try{
				for (int i=0; i<stranica*stranica; i++){
					slike[i] = ImageIO.read(new File(putanja + Integer.toString(i) + ".png"));
				}
			}
			catch(IOException e){
				JOptionPane.showMessageDialog(null, "Nisu prona�eni elementi!");
			}
			
		}
		
		public void paint (Graphics g){
			for (int i=0; i<stranica; i++){
				for (int j=0; j<stranica; j++){
					g.drawImage(slike[matrica.element(i, j)], j*dimenzijaSlike, i*dimenzijaSlike, dimenzijaSlike, dimenzijaSlike, null);
				}
			}
			
		}
		
		public void zavrsiIgru(){
			JFrame frameRezultat = new JFrame ("Kraj! Bravo za Vas :)");
			frameRezultat.setLayout(new GridBagLayout());
			Font font = new Font("Arial",Font.BOLD,22);
						
			JTextArea textRezultat = new JTextArea("VRIJEME: " + dvijeDecimale(vrijemeIgre) + " s\n");
			textRezultat.append("BROJ POTEZA: " + matrica.vratiBrojPoteza() + "\n");
			textRezultat.append("REZULTAT: " + dvijeDecimale(2*vrijemeIgre + 0.3*matrica.vratiBrojPoteza()));
			textRezultat.setEditable(false);
			textRezultat.setFont(font);
			textRezultat.setBackground(Color.getHSBColor(0.64f,0.87f,0.12f));
			textRezultat.setForeground(Color.getHSBColor(0.64f,0.87f,0.89f));
			
			frameRezultat.getContentPane().add(textRezultat);
			frameRezultat.getContentPane().setBackground(Color.getHSBColor(0.64f,0.87f,0.12f));
			frameRezultat.setSize(400,200);
			frameRezultat.setVisible(true);
		}
		
	}
	
	public class TipkaListener implements KeyListener{
		
		public void keyPressed(KeyEvent e1){
			int kodTipke = e1.getKeyCode();
						
			switch(kodTipke){
			case 37: // lijevo
				matrica.pomjeriLijevo();
				break;
			case 38: // gore
				matrica.pomjeriGore();
				break;
			case 39: // desno
				matrica.pomjeriDesno();
				break;
			case 40: // dole
				matrica.pomjeriDole();
				break;
			case 76: // cheat
				matrica.pokreniCheat();
				vrijemeKraja=System.nanoTime();
				vrijemeIgre=dvijeDecimale((vrijemeKraja-vrijemePocetka)/1000000000.0);
				frame.repaint();
				panelZaSlike.zavrsiIgru();
				frame.removeKeyListener(this);
				return;
			}
			
			frame.repaint();
			if (matrica.daLiJeIgraGotova()){
				vrijemeKraja=System.nanoTime();
				vrijemeIgre=dvijeDecimale((vrijemeKraja-vrijemePocetka)/1000000000.0);
				panelZaSlike.zavrsiIgru();
				frame.removeKeyListener(this);
			}
			
		}
		public void keyTyped(KeyEvent e1){
		}
		public void keyReleased(KeyEvent e1){
		}
	}
	double dvijeDecimale(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
	}
}




