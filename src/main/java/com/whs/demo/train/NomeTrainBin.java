package com.whs.demo.train;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class NomeTrainBin {
	
	//Where to save the model once trained	
	static String onlpModelPath = "./pt-ner-person.bin";
	// location of the training data set
	static String trainingDataFilePath = "./nomes-train.txt";

	public static void main(String[] args) throws IOException {

		Charset charset = Charset.forName("UTF-8");

		ObjectStream lineStream = new PlainTextByLineStream(() -> new FileInputStream(trainingDataFilePath), charset);

		ObjectStream sampleStream = new NameSampleDataStream(lineStream);

		TokenNameFinderModel model;
		TokenNameFinderFactory nameFinderFactory = new TokenNameFinderFactory();

		try {
			model = NameFinderME.train("pt", "person", sampleStream, TrainingParameters.defaultParams(),
					nameFinderFactory);
		} finally {
			sampleStream.close();
		}
		//Saving the model
		BufferedOutputStream modelOut = null;
		try {
			modelOut = new BufferedOutputStream(new FileOutputStream(onlpModelPath));
			model.serialize(modelOut);
		} finally {
			if (modelOut != null)
				modelOut.close();
		}
	}
}