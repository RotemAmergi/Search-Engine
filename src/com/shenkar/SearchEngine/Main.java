package com.shenkar.SearchEngine;
import java.awt.BorderLayout;

import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.util.Date;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.print.*;
import java.awt.Dimension; import java.awt.Graphics; import java.awt.Image; import javax.swing.ImageIcon; import javax.swing.JFrame; import javax.swing.JPanel;



public class Main extends JFrame{

	private IndexFile 		 in			    ;
	private PostingFile 	 pf 		    ;
	private JPanel 			 contentPane    ;
	private JTextField  	 textField	    ;
	private JList 			 list		    ;
	private DefaultListModel model	        ;
	private ArrayList<String> printArray    ;
	private JScrollPane 	 sp		        ;
	private JTextArea 		 textLeb	    ;
	private JTextArea         textLeb2      ;
	private JPanel 			 contentPanePic ;
	private JLabel			  jpanpic		;
	private JFrame			  picfream		;
	
	 
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					//frame.checkFiles();
					 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public Main()
	{
		
		
		
		
		
		
		printArray =new ArrayList<String>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultLookAndFeelDecorated(true);
		setBounds(700, 100, 1100, 600);
		setTitle("Search Engine - Rotem & Shahar");
		 
		
		contentPane = new JPanel();	
		
		//String for Searching
		String w1 =
				  "Searching :\n" +
				  "The search engine supports several types of queries:\n\r " 	
	    		 +"AND: \n\r"
	    		 + "[word1] AND [word2] – will return the files containing both words.\n\r"
	    		 + "OR:\n\r "
	    		 + "[word1] OR [word2] – will return files containing at least one of the searched words.\n\r"
	    		 +"NOT:\n\r"
	    		 + "will return files containing specific word and not containing another word at all. \n\r " +
	    		 "Files containing only the first word before the NOT operator and not containing the second word will be shown.\n\r"+
	    		 "wildcard *:\n\r"
	    		 + "will return files with ANY combination of chars for a given prefix.\n\r"+
				 "(): \n\r"
				 + "query operator, the word inside the brackets is handled first, the result is sent\n\r"
				 + "back to be handled again with the correct value.\n\r"
	    		 ;
		//String for Sample of query
		String w2 =
				  "Sample of query :\n" +
				  "served\n\r"+
				  "laptop  AND developed\n\r"+
				  "laptop OR politician \n\r"+
				  " (laptop  OR  developed)  NOT  language \n\r"+
				  "references *\n\r"+
				  " ((laptop  OR  developed)  AND  language ) OR multitasking\n\r"
	    		 ;
		
		//textLeb.setText(w1);
		//textLeb.setPreferredSize(new Dimension(100,100));
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    add(contentPane);
		contentPane.setLayout(null);

		
		
		 
		
		 //text query
		textField = new JTextField();
		textField.setBounds(40, 480, 308, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		//0 label
		final JLabel NumberFileslabel = new JLabel("0");
		NumberFileslabel.setBounds(180,420, 46, 14);
		contentPane.add(NumberFileslabel);
		
		
		
		//contentPane.add();
		//Options menu Bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 100, 40);
		contentPane.add(menuBar);
		
		JMenu filemenu = new JMenu("Options");
		filemenu.setBounds(0,0,50,15);
		
		JMenu Printmenu = new JMenu("Print");
		Printmenu.setBounds(0,5,50,15);
		JMenuItem Printmenu1 = new JMenuItem("Print");
		Printmenu1.add(new JSeparator());
		JMenuItem fileItem1 = new JMenuItem("Import Dictionary");
		JMenuItem fileItem2 = new JMenuItem("Add File");
		fileItem2.add(new JSeparator());
		JMenuItem fileItem3 = new JMenuItem("Remove File");
		fileItem3.add(new JSeparator());
		JMenuItem fileItem5 = new JMenuItem("Help");
		fileItem5.add(new JSeparator());
		JMenuItem fileItem4 = new JMenuItem("Exit");
		fileItem4.add(new JSeparator());
		
		
		//adding action listener to menu items
		fileItem2.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
						 
						  JFileChooser fileChooser = new JFileChooser();
					      fileChooser.setCurrentDirectory(new java.io.File("."));

					       fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					        int returnValue = fileChooser.showOpenDialog(null);
					        if (returnValue == JFileChooser.APPROVE_OPTION) 
					        {
					        	if(pf != null){
					        		pf.AddFile(fileChooser.getSelectedFile());
					        		NumberFileslabel.setText(Integer.toString(pf.counter));
					        		
					        		try {
										in.AddFile(fileChooser.getSelectedFile());
									} catch (FileNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
					        	}
					        }
							
						}
					}
				);		
		
		
		//adding action listener to menu items
		fileItem3.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							JFileChooser fileChooser = new JFileChooser();
						      fileChooser.setCurrentDirectory(new java.io.File("."));

