package com.telusko.joblisting.Repository;

import com.telusko.joblisting.Model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String>{
	
}
