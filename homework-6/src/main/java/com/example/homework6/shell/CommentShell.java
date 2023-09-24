package com.example.homework6.shell;

import com.example.homework6.domain.Book;
import com.example.homework6.domain.Comment;
import com.example.homework6.service.BookService;
import com.example.homework6.service.CommentService;
import com.example.homework6.utils.ConsoleReader;
import com.example.homework6.utils.ConsoleWriter;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import java.util.List;

@ShellComponent
public class CommentShell {
    private final CommentService commentService;

    private final BookService bookService;

    private final ConsoleReader consoleReader;

    private final ConsoleWriter consoleWriter;

    public CommentShell(
            CommentService commentService,
            BookService bookService,
            ConsoleReader consoleReader,
            ConsoleWriter consoleWriter
    ) {
        this.commentService = commentService;
        this.bookService = bookService;
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
    }

    @ShellMethod(value = "add comment", key = {"add_comment", "ac"})
    public void createComment() {
        String text = consoleReader.read("Enter comment: ");
        Long bookId = consoleReader.readLong("Enter book id: ");
        Book book = bookService.getById(bookId);
        Comment newComment = Comment.builder()
                .text(text)
                .book(book)
                .build();
        commentService.createComment(newComment);
        consoleWriter.write("Comment added.");
    }

    @ShellMethod(value = "show comments", key = {"show_comment", "sc"})
    public void showComments() {
        List<Comment> comments = commentService.getAll();
        for (var comment : comments) {
            consoleWriter.writeEntityInfo(comment);
        }
    }

    @ShellMethod(value = "delete comment", key = {"delete_comment", "dc"})
    public void deleteComment() {
        Long commentId = readCommentId();
        commentService.deleteComment(commentId);
        consoleWriter.write("Comment deleted.");
    }

    @ShellMethod(value = "update author", key = {"update_comment", "uc"})
    public void updateComment() {
        Long commentId = readCommentId();
        String text = consoleReader.read("Enter comment: ");
        Long bookId = consoleReader.readLong("Enter book id: ");
        Book book = bookService.getById(bookId);
        Comment comment = Comment.builder()
                .id(commentId)
                .text(text)
                .book(book)
                .build();
        commentService.updateComment(comment);
        consoleWriter.write("Comment updated.");
    }

    @ShellMethod(value = "show single author", key = {"show_single_comment", "ssc"})
    public void showSingleComment() {
        Long commentId = readCommentId();
        Comment comment = commentService.getById(commentId);
        consoleWriter.writeEntityInfo(comment);
    }

    private Long readCommentId() {
        return consoleReader.readLong("Enter comment id: ");
    }
}
