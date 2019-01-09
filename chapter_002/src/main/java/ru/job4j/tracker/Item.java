package ru.job4j.tracker;

/**
 * Class Item заявка.
 * @version $Id$
 * @since 0.1
 */
public class Item {
    private String name;
    private String desc;
    private long create;
    private String[] comments;
    private String id;

    public Item(String name, String desc, long create) {
        this.name = name;
        this.desc = desc;
        this.create = create;
    }

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Item(String name) {
        this.name = name;
    }
    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getCreate() {
        return create;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String ls = System.lineSeparator();
        return String.format("The Item name: %s,%sdescription: %s,%sid: %s%s",
                this.getName(), ls, this.getDesc(), ls, this.getId(), ls);
    }
}
