package ru.mail.krivonos.al.finalcontrolwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mail.krivonos.al.finalcontrolwork.repository.BusinessCardRepository;
import ru.mail.krivonos.al.finalcontrolwork.repository.UserRepository;
import ru.mail.krivonos.al.finalcontrolwork.repository.model.BusinessCard;
import ru.mail.krivonos.al.finalcontrolwork.repository.model.User;
import ru.mail.krivonos.al.finalcontrolwork.service.BusinessCardService;
import ru.mail.krivonos.al.finalcontrolwork.service.converter.BusinessCardConverter;
import ru.mail.krivonos.al.finalcontrolwork.service.model.BusinessCardDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service("businessCardService")
public class BusinessCardServiceImpl implements BusinessCardService {

    private final BusinessCardRepository businessCardRepository;
    private final BusinessCardConverter businessCardConverter;
    private final UserRepository userRepository;

    @Autowired
    public BusinessCardServiceImpl(
            BusinessCardRepository businessCardRepository,
            BusinessCardConverter businessCardConverter,
            UserRepository userRepository) {
        this.businessCardRepository = businessCardRepository;
        this.businessCardConverter = businessCardConverter;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<BusinessCardDTO> getBusinessCards(Long userID) {
        List<BusinessCard> businessCards = userRepository.findById(userID).getBusinessCards();
        return getBusinessCardDTOs(businessCards);
    }

    @Override
    @Transactional
    public BusinessCardDTO add(BusinessCardDTO businessCardDTO) {
        BusinessCard businessCard = businessCardConverter.toEntity(businessCardDTO);
        User user = userRepository.findUserByUsername(businessCardDTO.getUser().getUsername());
        if (user == null) {
            return null;
        }
        businessCard.setUser(user);
        businessCardRepository.persist(businessCard);
        return businessCardConverter.toDTO(businessCard);
    }

    @Override
    @Transactional
    public BusinessCardDTO deleteBusinessCard(Long id) {
        BusinessCard businessCard = businessCardRepository.findById(id);
        if (businessCard == null) {
            return null;
        }
        businessCardRepository.remove(businessCard);
        return businessCardConverter.toDTO(businessCard);
    }

    private List<BusinessCardDTO> getBusinessCardDTOs(List<BusinessCard> businessCards) {
        return businessCards.stream()
                .map(businessCardConverter::toDTO)
                .collect(Collectors.toList());
    }
}
