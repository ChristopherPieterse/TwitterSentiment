package com.twitter.client;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetManager {

	public static ArrayList<String> getTweets(String topic) {//returns all tweets as strings about a topic

		Twitter twitter4jthing = new TwitterFactory().getInstance();//instance of twitter4j
		ArrayList<String> tweetList = new ArrayList<String>();
		try {//get tweets about topic
			Query q = new Query(topic);
			QueryResult result;
			do {
				result = twitter4jthing.search(q);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					tweetList.add(tweet.getText());
				}
			} while ((q = result.nextQuery()) != null);
		} catch (TwitterException te) {//twitter exception. From twitter4j
			te.printStackTrace();
		}
		return tweetList;//returns tweet list
	}
}
