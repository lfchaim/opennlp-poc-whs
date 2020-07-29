package com.whs.demo.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class PortugueseNameTest {

	static String onlpModelPath = "en-ner-drugs.bin";
	// training data set
	static String trainingDataFilePath = "D:/NLPTools/Datasets/drugsDetails.txt";

	public static void main(String[] args) throws IOException {
		Charset charset = Charset.forName("UTF-8");
		InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File(trainingDataFilePath));
		ObjectStream<String> lineStream = new PlainTextByLineStream(dataIn, charset);
		ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);
		TokenNameFinderModel model = null;
		HashMap<String, Object> mp = new HashMap<String, Object>();
		TrainingParameters params = new TrainingParameters();
		params.put(TrainingParameters.ITERATIONS_PARAM, 70);
		params.put(TrainingParameters.CUTOFF_PARAM, 1);
		TokenNameFinderFactory nameFinderFactory = new TokenNameFinderFactory();
		try {
			model = NameFinderME.train("en", "drugs", sampleStream, params, nameFinderFactory);
		} finally {
			sampleStream.close();
		}
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
