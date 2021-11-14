package br.com.dwallet.model.dto;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BillPaymentDTOTest {

    private static final String PATH_TO_FILE = "/billPayment.json";
    private static final String BILL_NAME = "agua - sanepar";
    private static final BigDecimal VALUE = new BigDecimal("120.45");

    @Test
    public void should_read_from_json_and_assert_values() throws IOException {
        BillPaymentDTO billPaymentDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE, BillPaymentDTO.class, BillPaymentDTOTest.class);
        assertNotNull(billPaymentDTO);
        assertEquals(BILL_NAME, billPaymentDTO.getBillName());
        assertEquals(VALUE, billPaymentDTO.getValue());
    }

}