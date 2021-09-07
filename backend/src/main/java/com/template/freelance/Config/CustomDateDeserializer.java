package com.template.freelance.Config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class CustomDateDeserializer extends StdDeserializer<Date> {

  private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

  public CustomDateDeserializer() {
    this(null);
  }

  public CustomDateDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Date deserialize(JsonParser jsonparser, DeserializationContext context)
      throws IOException, JsonProcessingException {
    String dateStr = jsonparser.getText();
    try {
      if (StringUtils.isNotBlank(dateStr)) {
        int[] arr = Arrays.stream(dateStr.split("/")).mapToInt(s -> Integer.parseInt(s)).toArray();
        LocalDate localDate = LocalDate.of(arr[2] - 543, arr[1], arr[0]);
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
      } else {
        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
