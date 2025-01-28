package ru.netology.inherit;

public class Meeting extends Task {

    protected String topic;
    protected String project;
    protected String start;

    public Meeting(int id, String topic, String project, String start) {
        super(id);
        this.topic = topic;
        this.project = project;
        this.start = start;
    }

    @Override
    public boolean matches(String query) {
        if (topic.toLowerCase().contains(query.toLowerCase())) {
            return true;
        }
        if (project.toLowerCase().contains(query.toLowerCase())) {
            return true;
        }
        return false;
    }

    public String getTopic() {
        return topic;
    }

    public String getProject() {
        return project;
    }

    public String getStart() {
        return start;
    }
}
