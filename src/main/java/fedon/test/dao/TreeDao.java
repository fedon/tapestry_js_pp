package fedon.test.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import fedon.test.model.TreeNode;

/**
 * @author Dmytro Fedonin
 *
 */
@Repository
public class TreeDao {
    Logger log = LoggerFactory.getLogger(TreeDao.class);

    private String tree;
    private TreeNode root;
    private Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
    private int idCount = 0;

    public String getStaticTree() throws IOException {
        if (tree == null) {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("static_tree.json");
            StringBuilder sb = new StringBuilder();
            char buf[] = new char[1024];
            Reader reader = new BufferedReader(new InputStreamReader(in));
            int result = 0;
            do {
                result = reader.read(buf);
                sb.append(buf, 0, result);
            } while (result == 1024);
            tree = sb.toString();
        }
        return tree;
    }

    public String getDynamicTree() {
        Gson gson = new Gson();
        return gson.toJson(root);
    }

    public TreeNode initTree() {
        root = new TreeNode();
        root.setId(idCount++);
        root.setName("root");
        root.setChildren(new HashSet<TreeNode>());
        root.setData(root.new Data());
        root.getData().setBand("may be project name");
        root.getData().setRelation("may be type of relation");
        map.clear();
        map.put(root.getId(), root);
        return root;
    }

    public TreeNode addNode(Integer parentId, TreeNode newNode) {
        TreeNode parent = map.get(parentId);
        newNode.setId(idCount++);
        newNode.setChildren(new HashSet<TreeNode>());
        parent.getChildren().add(newNode);
        map.put(newNode.getId(), newNode);
        return newNode;
    }

    public Map<Integer, TreeNode> getNodeModel() {
        return map;
    }
}
