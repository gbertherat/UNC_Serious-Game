package Components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * Permet la cr�� de composants JSwing
 * @author Guillaume
 */
public class Factory {
	
	/**
	 * Permet d'ajouter un JPanel
	 * @return un JPanel
	 */
	public static JPanel addPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setOpaque(false);
		return panel;
	}
	
	/**
	 * Permet d'ajouter un JLabel
	 * @param text - Le texte � afficher
	 * @param size - La taille du texte
	 * @param bold - Si le texte est en gras
	 * @return un JLabel
	 */
	public static JLabel addLabel(String text, int size, boolean bold) {
		JLabel label = new JLabel(text);
		if(bold) {
			label.setFont(new Font("Arial", Font.BOLD, size));
		} else {
			label.setFont(new Font("Arial", Font.PLAIN, size));
		}
		return label;
	}
	
	/**
	 * Permet d'ajouter un JButton
	 * @param text - Le texte � afficher sur le bouton
	 * @param width - La largeur du bouton
	 * @param height - La hauteur du bouton
	 * @return un JButton
	 */
	public static JButton addButton(String text, int width, int height) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.PLAIN, 15));
		Dimension dimension = new Dimension(width, height);
		button.setMinimumSize(dimension);
		button.setMaximumSize(dimension);
		button.setPreferredSize(dimension);
		return button;
	}
	
	/**
	 * Permet d'ajouter un JTextField
	 * @param width - La largeur du field
	 * @param height - La hauteur du field
	 * @param editable - Si le JTextField peut �tre modifi�
	 * @return un JTextField
	 */
	public static JTextField addField(int width, int height, boolean editable) {
		JTextField field = new JTextField();
		Dimension dimension = new Dimension(width, height);
		field.setMaximumSize(dimension);
		field.setMinimumSize(dimension);
		field.setPreferredSize(dimension);
		field.setEditable(editable);
		field.setFont(new Font("Arial", Font.PLAIN, 16));
		return field;
	}
	
	/**
	 * Permet d'ajouter un JTextArea
	 * @param width - La largeur de l'area
	 * @param height - La hauteur de l'area
	 * @param editable - Si l'area peut �tre modifi�
	 * @return un JTextArea
	 */
	public static JTextArea addTextArea(int width, int height, boolean editable) {
		JTextArea area = new JTextArea(4, 30);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setFont(new Font("Arial", Font.PLAIN, 15));
		area.setEditable(editable);
		
		Dimension dimension = new Dimension(width, height);
		area.setMinimumSize(dimension);
		area.setMaximumSize(dimension);
		area.setPreferredSize(dimension);
		
		return area;
	}
	
	/**
	 * Permet d'ajouter un JPasswordField
	 * @param width - La largeur du field 
	 * @param height - La hauteur du field
	 * @return un JPasswordField
	 */
	public static JPasswordField addPassField(int width, int height) {
		JPasswordField field = new JPasswordField();
		Dimension dimension = new Dimension(width, height);
		field.setMaximumSize(dimension);
		field.setMinimumSize(dimension);
		field.setPreferredSize(dimension);
		return field;
	}
	
	/**
	 * Permet d'ajouter un JComboBox
	 * @param list - La liste des options
	 * @param width - La largeur de la box
	 * @param height - La hauteur de la box
	 * @return un JComboBox
	 */
	public static JComboBox<String> addBox(String[] list, int width, int height){
		JComboBox<String> box = new JComboBox<String>(list);
		try {
			box.setSelectedIndex(0);
		} catch(IllegalArgumentException e) {
			;
		}
		
		Dimension dimension = new Dimension(width, height);
		box.setMinimumSize(dimension);
		box.setMaximumSize(dimension);
		box.setPreferredSize(dimension);
		box.setFont(new Font("Arial", Font.PLAIN, 16));
		return box;
	}
	
	/**
	 * Permet d'ajouter un JSlider 
	 * @param width - La largeur du JSlider
	 * @param height - La hauteur du JSlider
	 * @param min - La valeur minimale
	 * @param max - La valeur maximale
	 * @param current - La valeur selectionn�e
	 * @return un JSlider
	 */
	public static JSlider addSlider(int width, int height, int min, int max, int current) {
		JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, current);
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		Dimension dimension = new Dimension(width, height);
		slider.setMinimumSize(dimension);
		slider.setMaximumSize(dimension);
		slider.setPreferredSize(dimension);
		return slider;
	}
	
	/**
	 * Permet d'ajouter un JSpinner
	 * @param width - La largeur du JSpinner
	 * @param height - La hauteur du JSpinner
	 * @param min - La valeur minimale
	 * @param max - La valeur maximale
	 * @param current - La valeur selectionn�e
	 * @return un JSpinner
	 */
	public static JSpinner addSpiner(int width, int height, int min, int max, int current) {
		SpinnerModel model = new SpinnerNumberModel(current, min, max, 1);
		JSpinner spinner = new JSpinner(model);
		spinner.setFont(new Font("Arial", Font.PLAIN, 15));
		
		Dimension dimension = new Dimension(width, height);
		spinner.setMinimumSize(dimension);
		spinner.setMaximumSize(dimension);
		spinner.setPreferredSize(dimension);
		return spinner;
	}
	
	/**
	 * Permet d'ajouter un JScrollPane
	 * @param item - L'objet sur lequel attacher le JScrollPane
	 * @param width - La largeur du panel
	 * @param height - La hauteur du panel
	 * @return un JScrollPane
	 */
	public static JScrollPane addScroll(Component item, int width, int height) {
		JScrollPane pane = new JScrollPane(item);
		Dimension dimension = new Dimension(width, height);
		pane.setMinimumSize(dimension);
		pane.setMaximumSize(dimension);
		pane.setPreferredSize(dimension);
		return pane;
	}
	
	/**
	 * Permet d'ajouter un JCheckBox
	 * @return un JCheckBox
	 */
	public static JCheckBox addCheck() {
		JCheckBox box = new JCheckBox();
		return box;
	}
	
	/**
	 * Permet d'ajouter un espace
	 * @param height - La hauteur de l'espace
	 * @return un Box.rigidArea object
	 */
	public static Component addSpace(int height) {
		return Box.createRigidArea(new Dimension(1024, height));
	}

}
