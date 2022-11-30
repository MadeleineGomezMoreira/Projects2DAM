package modulos;

import exception.JMazmorraException;

import javax.swing.*;
import java.util.List;

public interface JMazmorraNavigation {
    void newRoom(List <String> doors, String description) throws JMazmorraException;
}
