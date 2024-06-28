package com.example.TPO;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Utils {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static JFormattedTextField createDateFormattedField() {
        JFormattedTextField formattedTextField = new JFormattedTextField(new DateFormatter(new SimpleDateFormat("yyyy-MM-dd")));
        formattedTextField.setColumns(10); // Ajusta el ancho del campo según sea necesario
        return formattedTextField;
    }


    public static JFormattedTextField createFormattedTextField() {
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0);
        return new JFormattedTextField(formatter);
    }

    public static Date parseDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (DateTimeParseException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return dateFormat.format(date);
    }
}
