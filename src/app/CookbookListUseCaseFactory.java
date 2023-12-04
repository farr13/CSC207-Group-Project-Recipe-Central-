package app;

import backend.service.back_to_menu.BackToMenuController;
import backend.service.back_to_menu.BackToMenuInteractor;
import backend.service.back_to_menu.BackToMenuPresenter;
import backend.service.delete_cookbook.DeleteCookbookController;
import backend.service.delete_cookbook.DeleteCookbookDAI;
import backend.service.delete_cookbook.DeleteCookbookInteractor;
import backend.service.delete_cookbook.DeleteCookbookPresenter;
import backend.service.go_add_cookbook.GoAddCookbookController;
import backend.service.go_add_cookbook.GoAddCookbookInteractor;
import backend.service.go_add_cookbook.GoAddCookbookPresenter;
import backend.service.see_list_cookbooks.SeeListCookbooksDAI;
import backend.service.view_cookbook.ViewCookbookController;
import backend.service.view_cookbook.ViewCookbookDAI;
import backend.service.view_cookbook.ViewCookbookInteractor;
import backend.service.view_cookbook.ViewCookbookPresenter;
import view.usecase_views.CookbookListView;
import view.view_managers.ViewManagerModel;
import view.view_models.AddCookbookViewModel;
import view.view_models.CookbookListViewModel;
import view.view_models.MainMenuViewModel;
import view.view_models.OpenCookbookViewModel;

public class CookbookListUseCaseFactory {
    private CookbookListUseCaseFactory(){}

    public static CookbookListView create(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel,
                                          CookbookListViewModel cookbookListViewModel, OpenCookbookViewModel openCookbookViewModel,
                                          AddCookbookViewModel addCookbookViewModel,
                                          ViewCookbookDAI viewCookbookDAO, DeleteCookbookDAI deleteCookbookDAO){
        ViewCookbookController viewCookbookController = CookbookListUseCaseFactory.createViewCookbookUsecase(viewManagerModel,
                cookbookListViewModel, openCookbookViewModel, viewCookbookDAO);
        DeleteCookbookController deleteCookbookController = CookbookListUseCaseFactory.createDeleteCookbookUsecase(viewManagerModel,
                cookbookListViewModel, deleteCookbookDAO, (SeeListCookbooksDAI) viewCookbookDAO);
        BackToMenuController backToMenuController = CookbookListUseCaseFactory.createBackToMenuUsecase(viewManagerModel, mainMenuViewModel);
        GoAddCookbookController goAddCookbookController = CookbookListUseCaseFactory.createGoAddCookbookUsecase(viewManagerModel, addCookbookViewModel);
        return new CookbookListView(cookbookListViewModel, viewCookbookController, deleteCookbookController, backToMenuController, goAddCookbookController);
    }
    private static ViewCookbookController createViewCookbookUsecase(ViewManagerModel viewManagerModel, CookbookListViewModel cookbookListViewModel,
                                                                    OpenCookbookViewModel openCookbookViewModel,ViewCookbookDAI viewCookbookDAO){
        ViewCookbookPresenter viewCookbookPresenter = new ViewCookbookPresenter(viewManagerModel, openCookbookViewModel, cookbookListViewModel);
        ViewCookbookInteractor viewCookbookInteractor = new ViewCookbookInteractor(viewCookbookDAO, viewCookbookPresenter);
        return new ViewCookbookController(viewCookbookInteractor);
    }
    private static DeleteCookbookController createDeleteCookbookUsecase(ViewManagerModel viewManagerModel,
                                                                        CookbookListViewModel cookbookListViewModel,
                                                                        DeleteCookbookDAI deleteCookbookDAO,
                                                                        SeeListCookbooksDAI viewCookbookDAO){
        DeleteCookbookPresenter deleteCookbookPresenter = new DeleteCookbookPresenter(viewManagerModel, cookbookListViewModel);
        DeleteCookbookInteractor deleteCookbookInteractor = new DeleteCookbookInteractor(deleteCookbookDAO, viewCookbookDAO,
                deleteCookbookPresenter);
        return new DeleteCookbookController(deleteCookbookInteractor);
    }
    private static BackToMenuController createBackToMenuUsecase(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel){
        BackToMenuPresenter backToMenuPresenter = new BackToMenuPresenter(viewManagerModel, mainMenuViewModel);
        BackToMenuInteractor backToMenuInteractor = new BackToMenuInteractor(backToMenuPresenter);
        return new BackToMenuController(backToMenuInteractor);
    }
    private static GoAddCookbookController createGoAddCookbookUsecase(ViewManagerModel viewManagerModel,
                                                                      AddCookbookViewModel addCookbookViewModel){
        GoAddCookbookPresenter goAddCookbookPresenter = new GoAddCookbookPresenter(viewManagerModel, addCookbookViewModel);
        GoAddCookbookInteractor goAddCookbookInteractor = new GoAddCookbookInteractor(goAddCookbookPresenter);
        return new GoAddCookbookController(goAddCookbookInteractor);
    }

}
