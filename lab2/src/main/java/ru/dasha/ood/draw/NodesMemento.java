package ru.dasha.ood.draw;

import ru.dasha.ood.draw.nodes.GenericNode;

import java.util.Set;

public class NodesMemento {
    private final GenericNode[] nodes;

    public NodesMemento(GenericNode[] nodes) {
        this.nodes = nodes;
    }

    public static NodesMemento createUsingClones(Set<GenericNode> nodes) {
        GenericNode[] newNodes = new GenericNode[nodes.size()];
        int index = 0;
        for (GenericNode node : nodes)
            newNodes[index++] = (GenericNode) node.cloneIt();
        return new NodesMemento(newNodes);
    }

    public GenericNode[] getNodes() {
        return nodes;
    }
}
