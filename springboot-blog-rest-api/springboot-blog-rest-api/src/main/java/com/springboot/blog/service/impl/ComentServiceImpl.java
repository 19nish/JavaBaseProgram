package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import com.springboot.dto.CommentDto;

@Service
public class ComentServiceImpl implements CommentService {
	
	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper mapper;
	public ComentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,ModelMapper mapper) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.mapper = mapper;
	}

	// create comments for a perticular post
	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		Comment comment= mapToEntity(commentDto);
		
		//retrive post entity by  id 
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId)) ;
		
		//set post to comment entity 
		comment.setPost(post);
		
		// comment entity to db
		Comment newComment = commentRepository.save(comment);
		return mapToDTO(newComment);
	}
	
	//get all the comments on a post 
	@Override
	public List<CommentDto> getCommentByPostId(long postId) {
		//retrive comments by post id
		List<Comment> comments= commentRepository.findByPostId(postId);
		
		//convert list of comments entities into list of comment dto's
		return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
	}
	
	//get comment by its id on the post
	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {

		//Retrieve post entity by id
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId)) ;
		
		//retrieve comment by id
		Comment comment= commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId ));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not blong to post");
		}
		return mapToDTO(comment);
	}
	
	//update acomment bt id 
		@Override
		public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
			
				//Retrieve post entity by id
				   Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId)) ;
				 //retrieve comment by id
					Comment comment= commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId ));
					
				if(!comment.getPost().getId().equals(post.getId())) {
					throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post" );
				}
				
				comment.setName(commentRequest.getName());
				comment.setEmail(commentRequest.getEmail());
				comment.setBody(commentRequest.getBody());
				Comment updatedComment =commentRepository.save(comment);
						
			
			return mapToDTO(updatedComment);
		}
		
		@Override
		public void deleteComment(Long postId, Long commentId) {
			// TODO Auto-generated method stub
			//Retrieve post entity by id
			   Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId)) ;
			 //retrieve comment by id
				Comment comment= commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId ));
			if(!comment.getPost().getId().equals(post.getId())) {
					throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post" );
				}
			commentRepository.delete(comment);
			
		}
	
	
	
	private CommentDto mapToDTO(Comment comment) {
		
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
		
//		CommentDto commentDto = new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());
		return commentDto;
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = mapper.map(commentDto, Comment.class);
//		Comment comment = new Comment();
//		comment.setId(commentDto.getId());
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
//		comment.setBody(commentDto.getBody());
		return comment;
	}

	

	

	

	

}
