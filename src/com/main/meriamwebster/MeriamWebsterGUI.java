package com.main.meriamwebster;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeriamWebsterGUI extends JFrame {
	    private static int counter = -1;
	    private final JLabel lblNewLabel = new JLabel("Word Count  ");
	    private final JButton StartOverButton = new JButton("Start Over");
	    private final JButton backButton = new JButton("Back");
	    private final JButton nextButton = new JButton("Next");
	    private final JTextField counterField = new JTextField();
	    private final JButton btnPlayAgain = new JButton("Play Again");
	    static String[] word = new String[20];
	    private final JLabel label = new JLabel("");
	    private final JLabel label_1 = new JLabel("");
	    private final JLabel label_2 = new JLabel("");
	    public MeriamWebsterGUI() throws IOException{
	    	setTitle("Keerthan MeriamWebster");
	    	counterField.setBackground(SystemColor.inactiveCaptionBorder);
	    	counterField.setHorizontalAlignment(SwingConstants.RIGHT);
	    	counterField.setEditable(false);
	    	counterField.setColumns(10);
			int noOfLines = NumberOfLines();
			word = new String[noOfLines];
			ReadFile();
	    	getContentPane().setLayout(new GridLayout(3, 5, 10, 10));
	    	
	    	getContentPane().add(label);
	    	lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    	getContentPane().add(lblNewLabel);
	    	getContentPane().add(counterField);
	    	
	    	getContentPane().add(label_1);
	    	getContentPane().add(StartOverButton);
	    	
	    	getContentPane().add(label_2);
	    	getContentPane().add(backButton);
	    	backButton.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			--counter;
	    			counterField.setText("" + counter);
					String url = "https://www.merriam-webster.com/dictionary/"  + word[counter];
	    			String audioFile;
					try {
						audioFile = parseURL(url,true);
						System.out.println("Audio File: " + audioFile);
						playfile(audioFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	    			
	    		}
	    	});
	    	getContentPane().add(btnPlayAgain);
	    	getContentPane().add(nextButton);
	    	StartOverButton.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			counter = 0;
	    			counterField.setText("" + counter);
					String url = "https://www.merriam-webster.com/dictionary/"  + word[counter];
	    			String audioFile;
					try {
						audioFile = parseURL(url,true);
						System.out.println("Audio File: " + audioFile);
						playfile(audioFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	    			
	    		}
	    	});

	    	nextButton.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			++counter;
	    			counterField.setText("" + counter);
					String url = "https://www.merriam-webster.com/dictionary/"  + word[counter];
	    			String audioFile;
					try {
						audioFile = parseURL(url,true);
						System.out.println("Audio File: " + audioFile);
						playfile(audioFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	    			
	    		}
	    	});

	    	btnPlayAgain.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			counterField.setText("" + counter);
	    			String url = "https://www.merriam-webster.com/dictionary/"  + word[counter];
	    			String audioFile;
					try {
						audioFile = parseURL(url,true);
						System.out.println("Audio File: " + audioFile);
		    			playfile(audioFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	    			
	    		}
	    	});
	    }
	    
		public static int NumberOfLines() throws IOException {
			int counter=0;
			File dir = new File(".");
			File fin = new File(dir.getCanonicalPath() + File.separator + "WordsList.txt");
			FileInputStream fis = new FileInputStream(fin);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			while ((line = br.readLine()) != null) {
				counter++;
			}
			br.close();
			return counter;
		}

		public static void ReadFile() throws IOException {
			File dir = new File(".");
			File fin = new File(dir.getCanonicalPath() + File.separator + "WordsList.txt");
			System.out.println(fin);
			FileInputStream fis = new FileInputStream(fin);

			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			int i=0;
			String line = null;
			while ((line = br.readLine()) != null) {
				word[i] = line;
				i++;
				counter = 0;
			}
			br.close();
		}
		public static String parseURL(String URL, boolean isAudioFile) throws IOException{
			URL oracle = new URL(URL);
	        URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
			String pattern = "data-file=\"(.*?)\" data-dir=\"(.*?)\" href=\"(.*)\" (.*?)(audio)(.*)";
			Pattern urlpattern = Pattern.compile(pattern);
	        while ((inputLine = in.readLine()) != null) {
				// Now create matcher object.
				if (inputLine.contains("<h1"))
					System.out.println("");
				Matcher m = urlpattern.matcher(inputLine);
				if (m.find())
					return "https://media.merriam-webster.com/soundc11/" + m.group(2) + "/" + m.group(1)+ ".wav";
	        }
	        in.close();
	        
			return "";
		}
		public static void playfile(String audioURL) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		    AudioInputStream din = null;
		    try {
		        AudioInputStream in = AudioSystem.getAudioInputStream(new URL(audioURL));
		        AudioFormat baseFormat = in.getFormat();
		        AudioFormat decodedFormat = new AudioFormat(
		                AudioFormat.Encoding.PCM_SIGNED,
		                baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
		                baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
		                false);
		        din = AudioSystem.getAudioInputStream(decodedFormat, in);
		        DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
		        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
		        if(line != null) {
		            line.open(decodedFormat);
		            byte[] data = new byte[4096];
		            // Start
		            line.start();

		            int nBytesRead;
		            while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
		                line.write(data, 0, nBytesRead);
		            }
		            // Stop
		            line.drain();
		            line.stop();
		            line.close();
		            din.close();	        
		        }
		   } finally {}
		}
		
		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub
	        JFrame frame = new MeriamWebsterGUI();	
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(400,137);
	        frame.setTitle("Keerthan MeriamWebster");
	        frame.setVisible(true);
		}

}
