package pe.nttdata.bc.transaction.repository;

import org.springframework.stereotype.Service;
import pe.nttdata.bc.transaction.business.DTO.TransactionAllDTO;
import pe.nttdata.bc.transaction.business.DTO.TransactionDTO;
import pe.nttdata.bc.transaction.business.model.ProductsWithBalance;
import pe.nttdata.bc.transaction.business.model.TransactionResponse;
import pe.nttdata.bc.transaction.business.model.TransactionSaleRequest;
import reactor.core.publisher.Flux;

@Service
public interface TransactionRepository {
    TransactionResponse save(TransactionDTO transactionDTO);

    Flux<TransactionAllDTO> getAll();

    Flux<ProductsWithBalance> getAllSales(TransactionSaleRequest accountRequest);
}
