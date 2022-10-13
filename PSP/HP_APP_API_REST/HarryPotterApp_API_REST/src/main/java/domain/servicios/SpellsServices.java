package domain.servicios;

import dao.DaoSpells;
import domain.modelo.Spell;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class SpellsServices {

    private DaoSpells daoSpells;

    @Inject
    public SpellsServices(DaoSpells daoSpells) {
        this.daoSpells = daoSpells;
    }

    public Either<String, List<Spell>> getSpells() {
        return daoSpells.retrofitCallAllSpells();
    }


}
