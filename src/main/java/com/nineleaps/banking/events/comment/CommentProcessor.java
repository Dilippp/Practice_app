package com.nineleaps.banking.events.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentProcessor {

    @EventListener
    public void handleCommentCreation(CommentCreated commentCreated) {
        // Spring will find the specific event listener method based on argument CommentCreated
        log.info(
                "Request for comment creation. The comment is: {}",
                commentCreated.getCommentDto().getText());
        // once event caught try to call the specific services based on the
        // commentCreated.getExternalType()
    }
}
