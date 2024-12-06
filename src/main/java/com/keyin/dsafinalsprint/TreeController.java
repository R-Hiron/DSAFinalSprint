package com.keyin.dsafinalsprint;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TreeController {
    @Autowired
    private BinarySearchTreeService bstService;
    private final TreeRepository treeRepository;

    public TreeController(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    @PostMapping("/process-numbers")
    public Node processNumbers(@RequestBody List<Integer> numbers) {
        int[] nums = numbers.stream().mapToInt(i -> i).toArray();
        Node root = bstService.buildTree(nums);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String treeJson = mapper.writeValueAsString(root);
            bstService.saveTree(numbers.toString(), treeJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }

    @GetMapping("/previous-trees")
    public List<TreeEntity> showPreviousTrees() {
        return bstService.getAllTrees();
    }

    @DeleteMapping("/delete-tree/{id}")
    public ResponseEntity<Void> deleteTree(@PathVariable Long id) {
        if (treeRepository.existsById(id)) {
            treeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

