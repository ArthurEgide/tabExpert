package tabExpert;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.JTextArea;

public class CifraEmTexto {
	
	public String printa() {
		return "Ola,estou aqui e funciono";
	}
	
	public String tala() {
		if(this.file.exists()) {
			return "Ta mesmo";
		}else{
			return "Ta nada";
		}
	}

	public void lerMusica(JTextArea txtpnFullmusic){

		try{
	        FileInputStream fstream = new FileInputStream("GP/matanza-tempo-ruim(# line IN #).txt");
	        // do not use DataInputStream to read text
	        // DataInputStream in = new DataInputStream(fstream);
	        Reader reader = new InputStreamReader(fstream);
	        txtpnFullmusic.read(reader, fstream);
	    }catch(Exception er){System.err.println("Error: " + er.getMessage());}
	    txtpnFullmusic.setBounds(100, 222, 593, 528);
		
	}
	
	
	File file = new File("GP/ed-sheeran-shape-of-you(# line IN #).txt");
	
	
}
