package blog;

import static java.util.Collections.emptyList;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public record Article (String name, String content, List<Comment> comments){

    public Article(String name, String content) {
        this(name, content, emptyList());
    }

    public Article addComment(String text, String author, LocalDate creationDate) {
        var comment = new Comment(text, author, creationDate);

        if (comments.contains(comment)) {
            throw new CommentAlreadyExistException();
        }

        return new Article(name, content, append(comments, comment));
    }

    // too bad java doesn't support this out of the box
    private <T> List<T> append(List<T> list, T comment) {
        return Stream.concat(list.stream(), Stream.of(comment)).toList();
    }
}

