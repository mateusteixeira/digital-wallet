package br.com.dwallet.api;

import br.com.dwallet.model.dto.WalletAccountDTO;
import br.com.dwallet.service.WalletAccountService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user/{idUser}/walletaccount")
public class WalletAccountAPI {

    private final WalletAccountService walletAccountService;

    public WalletAccountAPI(WalletAccountService walletAccountService) {
        this.walletAccountService = walletAccountService;
    }

    @PostMapping
    public ResponseEntity<WalletAccountDTO> createWalletAccount(@RequestBody WalletAccountDTO walletAccountDTO, @PathVariable(name = "idUser") String idUser) {
        walletAccountDTO.setIdUser(idUser);
        WalletAccountDTO savedWalletAccountDTO = this.walletAccountService.createWalletAccount(walletAccountDTO);
        URI location = getUriToHeader(savedWalletAccountDTO);
        return ResponseEntity.created(location).body(savedWalletAccountDTO);
    }

    @GetMapping
    public ResponseEntity<List<WalletAccountDTO>> getWalletAccounts(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int pageSize,
                                                  @RequestParam(defaultValue = "id,desc") String[] sort) {
        Pageable paging = PageRequest.of(page, pageSize, Sort.by(sort));
        return ResponseEntity.ok(walletAccountService.getAllWalletAccounts(paging));
    }

    @GetMapping("{id}")
    public ResponseEntity<WalletAccountDTO> getWalletAccount(@PathVariable(name = "id") String idWalletAccount) {
        return ResponseEntity.ok(walletAccountService.getWalletAccountById(idWalletAccount));
    }

    @PutMapping("{id}")
    public void updateWalletAccount(@RequestBody WalletAccountDTO walletAccountDTO, @PathVariable(name = "id") String idWalletAccount) {
        walletAccountService.updateWalletAccount(walletAccountDTO, idWalletAccount);
    }

    @DeleteMapping("{id}")
    public void deleteWalletAccount(@PathVariable(name = "id") String idWalletAccount) {
        walletAccountService.deleteWalletAccount(idWalletAccount);
    }

    @DeleteMapping
    public void deleteAllWalletAccounts() {
        walletAccountService.deleteAllWalletAccounts();
    }

    private URI getUriToHeader(WalletAccountDTO walletAccountDTO) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(walletAccountDTO.getId())
                .toUri();
    }


}
