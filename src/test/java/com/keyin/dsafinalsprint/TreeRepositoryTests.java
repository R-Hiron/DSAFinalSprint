package com.keyin.dsafinalsprint;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TreeRepositoryTests {

    @Autowired
    private TreeRepository treeRepository;

    @Test
    public void testSaveTree() {
        TreeEntity tree = new TreeEntity();
        tree.setInputNumbers("10,5,15");
        tree.setTreeJson("{\"value\":10,\"left\":{\"value\":5},\"right\":{\"value\":15}}");

        TreeEntity savedTree = treeRepository.save(tree);

        assertNotNull(savedTree.getId());
        assertEquals("10,5,15", savedTree.getInputNumbers());
        assertEquals("{\"value\":10,\"left\":{\"value\":5},\"right\":{\"value\":15}}", savedTree.getTreeJson());
    }

    @Test
    public void testFetchPreviousTrees() {
        TreeEntity tree = new TreeEntity();
        tree.setInputNumbers("20,10,30");
        tree.setTreeJson("{\"value\":20,\"left\":{\"value\":10},\"right\":{\"value\":30}}");
        treeRepository.save(tree);

        List<TreeEntity> trees = treeRepository.findAll();
        assertFalse(trees.isEmpty());
        assertTrue(trees.stream().anyMatch(t -> t.getInputNumbers().equals("20,10,30")));
    }

    @Test
    public void testDeleteTree() {
        TreeEntity tree = new TreeEntity();
        tree.setInputNumbers("40,20,60");
        tree.setTreeJson("{\"value\":40,\"left\":{\"value\":20},\"right\":{\"value\":60}}");
        TreeEntity savedTree = treeRepository.save(tree);

        Long treeId = savedTree.getId();
        treeRepository.deleteById(treeId);

        assertFalse(treeRepository.findById(treeId).isPresent());
    }
}
