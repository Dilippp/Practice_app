package com.nineleaps.banking.controller;

import com.nineleaps.banking.constant.AppConstants;
import com.nineleaps.banking.dto.CommentDto;
import com.nineleaps.banking.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(value = "Comment resource rest endpoint", tags = "Shows the comment information")
public class CommentController {

    private final CommentService commentService;

    @PostMapping(
            value = "/comments/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "Create a new comment")
    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = AppConstants.CREATED_SUCCESSFULLY),
                @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
                @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
                @ApiResponse(code = 400, message = AppConstants.BAD_REQUEST),
                @ApiResponse(code = 422, message = AppConstants.UN_PROCESSABLE_ENTITY),
                @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
            })
    public CommentDto create(
            @PathVariable("id") Long id, @RequestBody @Valid CommentDto commentDto) {
        return commentService.handleComment(id, commentDto);
    }
}
