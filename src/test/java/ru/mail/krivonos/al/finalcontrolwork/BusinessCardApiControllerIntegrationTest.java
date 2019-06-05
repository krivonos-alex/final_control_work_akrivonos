package ru.mail.krivonos.al.finalcontrolwork;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mail.krivonos.al.finalcontrolwork.service.model.BusinessCardDTO;
import ru.mail.krivonos.al.finalcontrolwork.service.model.UserDTO;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BusinessCardApiControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetBusinessCards() {
        restTemplate.withBasicAuth("customer@customer.com", "admin");
        BusinessCardDTO[] admins = restTemplate
                .withBasicAuth("customer@customer.com", "admin")
                .getForObject("http://localhost:8080/api/v1/customer/cards", BusinessCardDTO[].class);
        Assert.assertNotNull(admins);
    }

    @Test
    public void shouldSaveBusinessCard() {
        BusinessCardDTO businessCard = new BusinessCardDTO();
        businessCard.setTitle("Some title");
        businessCard.setWorkingTelephone("+375447117187");
        businessCard.setFullName("Alexei");
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("admin@admin.com");
        businessCard.setUser(userDTO);
        restTemplate.withBasicAuth("admin@admin.com", "admin");
        BusinessCardDTO admins = restTemplate
                .withBasicAuth("admin@admin.com", "admin")
                .postForObject("http://localhost:8080/api/v1/admin/cards", businessCard, BusinessCardDTO.class);
        Assert.assertNotNull(admins.getId());
    }

    @Test
    public void shouldDeleteBusinessCard() {
        restTemplate.withBasicAuth("api@api.com", "admin");
        restTemplate
                .withBasicAuth("api@api.com", "admin")
                .delete("http://localhost:8080/api/v1/admin/cards/1", ResponseEntity.class);
    }
}
