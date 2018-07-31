package tabExpert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Handler;

import javax.net.ssl.HttpsURLConnection;

public class matriz {
	static Scanner sc = new Scanner(System.in);
	static final String inicialUrl = "https://www.cifraclub.com.br/";
	static final String cc = "https://www.cifraclub.com.br/";
	static String cantor = "";
	static String musica = "";

	static URL linkMusic(boolean noGP) throws MalformedURLException {
		/*
		 * Pares = nomes Impares = links. (0Nome+1Link) (2Nome+3Link) (4Nome+5Link)
		 */
		
		System.out.print("Digite a banda >");
		if(cantor.isEmpty()) {	// Anti Spam
			
			 String cantorSC = sc.nextLine(); // Input do usuário
//				String cantorSC = "ed sheeran"; // AutoTeste sem GP
//				 String cantorSC = "guns n roses"; // AutoTeste com GP

				System.out.print("Digite a musica >"); // Input do usuário
				 String musicaSC = sc.nextLine(); // AutoTeste sem GP
//				String musicaSC = "shape of you"; // AutoTeste com Gp
//				 String musicaSC = "welcome to the jungle";
					String cantor = cantorSC.replaceAll(" ", "-");
					String musica = musicaSC.replaceAll(" ", "-");

					matriz.cantor = cantor;
					matriz.musica = musica;
		}

		String complementoNoGP = cantor + "/" + musica + "/";
		String complementoGP = cantor + "/" + musica + "/" + "guitarpro" + "/";

		URL siteFull;
		if(noGP) {
			siteFull = new URL(cc + complementoNoGP);
		}else {
			siteFull = new URL(cc + complementoGP);
		}
		
		
		
		return siteFull;
	}

	static String[] checkLinks(URL siteFull) {

		String[] links;

		List<String> fullPag = new ArrayList<>();
		List<String> arquivos = new ArrayList<>();

		try {
			HttpsURLConnection con = (HttpsURLConnection) siteFull.openConnection();
			con.connect();
			InputStreamReader sr = new InputStreamReader(con.getInputStream());
			BufferedReader in = new BufferedReader(sr);
			String find = "/ajax";
			String link = "";
			
			String inputLine = in.readLine(); // Pula linhas.
			
			while (in.ready()) {
				if (inputLine.contains(find)) {
					link = inputLine;
				}
				
				fullPag.add(inputLine);
				inputLine = in.readLine();
			}

				links = link.split("\"");
				for (int i = 0; i < links.length; i++) {
					if (links[i].contains("ajax")) {
						arquivos.add(links[(i + 3)]);
						arquivos.add(links[i]);
					}
				}
				
				if (links[0].isEmpty()) {
					in.close();
					sr.close();
					con.disconnect();
					return fullPag.toArray(new String[fullPag.size()]);
				}
			in.close();
			sr.close();
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arquivos.toArray(new String[arquivos.size()]);
	}

	static String[] nomeVersoes(String[] checkLinks) {

		int counter = 0;
		List<String> versoes = new ArrayList<>();
		System.out.println("Boas novas...");
		System.out.println("Encontrei as seguintes versões :)");

		for (int i = 0; i < checkLinks.length; i++) {
			String[] corte = checkLinks[i].replaceAll("VersÃ£o", "Versão").toString().split("<span>");
			for (int p = 0; p < corte.length; p++) {
				if (p == 1) {
					System.out.println(++counter + " - " + corte[p].replaceAll("</span>", ""));
					versoes.add(corte[p].replaceAll("</span>", ""));
				}
			}
		}

		System.out.println("(:  Pratique  :)");
		return versoes.toArray(new String[versoes.size()]);
	}

	static URL[] versoes(String[] complementos) throws MalformedURLException {

		int quantidade = complementos.length;
		URL[] links = new URL[quantidade];

		for (int i = 0; i < quantidade; i++) {
			links[i] = new URL(cc + complementos[i]);
		}
		return links;
	}

	static void download(URL[] links, String[] versoes) throws IOException {

		System.out.println("Fazendo download ");
		String titulo = cantor + "-" + musica;

		for (int i = 1, q = 0; i < links.length; i += 2, q++) {
			File file = new File("GP\\" + titulo + "(" + versoes[q] + ")."
					+ links[i].toString().substring((links[i].toString().length() - 3), links[i].toString().length()));

			InputStream is = links[i].openStream();	//Importação de todos os bytes do arquivo GP
			FileOutputStream fos = new FileOutputStream(file);

			int bytes = 0;

			while ((bytes = is.read()) != -1) {	// Cópia dos bytes para o arquivo interno
				fos.write(bytes);
			}
			is.close();
			fos.close();
		}

		System.out.println("Download concluído com sucesso!");
	}

	static void download(String[] linhas) throws IOException {
		String titulo = cantor + "-" + musica;

		File file = new File("GP\\" + titulo + "# line IN #." + "txt");
		file.createNewFile();

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (int contador = 0; contador < linhas.length; contador++) {
				bw.write(linhas[contador]);
				bw.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("Arquivo escrito com sucesso!");
	}

	static String[] textHandler(String[] sourceHtml) {

		List<String> corpo = new ArrayList<String>();

		int contador = 0;
		boolean cifra = false; // Checa se começou a parte da cifra + letra

		do {
			if (sourceHtml[contador].contains("<pre>")) {
				cifra = true;
			}
			if (cifra) {
				corpo.add(sourceHtml[contador]); // Adiciona à lista apenas as linhas com cifra e letra.
			}

			contador++;
		} while (!(sourceHtml[contador].contains("</pre>"))); // Checa se terminou a parte da cifra + letra

		String[] handled = new String[corpo.size()];

		for (int i = 0; i < corpo.toArray().length; i++) {		// Remove tudo que não é letra e/ou cifra.
			if (((String) corpo.toArray()[i]).contains("<pre>")) {
				handled[i] = (((String) corpo.toArray()[i])
						.replaceAll("<u>", "")
						.replaceAll("<span class=\"tablatura\"><span class=\"cnt\">","")
						.replaceAll("</u>", "").replaceAll("<b>", "").replaceAll("</span>", "")
						.replaceAll("</b>", ""))
						.split("<pre>")[1];
			} else {
				handled[i] = (((String) corpo.toArray()[i]).replaceAll("<u>", "")
						.replaceAll("<span class=\"tablatura\"><span class=\"cnt\">","")
						.replaceAll("</u>", "").replaceAll("<b>", "")
						.replaceAll("</span>", "")
						.replaceAll("</b>", ""));
				}
		}
		return handled;
	}

	static void print(String[] handledText) throws IOException {


		for (int i = 0; i < handledText.length; i++) {
			System.out.println(handledText[i]);
		}
	}

	public static void main(String[] args) throws Exception {

		System.out.println("Iniciando ");

//		Musica primeira = new Musica("seu jorge","burguesinha");
//		Musica segunda = new Musica("the stroker","reptlia");
		
//		primeira.print();
//		segunda.print();
		
		String[] checkLinks = checkLinks(linkMusic(false));		// Primeiro Scan.
		
		if(checkLinks[1].contains("/ajax")) {							// Se houver versão guitar pro
			String[] nomes = nomeVersoes(checkLinks);
			URL[] versoes = versoes(checkLinks);
			download(versoes, nomes);
		}else {
			checkLinks = checkLinks(linkMusic(true));			// Scan alternativo
			String[] handled = textHandler(checkLinks);
			print(handled);
			download(handled);
		}
	}
}