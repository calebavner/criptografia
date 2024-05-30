package app.service;

import app.dto.CreateTransactionRequest;
import app.dto.TransactionResponse;
import app.model.Transaction;
import app.repo.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void create(CreateTransactionRequest request) {
        Transaction t = new Transaction();
        t.setRawUserDocument(request.userDocument());
        t.setRawCreditCardToken(request.creditCardToken());
        t.setTransactionValue(request.value());

        repository.save(t);
    }

    public TransactionResponse getById(Long id) {
        var response = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return TransactionResponse.fromEntity(response);
    }

    public Page<TransactionResponse> listAll(int page, int pageSize) {
        var response = repository.findAll(PageRequest.of(page, pageSize));
        return response.map(TransactionResponse::fromEntity);
    }

    public void removeTransaction(Long id) {
        var response = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteById(response.getId());
    }
}
