package tabExpert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class CifraClub extends Site{
	
	private String titulo;
	private String autor;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public CifraClub(String cantor, String musica) {
		// Preencher com correção dos nomes,busca automática no google
		titulo = musica;
		autor = cantor;
	}

	public void print() {
		System.out.println("Musica: " + getTitulo() + "\nAutor: " + getAutor() + "\n");
	}

	String[] leituraHtml(URL siteFull) {

		List<String> fullPag = new ArrayList<String>();

		try {
			HttpsURLConnection con = (HttpsURLConnection) siteFull.openConnection();
			con.connect();
			InputStreamReader sr = new InputStreamReader(con.getInputStream());
			BufferedReader in = new BufferedReader(sr);
			String inputLine = in.readLine(); // Pula linhas.

			while (in.ready()) {
				fullPag.add(inputLine);
				inputLine = in.readLine();
			}

			in.close();
			sr.close();
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fullPag.toArray(new String[fullPag.size()]);
	}

	void cifraClub(Boolean noGP) throws MalformedURLException {

		// Implementação de todos os métodos da void main responsaveis pelo
		// download com o provedor Cifra Club

		// linkMusic()
		final String cc = "https://www.cifraclub.com.br/";
		String cantorSC = getAutor();
		String musicaSC = getTitulo();

		String cantor = cantorSC.replaceAll(" ", "-");
		String musica = musicaSC.replaceAll(" ", "-");

		matriz.cantor = cantor;
		matriz.musica = musica;

		String complementoNoGP = cantor + "/" + musica + "/";
		String complementoGP = cantor + "/" + musica + "/" + "guitarpro" + "/";

		// checkLink()

		URL siteFull;

		if (noGP) {
			siteFull = new URL(cc + complementoNoGP);

		} else {

			siteFull = new URL(cc + complementoGP);
			String[] links = leituraHtml(siteFull);

			List<String> fullPag = new ArrayList<>();
			List<String> arquivos = new ArrayList<>();

			String find = "/ajax";
			String link = "";

			for (int i = 0; i < links.length; i++) {

				if (links[i].contains(find)) {
					link = links[i];
				}

				fullPag.add(links[i]);
			}

			if (fullPag.toArray(new String[fullPag.size()])[1].contains("/ajax")) {
				cifraClub(true);
			}

			links = link.split("\"");
			for (int i = 0; i < links.length; i++) {
				if (links[i].contains("ajax")) {
					arquivos.add(links[(i + 3)]);
					arquivos.add(links[i]);
				}
			}

		// Precisa checar se há uma versão GUITARPRO. Tendo usa GP não sendo NoGP

		// nomeVersoes() >> COM GP

		int counter = 0;
		List<String> versoes = new ArrayList<>();
		System.out.println("Boas novas...");
		System.out.println("Encontrei as seguintes versões :)");
		String[] checkLinks = fullPag.toArray(new String[fullPag.size()]);
		
		for (int i = 0; i < checkLinks.length; i++) {
			
			String[] corte = checkLinks[i].replaceAll("VersÃ£o", "Versão").toString().split("<span>");
			URL[] linksFinal = new URL[checkLinks.length];
			for (int p = 0; p < corte.length; p++) {
				if (p == 1) {
					System.out.println(++counter + " - " + corte[p].replaceAll("</span>", ""));
					versoes.add(corte[p].replaceAll("</span>", ""));
				}
			}
		}

		System.out.println("(:  Pratique  :)");

		// download()
		// Começar daqui.
		
		String[] versions = versoes.toArray(new String[versoes.size()]);
		
		System.out.println("Fazendo download ");
		versoes.toArray(new String[versoes.size()]);
		
		for (int i = 1, q = 0; i < links.length; i += 2, q++) {
			
			File file = new File("GP\\" + titulo + "(" + versions[q] + ")."
					+ links[i].toString().substring((links[i].toString().length() - 3), links[i].toString().length()));

			InputStream is = links[i].openStream();
			FileOutputStream fos = new FileOutputStream(file);

			int bytes = 0;

			while ((bytes = is.read()) != -1) {
				fos.write(bytes);
			}
			is.close();
			fos.close();
		}

		System.out.println("Download concluído com sucesso!");
		
		
		
		// String[] nomes = nomeVersoes(checkLinks);
		// URL[] versoes = versoes(checkLinks);
		// download(versoes, nomes);
		}
	}
}