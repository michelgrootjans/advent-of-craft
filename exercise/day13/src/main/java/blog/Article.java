package blog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Article {
    private final String name;
    private final String content;
    private final ArrayList<Comment> comments;

    public Article(String name, String content) {
        this.name = name;
        this.content = content;
        this.comments = new ArrayList<>();
    }

    void addComment(
        String text,
        String author,
        LocalDate creationDate
    ) throws CommentAlreadyExistException {
        var comment = new Comment(text, author, creationDate);

        if (comments.contains(comment)) {
            throw new CommentAlreadyExistException();
        } else comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }
}

