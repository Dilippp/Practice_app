package com.nineleaps.banking.controller;

import com.nineleaps.banking.constant.AppConstants;
import com.nineleaps.banking.entity.Characteristic;
import com.nineleaps.banking.repository.entity_graph.CharacteristicsRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CharacteristicController {

    private final CharacteristicsRepository characteristicsRepository;

    @GetMapping(
            value = "/characteristics/{type}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Fetch a characteristics from the system")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = AppConstants.SUCCESS),
                @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
                @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
                @ApiResponse(code = 404, message = AppConstants.RESOURCE_NOT_FOUND),
                @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
            })
    public Characteristic find(@PathVariable("type") String type) {
        return characteristicsRepository.findByType(type);
    }
}
