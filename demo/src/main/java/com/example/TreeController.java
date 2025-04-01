package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
class TreeController {

    @Autowired
    private TreeRepository treeRepository;

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    @ResponseBody
    public String processNumbers(@RequestParam("numbers") String numbers) throws Exception {
        String[] numberStrings = numbers.split(",");
        List<Integer> numberList = new ArrayList<>();
        for (String num : numberStrings) {
            try {
                numberList.add(Integer.parseInt(num.trim()));
            } catch (NumberFormatException e) {}
        }

        TreeNode root = null;
        for (int num : numberList) {
            root = insert(root, num);
        }

        TreeEntity treeEntity = new TreeEntity();
        treeEntity.setInputNumbers(numberList.stream().map(Object::toString).collect(Collectors.joining(",")));
        treeEntity.setTreeJson(treeToJson(root));
        treeRepository.save(treeEntity);

        return treeToJson(root);
    }

    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        List<TreeEntity> trees = treeRepository.findAll();
        model.addAttribute("trees", trees);
        return "previous-trees";
    }

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }
        return root;
    }

    private String treeToJson(TreeNode root) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(treeNodeToJson(root));
    }

    private ObjectNode treeNodeToJson(TreeNode node) {
        if (node == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNode = mapper.createObjectNode();
        jsonNode.put("value", node.val);
        jsonNode.set("left", treeNodeToJson(node.left));
        jsonNode.set("right", treeNodeToJson(node.right));
        return jsonNode;
    }

    // Bonus: Balance the tree
    private TreeNode balanceTree(List<Integer> sortedList, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(sortedList.get(mid));

        root.left = balanceTree(sortedList, start, mid - 1);
        root.right = balanceTree(sortedList, mid + 1, end);

        return root;
    }

    @PostMapping("/process-balanced-numbers")
    @ResponseBody
    public String processBalancedNumbers(@RequestParam("numbers") String numbers) throws Exception {
        String[] numberStrings = numbers.split(",");
        List<Integer> numberList = new ArrayList<>();
        for (String num : numberStrings) {
            try {
                numberList.add(Integer.parseInt(num.trim()));
            } catch (NumberFormatException e) {}
        }
        Collections.sort(numberList);

        TreeNode root = balanceTree(numberList, 0, numberList.size() - 1);

        TreeEntity treeEntity = new TreeEntity();
        treeEntity.setInputNumbers(numberList.stream().map(Object::toString).collect(Collectors.joining(",")));
        treeEntity.setTreeJson(treeToJson(root));
        treeRepository.save(treeEntity);

        return treeToJson(root);
    }
}