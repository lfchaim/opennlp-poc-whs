package com.whs.demo.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class NERDemo {

//Important Note: Run time may take up to 15-20 minutes... 
// Go to project folder to see the obtained output file after run finishes...  

	public static void main(String[] args) throws FileNotFoundException, IOException {

		File outputFile = new File("cmput690w16a1_ATAKISHIYEV.tsv");

		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

		Scanner scanner = new Scanner(new File("cmput690a1_documents.txt"));

		int index = 0;

		while (scanner.hasNext()) {

			String sentence = scanner.nextLine();

			try {

				InputStream tokenStream = new FileInputStream(new File("/opt/opennlp-models/en-token.bin"));

				TokenizerModel tokenModel = new TokenizerModel(tokenStream);

				Tokenizer tokenizer = new TokenizerME(tokenModel);

				String modelNames[] = { "/opt/opennlp-models/en-ner-person.bin", "/opt/opennlp-models/en-ner-location.bin", "/opt/opennlp-models/en-ner-organization.bin" };

				ArrayList<String> list = new ArrayList();

				for (String name : modelNames) {

					TokenNameFinderModel entityModel = new TokenNameFinderModel(new FileInputStream(new File(name)));

					NameFinderME nameFinder = new NameFinderME(entityModel);

					String tokens[] = tokenizer.tokenize(sentence);

					Span nameSpans[] = nameFinder.find(tokens);

					for (Span span : nameSpans) {

						list.add(span.getType() + '\t' + tokens[span.getStart()] + '\t' + index);
					}

					for (String element : list) {

						bw.write(element + '\n');
					}
					index++;
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}

		bw.close();
	}
}