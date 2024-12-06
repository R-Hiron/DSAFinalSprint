package com.keyin.dsafinalsprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BinarySearchTreeService {
    @Autowired
    private TreeRepository treeRepository;

    private Node root;

    public Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        if (value < root.getValue()) {
            root.setLeft(insert(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(insert(root.getRight(), value));
        }
        return root;
    }

    public Node buildTree(int[] values) {
        root = null;
        for (int value : values) {
            root = insert(root, value);
        }
        return root;
    }

    public void saveTree(String inputNumbers, String treeJson) {
        TreeEntity treeEntity = new TreeEntity();
        treeEntity.setInputNumbers(inputNumbers);
        treeEntity.setTreeJson(treeJson);
        treeRepository.save(treeEntity);
    }

    public List<TreeEntity> getAllTrees() {
        return treeRepository.findAll();
    }

    public Node getRoot() {
        return root;
    }
}
