/*
 * Copyright (C) 2015 Jorge Castillo Pérez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jorgecastilloprz.corleone.sample.ui.presentation;

import com.github.jorgecastilloprz.corleone.sample.domain.model.Game;
import com.github.jorgecastilloprz.corleone.sample.domain.model.LucasArtGame;
import java.util.List;

/**
 * @author Jorge Castillo Pérez
 */
public interface GameListPresenter extends Presenter {

  void setView(View view);

  void onGameClicked(Game game);

  List<Game> getCurrentGamesLoaded();

  void restoreLoadedGames(List<Game> games);

  interface View {

    void drawGames(List<Game> games);

    void displayConnectionError();

    void displayLoadGamesError();

    void displayGamesStoredIndication();

    void displayStoreGamesError();
  }
}
