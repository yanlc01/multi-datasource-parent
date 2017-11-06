package com.multi.sessionFactory.ext;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LogbackIpConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return "192.168.2.227";
    }
}
