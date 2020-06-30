package com.whs.demo.main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class NomeTrainMain {

	public static void main(String[] args) throws Exception {
		// Loading the tokenizer model
		InputStream inputStreamTokenizer = new FileInputStream("/opennlp-models/pt-token.bin");
		TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);

		// Instantiating the TokenizerME class
		TokenizerME tokenizer = new TokenizerME(tokenModel);

		// Tokenizing the sentence in to a string array
		String sentence = "A candidata Bruna Camargo foi aprovada, mas a candidata Carla Isabel não conseguiu nota para aprovação.";
		String tokens[] = tokenizer.tokenize(sentence);

		// Loading the NER-person model
		InputStream inputStreamNameFinder = new FileInputStream("./pt-ner-person.bin");
		TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);

		// Instantiating the NameFinderME class
		NameFinderME nameFinder = new NameFinderME(model);

		// Finding the names in the sentence
		Span nameSpans[] = nameFinder.find(tokens);

		// Printing the names and their spans in a sentence
		for (Span s : nameSpans)
			System.out.println(s.toString() + "  " + tokens[s.getStart()]);

		String[] names = Span.spansToStrings(nameSpans, tokens);
		for( int i = 0; i < names.length; i++ ) {
			System.out.println(names[i]);
		}
	}

}
