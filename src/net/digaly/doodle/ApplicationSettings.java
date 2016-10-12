package net.digaly.doodle;

import net.digaly.doodle.rendering.NoRenderer;
import net.digaly.doodle.rendering.Renderer;

/**
 * Created by Tom Dobbelaere on 11/10/2016.
 */
public class ApplicationSettings
{
    private boolean fullscreen;
    private String title;
    private String iconPath;
    private boolean debugMode;
    private Renderer renderer;
    private boolean depthBuffer;

    public ApplicationSettings() {
        this.title = "";
        this.iconPath = "";
        this.renderer = new NoRenderer();
        this.depthBuffer = false;
    }

    public boolean isFullscreen()
    {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen)
    {
        this.fullscreen = fullscreen;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getIconPath()
    {
        return iconPath;
    }

    public void setIconPath(String iconPath)
    {
        this.iconPath = iconPath;
    }

    public boolean isDebugMode()
    {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode)
    {
        this.debugMode = debugMode;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public Renderer getRenderer() {
        return this.renderer;
    }

    public void setDepthBuffer(boolean value) {
        this.depthBuffer = value;
    }

    public boolean getDepthBuffer() {
        return this.depthBuffer;
    }
}
