package ui.screens.spells;

import domain.modelo.Spell;
import domain.servicios.SpellsServices;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class SpellsViewModel {

    private SpellsServices spellsServices;

    @Inject
    public SpellsViewModel(SpellsServices spellsServices) {
        this.spellsServices = spellsServices;
        _state = new SimpleObjectProperty<>(new SpellsState(null, null));

    }

    private ObjectProperty<SpellsState> _state;

    public ReadOnlyObjectProperty<SpellsState> getState() {
        return _state;
    }

    public void loadSpells() {

        List<Spell> listSpells = spellsServices.getSpells().get();
        SpellsState state = new SpellsState(listSpells, null);
        _state.setValue(state);


    }
}
