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

import javax.net.ssl.HttpsURLConnection;

public class matriz {
	static Scanner sc = new Scanner(System.in);
	static final String inicialUrl = "https://www.cifraclub.com.br/";
	static final String cc = "https://www.cifraclub.com.br/";
	static String cantor = "";
	static String musica = "";

	static URL linkMusic() throws MalformedURLException {
		/*
		 * Pares = nomes Impares = links. (0Nome+1Link) (2Nome+3Link) (4Nome+5Link)
		 */
		System.out.print("Digite a banda >");
		String cantorSC = sc.nextLine();
//		String cantorSC = "gabriela rocha";

		System.out.print("Digite a musica >");
		String musicaSC = sc.nextLine();
//		String musicaSC = "eu navegarei";

		String cantor = cantorSC.replaceAll(" ", "-");
		String musica = musicaSC.replaceAll(" ", "-");

		String complemento = cantor + "/" + musica + "/" + "guitarpro" + "/";

		matriz.cantor = cantor;
		matriz.musica = musica;

		URL siteFull = new URL(cc + complemento);
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
			String inputLine = in.readLine(); // Pula linhas.
			String link = "";
			while (in.ready()) {
				if (inputLine.contains(find)) {
					link = inputLine;
				}

				fullPag.add(inputLine);
				inputLine = in.readLine();
			}

			if (link.isEmpty()) {
				in.close();
				sr.close();
				con.disconnect();
				System.out.println("Não tem uma versão Guitar Pro!");
				return fullPag.toArray(new String[fullPag.size()]);
			} else {

				links = link.split("\"");
				for (int i = 0; i < links.length; i++) {
					if (links[i].contains("ajax")) {
						arquivos.add(links[(i + 3)]);
						arquivos.add(links[i]);
					}
				}
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
		System.out.println("Encontrei as seguintes versï¿½es :)");

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

			InputStream is = links[i].openStream();
			FileOutputStream fos = new FileOutputStream(file);

			int bytes = 0;

			while ((bytes = is.read()) != -1) {
				fos.write(bytes);
			}
			is.close();
			fos.close();
		}

		System.out.println("Download concluï¿½do com sucesso!");
	}

	static void download(String[] linhas) throws IOException {
		System.out.println("Fazendo download ");
		String titulo = cantor + "-" + musica;

		File file = new File("GP\\" + titulo + "(# line IN #)." + "txt");
		file.createNewFile();

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (int contador = 0; contador < linhas.length; contador++) {
				bw.write(linhas[contador]);
				bw.write("");
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
		System.out.println("Download concluído com sucesso!");
	}

	static String[] textHandler(String[] sourceHtml) {

//		System.out.println(sourceHtml.length);
		String[] handled = new String[sourceHtml.length];

		for (int i = 0; i < sourceHtml.length; i++) {
			handled[i] = sourceHtml[i].replaceAll("<u>", "").replaceAll("</u>", "").replaceAll("<b>", "")
					.replaceAll("</b>", "");
//			System.out.println(handled[i]);

		}

		return handled;
	}

	static void noGP(String[] handledText) throws IOException {

//		System.out.println(handledText.length);
		String padrao_2 = "<pre>";
		String padrao_3 = "</pre>";

		int pad_2 = 0, pad_3 = 0;

//		System.out.println(handledText.length);

		List<String> primeira = new ArrayList<>();

		for (int i = 0; i < handledText.length; i++) {

			if (handledText[i].contains(padrao_2)) {
				pad_2 += i;
//				System.out.println("padrï¿½o 2. Linha >" + pad_2);
			}

			if (handledText[i].contains(padrao_3)) {
				pad_3 += i;
//				System.out.println("padrï¿½o 3. Linha >" + pad_3);
				break;
			}
		}

		for (int i = pad_2; i < pad_3; i++) {

			System.out.println(handledText[i]);

		}
	}

	public static void main(String[] args) throws Exception {

		System.out.println("Iniciando ");

		URL linkMusic = linkMusic();

		String[] checkLinks = checkLinks(linkMusic);

		String[] nomes = nomeVersoes(checkLinks);

		URL[] versoes = versoes(checkLinks);

		String[] handled = textHandler(checkLinks);

		noGP(handled);

		System.out.println();
		if (checkLinks.length > 0) {
			download(versoes, nomes);
		} else if(checkLinks[0] == null) {
			download(handled);
//		}
		}
	}
}