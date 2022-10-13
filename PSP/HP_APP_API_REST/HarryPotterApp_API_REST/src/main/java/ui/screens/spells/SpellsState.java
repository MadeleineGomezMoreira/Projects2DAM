package ui.screens.spells;

import domain.modelo.Spell;
import lombok.Data;

import java.util.List;

@Data
public class SpellsState {

    private final List<Spell> spells;

    private final String error;
}
