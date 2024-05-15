package com.example.EmailService;

public interface EmailDto{
    void sendHtmlNewProjectEmail(String email, String owner, String project, String projecttype);
}
