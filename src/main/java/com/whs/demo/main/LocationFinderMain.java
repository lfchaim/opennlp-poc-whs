package com.whs.demo.main;

import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class LocationFinderMain {
	public static void main(String args[]) {

		try {

			InputStream inputStreamTokenizer = new FileInputStream("/opt/opennlp-models/en-token.bin");
			TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);

			// String paragraph = "Mike and Smith are classmates";
			String paragraph = "Tutorialspoint is located in Hyderabad";

			// Instantiating the TokenizerME class
			TokenizerME tokenizer = new TokenizerME(tokenModel);
			String tokens[] = tokenizer.tokenize(paragraph);

			// Loading the NER-location model
			InputStream inputStreamNameFinder = new FileInputStream("/opt/opennlp-models/en-ner-location.bin");
			TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);

			// Instantiating the NameFinderME class
			NameFinderME nameFinder = new NameFinderME(model);

			// Finding the names of a location
			Span nameSpans[] = nameFinder.find(tokens);
			// Printing the spans of the locations in the sentence
			for (Span s : nameSpans)
				System.out.println(s.toString() + "  " + tokens[s.getStart()]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}