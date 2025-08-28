package com.app.pdf.access.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Data
@Document(collection = "User_details")
public class User {
        @Id
        private ObjectId id;
        @NonNull
        private String firstName;
        @NonNull
        private String surname;
        @NonNull
        private String email;
        @NonNull
        private String mobile;
        private String otp;
    }


