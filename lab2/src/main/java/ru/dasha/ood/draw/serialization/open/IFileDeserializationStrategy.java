package ru.dasha.ood.draw.serialization.open;

import ru.dasha.ood.draw.nodes.GenericNode;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface IFileDeserializationStrategy {
    Collection<GenericNode> deserialize(File target) throws IOException;
}
