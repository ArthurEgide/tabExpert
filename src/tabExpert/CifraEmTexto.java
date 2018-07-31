package tabExpert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

public class CifraEmTexto {
	
	public String printa() {
		return "Ola,estou aqui e funciono";
	}
	

	public void lerMusica(JTextArea txtpnFullmusic,String musica){

//		String selected = jList1.getSelectedValue();
		
		
		
		try{
	        FileInputStream fstream = new FileInputStream("GP/" + musica);
	        // do not use DataInputStream to read text
	        // DataInputStream in = new DataInputStream(fstream);
	        Reader reader = new InputStreamReader(fstream);
	        txtpnFullmusic.read(reader, fstream);
	    }catch(Exception er){System.err.println("Error: " + er.getMessage());}
	    txtpnFullmusic.setBounds(100, 222, 593, 528);
		
	}
	
	public String[] getCifras() {
		File diretorio = new File("GP/");
		String[] todosArquivos = diretorio.list();
		List<String> selecionados = new ArrayList<>();
		for(int i = 0; i < todosArquivos.length ; i++) {
			if(todosArquivos[i].contains("# line IN #")) {
				selecionados.add(todosArquivos[i]);
			}
			
		}
		
		return selecionados.toArray(new String[selecionados.size()]);
	}
	
	
	
	
}
