package com.budget_app.mapper.transaction;

import com.budget_app.domain.transaction.Transaction;
import com.budget_app.dto.transaction.TransactionRequest;
import com.budget_app.dto.transaction.TransactionResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "account", ignore = true)
    @Mapping(target = "allocation", ignore = true)
    Transaction toEntity(TransactionRequest request);

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "allocation.id", target = "allocationId")
    TransactionResponse toResponse(Transaction entity);
}
