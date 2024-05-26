package com.telusko.joblisting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.telusko.joblisting.Model.Post;
import com.telusko.joblisting.Repository.SearchRepository;

@Component
public class SearchImplementation implements SearchRepository{
	
	@Autowired
	MongoClient client;
	
	@Autowired
	MongoConverter converter;
	
	@Override
	public List<Post> searchByText(String text) {
		final List<Post> posts = new ArrayList<>();
		
		MongoDatabase database = client.getDatabase("Telusko");
		MongoCollection<Document> collection = database.getCollection("JobPost");
		
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
		    new Document("index", "default").append("text", 
		    new Document("query", text).append("path", Arrays.asList("techs", "desc", "profile")))), 
		    new Document("$sort", 
		    new Document("exp", -1L)), 
		    new Document("$limit", 5L)));
		
		result.forEach(element -> posts.add(converter.read(Post.class, element)));
		return posts;
	}
	
	
	// we use final keyword when there's no need to change the variable. 
}
