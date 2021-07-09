package com.nineleaps.banking.controller;

import com.nineleaps.banking.constant.AppConstants;
import com.nineleaps.banking.dto.TransactionDto;
import com.nineleaps.banking.dto.TransactionDtos;
import com.nineleaps.banking.entity.Transaction;
import com.nineleaps.banking.mapper.TransactionMapper;
import com.nineleaps.banking.service.TransactionsService;
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
@Api(value = "Transaction resource rest endpoint", tags = "Shows the transaction information")
public class TransactionsController {

    private final TransactionsService transactionsService;
    private final TransactionMapper transactionMapper;

    @GetMapping(value = "/transactions",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Fetch all transactions from the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AppConstants.SUCCESS),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.RESOURCE_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public TransactionDtos getTransactions() {
        List<Transaction> allTransactions = transactionsService.getAllTransactions();

        TransactionDtos transactionDtos = TransactionDtos
                .builder()
                .build();
        transactionDtos.setTransactionDto(allTransactions
                .stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList()));
        return transactionDtos;
    }

    @GetMapping(value = "/transactions/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Fetch single transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AppConstants.SUCCESS),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.RESOURCE_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public TransactionDto find(@PathVariable("id") Integer id) {
        return transactionMapper.toDto(transactionsService.getTransactionById(id));
    }

    @GetMapping(value = "/accounts/{id}/transactions",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Fetch all transactions for an account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AppConstants.SUCCESS),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.RESOURCE_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public TransactionDtos findByAccountId(@PathVariable("id") Integer id) {
        List<TransactionDto> transactionDtos = transactionsService.findByAccountId(id).stream()
                .map(transactionMapper::toDto).collect(Collectors.toList());
        return TransactionDtos.builder().transactionDto(transactionDtos).build();
    }

    @GetMapping(value = "/accounts/{accountId}/transactions/{transactionId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Fetch single transaction for an account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AppConstants.SUCCESS),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.RESOURCE_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public TransactionDto findByAccountIdAndTransactionId(@PathVariable("accountId") Integer accountId,
                            @PathVariable("transactionId") Integer transactionId) {
        return transactionMapper.toDto(transactionsService
                .findByAccountIdAndTransactionId(accountId, transactionId));
    }

    @PostMapping(value = "/transactions",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "Create new transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = AppConstants.CREATED_SUCCESSFULLY),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 400, message = AppConstants.BAD_REQUEST),
            @ApiResponse(code = 422, message = AppConstants.UN_PROCESSABLE_ENTITY),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public TransactionDto create(@RequestBody @Valid TransactionDto transactionDto) {
        return transactionMapper.toDto(transactionsService.createOrUpdateTransaction(transactionDto));
    }

    @PutMapping(value = "/transactions/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Update the existing transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AppConstants.SUCCESS),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 400, message = AppConstants.BAD_REQUEST),
            @ApiResponse(code = 404, message = AppConstants.RESOURCE_NOT_FOUND),
            @ApiResponse(code = 422, message = AppConstants.UN_PROCESSABLE_ENTITY),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public TransactionDto update(@RequestBody @Valid TransactionDto transactionDto, @PathVariable("id") Integer id) {
        transactionDto.setId(id);
        return transactionMapper.toDto(transactionsService.createOrUpdateTransaction(transactionDto));
    }

    @DeleteMapping(value = "/transactions/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a single transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = AppConstants.DELETED_SUCCESSFULLY),
            @ApiResponse(code = 401, message = AppConstants.UNAUTHORIZED_ACCESS),
            @ApiResponse(code = 403, message = AppConstants.ACCESS_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.RESOURCE_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_SERVER_ERROR)
    })
    public void delete(@PathVariable("id") Integer id) {
        transactionsService.deleteTransaction(id);
    }
}
