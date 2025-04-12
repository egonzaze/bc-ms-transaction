package pe.nttdata.bc.transaction.repository.impl;

import org.springframework.stereotype.Service;
import pe.nttdata.bc.transaction.business.DTO.TransactionAllDTO;
import pe.nttdata.bc.transaction.business.DTO.TransactionDTO;
import pe.nttdata.bc.transaction.business.model.ProductsWithBalance;
import pe.nttdata.bc.transaction.business.model.TransactionResponse;
import pe.nttdata.bc.transaction.business.model.TransactionSaleRequest;
import pe.nttdata.bc.transaction.business.model.TypeMessage;
import pe.nttdata.bc.transaction.repository.TransactionRepository;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionRepositoryImpl implements TransactionRepository {

    private final List<TransactionDTO> transactionListDummy = new ArrayList<>();

    @Override
    public TransactionResponse save(TransactionDTO transactionDTO) {
        transactionListDummy.add(transactionDTO);
        return new TransactionResponse(
                TypeMessage.CODE_SUCCESSFUL.getCode(),
                TypeMessage.CODE_SUCCESSFUL.getMsj());
    }

    @Override
    public Flux<TransactionAllDTO> getAll() {
        return Flux.fromIterable(this.transactionListDummy)
                .map(transaction -> TransactionAllDTO.builder()
                        .transactionId(transaction.getIdTransaction())
                        .typeOperation(transaction.getTypeOperation())
                        .accountId(transaction.getAccountId())
                        .productId(transaction.getProductId())
                        .description(transaction.getDescription())
                        .amount(transaction.getAmount())
                        .build());
    }

    @Override
    public Flux<ProductsWithBalance> getAllSales(TransactionSaleRequest accountRequest) {
        return Flux.fromIterable(this.transactionListDummy)
                .filter(transaction -> accountRequest.getAccountId().contains(transaction.getAccountId()))
                .doOnNext(item -> System.out.println(String.format("saldo: %s - Datos %s - Account %s", item.getAmount(), item.getTypeOperation(), item.getAccountId())))
                .groupBy(TransactionDTO::getAccountId)
                .flatMap(accountGroup ->
                        accountGroup
                                .groupBy(TransactionDTO::getProductId)
                                .flatMap(
                                        typeOperationGroup ->
                                                typeOperationGroup
                                                        .reduce(0.0, (acc, transaction)->
                                                                switch (transaction.getTypeOperation()) {
                                                                    case DEPOSIT -> acc + transaction.getAmount();
                                                                    case WITHDRAWAL -> acc - transaction.getAmount();
                                                                }

                                        ).map(item -> ProductsWithBalance.builder()
                                                                .productId(typeOperationGroup.key().toString())
                                                                .accountId(accountGroup.key())
                                                                .amount(item)
                                                                .build())
                                )
                );
    }

}
