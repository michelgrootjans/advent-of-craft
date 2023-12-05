package blog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ArticleTests {
    @Test
    void article_equality() {
        var article = new Article("a name", "some content");

        assertThat(article).isEqualTo(new Article("a name", "some content"));
    }

    @Test
    void it_should_add_a_comment_with_the_given_text() {
        var article = buildArticle();

        article.addComment("Amazing article !!!", "Pablo Escobar");

        assertThat(article.getComments()).containsExactly(
            new Comment("Amazing article !!!", "Pablo Escobar", LocalDate.now())
        );
    }

    @Test
    void it_should_throw_an_exception_when_adding_existing_comment() {
        var article = buildArticle();
        article.addComment("Amazing article !!!", "Pablo Escobar");

        assertThatThrownBy(() -> article.addComment("Amazing article !!!", "Pablo Escobar"))
            .isInstanceOf(CommentAlreadyExistException.class);
    }

    private Article buildArticle() {
        return new Article(
            "Lorem Ipsum",
            "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
    }
}
