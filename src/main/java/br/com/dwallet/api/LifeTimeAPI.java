package br.com.dwallet.api;

import br.com.dwallet.model.dto.LifeTimeDTO;
import br.com.dwallet.model.dto.WalletAccountLifeTimeDTO;
import br.com.dwallet.service.LifeTimeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{idUser}/operation/")
public class LifeTimeAPI {

    private final LifeTimeService lifeTimeService;

    public LifeTimeAPI(LifeTimeService lifeTimeService) {
        this.lifeTimeService = lifeTimeService;
    }

    @GetMapping("/lifetime")
    public ResponseEntity<LifeTimeDTO> getLifeTime(@PathVariable(name = "idUser") String idUser, @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "3") int pageSize,
                                                   @RequestParam(defaultValue = "operationAt,desc") String[] sort) {
        Pageable paging = PageRequest.of(page, pageSize, Sort.by(sort));
        return ResponseEntity.ok().body(lifeTimeService.getLifeTime(idUser, paging));
    }

    @GetMapping("/walletaccountlifetime/{idWalletAccount}")
    public ResponseEntity<WalletAccountLifeTimeDTO> getWalletAccountLifeTime(@PathVariable(name = "idUser") String idUser,
                                                                             @PathVariable(name = "idWalletAccount") String idWalletAccount, @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "3") int pageSize,
                                                                             @RequestParam(defaultValue = "operationAt,desc") String[] sort) {
        Pageable paging = PageRequest.of(page, pageSize, Sort.by(sort));
        return ResponseEntity.ok().body(lifeTimeService.getWalletAccountLifeTime(idUser, idWalletAccount, paging));
    }

}
