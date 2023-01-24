package com.springboot.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.service.CommentService;
import com.springboot.dto.CommentDto;

@RestController
@RequestMapping("/api/")
public class CommentController {
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
			
	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value ="postId") long postId, @Valid@RequestBody CommentDto commentDto){
	
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED); 
		
	}
	
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentByPostId( @PathVariable(value = "postId") long postID){
		return commentService.getCommentByPostId(postID);
	}
	
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "commentId") long commentId){
		CommentDto commentDto = commentService.getCommentById(postId, commentId);
		return new ResponseEntity<>(commentDto, HttpStatus.OK);
		
	}
	
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value =" postId") long postId, 
												@PathVariable(value ="commentId") long commentId,
												@Valid@RequestBody CommentDto commentDto){
		CommentDto updateComment = commentService.updateComment(postId, commentId, commentDto);
		return new ResponseEntity<>(updateComment,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable(value ="postId") Long postId,@PathVariable(value ="commentId") Long commentId) {
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<>("comment deleted succesfully", HttpStatus.OK);
	}
	
}
