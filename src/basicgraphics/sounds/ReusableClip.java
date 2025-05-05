package basicgraphics.sounds;

import basicgraphics.BasicFrame;
import basicgraphics.BasicLayout;
import basicgraphics.FileUtility;
import basicgraphics.TaskRunner;
import basicgraphics.images.RuntimeIOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.sound.sampled.*;

import javax.swing.*;


/**
 * An interface to the java sound system
 *
 * @author Steven R. Brandt
 */
public final class ReusableClip {
    private volatile int position, queued = 0;
    private volatile boolean isPlaying, isPaused, isLooping = false;
    byte[] buf;
    AudioFormat audioFormat;
    SourceDataLine sourceLine;
    private Thread thread;
    public static boolean verbose = false;


    private static URI getURI(String name) {

        URL src = null;
        try {
            src = new URL(name);
            System.out.println("src: " + src);
        } catch (MalformedURLException me) {
            ;
        }
        if (src == null) {
            src = ReusableClip.class.getResource(name);
        }
        if (src == null) {
            try {
                src = FileUtility.findFile(name).toURL();
            } catch (MalformedURLException ex) {
                TaskRunner.report(ex, null);
            }
            if (src == null) {
                new RuntimeIOException("Could not load: " + name).printStackTrace();
                src = ReusableClip.class.getResource("beep.wav");
            }
        }
        try {

            return src.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ReusableClip(String name) {

        this(getURI(name));
    }
    final String name;

    public ReusableClip(URI uri) {

        this.name = uri == null ? "?" : uri.getPath();
        System.out.println(this.name);
        try {
            if (verbose) {
                System.out.println("loading sound: " + uri);
            }
            if (uri.toString().endsWith(".wav")) {
                try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(uri.toURL())) {
                    audioFormat = audioStream.getFormat();
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                    sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                    sourceLine.open(audioFormat);

                    //^^^original brandt code
                    //171-177 reads buffer.length bytes from the audiostream. while loop writes that buffer to the bytearrayoutputstream. writes in chunks of 4096
                    //the audio.stream method not only writes to the buffer, but advances its own position in the stream
                    //a great comparison is filing up a bucket using a cup from a faucet of water.
                    ByteArrayOutputStream audioBytes = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = audioStream.read(buffer)) != -1) {
                        audioBytes.write(buffer, 0, bytesRead);
                    }
                    //buf will be used for sound.
                    buf = audioBytes.toByteArray();


                }
            }   else if (uri.toString().endsWith(".zip")) {
                try (InputStream in = uri.toURL().openStream(); ZipInputStream zis = new ZipInputStream(in)) {


                    ByteArrayOutputStream wavBuffer = new ByteArrayOutputStream();
                    ZipEntry ze;

                    // check if the next object in the zip file is there, then checks if it's not a directory and ends in .wav

                    while ((ze = zis.getNextEntry()) != null) {
                        if (!ze.isDirectory() && ze.getName().toLowerCase().endsWith(".wav")) {
                            // essentially does the same thing as above. however, wavbuffer will be used as storage to decompress the zip

                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = zis.read(buffer)) > 0) {
                                wavBuffer.write(buffer, 0, len);
                            }

                            break; // stop after first file
                        }
                    }

                    if (wavBuffer.size() == 0) {
                        throw new IOException("No WAV file found in ZIP");
                    }

                    // does the same as above, but with audiostream to a byte array this time.
                    try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(wavBuffer.toByteArray()))) {

                        audioFormat = audioStream.getFormat();
                        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                        sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                        sourceLine.open(audioFormat);

                        // Read all audio data into buf
                        ByteArrayOutputStream audioBytes = new ByteArrayOutputStream();
                        byte[] tempBuffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = audioStream.read(tempBuffer)) != -1) {
                            audioBytes.write(tempBuffer, 0, bytesRead);
                        }
                        buf = audioBytes.toByteArray();


                    }
                }
            }



        } catch (Exception ex) {
            if (verbose) {
                ex.printStackTrace();
            } else {
                System.err.println(ex);
            }
        }
    }

    public void playOverlapping(){
        if (verbose) {System.out.println("Playing overlapping");}

        thread = new Thread(()->{
            try{
                sourceLine = AudioSystem.getSourceDataLine(audioFormat);
                sourceLine.open(audioFormat);
                sourceLine.start();
                //basic setup to begin writing to buffer
                if (verbose) { System.out.println("overlap");}

                //only time this thread will ever be referenced, is ok to block it
                sourceLine.write(buf, 0 , buf.length);

            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
    public synchronized ReusableClip play() {
        if (isPlaying) {
            if (!isLooping) {
                if (verbose) {System.out.println("enqueued");}
                //if play button is pressed during playback and isnt looping, add another one of itself to queue. not sure what use this is but still
                ReusableClip newClip = new ReusableClip(name);
                newClip.play();
            }else
            {if (verbose){System.out.println("wont enqueue cus looping");}}
            if (verbose)System.out.println("returned");
            return this;
        }

        isPlaying = true;
        isPaused = false;

        thread = new Thread(() -> {
            try {
                sourceLine = AudioSystem.getSourceDataLine(audioFormat);
                sourceLine.open(audioFormat);
                sourceLine.start();
                //basic setup to begin writing
                if (verbose) { System.out.println("starting");}
                while (position < buf.length && isPlaying) {
                    //while sound hasnt been finished and is playing
                    if (isPaused) {
                        synchronized (ReusableClip.this) {
                            while (isPaused && isPlaying) {
                                ReusableClip.this.wait(); // puts the thread to bed until notifyall is called
                            }
                        }
                    }
                    // Write data in chunks to avoid blocking indefinitely. I hate java sound
                    //unfortunately pause isn't immediate because all pause does is stop the next buffer write. the window is small enough though.
                    int available = sourceLine.available();
                    if (available > 0) {
                        //makes sure the buffer doesnt try to write with nothing left
                        if(available + position >= buf.length) {
                            available = buf.length - position;
                        }
                        int written = sourceLine.write(buf, position, available);
                        position += written;


                        if (position >= buf.length) {

                            break;
                        }
                    }
                }
                position = 0;
                sourceLine.drain(); //basic sourceline hygiene. blocks thread until everything in the buffer has been written
            } catch (LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                //cleanup to prepare to begin playback again
                isPlaying = false;
                position = 0;
                closeLine();
                if (isLooping){play();}
                if (queued > 0){
                    queued--;
                    play();
                }
            }
        });

        thread.start();

        return this;
    }
    public synchronized void pause() {
        //modifies volatile bools so that the thread can access them during while loop
        if (!isPlaying || isPaused) return;
        if (verbose)System.out.println("pause");
        isPaused = true;
    }
    public synchronized void stop() {
        //modifies volatile bools so that the thread can access them during while loop
        isPlaying = false;
        isPaused = false;
        isLooping = false;
        position = 0;
        closeLine();
        notifyAll(); // Wake up thread if paused. basic hygiene to make sure that thread is still usable
    }
    private void closeLine() {
        //kills the line
        if (sourceLine != null) {
            sourceLine.close();
            sourceLine = null;
        }
    }
    public synchronized void resume() {
        //modifies volatile bools so that the thread can access them during while loop
        if (!isPlaying || !isPaused) return;
        isPaused = false;
        notifyAll(); // Wake up the playback thread to begin writing again.
    }
    public synchronized void loop() {
        //modifies volatile bools so that the thread can access them during while loop
        if (isLooping) {
            isLooping = false;
            if (verbose)System.out.println("looping false");
        }else {
            isLooping = true;
            if (verbose)System.out.println("looping true");
        }

    }

    public static void main(String[] args) throws Exception {
        // It won't play without a JFrame.
        verbose = true;
        BasicFrame bf = new BasicFrame("title");

        ReusableClip clip1 = new ReusableClip("arrow.wav");
        ReusableClip clip2 = new ReusableClip("beep.wav");
        //clip1.loop();


        BasicLayout layout = new BasicLayout();
        JButton clip1Button = new JButton("Clip 1");
        JButton clip2Button = new JButton("Clip 2");

        JButton clip1Stop = new JButton("stop");
        JButton clip2Stop = new JButton("stop");
        final boolean[] init = {false};

        clip1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!init[0]){
                    init[0] = true;
                    clip1.play();
                    //clip1loop();

                } else {
                    clip1.play();
                }

            }
        });
        clip2Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clip2.play();
            }
        });

        clip1Stop.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                clip1.pause();
            }
        });
        clip2Stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clip2.pause();
            }
        });

        bf.setLayout(layout);
        bf.add("x=0,y=0,w=1,h=1", clip1Button);
        bf.add("x=1,y=0,w=1,h=1", clip2Button);
        bf.add("x=0,y=1,w=1,h=1", clip1Stop);
        bf.add("x=1,y=1,w=1,h=1", clip2Stop);


        bf.show();

    }
}