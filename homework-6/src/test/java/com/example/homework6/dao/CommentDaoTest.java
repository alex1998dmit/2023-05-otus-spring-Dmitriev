package com.example.homework6.dao;

import com.example.homework6.domain.Comment;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CommentsRepository tests")
@DataJpaTest
@Import({CommentDaoImpl.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup("book-dataset.xml")
public class CommentDaoTest {
    private final Comment newComment = Comment.builder()
            .text("new test comment")
            .book(BookDaoTest.lordOfTheRings)
            .build();

    @Autowired
    private CommentDao commentDao;

    @Test
    void getCommentsTest() {
        List<Comment> comments = commentDao.getAll();
        assertThat(comments).hasSize(2);
        assertThat(comments.get(0).getId()).isEqualTo(1000L);
        assertThat(comments.get(1).getId()).isEqualTo(2000L);
    }

    @Test
    void getCommentByIdTest() {
        Comment comment = commentDao.getById(1000L);
        assertThat(comment).isNotNull();
        assertThat(comment.getId()).isEqualTo(1000L);
    }

    @Test
    void deleteCommentTest() {
        List<Comment> commentList;
        Comment addedComment = commentDao.createComment(newComment);
        commentList = commentDao.getAll();
        newComment.setId(addedComment.getId());
        assertThat(commentList).hasSize(3);

        commentDao.deleteComment(addedComment.getId());
        commentList = commentDao.getAll();
        assertThat(commentList).hasSize(2);
        assertThat(commentDao.getById(addedComment.getId())).isNull();
    }

    @Test
    void updateCommentTest() {
        Long commentId = 1000L;
        Comment thirdComment = Comment.builder()
                .id(commentId)
                .text("Another bit of dust")
                .build();
        commentDao.updateComment(thirdComment);
        Comment addedComment = commentDao.getById(commentId);
        thirdComment.setId(commentId);
        assertThat(addedComment).isEqualTo(thirdComment);
    }

    @Test
    void creatCommentTest() {
        Comment addedComment = commentDao.createComment(newComment);
        newComment.setId(addedComment.getId());
        List<Comment> commentList = commentDao.getAll();
        assertThat(commentList).hasSize(3);
        assertThat(commentDao.getById(addedComment.getId())).isNotNull();
    }
}
