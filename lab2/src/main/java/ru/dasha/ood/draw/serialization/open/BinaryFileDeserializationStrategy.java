package ru.dasha.ood.draw.serialization.open;

import org.json.JSONArray;
import ru.dasha.ood.draw.nodes.GenericNode;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class BinaryFileDeserializationStrategy implements IFileDeserializationStrategy {

    private static byte[] readFileBytes(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(fileName));
    }

    @Override
    public Collection<GenericNode> deserialize(File target) throws IOException {
        byte[] fileBytes = readFileBytes(target.getAbsolutePath());
        String stringFromBinary = new String(fileBytes, StandardCharsets.UTF_8);
        return GenericNodeDeserializer.nodesFromJSON(new JSONArray(stringFromBinary));
    }
}
