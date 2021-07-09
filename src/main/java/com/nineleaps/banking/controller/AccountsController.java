package com.nineleaps.banking.controller;

import com.nineleaps.banking.constant.AppConstants;
import com.nineleaps.banking.dto.AccountDto;
import com.nineleaps.banking.dto.AccountDtos;
import com.nineleaps.banking.entity.Account;
import com.nineleaps.banking.mapper.AccountMapper;
import com.nineleaps.banking.service.AccountsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Api(value = "Account resource rest endpoint", tags = "Shows the account information")
public class AccountsController {

    private final AccountsService accountsService;
    private final AccountMapper accountMapper;

    @GetMapping(value = "/accounts",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Fetch all accounts from the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AppConstants.SUCCESS),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.RESOURCE_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public AccountDtos getAccounts() {
        List<Account> allAccounts = accountsService.getAllAccounts();

        AccountDtos accountDtos = AccountDtos
                .builder()
                .build();
        accountDtos.setAccountDto(allAccounts
                        .stream()
                        .map(accountMapper::toDto)
                        .collect(Collectors.toList()));
        return accountDtos;
    }

    @GetMapping(value = "/accounts/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Fetch an account from the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AppConstants.SUCCESS),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.RESOURCE_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public AccountDto find(@PathVariable("id") Integer id) {
        return accountMapper.toDto(accountsService.getAccountById(id));
    }

    @PostMapping(value = "/accounts",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = AppConstants.CREATED_SUCCESSFULLY),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 400, message = AppConstants.BAD_REQUEST),
            @ApiResponse(code = 422, message = AppConstants.UN_PROCESSABLE_ENTITY),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public AccountDto create(@RequestBody @Valid AccountDto accountDto) {
        return accountMapper.toDto(accountsService.createOrUpdateAccount(accountDto));
    }

    @PutMapping(value = "/accounts/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Update the existing account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AppConstants.SUCCESS),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 400, message = AppConstants.BAD_REQUEST),
            @ApiResponse(code = 404, message = AppConstants.RESOURCE_NOT_FOUND),
            @ApiResponse(code = 422, message = AppConstants.UN_PROCESSABLE_ENTITY),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public AccountDto update(@RequestBody @Valid AccountDto accountDto, @PathVariable("id") Integer id) {
        accountDto.setId(id);
        return accountMapper.toDto(accountsService.createOrUpdateAccount(accountDto));
    }

    @DeleteMapping(value = "/accounts/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an account from system")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = AppConstants.DELETED_SUCCESSFULLY),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.RESOURCE_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public void delete(@PathVariable("id") Integer id) {
        accountsService.deleteAccount(id);
    }
}
