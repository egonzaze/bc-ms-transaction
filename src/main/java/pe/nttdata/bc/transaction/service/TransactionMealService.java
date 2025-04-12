package pe.nttdata.bc.transaction.service;

import org.springframework.stereotype.Service;
import pe.nttdata.bc.transaction.business.DTO.TransactionAllDTO;
import pe.nttdata.bc.transaction.business.model.TransactionSaleRequest;
import pe.nttdata.bc.transaction.business.model.ProductsWithBalance;
import pe.nttdata.bc.transaction.business.model.TransactionAllRequest;
import pe.nttdata.bc.transaction.business.model.TransactionResponse;
import pe.nttdata.bc.transaction.business.model.TransactionRequest;
import reactor.core.publisher.Flux;

@Service
public interface TransactionMealService {
    Flux<ProductsWithBalance> getBalanceAllProducts(TransactionSaleRequest accountRequest);

    TransactionResponse addOperation(TransactionRequest transactionRequest);

    Flux<TransactionAllDTO> getTransactionAll(TransactionAllRequest transactionAllRequest);
}
