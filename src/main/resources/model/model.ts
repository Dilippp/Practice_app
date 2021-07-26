/* tslint:disable */
/* eslint-disable */

/**
 * All the details related to Account
 */
export interface AccountDto {
    id: number;
    name: string;
    type: string;
    openDate: Date;
}

export interface AccountDtos {
    accountDto: AccountDto[];
}

export interface CommentDto {
    text: string;
}

/**
 * All the details related to Transaction
 */
export interface TransactionDto {
    id: number;
    type: string;
    amount: number;
    description: string;
    accountDto: AccountDto;
}

export interface TransactionDtos {
    transactionDto: TransactionDto[];
}
