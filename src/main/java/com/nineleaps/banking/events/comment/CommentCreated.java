package com.nineleaps.banking.events.comment;

import com.nineleaps.banking.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentCreated {

    private final CommentDto commentDto;
    private final Long activityId;
    private final String externalType;
}
