package blog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ArticleTests {
    private final LocalDate today = LocalDate.now();

    @Test
    void article_equality() {
        var article = new Article("a name", "some content");

        assertThat(article).isEqualTo(new Article("a name", "some content"));
    }

    @Test
    void adding_a_comment() {
        var article = buildArticle();
        article.addComment("Amazing article !!!", "Pablo Escobar", today);

        assertThat(article.getComments()).containsExactly(
            new Comment("Amazing article !!!", "Pablo Escobar", today)
        );
    }

    @Test
    void adding_an_identical_comment_twice() {
        var article = buildArticle();
        article.addComment("Amazing article !!!", "Pablo Escobar", today);

        assertThatThrownBy(() -> article.addComment("Amazing article !!!", "Pablo Escobar", today))
            .isInstanceOf(CommentAlreadyExistException.class);
    }

    private Article buildArticle() {
        return new Article(
            "Lorem Ipsum",
            "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
    }
}
