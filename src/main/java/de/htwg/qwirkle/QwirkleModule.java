package de.htwg.qwirkle;

import com.google.inject.AbstractModule;
import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.impl.QController;

/**
 * Created by opuee on 21.01.16.
 */
public class QwirkleModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IQController.class).to(QController.class);
    }
}
