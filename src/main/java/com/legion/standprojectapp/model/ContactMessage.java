package com.legion.standprojectapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactMessage {
    private String name;
    private String email;
    private String phoneNumber;
    private String message;

    public String toHtml() {
        return "<div><ul><li>Od: " + name + "</li>" +
                "<li>Email: " + email + "</li>" +
                "<li>Telefon: " + phoneNumber + "</li>" +
                "<li>Treść wiadomości: " + message + "</li>" +
                "</ul></div>";
    }
}
