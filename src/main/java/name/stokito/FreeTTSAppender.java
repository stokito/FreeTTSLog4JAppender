package name.stokito;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

class FreeTTSAppender extends AppenderSkeleton {
    private static FreeTTSAppender instance = new FreeTTSAppender();
    private Voice helloVoice;

    private String voiceName = "kevin16";

    public String getVoiceName() { return voiceName; }

    public void setVoiceName(String voiceName) { this.voiceName = voiceName; }

    public FreeTTSAppender() {
    }

    @Override
    public void activateOptions() {
        VoiceManager voiceManager = VoiceManager.getInstance();
        helloVoice = voiceManager.getVoice(voiceName);
        /* Allocates the resources for the voice */
        helloVoice.allocate();
    }

    /**
     * Whenever you can, use this method to retreive an instance instead
     * of instantiating a new one with <code>new</code>.
     *
     * @deprecated Use getFreeTTSAppender instead.  getInstance should have been static.
     */
    public FreeTTSAppender getInstance() {
        return instance;
    }

    /**
     * Whenever you can, use this method to retreive an instance instead
     * of instantiating a new one with <code>new</code>.
     */
    public static FreeTTSAppender getFreeTTSAppender() {
        return instance;
    }

    @Override
    public void close() {
        if (helloVoice != null) {
            helloVoice.deallocate();
        }
    }

    @Override
    protected void append(LoggingEvent event) {
        /* Synthesize speech. */
        helloVoice.speak(event.getMessage().toString());
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

}
