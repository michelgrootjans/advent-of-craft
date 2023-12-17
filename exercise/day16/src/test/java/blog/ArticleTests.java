package blog;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.instancio.Instancio.create;

class ArticleTests {
    private final LocalDate now = LocalDate.now();

    @Test
    void should_add_comment_in_an_article() throws CommentAlreadyExistException {
        var article = buildArticle()
            .addComment("a simple comment", "John", now);

        assertThat(article.comments()).containsExactly(
            new Comment("a simple comment", "John", now)
        );
    }

    @Test
    void should_add_comment_in_an_article_containing_already_a_comment() throws Throwable {
        var article = buildArticle()
            .addComment("a comment", "John", now)
            .addComment("another comment", "Paul", now);

        assertThat(article.comments()).containsExactly(
            new Comment("a comment", "John", now),
            new Comment("another comment", "Paul", now)
        );
    }

    @Nested
    class Fail {
        @Test
        void when__adding_an_existing_comment() throws CommentAlreadyExistException {
            var article = buildArticle()
                .addComment("a comment", "Paul", now);

            assertThatThrownBy(() -> {
                article.addComment("a comment", "Paul", now);
            }).isInstanceOf(CommentAlreadyExistException.class);
        }
    }

    private Article buildArticle() {
        return new Article(create(String.class), create(String.class));
    }
}