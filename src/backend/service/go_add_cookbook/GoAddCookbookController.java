package backend.service.go_add_cookbook;

public class GoAddCookbookController {
    final GoAddCookbookInputBoundary goAddCookbookInteractor;

    public GoAddCookbookController(GoAddCookbookInputBoundary goAddCookbookInteractor){
        this.goAddCookbookInteractor = goAddCookbookInteractor;
    }

    public void execute() {
        goAddCookbookInteractor.execute();
    }
}
