package com.whs.demo.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.whs.demo.sample.SentenceSample;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceMain {

	public static void main(String[] args) throws Exception {
		String sentence = "A candidata Bruna Camargo foi aprovada, mas a candidata Carla Isabel não conseguiu nota para aprovação. Veremos se haverá lista de espera.";
		InputStream modelIn = new FileInputStream("/opt/opennlp-models/en-sent.bin");
		try {
		  SentenceModel model = new SentenceModel(modelIn);
		  SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
		  sentence = SentenceSample.SAMPLE_1;
		  String sentences[] = sentenceDetector.sentDetect(sentence);
		  for( int i = 0; i < sentences.length; i++ ) {
			  System.out.println("SENT: "+sentences[i]);
		  }
		  System.out.println("Size: "+sentences.length);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
	}

}
