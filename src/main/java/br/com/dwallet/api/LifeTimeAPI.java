package br.com.dwallet.api;

import br.com.dwallet.model.dto.LifeTimeDTO;
import br.com.dwallet.model.dto.OperationErrorDTO;
import br.com.dwallet.model.dto.WalletAccountLifeTimeDTO;
import br.com.dwallet.service.LifeTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/{idUser}/operation/")
public class LifeTimeAPI {

    private final LifeTimeService lifeTimeService;

    public LifeTimeAPI(LifeTimeService lifeTimeService) {
        this.lifeTimeService = lifeTimeService;
    }

    @GetMapping("/lifetime")
    public ResponseEntity<LifeTimeDTO> getLifeTime(@PathVariable(name = "idUser") String idUser, Pageable pageable) {
        log.info("Receiving request for getLifeTime");
        return ResponseEntity.ok().body(lifeTimeService.getLifeTime(idUser, pageable));
    }

    @GetMapping("/walletaccountlifetime/{idWalletAccount}")
    public ResponseEntity<WalletAccountLifeTimeDTO> getWalletAccountLifeTime(@PathVariable(name = "idUser") String idUser,
                                                                             @PathVariable(name = "idWalletAccount") String idWalletAccount, Pageable pageable) {
        log.info("Receiving request for getWalletAccountLifeTime");
        return ResponseEntity.ok().body(lifeTimeService.getWalletAccountLifeTime(idUser, idWalletAccount, pageable));
    }

    @GetMapping("/errors")
    public ResponseEntity<List<OperationErrorDTO>> getOperationErrors(@PathVariable(name = "idUser") String idUser, Pageable pageable) {
        log.info("Receiving request for getOperationErrors");
        return ResponseEntity.ok(lifeTimeService.getErrorsByUser(idUser, pageable));
    }

}
