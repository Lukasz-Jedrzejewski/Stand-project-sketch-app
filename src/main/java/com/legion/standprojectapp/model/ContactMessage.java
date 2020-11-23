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
}
