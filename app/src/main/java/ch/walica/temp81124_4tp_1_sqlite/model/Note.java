package ch.walica.temp81124_4tp_1_sqlite.model;

import java.time.Instant;

public class Note {
    private int id;
    private String title;
    private String description;
    private long createDate;

    public Note(String title, String description) {
        this.id = -1;
        this.title = title;
        this.description = description;
        this.createDate = Instant.now().getEpochSecond();
    }

    public Note(int id, String title, String description, long createDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getCreateDate() {
        return createDate;
    }
}
