package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import com.springboot.dto.PostDto;
import com.springboot.dto.PostResponse;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postReository;
	
	private ModelMapper mapper;
	
	public PostServiceImpl(PostRepository postReository, ModelMapper mapper) {
		super();
		this.postReository = postReository;
		this.mapper = mapper;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		
		
		/*
		 * Post post= new Post(); post.setTitle(postDto.getTitle());
		 * post.setDescription(postDto.getDescription());
		 * post.setContent(postDto.getContent());
		 */
		//convert DTo to entity
		 Post post= mapToEntity(postDto);
		 Post newPost = postReository.save(post);
		
		// convert entity to DTO
		PostDto postResponse = mapToDTO(newPost);
		
		return postResponse;
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		// create pageable instance 
		Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
		Page<Post> posts = postReository.findAll(pageable);
		
		// get content from page object 
		List<Post> listOfPosts= posts.getContent();
		List<PostDto> content = listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		return postResponse;
		
	}
	
	@Override
	public PostDto getPostById(long id) {
		Post post = postReository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("post", "id", id));
		
		return mapToDTO(post);
	}
	
	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		
		//get post by id froim datebase and if not availabel throw  exception
		Post post = postReository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("post", "id", id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatePost = postReository.save(post);
		return mapToDTO(updatePost);
	}
	
	@Override
	public void deletePostById(long id) {
		Post post = postReository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("post", "id", id));
		postReository.delete(post);
		
	}


	//converted entity into dto
	private PostDto mapToDTO(Post post) {
		PostDto postDto = mapper.map(post, PostDto.class);
//		PostDto postDto= new PostDto();
//		postDto.setId(post.getId());
//		postDto.setTitle(post.getTitle());
//		postDto.setDescription(postDto.getDescription());
//		postDto.setContent(post.getContent());
		return postDto;
	}
	
	//convert DTo to entity
	private Post mapToEntity(PostDto postDto) {
		Post post = mapper.map(postDto, Post.class);
//		Post post= new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		return post;
	}



	
	
}
