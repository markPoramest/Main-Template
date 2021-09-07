package com.template.freelance.Config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;

public class CustomDateSerializer extends StdSerializer<Date> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CustomDateSerializer() {
        this(null);
    }

    public CustomDateSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize (Date value, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        if(value != null) {
            LocalDate date = Instant.ofEpochMilli(value.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            ChronoLocalDate thDate = ThaiBuddhistChronology.INSTANCE.date(date);
            String strDate = formatter.format(thDate);
            gen.writeString(strDate);
        }
    }
}
