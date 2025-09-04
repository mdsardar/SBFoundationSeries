package tech.transactionpropagation.org.controller;

import org.springframework.web.bind.annotation.*;
import tech.transactionpropagation.org.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long senderId,
                           @RequestParam Long receiverId,
                           @RequestParam Long amount) {
        try {
            walletService.transfer(senderId, receiverId, amount);
        } catch (Exception e) {
            return "Transfer failed: " + e.getMessage();
        }
        return "Transfer completed";
    }
}
