package ru.job4j.tracker;

public abstract class BaseAction implements UserAction {
    private final String key;
    private final String name;

    protected BaseAction(final String key, final String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s - %s", this.key(), this.name);
    }
}
