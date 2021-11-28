package org.stonlexx.messenger.app.util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.media.AudioTrack;
import javafx.scene.media.SubtitleTrack;
import javafx.scene.media.VideoTrack;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.stonlexx.messenger.protocol.objects.AudioAttachment;
import org.stonlexx.messenger.protocol.objects.ImageAttachment;
import org.stonlexx.messenger.protocol.objects.SubtitleAttachment;
import org.stonlexx.messenger.protocol.objects.VideoAttachment;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@UtilityClass
public class AttachmentFactory {

    @SneakyThrows
    public static ImageAttachment createAttachment(int userId, Image image) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "JPEG", outputStream);

        return new ImageAttachment(userId, new ByteArrayInputStream(outputStream.toByteArray()));
    }

    public static VideoAttachment createAttachment(int userId, VideoTrack videoTrack) {
        throw new UnsupportedOperationException();
    }

    public static AudioAttachment createAttachment(int userId, AudioTrack audioTrack) {
        throw new UnsupportedOperationException();
    }

    public static SubtitleAttachment createAttachment(int userId, SubtitleTrack subtitleTrack) {
        throw new UnsupportedOperationException();
    }

}
