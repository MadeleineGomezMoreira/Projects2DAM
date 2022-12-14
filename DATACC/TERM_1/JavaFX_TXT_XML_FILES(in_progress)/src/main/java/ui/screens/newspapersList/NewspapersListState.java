package ui.screens.newspapersList;

import domain.modelo.Newspaper;
import lombok.Data;

import java.util.List;

@Data
public class NewspapersListState {

    private List<Newspaper> newspapers;
    private String error;


    public NewspapersListState(List<Newspaper> newspapers, String error) {
        this.newspapers = newspapers;
        this.error = error;
    }
}
