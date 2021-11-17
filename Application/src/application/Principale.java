package application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;

public class Principale extends JFrame{
	private JPanel p_centre; 
	private JTextPane jcomp1;
	private JMenuBar Fichier;
	private UndoManager manager;
	private static String intermediaire;
	private JLabel l_Titre;
	public Principale() {
		this.setTitle("Editeur"); 
		this.setLayout(new BorderLayout());
		this.setSize(1000, 1000);
		Fichier = new JMenuBar();
		Fichier.setBounds(1000,1500,1000,1000);
        Fichier = new JMenuBar();
    	JMenu fileMenu = new JMenu ("Fichier");
      	JMenuItem nouveau = new JMenuItem ("Nouveau");
      	nouveau.setMnemonic(KeyEvent.VK_N);
      	nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
      	nouveau.setActionCommand("Nouveau");
        fileMenu.add (nouveau);
        JMenuItem modifierItem = new JMenuItem ("Ouvrir Fichier");
        modifierItem.setMnemonic(KeyEvent.VK_O);
        modifierItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        modifierItem.setActionCommand("Ouvrir Fichier");
        fileMenu.add (modifierItem);
        JMenuItem savMenu = new JMenuItem ("Enregistrer");
        savMenu.setMnemonic(KeyEvent.VK_S);
        savMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        savMenu.setActionCommand("Enregistrer");
        fileMenu.add (savMenu);
        
        
    	JMenu editMenu = new JMenu ("Edit");
      	JMenuItem italic = new JMenuItem ("Italic");
      	italic.setActionCommand("Italic");
      	editMenu.add (italic);
      	
      	JMenuItem gras = new JMenuItem ("Gras");
      	gras.setActionCommand("Gras");
      	
      	JMenuItem plain = new JMenuItem ("Normal");
      	editMenu.add (plain);
      	plain.setActionCommand("Plein");
      	
      	JMenuItem souligne = new JMenuItem ("Souligné");
      	editMenu.add (souligne);
      	souligne.setActionCommand("Souligné");
      	
    	JMenuItem couper = new JMenuItem ("Couper");
    	couper.setMnemonic(KeyEvent.VK_X);
      	couper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
      	couper.setActionCommand("Couper");
      	editMenu.add (couper);
      	
    	JMenuItem copie = new JMenuItem ("Copier");
      	copie.setMnemonic(KeyEvent.VK_C);
      	copie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
      	copie.setActionCommand("Copier");
      	editMenu.add (copie);
      	
      	JMenuItem coller = new JMenuItem ("Coller");
		coller.setMnemonic(KeyEvent.VK_V);
		coller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
      	coller.setActionCommand("Coller");
      	editMenu.add (coller);
      	
      	JMenuItem all = new JMenuItem ("Selectionner Tout");
      	all.setMnemonic(KeyEvent.VK_A);
      	all.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
      	all.setActionCommand("all");
      	editMenu.add (all);

      	JMenuItem annuler = new JMenuItem ("Annuler");
      	annuler.setMnemonic(KeyEvent.VK_Z);
      	annuler.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
      	annuler.setActionCommand("annuler");
      	editMenu.add (annuler);
      	
      	JMenuItem repeter = new JMenuItem ("Repeter");
      	repeter.setMnemonic(KeyEvent.VK_Y);
      	repeter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
      	repeter.setActionCommand("repeter");
      	editMenu.add (repeter);
      	
      	JMenu rognerMenu = new JMenu ("Rogner");
      	JMenuItem scrop = new JMenuItem ("Augmenter Image");
        scrop.setActionCommand("scrop");
      	editMenu.add (scrop);
      	
      	JMenuItem scrop1 = new JMenuItem ("Diminuer Image");
        scrop1.setActionCommand("scrop1");
      	editMenu.add (scrop1);

    	JMenu policeMenu = new JMenu ("Police");
        JMenuItem agency_fbItem = new JMenuItem ("Agency FB");
        agency_fbItem.setActionCommand("Agency FB");
        policeMenu.add (agency_fbItem);
        
        JMenuItem arialItem = new JMenuItem ("Arial Black");
        arialItem.setActionCommand("Arial Black");
        policeMenu.add (arialItem);
        
        JMenuItem franklin_gothic_mediumItem = new JMenuItem ("Franklin Gothic Medium");
        franklin_gothic_mediumItem.setActionCommand("Franklin Gothic Medium");
        policeMenu.add (franklin_gothic_mediumItem);
        
        JMenuItem times_new_romanItem = new JMenuItem ("Times New Roman");
        times_new_romanItem.setActionCommand("Times New Roman");
        policeMenu.add (times_new_romanItem);
        
        JMenuItem algerianItem = new JMenuItem ("Algerian");
        algerianItem.setActionCommand("Algerian");
        policeMenu.add (algerianItem);
        
        JMenuItem Gigi = new JMenuItem ("Gigi");
        Gigi.setActionCommand("Gigi");
        policeMenu.add (Gigi);
        
        JMenuItem Castellar = new JMenuItem ("Castellar");
        Castellar.setActionCommand("Castellar");
        policeMenu.add (Castellar);
        
        JMenuItem Harrington = new JMenuItem ("Harrington");
        Harrington.setActionCommand("Harrington");
        policeMenu.add (Harrington);
        
        JMenuItem Magneto = new JMenuItem ("Magneto");
        Magneto.setActionCommand("Magneto");
        policeMenu.add (Magneto);
        
        JMenuItem Vladimir = new JMenuItem ("Vladimir Script");
        Vladimir.setActionCommand("Vladimir Script");
        policeMenu.add (Vladimir);

        JMenuItem Calibri = new JMenuItem ("Calibri");
        Calibri.setActionCommand("Calibri");
        policeMenu.add (Calibri);
        
        JMenuItem Freestyle = new JMenuItem ("Freestyle Script");
        Freestyle.setActionCommand("Freestyle Script");
        policeMenu.add (Freestyle);
        

    	JMenu couleurMenu = new JMenu ("Couleur");
        JMenuItem rougeItem = new JMenuItem ("Choisir Couleur");
        couleurMenu.add (rougeItem);
        rougeItem.setActionCommand("Rouge");


    	JMenu insererMenu = new JMenu ("Inserer");
        JMenuItem image = new JMenuItem ("Inserer Image");
        couleurMenu.add (image);
        image.setActionCommand("image"); 
        JMenuItem forme = new JMenuItem ("Inserer Forme");
        couleurMenu.add (forme);
        forme.setActionCommand("forme");
        
        
        SpinnerModel tt =  new SpinnerNumberModel(15, 8, 100,1); //step  
       JSpinner spinner = new JSpinner(tt);   
               spinner.setBounds(100,100,50,30);   
        	      try {
               spinner.addChangeListener(new ChangeListener() {  
           public void stateChanged(ChangeEvent e) {  
        	   	String dif = ((JSpinner)e.getSource()).getValue().toString();
        	   	int di=Integer.parseInt(dif);
        	    int debutSel=jcomp1.getSelectionStart();
        	    int finSel=jcomp1.getSelectedText().length();
        	    SimpleAttributeSet taillePolice=new SimpleAttributeSet();
        	    StyleConstants.setFontSize(taillePolice,di);
        	    StyledDocument doc=jcomp1.getStyledDocument();
        	    doc.setCharacterAttributes(debutSel, finSel, taillePolice, false);
           }  
        });  
   }catch (Exception ey) { }
    	
        
        JMenu helpMenu = new JMenu ("Help");
        JMenuItem aboutItem = new JMenuItem ("About");
        helpMenu.add (aboutItem);
	
        MenuItemListener menuItemListener = new MenuItemListener();
		nouveau.addActionListener(menuItemListener);
		modifierItem.addActionListener(menuItemListener);
		savMenu.addActionListener(menuItemListener);
		couper.addActionListener(menuItemListener);
		copie.addActionListener(menuItemListener);
		coller.addActionListener(menuItemListener);
		gras.addActionListener(menuItemListener);
		italic.addActionListener(menuItemListener);
		souligne.addActionListener(menuItemListener);
		plain.addActionListener(menuItemListener);
		agency_fbItem.addActionListener(menuItemListener);
		Calibri.addActionListener(menuItemListener);
		arialItem.addActionListener(menuItemListener);
		franklin_gothic_mediumItem.addActionListener(menuItemListener);
		times_new_romanItem.addActionListener(menuItemListener);
		algerianItem.addActionListener(menuItemListener);
		Freestyle.addActionListener(menuItemListener);
		Vladimir.addActionListener(menuItemListener);
		Magneto.addActionListener(menuItemListener);
		Harrington.addActionListener(menuItemListener);
		Castellar.addActionListener(menuItemListener);
		Gigi.addActionListener(menuItemListener);
		rougeItem.addActionListener(menuItemListener);
		image.addActionListener(menuItemListener);
		forme.addActionListener(menuItemListener);
		all.addActionListener(menuItemListener);
		annuler.addActionListener(menuItemListener);
		repeter.addActionListener(menuItemListener);
		scrop.addActionListener(menuItemListener);
		scrop1.addActionListener(menuItemListener);
		
		fileMenu.add(nouveau);
		fileMenu.add(modifierItem);
        fileMenu.add (savMenu);
		editMenu.add(annuler);
		editMenu.add(repeter);
		editMenu.add(all);
		editMenu.add(couper);
		editMenu.add(copie);
		editMenu.add(coller);
		editMenu.add(italic);
		editMenu.add(gras);
		editMenu.add(souligne);
		editMenu.add(plain);
		policeMenu.add (agency_fbItem);
		policeMenu.add (algerianItem);
		policeMenu.add (arialItem);
		policeMenu.add (Calibri);
		policeMenu.add (Castellar);
		policeMenu.add (franklin_gothic_mediumItem);
		policeMenu.add (Freestyle);
		policeMenu.add (Gigi);
		policeMenu.add (Harrington);
		policeMenu.add (Magneto);
		policeMenu.add (times_new_romanItem);
		policeMenu.add (Vladimir);
		insererMenu.add(image);
		insererMenu.add(forme);
		rognerMenu.add(scrop);
		rognerMenu.add(scrop1);
		Fichier.add(fileMenu);
		Fichier.add(editMenu);
        Fichier.add (couleurMenu);
        Fichier.add (policeMenu);
        Fichier.add (insererMenu);
        Fichier.add (rognerMenu);
        Fichier.add (spinner);
		this.setJMenuBar(Fichier);
        Fichier.add (helpMenu);
		p_centre=new JPanel(new GridLayout(1,1)); 
		jcomp1 = new JTextPane();
		String userDictionaryPath = "/dictionnaire/";
		//SET DICTIONARY PROVIDER FROM DICTIONARY PATH
		SpellChecker.setUserDictionaryProvider(new FileUserDictionary(userDictionaryPath));
		//REGISTER DICTIONARY
		SpellChecker.registerDictionaries(getClass().getResource(userDictionaryPath), "fr");

    	SpellChecker.register(jcomp1);
		
    	
    	UndoManager manager = new UndoManager();
    	jcomp1.getDocument().addUndoableEditListener(manager);
    	annuler.addActionListener(new annulerAction(manager));
    	repeter.addActionListener(new retablirAction(manager));
    	jcomp1.setMaximumSize(new Dimension(40,
                40));
        p_centre.add(jcomp1);
		this.add(p_centre,BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(jcomp1);
        this.add(scrollPane);
		this.setVisible(true);

	}
	class MenuItemListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {            
          if (e.getActionCommand() == "Nouveau") {
        	  new Principale();
          }
          
          if (e.getActionCommand() == "Ouvrir Fichier") {
        	JFileChooser dialogue = new JFileChooser(new File("."));
      		PrintWriter sortie;
      		File fichier;
      		if (dialogue.showOpenDialog(null)== 
      		    JFileChooser.APPROVE_OPTION) {
      		    fichier = dialogue.getSelectedFile();
      		    String absolutePath = fichier.getAbsolutePath();
      		    	intermediaire=absolutePath;
      		    try {
              	  new Principale();
					sortie = new PrintWriter
					(new FileWriter(fichier.getPath(), true));
			          FileReader fr=new FileReader(fichier);    
			          BufferedReader br=new BufferedReader(fr);
			          int i;    
			          while((i=br.read())!=-1){  
			        	  jcomp1.setText(jcomp1.getText()+Character.toString((char)i));
			          }  
			          br.close();    
			          fr.close();
			          
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
      		}
          }
          if (e.getActionCommand() == "Enregistrer"){
        	  File fi=new File(""+intermediaire+"");
			if(fi.exists()){
				FileWriter fw = null;
				BufferedWriter bw = null; 
				PrintWriter pw = null; 
				try { 
				fw = new FileWriter(intermediaire, false);
				bw = new BufferedWriter(fw);
				pw = new PrintWriter(bw); 

				pw.println(jcomp1.getText());

				pw.flush();
				 } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally { 
				try { 
				pw.close();
				 bw.close();
				 fw.close();
				 } catch (IOException io) {// can't do anything } 
				} 
				}

			}
			else{
	         	 JFileChooser chooser = new JFileChooser();
	         	 BufferedWriter writer;
	              int returnVal = chooser.showSaveDialog(getParent());
	              if(returnVal == JFileChooser.APPROVE_OPTION)
	              {
	                  try
	                  {
	                 	 writer = new BufferedWriter(new FileWriter(chooser.getSelectedFile()));
	                      jcomp1.write(writer);
	                      writer.close();
	                  } catch (FileNotFoundException fnfe)
	                  {
	                      fnfe.printStackTrace();
	                  } catch (IOException ioe)
	                  {
	                      ioe.printStackTrace();
	                  }
	              }
			}
          }
          if (e.getActionCommand() == "Copier") {
       	   try
              {
                       String str=jcomp1.getSelectedText();
       		Toolkit toolkit = Toolkit.getDefaultToolkit();
       		Clipboard clipboard = toolkit.getSystemClipboard();
       		StringSelection strSel = new StringSelection(str);
       		clipboard.setContents(strSel, null);
              }catch(Exception err)
              {
                  err.printStackTrace();
              }      	   
          }
          if (e.getActionCommand() == "Couper") {
       	   try
              {
       		String str=jcomp1.getSelectedText();
       		Toolkit toolkit = Toolkit.getDefaultToolkit();
       		Clipboard clipboard = toolkit.getSystemClipboard();
       		StringSelection strSel = new StringSelection(str);
       		clipboard.setContents(strSel, null);
       		String texto="";
            	jcomp1.replaceSelection(texto);
              }catch(Exception err)
              {
                  err.printStackTrace();
              }         	   
          }
          if (e.getActionCommand() == "Coller") {
       	   Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
              String text="";
                 try
                 {  
               	 	text = (String) clipboard.getData(DataFlavor.stringFlavor);
                	   jcomp1.replaceSelection(text);
                 }
                 catch (Exception e1)
                 {
                    JOptionPane.showMessageDialog(null, e1);
                 }
             }
          if (e.getActionCommand() == "Italic") {
  		    int debutSel=jcomp1.getSelectionStart();
  		    int finSel=jcomp1.getSelectedText().length();
  		    SimpleAttributeSet styleItalic=new SimpleAttributeSet();
  		    StyleConstants.setItalic(styleItalic, true);
  		    StyledDocument doc=jcomp1.getStyledDocument();
  		    doc.setCharacterAttributes(debutSel, finSel, styleItalic, false);
             }
          if (e.getActionCommand() == "Gras") {
       		    int debutSel=jcomp1.getSelectionStart();
       		    int finSel=jcomp1.getSelectedText().length();
       		    SimpleAttributeSet styleGras = new SimpleAttributeSet();
       		    StyleConstants.setBold(styleGras, true);
       		    StyledDocument doc=jcomp1.getStyledDocument();
       		    doc.setCharacterAttributes(debutSel, finSel, styleGras, false);
          }
          if (e.getActionCommand() == "Plein") {

      		String textot="";
       	   Style defaut = jcomp1.getStyle("default");
       	   Style style1 = jcomp1.addStyle("bold", defaut);
       	   StyleConstants.setFontFamily(style1, "bold");
       	   Style style2 = jcomp1.addStyle("style2", style1);
       	      StyleConstants.setForeground(style2, Color.black);
       	      StyleConstants.setFontSize(style2, 10);        
       	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
       	      try {
              	   int debut = jcomp1.getSelectionStart();
              	   sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
              	   jcomp1.replaceSelection(textot);
       	      } catch (BadLocationException ey) { }
             }
         if (e.getActionCommand() == "Souligné") {
          int debutSel=jcomp1.getSelectionStart();
		    int finSel=jcomp1.getSelectedText().length();
		    SimpleAttributeSet styleUnderline = new SimpleAttributeSet();
		    StyleConstants.setUnderline(styleUnderline, true);
		    StyledDocument doc=jcomp1.getStyledDocument();
		    doc.setCharacterAttributes(debutSel, finSel, styleUnderline, false);
         }

         if (e.getActionCommand() == "Rouge") {
       	  try
       	  {
       		  int debutSel=jcomp1.getSelectionStart();
       		    int finSel=jcomp1.getSelectedText().length();
       		    SimpleAttributeSet couleurTexte=new SimpleAttributeSet();
       		    Color couleur=JColorChooser.showDialog(jcomp1, "Choisir couleur police", Color.black);
       		    StyleConstants.setForeground(couleurTexte, couleur);
       		    StyledDocument doc=jcomp1.getStyledDocument();
       		    doc.setCharacterAttributes(debutSel, finSel, couleurTexte, false);
       		    
       	  }
       	  catch(Exception ex){}  	   
          }
         if (e.getActionCommand() == "all") {
        	 jcomp1.selectAll();
            }

         if (e.getActionCommand() == "Agency FB") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Agency FB");
   	      StyleConstants.setFontSize(style1, 17);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Agency="";
            	jcomp1.replaceSelection(Agency);
   	    } catch (BadLocationException ey) { }   	   
            }

         if (e.getActionCommand() == "Arial Black") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Arial Black");
   	      StyleConstants.setFontSize(style1, 17);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Arial="";
            	jcomp1.replaceSelection(Arial);
   	    } catch (BadLocationException ey) { }   	   
            }

         if (e.getActionCommand() == "Franklin Gothic Medium") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Franklin Gothic Medium");
   	      StyleConstants.setFontSize(style1, 17);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Franklin="";
            	jcomp1.replaceSelection(Franklin);
   	    } catch (BadLocationException ey) { }   	   
            }

         if (e.getActionCommand() == "Times New Roman") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Times New Roman");
   	      StyleConstants.setFontSize(style1, 17);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Times="";
            	jcomp1.replaceSelection(Times);
   	    } catch (BadLocationException ey) { }   	   
            }

         if (e.getActionCommand() == "Algerian") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Algerian");
   	      StyleConstants.setFontSize(style1, 17);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Algerian="";
            	jcomp1.replaceSelection(Algerian);
   	    } catch (BadLocationException ey) { }   	   
            }

         if (e.getActionCommand() == "Freestyle Script") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Freestyle Script");
   	      StyleConstants.setFontSize(style1, 35);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Freestyle="";
            	jcomp1.replaceSelection(Freestyle);
   	    } catch (BadLocationException ey) { }   	   
            }

         if (e.getActionCommand() == "Vladimir Script") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Vladimir Script");
   	      StyleConstants.setFontSize(style1, 25);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Vladimir="";
            	jcomp1.replaceSelection(Vladimir);
   	    } catch (BadLocationException ey) { }   	   
            }

         if (e.getActionCommand() == "Magneto") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Magneto");
   	      StyleConstants.setFontSize(style1, 22);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Magneto="";
            	jcomp1.replaceSelection(Magneto);
   	    } catch (BadLocationException ey) { }   	   
            }

         if (e.getActionCommand() == "Harrington") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Harrington");
   	      StyleConstants.setFontSize(style1, 17);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Harrington="";
            	jcomp1.replaceSelection(Harrington);
   	    } catch (BadLocationException ey) { }   	   
            }

         if (e.getActionCommand() == "Calibri") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Calibri");
   	      StyleConstants.setFontSize(style1, 17);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Calibri="";
            	jcomp1.replaceSelection(Calibri);
   	    } catch (BadLocationException ey) { }   	   
            }

         if (e.getActionCommand() == "Castellar") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Castellar");
   	      StyleConstants.setFontSize(style1, 17);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Castellar="";
            	jcomp1.replaceSelection(Castellar);
   	    } catch (BadLocationException ey) { }   	   
            }

         if (e.getActionCommand() == "Gigi") {
       	  Style defaut = jcomp1.getStyle("default");
     		  Style style1 = jcomp1.addStyle("bold", defaut);
   	      StyleConstants.setForeground(style1, Color.black);
   	      StyleConstants.setFontFamily(style1, "Gigi");
   	      StyleConstants.setFontSize(style1, 25);        
   	      StyledDocument sDoc = (StyledDocument)jcomp1.getDocument();
   	      try {
          	   int debut = jcomp1.getSelectionStart();
   	          sDoc.insertString(debut, jcomp1.getSelectedText(), style1);
       		String Gigi="";
            	jcomp1.replaceSelection(Gigi);
   	    } catch (BadLocationException ey) { }   	   
            }
         if (e.getActionCommand() == "image") {
       	   JFileChooser dialogue = new JFileChooser(new File("."));
      		PrintWriter sortie;
      		File fichier;
      		if (dialogue.showOpenDialog(null)== 
      		    JFileChooser.APPROVE_OPTION) {
      		    fichier = dialogue.getSelectedFile();
      		    try {

                      BufferedImage image = ImageIO.read(fichier);
			        ImageIcon icon1 = new ImageIcon(image.getScaledInstance(300, 300, Image.SCALE_DEFAULT));
			      jcomp1.insertIcon(icon1);
	                
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
      		}
          }
         if (e.getActionCommand() == "scrop") {
       	   JFileChooser dialogue = new JFileChooser(new File("."));
      		PrintWriter sortie;
      		File fichier;
      		if (dialogue.showOpenDialog(null)== 
      		    JFileChooser.APPROVE_OPTION) {
      		    fichier = dialogue.getSelectedFile();
      		    try {

                      BufferedImage image = ImageIO.read(fichier);
			        ImageIcon icon1 = new ImageIcon(image.getScaledInstance(300, 300, Image.SCALE_DEFAULT));
			      jcomp1.insertIcon(icon1);
	                
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
      		}
          }
      }    
   	}
	class annulerAction implements ActionListener
	{
	    private final UndoManager manager;
	    public annulerAction(UndoManager manager)
	    {
	      this.manager=manager;
	    }


	    public void actionPerformed(ActionEvent evt)
	    {
	        try
	        {
	            manager.undo();
	        }
	        catch(CannotUndoException ex)
	        {
	            Toolkit.getDefaultToolkit().beep();
	        }
	    }
	}
	class retablirAction implements ActionListener
	{
	    private final UndoManager manager;
	    public retablirAction(UndoManager manager)
	    {
	      this.manager=manager;
	    }
	    
	    public void actionPerformed(ActionEvent evt)
	    {
	        try
	        {
	            manager.redo();
	        }
	        catch(CannotRedoException ex)
	        {
	            Toolkit.getDefaultToolkit().beep();
	        }
	    }
	}
	public static void main(String[] args) {
		Principale fen = new Principale();
		SpellChecker.registerDictionaries(null,"fr","fr");
	}
}

