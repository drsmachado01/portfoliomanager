package br.com.darlan.portfoliomanager.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConverterUtilTest {

    @Test
    void convertLocalDateToDate() {
        Date date = ConverterUtil.asDate(LocalDate.of(2022, 1, 1));
        assertEquals("2022-01-01", new SimpleDateFormat("yyy-MM-dd").format(date));
    }

    @Test
    void convertDateToLocalDate() {
        LocalDate date = ConverterUtil.asLocalDate(getDate("2022-01-01"));
        assertEquals("2022-01-01", ConverterUtil.toString(date));
    }

    @Test
    void convertStringToLocalDate() {
        LocalDate date = ConverterUtil.toDate("2022-01-01");
        assertEquals("2022-01-01", ConverterUtil.toString(date));
    }

    @Test
    void convertLocalDateToString() {
        String date = ConverterUtil.toString(LocalDate.of(2022, 1, 1));
        assertEquals("2022-01-01", date);
    }

    private Date getDate(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        try {
            return formatter.parse(dateInString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}