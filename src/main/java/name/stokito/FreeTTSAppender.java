package name.stokito;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.util.concurrent.ExecutorService;

public class FreeTTSAppender extends AppenderSkeleton {

    private static FreeTTSAppender instance = new FreeTTSAppender();

    private ExecutorService executorService;

    private Voice helloVoice;

    private String voiceName = "kevin16";

    public String getVoiceName() {
        return voiceName;
    }

    public void setVoiceName(String voiceName) {
        this.voiceName = voiceName;
    }

    private int nThreads = 10;

    public int getnThreads() {
        return nThreads;
    }

    public void setnThreads(int nThreads) {
        this.nThreads = nThreads;
    }

    /**
     * Whenever you can, use this method to retreive an instance instead
     * of instantiating a new one with <code>new</code>.
     */
    public static FreeTTSAppender getFreeTTSAppender() {
        return instance;
    }

    public FreeTTSAppender() {
    }

    @Override
    public void activateOptions() {
        VoiceManager voiceManager = VoiceManager.getInstance();
        assert helloVoice == null;
        helloVoice = voiceManager.getVoice(voiceName);
        /* Allocates the resources for the voice */
        helloVoice.allocate();
        assert executorService == null;
        executorService = java.util.concurrent.Executors.newFixedThreadPool(nThreads);
    }

    @Override
    public void close() {
        if (helloVoice != null) {
            helloVoice.deallocate();
        }
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Override
    protected void append(final LoggingEvent event) {
        /* Synthesize speech. */
        executorService.submit(new Runnable() {
            public void run() {
                helloVoice.speak(event.getMessage().toString());
            }
        });
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
