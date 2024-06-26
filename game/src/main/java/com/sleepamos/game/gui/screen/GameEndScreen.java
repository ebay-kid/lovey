package com.sleepamos.game.gui.screen;

import com.jme3.audio.AudioNode;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.HAlignment;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.VAlignment;
import com.sleepamos.game.Lovey;
import com.sleepamos.game.beatmap.Beatmap;

public class GameEndScreen extends Screen {
    private long score = -1;
    private Beatmap played_map;
    private AudioNode played_audio;

    public GameEndScreen(long s, Beatmap played, AudioNode audio) {
        this.score = s;
        this.played_map = played;
        this.played_audio = audio;
    }

    @Override
    protected void initialize() {
        Container window = this.createAndAttachContainer();

        // Put it somewhere that we will see it.
        // Note: Lemur GUI elements grow down from the upper left corner.
        window.setLocalTranslation(this.getScreenWidth() / 3f, this.getScreenHeight(), 0);

        window.addChild(new Label("Game Over! | Your Score: " + this.score));

        window.addChild(this.button("Replay").withHAlign(HAlignment.Center).withVAlign(VAlignment.Center)
                .withCommand(source -> {
                    Lovey.getInstance().getScreenHandler().hideLastShownScreen(); // remove ourselves

                    Lovey.getInstance().exitMap();

                    Lovey.getInstance().launchMap(this.played_map, this.played_audio);
                }));

        window.addChild(
                this.button("Quit").withHAlign(HAlignment.Center).withVAlign(VAlignment.Center).withCommand(source -> {
                    Lovey.getInstance().getScreenHandler().hideLastShownScreen(); // remove ourselves
                    Lovey.getInstance().exitMap();

                    Lovey.getInstance().useGUIBehavior(true);
                }));
    }
}
