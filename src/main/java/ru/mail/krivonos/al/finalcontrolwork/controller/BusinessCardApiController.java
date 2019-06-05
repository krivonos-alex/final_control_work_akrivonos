package ru.mail.krivonos.al.finalcontrolwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.krivonos.al.finalcontrolwork.service.BusinessCardService;
import ru.mail.krivonos.al.finalcontrolwork.service.model.AuthUserPrincipal;
import ru.mail.krivonos.al.finalcontrolwork.service.model.BusinessCardDTO;

import javax.validation.Valid;
import java.util.List;

import static ru.mail.krivonos.al.finalcontrolwork.constant.URLConstants.API_ADMIN_CARDS_URL;
import static ru.mail.krivonos.al.finalcontrolwork.constant.URLConstants.API_ADMIN_CARDS_WITH_ID_URL;
import static ru.mail.krivonos.al.finalcontrolwork.constant.URLConstants.API_CUSTOMER_CARDS_URL;

@RestController("businessCardApiController")
public class BusinessCardApiController {

    private final BusinessCardService businessCardService;

    @Autowired
    public BusinessCardApiController(BusinessCardService businessCardService) {
        this.businessCardService = businessCardService;
    }

    @GetMapping(API_CUSTOMER_CARDS_URL)
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<BusinessCardDTO>> getBusinessCards(
            @AuthenticationPrincipal AuthUserPrincipal userPrincipal
    ) {
        List<BusinessCardDTO> businessCards = businessCardService.getBusinessCards(userPrincipal.getUserID());
        return new ResponseEntity(businessCards, HttpStatus.OK);
    }

    @PostMapping(API_ADMIN_CARDS_URL)
    @SuppressWarnings("unchecked")
    public ResponseEntity<BusinessCardDTO> saveBusinessCard(
            @RequestBody @Valid BusinessCardDTO businessCardDTO, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        BusinessCardDTO returningBusinessCard = businessCardService.add(businessCardDTO);
        if (returningBusinessCard == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(returningBusinessCard, HttpStatus.CREATED);
    }

    @DeleteMapping(API_ADMIN_CARDS_WITH_ID_URL)
    public ResponseEntity deleteBusinessCard(
            @PathVariable("id") Long id
    ) {
        BusinessCardDTO businessCard = businessCardService.deleteBusinessCard(id);
        if (businessCard == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
