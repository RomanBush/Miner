package com.miner;

import com.miner.Constants.BoardPropertiesEnum;
import com.miner.Controllers.MinesweeperController;
import com.miner.Models.MinesweeperModel;
import com.miner.Views.MinesweeperView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        MinesweeperModel minesweeperModel = new MinesweeperModel();
        MinesweeperView minesweeperView = new MinesweeperView(stage);
        MinesweeperController minesweeperController=new MinesweeperController(minesweeperModel, minesweeperView);
        minesweeperController.newGame();
    }
}
