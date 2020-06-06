package Interface;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Components.Factory;
import v1.Defi;
import v1.Player;

public class Menu {
	private GUI myGui;
	private JFrame frame;
	
	public Menu(GUI myGui, JFrame frame) {
		this.myGui = myGui;
		this.frame = frame;
	}
	
	void repaint() {
		if(GUI.idSession != 0) {
			Player selected = myGui.getPlayer(GUI.idSession);
			
			Container panel = frame.getContentPane();
			panel.removeAll();
			panel.revalidate();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.add(Factory.addSpace(50));
			
			// TITRE //
			JPanel titrePanel = Factory.addPanel();
			JLabel titre = Factory.addLabel("Serious-Game", 21, true);
			titrePanel.add(titre);
			panel.add(titrePanel);
			
			panel.add(Factory.addSpace(20));
			
			// USERNAME //
			JPanel userPanel = Factory.addPanel();
			JLabel user = Factory.addLabel("Utilisateur: " + selected.getUsername(), 16, false);
			userPanel.add(user);
			panel.add(userPanel);
			
			// BOUTON : PROFIL //
			JPanel profilPanel = Factory.addPanel();
			JButton profilButton = Factory.addButton("Mon profil", 150, 30);
			profilButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new Profil(myGui, frame).repaint();
				}
			});
			profilPanel.add(profilButton);
			panel.add(profilPanel);

			// BOUTON : ADMIN PANEL //
			if(selected.isAdmin()) {
				JPanel adminPanel = Factory.addPanel();
				JButton adminButton = Factory.addButton("Administration", 150, 30);
				adminButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						new Administration(myGui, frame).repaint();
					}
					
				});
				adminPanel.add(adminButton);
				panel.add(adminPanel);
			}
			
			panel.add(Factory.addSpace(30));
			
			// NOMBRE DE DEFI //
			JPanel nbDefiPanel = Factory.addPanel();
			
			int nbrDefi = 0;
			LocalDateTime dateFin = null;
			for(Defi d : myGui.getListeDefis()) {
				if(d.getDestinataire().getID() == GUI.idSession && !d.isAccepte() && d.isReviewed()) {
					nbrDefi++;
					if(dateFin == null) {
						dateFin = d.getDateExpiration();
					} else if(d.getDateExpiration().isBefore(dateFin)) {
						dateFin = d.getDateExpiration();
					}
				}
			}
			
			JLabel nbDefi = Factory.addLabel("Vous avez " + nbrDefi + " d�fi(s) en attente.", 16, false);
			nbDefiPanel.add(nbDefi);
			panel.add(nbDefiPanel);
			
			// DATE EXPIRATION //
			if(dateFin != null) {
				JPanel datePanel = Factory.addPanel();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy � HH:mm");
				String formattedString = dateFin.format(formatter);
				
				JLabel dateExp = Factory.addLabel("Attention: Un d�fi se termine le " + formattedString, 15, true);
				datePanel.add(dateExp);
				panel.add(datePanel);
			}
			
			// BOUTONS : LANCER UN DEFI && ACCEPTER DEFI //
			JPanel buttonsPanel = Factory.addPanel();
			JButton lancerDefi = Factory.addButton("Envoyer un d�fi", 150, 50);
			lancerDefi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					(new LancerDefi(myGui, frame)).repaint();
				}
			});
			buttonsPanel.add(lancerDefi);
			
			JButton accDefi = Factory.addButton("Accepter un d�fi", 150, 50);
			accDefi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new AccepterDefi(myGui, frame).repaint(1);
				}
			});
			buttonsPanel.add(accDefi);
			panel.add(buttonsPanel);
			
			panel.add(Factory.addSpace(30));
			
			// BOUTON : SE DECONNECTER //
			JPanel decoPanel = Factory.addPanel();
			
			JButton decoButton = Factory.addButton("Se d�connecter", 150, 50);
			decoButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					GUI.idSession = 0;
					panel.removeAll();
					panel.revalidate();
					new Start(myGui, frame).repaint();
				}
			});
			decoPanel.add(decoButton);
			panel.add(decoPanel);
			
			panel.add(Factory.addSpace(30));
			
			// BOUTON : QUITER //
			JPanel quitPanel = Factory.addPanel();
			JButton quitButton = Factory.addButton("Quitter", 150, 50);
			quitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));		
				}
			});
			quitPanel.add(quitButton);
			panel.add(quitPanel);
			
			panel.repaint();
		}
	}
	
}
