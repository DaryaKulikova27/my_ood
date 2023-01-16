package ru.dasha.ood.draw.serialization.open;

import org.json.JSONArray;
import ru.dasha.ood.draw.nodes.GenericNode;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Scanner;

public class TextFileDeserializationStrategy implements IFileDeserializationStrategy {

    @Override
    public Collection<GenericNode> deserialize(File target) throws IOException {
        Scanner scanner = new Scanner(target);

        StringBuilder content = new StringBuilder();

        while (scanner.hasNextLine()) {
            content.append(scanner.nextLine());
        }

        return GenericNodeDeserializer.nodesFromJSON(new JSONArray(content.toString()));
    }
}
