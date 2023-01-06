package ru.dasha.ood.draw.ui;

import javafx.scene.Node;
import javafx.scene.shape.SVGPath;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class IconFactory {
    public static final IconFactory INSTANCE;

    static {
        try {
            INSTANCE = new IconFactory(Paths.get(IconFactory.class.getResource("icons.json").toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private HashMap<String, String> iconMap = new HashMap<>();
    private IconFactory(Path path) throws IOException {
        this(new JSONObject(Files.readString(path, StandardCharsets.UTF_8)));
    }
    private IconFactory(JSONObject data) {
        for (String key : data.keySet())
            iconMap.put(key, data.getJSONObject(key).getString("path"));
    }
    public SVGPath makeIcon(String name)
    {
        assert iconMap.containsKey(name);
        SVGPath path = new SVGPath();
        path.getStyleClass().add("app-ico");
        path.setContent(iconMap.get(name));
        return path;
    }
}