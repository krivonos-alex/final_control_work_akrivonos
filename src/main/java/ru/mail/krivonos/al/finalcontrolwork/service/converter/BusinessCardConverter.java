package ru.mail.krivonos.al.finalcontrolwork.service.converter;

import ru.mail.krivonos.al.finalcontrolwork.repository.model.BusinessCard;
import ru.mail.krivonos.al.finalcontrolwork.service.model.BusinessCardDTO;

public interface BusinessCardConverter {

    BusinessCardDTO toDTO(BusinessCard businessCard);

    BusinessCard toEntity(BusinessCardDTO businessCardDTO);
}
