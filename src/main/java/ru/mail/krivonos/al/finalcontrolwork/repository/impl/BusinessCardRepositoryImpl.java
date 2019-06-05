package ru.mail.krivonos.al.finalcontrolwork.repository.impl;

import org.springframework.stereotype.Repository;
import ru.mail.krivonos.al.finalcontrolwork.repository.BusinessCardRepository;
import ru.mail.krivonos.al.finalcontrolwork.repository.model.BusinessCard;

@Repository("businessCardRepository")
public class BusinessCardRepositoryImpl extends GenericRepositoryImpl<Long, BusinessCard> implements BusinessCardRepository {
}
