package blog;

import static java.util.Collections.emptyList;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Article {
    private final String name;
    private final String content;
    private final List<Comment> comments;

    public Article(String name, String content) {
        this.name = name;
        this.content = content;
        this.comments = emptyList();
    }

    private Article(String name, String content, List<Comment> comments) {
        this.name = name;
        this.content = content;
        this.comments = comments;
    }

    public Article addComment(
            String text,
            String author,
            LocalDate creationDate) {
        var comment = new Comment(text, author, creationDate);

        if (comments.contains(comment)) {
            throw new CommentAlreadyExistException();
        }

        return new Article(name, content, append(comments, comment));
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Article article = (Article) o;

        if (!Objects.equals(name, article.name)) {
            return false;
        }
        if (!Objects.equals(content, article.content)) {
            return false;
        }
        return comments.equals(article.comments);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + comments.hashCode();
        return result;
    }

    private <T> List<T> append(List<T> list, T comment) {
        return Stream.concat(list.stream(), Stream.of(comment)).toList();
    }
}

