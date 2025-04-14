package com.example.usermanagement.utils.requests;

import java.util.List;

public class EmailRequest {

    private String subject;

    private String message;

    private String pathToAttachment;

    private List<String> emailList;

    private String fromEmail;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPathToAttachment() {
        return pathToAttachment;
    }

    public void setPathToAttachment(String pathToAttachment) {
        this.pathToAttachment = pathToAttachment;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", pathToAttachment='" + pathToAttachment + '\'' +
                ", emailList=" + emailList +
                ", fromEmail='" + fromEmail + '\'' +
                '}';
    }
}
