package com.dhosiolux.brainbox.models;

import java.util.UUID;

public class User {
    final UUID userId = UUID.randomUUID();
    String firstName;
    String lastName;
    String username;
    Role userRole;

}