						       fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						        int returnValue = fileChooser.showOpenDialog(null);
						        if (returnValue == JFileChooser.APPROVE_OPTION) 
						        {
						        	if(pf != null)
						        	{
						        		pf.RemoveFile(fileChooser.getSelectedFile());
						        		System.out.println("Remove file: "+fileChooser.
						        				getSelectedFile().getName());
						        		NumberFileslabel.setText(Integer.toString(pf.counter));

						        		try {
											in.RemoveFile(fileChooser.getSelectedFile());
										} catch (FileNotFoundException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
						        	}
						        }
							
						}
					}
				);
		
		
		
		
		//adding action listener to menu items
		fileItem1.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
						     JFileChooser fileChooser = new JFileChooser();
						     fileChooser.setCurrentDirectory(new java.io.File("."));

						     fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						        int returnValue = fileChooser.showOpenDialog(null);
						        if (returnValue == JFileChooser.APPROVE_OPTION) 
						        {
						        	pf = new PostingFile(fileChooser.getSelectedFile());
						        	NumberFileslabel.setText(Integer.toString(pf.counter));
						        		
									try {
										in = new IndexFile(fileChooser.getSelectedFile());
										} catch (FileNotFoundException e1) {
											 
										e1.printStackTrace();
										}
						        }
						        else
						        {
						        	System.out.println("Import Was Canceled");
						        }
						}
					}
				);
		
		
		//adding action listener to menu items
		fileItem4.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dispose();
							
						}
					}
				);

		//Options - Help & Read-Me 
		fileItem5.addActionListener(
				new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				   
				JFrame jf = new JFrame("Read-Me");
        		jf.setBounds(750, 200, 500, 500);
        		
        		
        		JTextPane pane = new JTextPane();
        		pane.setEditable(false);
        		SimpleAttributeSet set = new SimpleAttributeSet();
        		StyleConstants.setBold(set, true);
        		
        	   
        		StyleConstants.setFontSize(set, 14);
        	    StyleConstants.setItalic(set, true);
        	    
        	    
        	    pane.setCharacterAttributes(set, true);
        	    Document doc = pane.getStyledDocument();
        	    
        	    String w =
        	    		"Search management project.\n\r" +
        	    		"Explain the use of the management system : \n\r" 	
        	    		 +"\n\r first:"+"\n"
        	    		 + "-Choose your documents folder by pressing\n\r"
        	    		 + " File -> import dictionary.\n\r"
        	    		 +"\n start search:\n\r"
        	    		 +"-For searching a single word, please type the word you \n\r"
        	    		 + " search in \n\r " +
        	    		 "the blank field and press the\n\r"
        	    		 +" Search Button.\n\r"
        	    		 +"-You can use different operators such as :\n\r"
        	    		 + "AND, NOT, OR,(), *, \"\n\r" 
        	    		 +"ADD:\n\r"+
        	    		 "-Add file to the Data Base: go to\n\r "
        	    		 + "File -> Add file and choose \n\r"
        	    		 +"the DOC you wish to add.\n\r"
        	    		 +"Remove:\n\r"
        	    		 + " -Remove file from the Date Base:\n\r "
        	    		 + "go to File -> Remove file and choose \n\r" 
        	    		 +"the file you wish to delete.\n\r"
        	    		 ;
        	 
        	     
        	    
        	    
        	    try {
        	    	
					doc.insertString(doc.getLength(),w , set);
					jf.getContentPane().add(new JPanelWithBackground("Search-Engine-Marketing-PPC.png"));
					jf.setVisible(true);
					
				} catch (BadLocationException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	    
        	    Container cp = jf.getContentPane();
        	    JScrollPane scrollPane = new JScrollPane(pane);
        	    cp.add(scrollPane, BorderLayout.CENTER);
        	         	         	    
        	    jf.setVisible(true);	
			}
		});
		
		
		
		
		/////Print  JButton
		Printmenu1.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						 
						JFrame yourComponent = new JFrame();
						PrinterJob pjob = PrinterJob.getPrinterJob();
						PageFormat preformat = pjob.defaultPage();
						preformat.setOrientation(PageFormat.LANDSCAPE);
						PageFormat postformat = pjob.pageDialog(preformat);
						//If user does not hit cancel then print.
						if (preformat != postformat) {
						    //Set print component
						    pjob.setPrintable(new PrintUIWindow(yourComponent), postformat);
						    if (pjob.printDialog()) {
						        try {
									pjob.print();
								} catch (PrinterException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						    }
						}
					}
				}
			);
		
		
		
		
		filemenu.add(fileItem1);
		filemenu.add(fileItem2);
		filemenu.add(fileItem3);
		filemenu.add(fileItem5);
		filemenu.add(fileItem4);
		
		Printmenu.add(Printmenu1);
		menuBar.add(filemenu);
		menuBar.add(Printmenu);
		
		//Files in DataBase Use JLabel
		JLabel lblFilesInDatabase = new JLabel("Files in DataBase Use :");
		lblFilesInDatabase.setBounds(40,420, 130, 14);
		contentPane.add(lblFilesInDatabase);
		
		
		model = new DefaultListModel();
		list = new JList();
 
		list.setModel(model);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setBackground(Color.white);
		list.setBounds(70, 145, 507, 409);
		sp = new JScrollPane( list);
		sp.setSize(450, 350);
		sp.setLocation(40, 60);
		textLeb = new JTextArea();
		textLeb2 = new JTextArea();
		//contentPanePic = new JPanel();
		//jpanpic =new JLabel();	
		
		//jpanpic.setIcon(new ImageIcon("C:\\Users\\rotem\\workspace\\Search Engine\\Search-Engine-Marketing-PPC.png"));
		//contentPanePic.add(jpanpic);
		
		
		//add the w1 && w2 string 
		textLeb.setSize(480,300);
		textLeb.setLocation(550,60);
		textLeb.setText(w1);
		textLeb.setBackground(Color.LIGHT_GRAY);
		textLeb2.setSize(480,350);
		textLeb2.setLocation(550,380);
		textLeb2.setText(w2);
		textLeb2.setBackground(Color.LIGHT_GRAY);
		contentPane.add(sp, BorderLayout.CENTER);
		contentPane.add(textLeb, BorderLayout.CENTER);
		contentPane.add(textLeb2, BorderLayout.CENTER);
		
		
		
		
		
		//Search JButton
		JButton btnSearch = new JButton("Search");
		
		btnSearch.setBounds(380, 478, 89, 23);
		
		
		//addActionListener for the operand of the query
		btnSearch.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	model.clear();
            	printArray.clear();
            	
            	if(textField.getText().toString().contains("AND") || textField.getText().toString().
            			contains("NOT") || textField.getText().toString()
            					.contains("\"" ) ||textField.getText().toString().contains("OR")
            					||textField.getText().toString().contains("(")
            					||textField.getText().toString().contains("*"))
            	{
            		ArrayList<Integer> Results = Search_Result();
            		if(Results.isEmpty()){
            			model.addElement("No Matches");
            		}
            		else{
            		for(int j=0;j<Results.size();j++){
            	        
        				//model.addElement(pf.Fileob.get(Results.get(j)));
            			model.addElement(in.summaryArray.get(Results.get(j)));
        			}
            		}
            		
            	}
            	else{
            	String s = 	textField.getText().toString().toLowerCase();	 
            	printArray.add(s);
				System.out.println("textField: "+s);	 
            	if(s.length()>=1){	 
            	for(int i=0;i<in.DictionaryArray.size();i++){
            		
            		if(in.DictionaryArray.get(i).name.equals(s))
            		{
            			System.out.println("name: "+in.DictionaryArray.get(i).name);
            			
            			for(int j=0;j<in.DictionaryArray.get(i).get_post_hits();j++){

            				
            				model.addElement(in.summaryArray.get(in.DictionaryArray.get(i).offsetArray.
            						get(j).postid ));
            			}
            			
            		}
            	}
            	}
            	}
           
            }
            //ArrayList of the Search Result
			private ArrayList<Integer> Search_Result() 
			{
					
				SearchResult sr = new SearchResult(textField.getText().toString());
				boolean isBrackets =   false;
				boolean isQuotes   =   false;
				boolean isStar     =   false;
				ArrayList<Integer> Result_Final =new ArrayList<Integer>();
				
				
				for(int i=0;i<sr.queryArray.size();i++)
				{
					if(sr.queryArray.get(i).contains("("))
					{
						isBrackets=true;
						Result_Final = sr.Brackets(in);
						printArray = sr.RemoveNot();
					}
					
					if(sr.queryArray.get(i).contains("*"))
					{
						isStar=true;
						Result_Final=sr.Star(in);
						printArray=sr.queryArray;
						
					}
					
					if(sr.queryArray.get(i).contains("\""))
					{
						isQuotes=true;
						Result_Final=sr.Quotes(in);
						printArray=sr.queryArray;
						
					}
				}
				
				
				if(isBrackets == false && isQuotes ==false && isStar == false)
				{
					Result_Final= sr.GiveMeResult(in);
					printArray  = sr.RemoveNot();

				}
			 
				
				System.out.println("printArray: "+printArray);

				return Result_Final;
			}
        });

		contentPane.add(btnSearch);
		
		JButton btnNameFiles = new JButton("List Of Files");
		btnNameFiles.setBounds(220,420, 107, 20);
		btnNameFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				model.clear();
				if(pf != null){ 
				for(int j=0;j<pf.Fileob.size();j++)
				{
					model.addElement(pf.Fileob.get(j));
					
				}
				}else{
					model.addElement("No Files In DataBase.");
				}
				
			}
		});
		
		contentPane.add(btnNameFiles);
		
		 list.addListSelectionListener(new ListSelectionListener() {

	            @Override
	            public void valueChanged(ListSelectionEvent arg0)  {
	            	
	                if (!arg0.getValueIsAdjusting()) {
	                	if(list.isSelectionEmpty()==false){
	                		
	                		JFrame jf = new JFrame("Result...");
	                		
	                		jf.setBounds(100, 100, 600, 600);
	                		
	                		JTextPane pane = new JTextPane();
	                		pane.setEditable(false);
	                		SimpleAttributeSet set = new SimpleAttributeSet();
	                		StyleConstants.setBold(set, true);
	                		pane.setCharacterAttributes(set, true);
	                	   
	                		StyleConstants.setFontSize(set, 14);
	                	    StyleConstants.setItalic(set, true);
	                	    
	                	    StyleConstants.setBackground(set, Color.blue);
	                	    Document doc = pane.getStyledDocument();

	                	    
	                	    Container cp = jf.getContentPane();
	                	    JScrollPane scrollPane = new JScrollPane(pane);
	                	    cp.add(scrollPane, BorderLayout.CENTER);

	                	    jf.setSize(600, 600);
	                	    
	                	 String w;
	                	 boolean Written=false;
	                	 String gg=Character.toString(list.getSelectedValue().toString().charAt(10));
	                	 if( Character.toString(list.getSelectedValue().toString().charAt(11)).equals("<")){}
	                	 else{
	                		 gg+= Character.toString(list.getSelectedValue().toString().charAt(11));
	                	 }

	                	File file = new File(pf.Fileob.get(Integer.parseInt(gg)));
	                	try {
	                		
							Scanner input =  new Scanner(file);
							System.out.println(printArray);
							while(input.hasNext() ) 
					    	{
								Written=false;
								w=input.next();
								
								String temp = w.replaceAll("[^a-zA-Z0-9]","");
								temp=temp.toLowerCase();
								
								for(int i=0;i<printArray.size();i++)
								{
									if(temp.equals(printArray.get(i).toString()))
									{
										StyleConstants.setBackground(set, Color.YELLOW);
									
										w+=" ";	
										doc.insertString(doc.getLength(), w, set);
										Written=true;
										
									}
									}
								if(Written==false)
								{
								StyleConstants.setBackground(set, Color.WHITE);
								w+=" ";	
								doc.insertString(doc.getLength(), w, set);
								
								}
					    	}
							input.close();
							
						} catch (FileNotFoundException e) {
							
							e.printStackTrace();
						} catch (BadLocationException e) {
							
							e.printStackTrace();
						}  
	                	
	                	jf.setVisible(true);
	                   
	                }
	                }
	                
	            }
	        });
		 
		 
	}
	
}
