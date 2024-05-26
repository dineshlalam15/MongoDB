package com.telusko.joblisting.Repository;

import java.util.List;
import com.telusko.joblisting.Model.Post;

public interface SearchRepository{
	
	List<Post> searchByText(String text);

}
