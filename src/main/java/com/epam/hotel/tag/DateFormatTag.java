package com.epam.hotel.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatTag extends TagSupport {
    public static final String LOCALE_ENG = "en";
    private String locale;
    private LocalDate date;
    private static final String dateFormatRu = "dd.MM.yyyy";
    private static final String dateFormatEng = "dd-MM-yyyy";
    String formattedDate;

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int doStartTag() throws JspException {
        if (LOCALE_ENG.equals(locale)) {
            formattedDate = date.format(DateTimeFormatter.ofPattern(dateFormatEng));
        } else {
            formattedDate = date.format(DateTimeFormatter.ofPattern(dateFormatRu));
        }
        JspWriter out = pageContext.getOut();
        try {
            out.write(formattedDate);
        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
