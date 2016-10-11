package net.digaly.doodle;

/**
 * Created by Tom Dobbelaere on 11/10/2016.
 */
public class ApplicationSettings
{
    private boolean fullscreen;
    private String title;
    private String iconPath;
    private boolean debugMode;

    public ApplicationSettings() {
        this.title = "";
        this.iconPath = "";
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
}
