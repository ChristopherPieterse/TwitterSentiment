package com.twitter.client;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class NLP {
	static StanfordCoreNLP pipeline;//pipeline for info to analyze

	public static void init() {
		pipeline = new StanfordCoreNLP("MyPropFile.properties");//uses the properties ile to initiate the pipeline
	}

	public static int findSentiment(String tweet) {

		int mainSentiment = 0;
		if (tweet != null && tweet.length() > 0) {//makes sure there is an actual tweet to check
			int longest = 0;
			Annotation annotation = pipeline.process(tweet);
			for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {//using the standford nlp library to guage sentiment
				Tree bush = sentence.get(SentimentAnnotatedTree.class);
				int sentiment = RNNCoreAnnotations.getPredictedClass(bush);
				String txt = sentence.toString();
				if (txt.length() > longest) {
					mainSentiment = sentiment;
					longest = txt.length();
				}

			}
		}
		return mainSentiment;
	}
}