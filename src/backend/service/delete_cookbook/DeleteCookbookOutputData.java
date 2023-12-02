package backend.service.delete_cookbook;

public class DeleteCookbookOutputData {

    private final String[] storedCookbooks;

    public DeleteCookbookOutputData(String[] storedCookbooks) {
        this.storedCookbooks = storedCookbooks;
    }

    public String[] getStoredCookbooks() {
        return storedCookbooks;
    }
}