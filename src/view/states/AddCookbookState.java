package view.states;

public class AddCookbookState {
    private String errorMessage = "";
    public AddCookbookState(CookbookListState copy){

    }
    public AddCookbookState(){}

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
