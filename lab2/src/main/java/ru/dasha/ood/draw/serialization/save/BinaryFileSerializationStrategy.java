package ru.dasha.ood.draw.serialization.save;

import ru.dasha.ood.draw.nodes.GenericNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class BinaryFileSerializationStrategy implements IFileSerializationStrategy {

    @Override
    public void serialize(Collection<GenericNode> nodes, File target) throws IOException {
        String json = GenericNodeSerializer.toJSON(nodes).toString(2);

        byte binaryJson[] = json.getBytes(StandardCharsets.UTF_8);
        FileOutputStream fos = new FileOutputStream(target);
        fos.write(binaryJson, 0, binaryJson.length);
        fos.flush();
        fos.close();
    }
}
