package com.nineleaps.banking.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppConstants {

    public static final String SUCCESS = "Processed successfully";
    public static final String CREATED_SUCCESSFULLY = "Successfully record created";
    public static final String DELETED_SUCCESSFULLY = "Deleted record successfully";
    public static final String BAD_REQUEST = "Bad request, missing some fields in the entity";
    public static final String UNAUTHORIZED_ACCESS = "You are not authorized to view the resource";
    public static final String ACCESS_FORBIDDEN =
            "Accessing the resource you were trying to reach is forbidden";
    public static final String RESOURCE_NOT_FOUND = "Resource not found in the system";
    public static final String UN_PROCESSABLE_ENTITY = "Entity may have field validation errors";
    public static final String INTERNAL_SERVER_ERROR =
            "Something went wrong at server side while processing the request";
}
