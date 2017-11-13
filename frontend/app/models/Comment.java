package models;

public class Comment {
    public String content;
    public long id;

    public Comment(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
