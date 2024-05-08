package ksmart.ks50team01.common.mail;

public class Context {
    private String subject;
    private String html;
    private StringBuilder builder;

    public Context(String subject) {
        this.subject = subject;
        this.builder = new StringBuilder();
    }

    public Context addLine(String line) {
        builder.append("<p>" + line + "</p>");
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public String getHtml() {
        html = builder.toString();
        return html;
    }

}