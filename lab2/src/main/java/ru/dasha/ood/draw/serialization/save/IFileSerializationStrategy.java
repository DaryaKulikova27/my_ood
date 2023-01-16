package ru.dasha.ood.draw.serialization.save;

import ru.dasha.ood.draw.nodes.GenericNode;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface IFileSerializationStrategy {
    void serialize(Collection<GenericNode> nodes, File target) throws IOException;
}
