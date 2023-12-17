package blog;

import static blog.ListHelper.append;
import static java.util.Collections.emptyList;

import java.time.LocalDate;
import java.util.List;

public class Article {
    private final String name;
    private final String content;
    private final List<Comment> comments;

    public Article(String name, String content) {
        this(name, content, emptyList());
    }

    private Article(String name, String content, List<Comment> comments) {
        this.name = name;
        this.content = content;
        this.comments = comments;
    }

    public Article addComment(String text, String author, LocalDate creationDate) throws CommentAlreadyExistException {
        var comment = new Comment(text, author, creationDate);

        if (comments.contains(comment)){ throw new CommentAlreadyExistException();}

        return new Article(name, content, append(comments, comment));
    }

    public List<Comment> comments() {
        return comments.stream().toList();
    }
}

