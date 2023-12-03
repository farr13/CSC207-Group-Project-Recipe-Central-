package backend.service.make_cookbook;

import backend.entity.Cookbook;
import backend.entity.Recipe;
import backend.service.see_list_cookbooks.SeeListCookbooksDAI;

import java.util.ArrayList;
import java.util.Objects;

public class MakeCookbookInteractor implements MakeCookbookInputBoundary{

    final MakeCookbookAddDAI makeCookbookAddDAO;
    final MakeCookbookViewDAI makeCookbookViewDAO;
    final MakeCookbookOutputBoundary makeCookbookPresenter;
    public MakeCookbookInteractor(MakeCookbookAddDAI makeCookbookAddDAO, MakeCookbookViewDAI makeCookbookViewDAO,
                                  MakeCookbookOutputBoundary makeCookbookOutputBoundary) {
        this.makeCookbookAddDAO = makeCookbookAddDAO;
        this.makeCookbookViewDAO = makeCookbookViewDAO;
        this.makeCookbookPresenter = makeCookbookOutputBoundary;
    }
    private boolean existByTitle(String cookbookName, String[] cookbooks) {
        for (String cookbook: cookbooks){
            if (Objects.equals(cookbook, cookbookName))
                return true;
        }
        return false;
    }
    private String[] convertCookbookNames(Cookbook[] cookbooks){
        ArrayList<String> cookbookNames = new ArrayList<String>();
        for (Cookbook cookbook: cookbooks)
            cookbookNames.add(cookbook.getName());
        return cookbookNames.toArray(new String[0]);
    }
    @Override
    public void execute(MakeCookbookInputData makeCookbookInputData) {
        try {
            Cookbook[] cookbooks = makeCookbookViewDAO.viewCookbooks();
            String[] cookbookNames = convertCookbookNames(makeCookbookViewDAO.viewCookbooks());
            if (!Objects.equals(makeCookbookInputData.getNewCookbookName(), "") &&
                    !existByTitle(makeCookbookInputData.getNewCookbookName(), cookbookNames)) {

                Cookbook newCookbook = new Cookbook(makeCookbookInputData.getNewCookbookName(), new Recipe[]{});
                makeCookbookAddDAO.addCookbook(newCookbook);

                cookbookNames = convertCookbookNames(makeCookbookViewDAO.viewCookbooks());
                makeCookbookPresenter.prepareSuccessView(new MakeCookbookOutputData(cookbookNames));
            }else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            makeCookbookPresenter.prepareFailView("Invalid cookbook name.");
        }
    }
}
