package view.usecase_views;

import backend.service.delete_cookbook.DeleteCookbookController;
import backend.service.make_cookbook.MakeCookbookController;
import backend.service.rename_cookbook.RenameCookbookController;
import backend.service.search_recipes.interface_adapters.SearchController;
import backend.service.view_cookbook.ViewCookbookController;
import view.view_managers.ViewManagerModel;
import view.view_models.CookbookListViewModel;
import view.view_models.MainMenuViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class CookbookListView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "cookbook list";
    private final CookbookListViewModel cookbookListViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MainMenuViewModel mainMenuViewModel;
    private final ViewCookbookController viewCookbookController;
    private final DeleteCookbookController deleteCookbookController;
    private final JTextField searchInputField = new JTextField(30);
    private final JButton mainMenu;
    //private final JButton viewCookbooks;

    public CookbookListView(CookbookListViewModel cookbookListViewModel,
                            ViewManagerModel viewManagerModel,
                            MainMenuViewModel mainMenuViewModel,
                            ViewCookbookController viewCookbookController,
                            DeleteCookbookController deleteCookbookController) {
        this.cookbookListViewModel = cookbookListViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.viewCookbookController = viewCookbookController;
        this.deleteCookbookController = deleteCookbookController;

        cookbookListViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(CookbookListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainMenu = new JButton(CookbookListViewModel.MAIN_MENU_BUTTON_LABEL);

        // Make Cookbook Scroll panel
        JList<String> cookbookNames = new JList<>(cookbookListViewModel.getState().getCookbookNames());
        JScrollPane scrollPane = new JScrollPane(cookbookNames);

        JPanel flowLayoutPanel = new JPanel(new FlowLayout());
        flowLayoutPanel.add(mainMenu);

        //Adding all components to this Jpanel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(mainMenu);
        this.add(scrollPane);
        this.add(flowLayoutPanel, BorderLayout.NORTH);
    }
    private static void createCheckBoxes(JPanel panel, String[] options) {
        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);
            panel.add(checkBox);
        }
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property changed method called");
    }
}