package hi.event.vidmot;

import hi.event.vinnsla.EventModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *
 *  Lýsing  : Controller fyrir kynningarmyndbandið
 *
 *
 *****************************************************************************/
public class KynningController {

    public Button fxByrjun;
    public Button fxPlayPause;
    public Button fxEndir;
    public VBox overlay;

    @FXML
    private MediaView fxKynning;

    /**
     * Frumstilling á controller. disabl-ar hnappa ef ekkert myndband er
     */
    public void initialize() {
        fxByrjun.disableProperty().bind(Bindings.createBooleanBinding(
                () -> fxKynning.mediaPlayerProperty().getValue() == null,
                fxKynning.mediaPlayerProperty()
        ));
        fxPlayPause.disableProperty().bind(Bindings.createBooleanBinding(
                () -> fxKynning.mediaPlayerProperty().getValue() == null,
                fxKynning.mediaPlayerProperty()
        ));
        fxEndir.disableProperty().bind(Bindings.createBooleanBinding(
                () -> fxKynning.mediaPlayerProperty().getValue() == null,
                fxKynning.mediaPlayerProperty()
        ));

    }

    /**
     * Spólar myndbandið fremst
     * @param event ónotað
     */
    @FXML
    void byrjun(ActionEvent event) {
        getMediaPlayer().seek(Duration.ZERO);
    }

    /**
     * Spólar myndbandið aftast
     * @param event ónotað
     */
    @FXML
    void endir(ActionEvent event) {
        getMediaPlayer().seek(getMediaPlayer().getTotalDuration());
    }

    /**
     * Spilar ef myndbandið er í pásu, annars pásar
     * @param event
     */
    @FXML
    void playPause(ActionEvent event) {
        if (fxKynning.getMediaPlayer().getStatus() == MediaPlayer.Status.PAUSED) {
            getMediaPlayer().play();
        }
        else {
            getMediaPlayer().pause();
        }
    }

    /**
     * Býr til nýjan media player fyrir media. Setur upp reglur á nýja mediaplayerinn
     * @param media
     */
    private void setMediaPlayer(Media media) {
        // nýr mediaPlayer búinn til með ákveðinni media skrá
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnError(() ->
                System.out.println("villa í player " + mediaPlayer
                        .getError().getMessage()));
        // Ef media player er ready þá byrja að spila
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.seek(Duration.ZERO);    // byrja frá byrjun
            // byrja að spila aftur ef komið er á enda
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play(); // spila
            fxKynning          // tengja við MediaView
                    .setMediaPlayer(mediaPlayer);

            // setja upp bindingu sem breytir útliti hnappsins frá || og í >
            // háð statusProperty á mediaPlayer
            fxPlayPause.textProperty().bind(Bindings.createStringBinding(this::bindPlayPause,
                    fxKynning.getMediaPlayer().statusProperty())); // háða breytan
        });

    }

    /**
     * Setur upp listener á viðburðinn þannig að ef media breytist þá er búinn til
     * nýr media player með nýju videoskránni.
     * @param eventView viðmótið fyrir viðburðinn
     */
    public void setEventView(EventView eventView) {
        EventModel eventModel = eventView.getEventModel();
        eventModel.mediaProperty().addListener((observable, oldValue, newValue) -> {
            setMediaPlayer(newValue);
        });
    }

    /**
     * Setur alignment á umlykkjandi VBoxi neðst í miðjuna
     */
    public void setAlignment() {
        StackPane.setAlignment(overlay, Pos.BOTTOM_CENTER);
    }

    /**
     * Hjálparaðferð sem skilar media playernum
     * @return media player
     */
    private MediaPlayer getMediaPlayer() {
        return fxKynning.getMediaPlayer();
    }


    /**
     * bindur || eða > við hnappinn eftir því hvort staðan á player er play eða pause
     * @return
     */
    private String bindPlayPause() {
        MediaPlayer player = fxKynning.getMediaPlayer();
        MediaPlayer.Status status = (null != player) ? player.getStatus() : null;
        return status == MediaPlayer.Status.PLAYING ? "||" : ">";
    }
}
