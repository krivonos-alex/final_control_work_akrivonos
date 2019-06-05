package ru.mail.krivonos.al.finalcontrolwork.service;

import ru.mail.krivonos.al.finalcontrolwork.service.model.BusinessCardDTO;

import java.util.List;

public interface BusinessCardService {

    List<BusinessCardDTO> getBusinessCards(Long userID);

    BusinessCardDTO add(BusinessCardDTO businessCardDTO);

    BusinessCardDTO deleteBusinessCard(Long id);
}
