package com.example.homework6.shell;

import com.example.homework6.service.CommentService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class CommentShell {
    private final CommentService commentService;

    public CommentShell(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod(value = "add comment", key = {"add_comment", "ac"})
    public void createComment() {
        commentService.createComment();
    }

    @ShellMethod(value = "show comments", key = {"show_comment", "sc"})
    public void showComments() {
        commentService.getAll();
    }

    @ShellMethod(value = "delete author", key = {"delete_comment", "dc"})
    public void deleteComment() {
        commentService.deleteComment();
    }

    @ShellMethod(value = "update author", key = {"update_comment", "uc"})
    public void updateComment() {
        commentService.updateComment();
    }

    @ShellMethod(value = "show single author", key = {"show_single_comment", "ssc"})
    public void showSingleUser() {
        commentService.getById();
    }
}
