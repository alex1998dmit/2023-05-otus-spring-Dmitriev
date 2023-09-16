package com.example.homework6.service;

import com.example.homework6.dao.BookDao;
import com.example.homework6.dao.CommentDao;
import com.example.homework6.domain.Book;
import com.example.homework6.domain.Comment;
import com.example.homework6.utils.ConsoleReader;
import com.example.homework6.utils.ConsoleReaderImpl;
import com.example.homework6.utils.ConsoleWriter;
import com.example.homework6.utils.ConsoleWriterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    private final BookDao bookDao;

    private final ConsoleWriter consoleWriter = new ConsoleWriterImpl();

    private ConsoleReader consoleReader = new ConsoleReaderImpl();

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, BookDao bookDao) {
        this.commentDao = commentDao;
        this.bookDao = bookDao;
    }

    public CommentServiceImpl(CommentDao commentDao, BookDao bookDao, ConsoleReader consoleReader) {
        this.commentDao = commentDao;
        this.bookDao = bookDao;
        this.consoleReader = consoleReader;
    }

    @Override
    public void createComment() {
        String comment = consoleReader.read("Enter comment: ");
        Long bookId = consoleReader.readLong("Enter book id: ");
        Book book = bookDao.getById(bookId);

        commentDao.createComment(
                Comment.builder()
                        .text(comment)
                        .book(book)
                        .build()
        );
        consoleWriter.write("Comment added.");
    }

    @Override
    public void updateComment() {
        Long commentId = readCommentId();
        String comment = consoleReader.read("Enter comment: ");
        Long bookId = consoleReader.readLong("Enter book id: ");
        Book book = bookDao.getById(bookId);

        commentDao.updateComment(
                Comment.builder()
                        .id(commentId)
                        .text(comment)
                        .book(book)
                        .build()
        );
        consoleWriter.write("Comment updated.");
    }

    @Override
    public void deleteComment() {
        Long commentId = readCommentId();
        commentDao.deleteComment(commentId);
        consoleWriter.write("Comment deleted.");
    }

    @Override
    public Comment getById() {
        Long commentId = readCommentId();
        Comment comment = commentDao.getById(commentId);
        consoleWriter.writeEntityInfo(comment);
        return comment;
    }

    @Override
    public List<Comment> getAll() {
        List<Comment> comments = commentDao.getAll();
        for (var comment : comments) {
            consoleWriter.writeEntityInfo(comment);
        }
        return comments;
    }

    private Long readCommentId() {
        return consoleReader.readLong("Enter comment id: ");
    }
}
