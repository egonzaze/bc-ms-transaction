package pe.nttdata.bc.transaction.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.nttdata.bc.transaction.business.DTO.TransactionAllDTO;
import pe.nttdata.bc.transaction.business.DTO.TransactionDTO;
import pe.nttdata.bc.transaction.business.model.TransactionSaleRequest;
import pe.nttdata.bc.transaction.business.model.ProductsWithBalance;
import pe.nttdata.bc.transaction.business.model.TransactionAllRequest;
import pe.nttdata.bc.transaction.business.model.TransactionRequest;
import pe.nttdata.bc.transaction.business.model.TransactionResponse;
import pe.nttdata.bc.transaction.business.model.TypeOperation;
import pe.nttdata.bc.transaction.repository.TransactionRepository;
import pe.nttdata.bc.transaction.repository.impl.TransactionRepositoryImpl;
import pe.nttdata.bc.transaction.service.TransactionMealService;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionMealService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Flux<ProductsWithBalance> getBalanceAllProducts(TransactionSaleRequest accountRequest) {
        return transactionRepository.getAllSales(accountRequest);
    }

    @Override
    public Flux<TransactionAllDTO> getTransactionAll(TransactionAllRequest transactionAllRequest) {
        return this.transactionRepository.getAll();
    }

    @Override
    public TransactionResponse addOperation(TransactionRequest transactionRequest) {
        return this.processOperation(transactionRequest);
    }

    private TransactionResponse processOperation(TransactionRequest transactionRequest) {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .idTransaction(UUID.randomUUID().toString())
                .accountId(transactionRequest.getAccount().getAccountId())
                .productId(transactionRequest.getAccount().getProductId())
                .typeOperation(transactionRequest.getTypeOperation())
                .amount(transactionRequest.getAmount())
                .description(this.getDescription(transactionRequest.getTypeOperation()))
                .build();

        return this.transactionRepository.save(transactionDTO);
    }

    private String getDescription(TypeOperation typeOperation) {
        return switch (typeOperation) {
            case DEPOSIT -> String.format("%s - Abono en Cuenta",TypeOperation.DEPOSIT.toString());
            case WITHDRAWAL -> String.format("%s - Retiro en Cuenta",TypeOperation.WITHDRAWAL.toString());
        };
    }
}
