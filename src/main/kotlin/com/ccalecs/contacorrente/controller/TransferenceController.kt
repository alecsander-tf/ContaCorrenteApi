package com.ccalecs.contacorrente.controller

import com.ccalecs.contacorrente.response.StatusResponse
import com.ccalecs.contacorrente.response.TransferenceResponse
import com.ccalecs.contacorrente.service.TransferenceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api/v1/transference"])
class TransferenceController @Autowired constructor(val transferenceService: TransferenceService) {
    @GetMapping(path = ["get-bank-statement"], params = ["clientId"])
    fun findTransferenceListByClientId(@RequestParam clientId: Long): List<TransferenceResponse> {
        return transferenceService.findTransferenceListByClientId(clientId)
    }

    @PostMapping(path = ["transfer"])
    fun transfer(
        @RequestParam clientIdSender: Long, @RequestParam clientIdReceiver: Long, @RequestParam value: Double
    ): StatusResponse {
        return transferenceService.transfer(clientIdSender, clientIdReceiver, value)
    }
}