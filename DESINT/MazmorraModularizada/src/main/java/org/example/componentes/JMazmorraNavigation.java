package org.example.componentes;

import exception.JMazmorraException;

import java.util.List;

public interface JMazmorraNavigation {
    void newRoom(List <String> doors, String description) throws JMazmorraException;
}
