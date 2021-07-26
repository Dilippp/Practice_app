package com.nineleaps.banking.service;

import com.nineleaps.banking.dto.CommentDto;
import com.nineleaps.banking.events.comment.CommentCreated;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ApplicationEventPublisher publisher;

    @Transactional
    public CommentDto handleComment(Long activityId, CommentDto commentDto) {
        // AbstractActivity activity = activityService.findById(activityId);
        publisher.publishEvent(
                new CommentCreated(
                        commentDto, null, null /*activity.getId(), activity.getExternalType()*/));
        return commentDto; // activity.getComment();
    }
}
