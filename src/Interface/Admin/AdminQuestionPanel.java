package Interface.Admin;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Components.Factory;
import Interface.GUI;
import v1.Player;
import v1.Question;

/**
 * Fen�tre administrateur affichant toutes les questions
 * @author Guillaume
 */
public class AdminQuestionPanel {
	// VARS //
	private GUI myGui;
	private JFrame frame;
	
	/**
	 * Constructeur de la classe AdminEditUser
	 * @param myGui - GUI � utiliser
	 * @param frame - Frame � utiliser
	 */
	public AdminQuestionPanel(GUI myGui, JFrame frame) {
		this.myGui = myGui;
		this.frame = frame;
	}
	
	/**
	 * Permet l'affichage de la fen�tre
	 * @param index - Num�ro de la page � afficher
	 */
	public void repaint(int index) {
		if(GUI.idSession != 0) { 
			Player selected = myGui.getPlayer(GUI.idSession);
			if(selected.isAdmin()) { // Si le joueur est un administrateur
				// On r�cup�re le panel principal
				Container panel = frame.getContentPane();
				panel.removeAll();
				panel.revalidate();
				panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
				panel.add(Box.createRigidArea(new Dimension(1024,20)));
			
				// TITRE //
				JPanel titrePanel = Factory.addPanel();
				
				JLabel titre = Factory.addLabel("Liste des questions", 21, true);
				titrePanel.add(titre);
				panel.add(titrePanel);
				
				panel.add(Box.createRigidArea(new Dimension(500, 20)));
				
				// BOUTON : AJOUTER QUESTION //
				JPanel addPanel = Factory.addPanel();
				JButton addButton = Factory.addButton("Ajouter une question", 200, 30);
				addButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new AdminAddQuestion(myGui, frame).repaint();
					}
				});
				addPanel.add(addButton);
				panel.add(addPanel);
				
				panel.add(Factory.addSpace(10));
				
				// LISTE QUESTIONS //
				Question choosen = null;
				ArrayList<Question> liste = myGui.getListeQuestions();
				int size = liste.size();
				JPanel[] userPanels = new JPanel[size];
				JLabel[] labelFields = new JLabel[size];
				JButton[] buttonFields = new JButton[size];
				for(int i = 0; i < 10; i++) {
					if((i+((index-1)*10)) == size){
						break;
					}
					
					choosen = liste.get((i+((index-1)*10)));
					
					userPanels[((i+((index-1)*10)))] = Factory.addPanel();
					labelFields[(i+((index-1)*10))] = Factory.addLabel(choosen.getTitre(), 16, false);
					labelFields[(i+((index-1)*10))].setMaximumSize(new Dimension(150, 40));
					buttonFields[(i+((index-1)*10))] = Factory.addButton("Modifier", 100, 25);
					
					final Question questionSel = choosen;
					buttonFields[(i+((index-1)*10))].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							new AdminEditQuestion(myGui, frame).repaint(questionSel.getID());
						}
					});
					
					userPanels[(i+((index-1)*10))].add(labelFields[(i+((index-1)*10))]);
					userPanels[(i+((index-1)*10))].add(Box.createRigidArea(new Dimension(20, 20)));
					userPanels[(i+((index-1)*10))].add(buttonFields[(i+((index-1)*10))]);
					
					panel.add(userPanels[(i+((index-1)*10))]);
					panel.add(Box.createRigidArea(new Dimension(20, 5)));
				}
				
				// BOUTONS : NAVIGATION //
				int nbPages = 1;
				if(size > 10) {
					nbPages = (int) Math.floor(size/10);
				}
				if(nbPages*10 < size) {
					nbPages++;
				}
				
				JPanel navPanel = Factory.addPanel();
				if(index > 1) {
					JButton precButton = new JButton("<-");
					precButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							new AdminQuestionPanel(myGui, frame).repaint(index-1);
						}
						
					});
					navPanel.add(precButton);
				}
				navPanel.add(Box.createRigidArea(new Dimension(20, 20)));
				JLabel numPage = Factory.addLabel("Page: " + index + "/" + nbPages, 15, true);
				navPanel.add(numPage);
				navPanel.add(Box.createRigidArea(new Dimension(20, 20)));
				if(index < nbPages) {
					JButton nextButton = new JButton("->");
					nextButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							new AdminQuestionPanel(myGui, frame).repaint(index+1);
						}
						
					});
					navPanel.add(nextButton);
				}
				panel.add(Box.createVerticalGlue());
				panel.add(navPanel);
				
				// BOUTON : RETOUR //
				panel.add(Box.createVerticalGlue());
				JPanel retourPanel = Factory.addPanel();
				JButton back = Factory.addButton("Retour", 100, 40);
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel.removeAll();
						panel.revalidate();
						panel.repaint();
						new AdminPanel(myGui, frame).repaint();
					}
				});
				retourPanel.add(back);
				retourPanel.add(Box.createHorizontalGlue());
				panel.add(retourPanel);
				
				panel.repaint();
			}
		}
	}
}
