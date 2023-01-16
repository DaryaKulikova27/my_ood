package ru.dasha.ood.draw.serialization.save;

import ru.dasha.ood.draw.nodes.GenericNode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class TextFileSerializationStrategy implements IFileSerializationStrategy {

    @Override
    public void serialize(Collection<GenericNode> nodes, File target) throws IOException {
        FileWriter writer = new FileWriter(target);
        writer.write(GenericNodeSerializer.toJSON(nodes).toString(2));
        writer.close();
    }
}
