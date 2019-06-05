package ru.mail.krivonos.al.finalcontrolwork.service.converter.impl;

import org.springframework.stereotype.Component;
import ru.mail.krivonos.al.finalcontrolwork.repository.model.BusinessCard;
import ru.mail.krivonos.al.finalcontrolwork.service.converter.BusinessCardConverter;
import ru.mail.krivonos.al.finalcontrolwork.service.model.BusinessCardDTO;

@Component("businessCardConverter")
public class BusinessCardConverterImpl implements BusinessCardConverter {

    @Override
    public BusinessCardDTO toDTO(BusinessCard businessCard) {
        BusinessCardDTO businessCardDTO = new BusinessCardDTO();
        businessCardDTO.setId(businessCard.getId());
        businessCardDTO.setTitle(businessCard.getTitle());
        businessCardDTO.setFullName(businessCard.getFullName());
        businessCardDTO.setWorkingTelephone(businessCard.getWorkingTelephone());
        return businessCardDTO;
    }

    @Override
    public BusinessCard toEntity(BusinessCardDTO businessCardDTO) {
        BusinessCard businessCard = new BusinessCard();
        businessCard.setTitle(businessCardDTO.getTitle());
        businessCard.setFullName(businessCardDTO.getFullName());
        businessCard.setWorkingTelephone(businessCardDTO.getWorkingTelephone());
        return businessCard;
    }
}
