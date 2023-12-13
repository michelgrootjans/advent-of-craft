package blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;

class ArticleTests {
    private Article article;
    private LocalDate now;

    @BeforeEach
    void setup() {
        article = new Article("Article title", "Article body");
        now = LocalDate.now();
    }

    @Test
    void should_add_comment_in_an_article() throws CommentAlreadyExistException {
        article.addComment("comment 1", "author 1", now);

        assertThat(article.getComments()).hasSize(1);

        assertThat(article.getComments()).containsExactly(
            new Comment("comment 1", "author 1", now)
        );
    }

    @Test
    void should_add_comment_in_an_article_containing_already_a_comment() throws CommentAlreadyExistException {

        article.addComment("comment 1", "author 1", now);
        article.addComment("comment 2", "commenter 2", now);

        assertThat(article.getComments()).containsExactlyInAnyOrder(
            new Comment("comment 1", "author 1", now),
            new Comment("comment 2", "commenter 2", now)
        );
    }

    @Nested
    class Fail {
        @Test
        void when_adding_an_existing_comment() throws CommentAlreadyExistException {
            article.addComment("comment 1", "author 1", now);

            assertThatThrownBy(() -> article.addComment("comment 1", "author 1", now))
                .isInstanceOf(CommentAlreadyExistException.class);
        }
    }
}
